package logico;

import dao.*;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Clinica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Persona> personas;
	private ArrayList <Diagnostico> diagnosticos;
	private ArrayList <Enfermedad> enfermedades;
	private ArrayList <Vacuna> vacunas;
	private ArrayList <Cita> citas;

	public int genMedico = 1;
	public int genPaciente = 1;
	public int genDiagnostico = 1;
	public int genEnfermedad = 1;
	public int genVacuna = 1;
	public int genCita = 1;
	public int genAdmin = 1;


	private static Clinica clinica = null;
	private static Persona personaLogueada;

	public Clinica() {
		super();
		personas = new ArrayList<>();
		diagnosticos = new ArrayList<>();
		enfermedades = new ArrayList<>();
		vacunas = new ArrayList<>();
		citas = new ArrayList<>();
	}

	public static void setClinica(Clinica aux) {
		Clinica.clinica = aux;
	}

	public static Clinica getInstance() {
		if(clinica == null) {
			clinica = new Clinica();
		}
		return clinica;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void addPersona(Persona aux) {
		PersonaDAO personaDAO = new PersonaDAO();
		personaDAO.insertar(aux);

		if (aux.getUser() != null) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.insertar(aux.getUser().getUserName(), aux.getUser().getPass(),
					aux.getUser().getTipo(), aux.getCodigo());
		}

		if (aux instanceof Medico) {
			new MedicoDAO().insertar((Medico) aux);
			genMedico++;
		} else if (aux instanceof Paciente) {
			new PacienteDAO().insertar((Paciente) aux);
			genPaciente++;
		}

		personas.add(aux);
	}

	public ArrayList<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void addDiagnostico(Diagnostico aux) {
		diagnosticos.add(aux);
		genDiagnostico++;
	}

	public void addEnfermedad(Enfermedad aux) {
		new EnfermedadDAO().insertar(aux);
		genEnfermedad++;
	}


	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}


	public ArrayList<Cita> getCitas() {
		return new CitaDAO().listarTodas();
	}

	public void addCita(Cita aux) {
		new CitaDAO().insertar(aux);
		genCita++;
	}

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}

	public void addVacuna(Vacuna aux) {
		vacunas.add(aux);
		genVacuna++;
	}

	public void addAdmin() {
		genAdmin++;
	}


	public Paciente buscarPacienteByCedula(String cedula) {
		String sql = "SELECT per.codigo, per.cedula, per.nombres, per.apellidos, per.fecha_nacimiento, " +
				"per.genero, per.telefono, per.direccion, per.email, pac.tipo_sangre " +
				"FROM PACIENTE pac JOIN PERSONA per ON pac.codigo = per.codigo " +
				"WHERE per.cedula ILIKE ?";

		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, cedula);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Paciente(
						rs.getString("codigo"),
						rs.getString("cedula"),
						rs.getString("nombres"),
						rs.getString("apellidos"),
						rs.getDate("fecha_nacimiento").toLocalDate(),
						rs.getString("genero").charAt(0),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getString("email"),
						rs.getString("tipo_sangre"),
						null
				);
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Persona personaById(String id) {
		return new PersonaDAO().buscarPorCodigo(id);
	}

	public boolean cedulaUnica(String cedula) {
		String sql = "SELECT COUNT(*) FROM PERSONA WHERE cedula = ?";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cedula);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1) == 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//Buscar los medicos de la misma especialidad para buscar cual se adapta a la fecha de la persona
	public ArrayList<Medico> medicosByEspecialidad(String especialidad){
		ArrayList<Medico> MedicosEspecialidad = new ArrayList<>();
		for (Persona p: personas) {
			if(p instanceof Medico) {
				Medico med = (Medico) p;
				if(med.getEspecialidad().equalsIgnoreCase(especialidad)) {
					MedicosEspecialidad.add(med);
				}
			}
		}
		return MedicosEspecialidad;
	}

	//Busca los medicos disponibles para la fecha solicitada
	public ArrayList<Medico> disponible(String especialidad ,LocalDate fecha) {
		ArrayList<Medico> meddisp = new ArrayList<>();
		ArrayList<Medico> MedEsp = medicosByEspecialidad(especialidad);
		for (Medico med : MedEsp) {
			if(med.isPosible(fecha)) {
				meddisp.add(med);
			}
		}
		return meddisp;
	}

	public boolean hacerCita(String cedula, String nombre, String apellido, String telefono, Medico med, LocalDate fecha) {
		boolean realizado = false;
		Paciente aux = buscarPacienteByCedula(cedula);

		if (aux != null) {
			Cita cita = new Cita("C-" + genCita, aux, med, fecha);
			addCita(cita);
			realizado = true;
		} else {
			// Nota: en el original se creaba una Persona genérica si no existía.
			// Con BD, para simplificar, solo permitimos agendar si el paciente ya existe.
			realizado = false;
		}
		return realizado;
	}

	/*Funcion: buscarCitaByCode
	 * Parametro: String -> code
	 * Retorna: Cita*/

	public Cita buscarCitaByCode(String code) {
		return new CitaDAO().buscarPorCodigo(code);
	}

	/*Funcion: buscarCitasByMedico
	 * Parametro: Medico medico
	 * Retorna: Citas*/
	public ArrayList<Cita> buscarCitasByMedico(Medico medico) {
		return new CitaDAO().listarPorMedico(medico.getCodigo());
	}

	/*Funcion: buscarCitasByPaciente
	 * Parametro: Paciente paciente
	 * Retorna: todas las citas*/
	public ArrayList<Cita> buscarCitasByPaciente(Paciente paciente) {
		ArrayList<Cita> todasLasCitas = new ArrayList<>();
		for (Cita cita : citas) {
			if(cita.getPersona().equals(paciente)) {
				todasLasCitas.add(cita);
			}
		}
		return todasLasCitas;
	}


	/*Funcion: getCitasMedico
	 * Parametro: Medico medico
	 * Retorna: ArrayList de todas las citas de ese medico*/
	public ArrayList<Cita> getCitasMedico(Medico med){
		int index=0;
		ArrayList<Cita> todasLasCitas = new ArrayList<>();
		while(index < citas.size()) {
			if(citas.get(index).getMedico().equals(med)) {
				todasLasCitas.add(citas.get(index));
			}
			index++;
		}
		return todasLasCitas;
	}

	/*Funcion: marcarEnfermedadControlada
	 * Parametro: codigo de enfermedad
	 * Retorna: Boolean*/
	public boolean marcarEnfermedadControlada(String code) {
		return new EnfermedadDAO().marcarControlada(code);
	}


	/*Funcion: getEnfermedadesControladas
	 * Retorna: Lista de enfb controladas*/
	public ArrayList<Enfermedad> getEnfermedadesControladas() {
		return new EnfermedadDAO().listarControladas(true);
	}


	public ArrayList<Enfermedad> getEnfermedades() {
		return new EnfermedadDAO().listarTodas();
	}


	/*Funcion: getEnfermedadesSinControlar
	 * Retorna: Lista de enfb sin controlar*/
	public ArrayList<Enfermedad> getEnfermedadesSinControlar() {
		return new EnfermedadDAO().listarControladas(false);
	}

	/*Funcion: dispCitaByFecha
	 * Parametros: Fecha a buscar
	 * Retorna: Lista de cita disponibles para la fecha*/
	public ArrayList<Cita> dispCitaByFecha(LocalDate fecha){
		ArrayList<Cita> disponibles = new ArrayList<>();
		for (Cita cita : citas) {
			LocalDate date = cita.getFecha();
			if(date.equals(fecha) && cita.isEstado()) {
				disponibles.add(cita);
			}
		}
		return disponibles;
	}

	/*Funcion: pacientesByMedico
	 * Parametros: Medico med
	 * Retorna: Pacientes de ese medico*/
	public ArrayList<Paciente> pacientesByMedico(Medico med){
		ArrayList<Paciente> lista = new ArrayList<>();
		for (Cita c : citas) {
			if( c instanceof Consulta) {
				Consulta cons = (Consulta) c;
				if(cons.getMedico().equals(med)) {
					Paciente p = (Paciente) cons.getPersona();
					if(!lista.contains(p)) {
						lista.add(p);
					}
				}
			}
		}
		return lista;
	}

	/*Funcion: cancelarCita
	 * Parametros:codigo
	 * Retorna: boolean -> true cancelada, false-> no se pudo cancelar*/

	public boolean cancelarCita(String code) {
		Cita c = buscarCitaByCode(code);
		if (c != null && !c.isEstado()) {
			return new CitaDAO().eliminar(code);
		}
		return false;
	}

	/*Funcion: citasPendientes
	 * Retorna: lista*/
	public ArrayList<Cita> citasPendientes(){
		ArrayList<Cita> lista = new ArrayList<>();
		for (Cita cita : citas) {
			if(!(cita.isEstado())) {
				lista.add(cita);
			}
		}
		return lista;
	}

	/*Funcion: historialConsultasByPaciente
	 * Parametros: Paciente
	 * Retorna: lista*/
	public ArrayList<Consulta> historialConsultaByPaciente(Paciente pac){
		ArrayList<Consulta> historial = new ArrayList<>(); //el historial se ira desarrollando a medida que se creen las consultas
		for (Cita cita : pac.getHistorial()) {
			if(cita instanceof Consulta) {
				Consulta cons = (Consulta) cita;
				historial.add(cons);
			}
		}
		return historial;
	}

	//REVISAR
	/*Funcion: historialPacienteByMed
	 * Parametro: Paciente, Medico
	 * Retorna: lista*/
	public ArrayList<Consulta> historialPacienteByMed(Paciente p, Medico med){
		ArrayList<Consulta> lista = new ArrayList<>();
		for (Cita cita : p.getHistorial()) {
			if(cita instanceof Consulta) {
				Consulta cons = (Consulta) cita;
				if(cons.getMedico().equals(med)) {
					lista.add(cons);
				}
			}
		}
		return lista;
	}

	/*Funcion: getVacunasControladas
	 * Retorna: Lista*/
	public ArrayList<Vacuna> getVacunasControladas(){
		ArrayList<Vacuna> controladas = new ArrayList<>();
		for (Vacuna vacuna : vacunas) {
			if(vacuna.isControlada()) {
				controladas.add(vacuna);
			}
		}
		return controladas;
	}

	public static Persona getLoginUser() {
		return personaLogueada;
	}

	public static void setLoginUser(Persona personaLogueada) {
		Clinica.personaLogueada = personaLogueada;
	}

	public boolean confirmarLogin(String usuario, String pass) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String codigoPersona = usuarioDAO.autenticar(usuario, pass);

		if (codigoPersona == null) {
			return false;
		}

		MedicoDAO medicoDAO = new MedicoDAO();
		if (medicoDAO.esMedico(codigoPersona) && !medicoDAO.isActivo(codigoPersona)) {
			return false;
		}

		PersonaDAO personaDAO = new PersonaDAO();
		Persona p = personaDAO.buscarPorCodigo(codigoPersona);

		if (p == null) return false;

		personaLogueada = p;
		return true;
	}




	public void save() {
		ObjectOutputStream objeto;
		FileOutputStream file;
		try {
			file = new FileOutputStream("clinica.dat");
			objeto = new ObjectOutputStream(file);
			objeto.writeObject(Clinica.getInstance());;
			objeto.close();
			file.close();
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		}catch (IOException e1) {
			// TODO: handle exception
		}
	}

	public static boolean load() {
		ObjectInputStream objeto;
		FileInputStream file;
		boolean val= false;

		try {
			file = new FileInputStream("clinica.dat");
			objeto = new ObjectInputStream(file);
			Clinica aux = (Clinica)objeto.readObject();
			Clinica.setClinica(aux);
			
			
			objeto.close();
			file.close();
			val = true;
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		}catch (IOException e) {

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return val;
	}

	public boolean reagendarCita(LocalDate fecha, String codigoCita) {
		Cita aux = buscarCitaByCode(codigoCita);
		if (aux != null && fecha.isAfter(aux.getFecha())) {
			return new CitaDAO().actualizarFecha(codigoCita, fecha);
		}
		return false;
	}

	public boolean crearConsulta(String codigoCita, Double precio, ArrayList<String> sintomas, String tratamiento, ArrayList<Enfermedad> enfermedades, Vacuna vacAplicada) {
	    boolean creada = false;
	    Cita aux = buscarCitaByCode(codigoCita);

	    if (aux != null && !aux.isEstado()) {
	    	Paciente pac = (Paciente) aux.getPersona();
	    	if(enfermedades == null) {
	        	enfermedades = new ArrayList<>();
	        }

	        Diagnostico diag = new Diagnostico("D-" + genDiagnostico, aux.getFecha(), sintomas, tratamiento);
	        diag.setEnfDiagnosticadas(enfermedades);
	        
	        Consulta nuevo = new Consulta("CN-" + genCita,aux.getPersona(), aux.getMedico(),aux.getFecha(),precio,pac,false,diag);
	        int index = citas.indexOf(aux);
	        if (index != -1) {
	            citas.set(index, nuevo);
	        }
	        
	        ArrayList<Enfermedad> enfPaciente = pac.getEnfermedades();
	        for (Enfermedad agregada : enfermedades) {
				boolean existe = false;
				 int i = 0;
		            while (i < enfPaciente.size() && !existe) {
		                if (enfPaciente.get(i).getCodigo().equals(agregada.getCodigo())) {
		                    existe = true;
		                }
		                i++;
		            }
		            if(!existe) {
		            	enfPaciente.add(agregada);
		            }
			}
	        
	        if (vacAplicada != null) {
	            ArrayList<Vacuna> vacPaciente = pac.getVacunas();
	            boolean tiene = false;
	            int j = 0;
	            while (j < vacPaciente.size() && !tiene) {
	                if (vacPaciente.get(j).getCodigo().equals(vacAplicada.getCodigo())) {
	                    tiene = true;
	                }
	                j++;
	            }
	            if (!tiene) {
	                vacPaciente.add(vacAplicada);
	            }
	        }
	        
	        int indexCita = citas.indexOf(aux);
	        if (indexCita != -1) {
	            citas.set(indexCita, nuevo);
	        }
	        
	        int indexMed = aux.getMedico().getHistorial().indexOf(aux);
	        if (indexMed != -1) {
	            aux.getMedico().getHistorial().set(indexMed, nuevo);
	        }
	        int indexPac = pac.getHistorial().indexOf(aux);
	        if (indexPac != -1) {
	            pac.getHistorial().set(indexPac, nuevo);
	        }
	        addDiagnostico(diag);
	        nuevo.getMedico().addPaciente(pac);
	        nuevo.setEstado(true);

	        creada = true;
	    }

	    return creada;
	}

	public boolean modificarPersona(String id, String nuevaDireccion, String nuevoTelefono, String nuevoEmail) {
		Persona pers = personaById(id);
		if(pers != null) {
			pers.setTelefono(nuevoTelefono);
			pers.setDireccion(nuevaDireccion);
			pers.setEmail(nuevoEmail);
			return true;
		}
		return false;
	}

	public boolean visibilidadConsulta(Consulta cons) {
		boolean esVisible = false;
		for (Enfermedad enf : cons.getDiagonistco().getEnfDiagnosticadas()) {
			if(enf.isControlada()) {
				cons.setVisibilidad(true);
				esVisible = true;
			}
		}
		return esVisible;
	}

	public ArrayList<Consulta> consultasVisibles(){
		ArrayList<Consulta> visibles = new ArrayList<>();
		for (Cita c : citas) {
			if(c instanceof Consulta) {
				Consulta cons = (Consulta) c;
				if(cons.isVisibilidad()) {
					visibles.add(cons);
				}
			}
		}
		return visibles;
	}

	public boolean medicoPuedeVerConsulta(Medico med, Consulta cons) {
		boolean puede = false;
		if(cons.isVisibilidad() || cons.getMedico().getEspecialidad().equalsIgnoreCase(med.getEspecialidad())) {
			puede = true;
		}
		return puede;
	}

	public Enfermedad buscarEnfByCode(String code) {
		return new EnfermedadDAO().buscarPorCodigo(code);
	}


	public boolean modificarEnfermedad(String codigo, String nuevoTratamiento, boolean nuevoControl, ArrayList<String> nuevosSintomas) {
		return new EnfermedadDAO().actualizar(codigo, nuevoTratamiento, nuevoControl, nuevosSintomas);
	}

	public Vacuna buscarVacByCode(String code) {
		for (Vacuna vac : vacunas) {
			if(vac.getCodigo().equalsIgnoreCase(code)) {
				return vac;
			}
		}
		return null;
	}

	public boolean modificarVacuna(String codigo, String nuevaDescripcion) {
		Vacuna vac = buscarVacByCode(codigo);
		if(vac!= null) {
			vac.setDescripcion(nuevaDescripcion);
			return true;
		}
		return false;
	}

	public boolean desactivarMedico(String codigoMedico) {
		Persona pers = personaById(codigoMedico);
		if(pers instanceof Medico) {
			Medico med = (Medico) pers;
			for (Cita cita : med.historial) {
				if(!cita.isEstado() && !cita.getFecha().isBefore(LocalDate.now())) {
					return false;
				}
			}
			med.setActivo(false);
			return true;
		}
		return false;
	}

	public boolean eliminarVacuna(String codigoVac) {
		Vacuna vac = buscarVacByCode(codigoVac);
		for (Persona pers : personas) {
			if(pers instanceof Paciente) {
				Paciente pac = (Paciente) pers;
				for (Vacuna vacunasPac : pac.getVacunas()) {
					if(vacunasPac.getCodigo().equalsIgnoreCase(codigoVac)) {
						return false;
					}
				}
			}
		}
		vacunas.remove(vac);
		return true;
	}

	public boolean eliminarEnfermedad(String codeEnf) {
		return new EnfermedadDAO().eliminar(codeEnf);
	}




	//IMPLEMENTACION DE HASHMAPS PARA REPORTES
	public HashMap<String, Integer> vacunasMasAplicadas(){
		HashMap<String, Integer> vacunasMap = new HashMap<>();
		for (Persona pers : personas) {
			if(pers instanceof Paciente) {
				Paciente pac = (Paciente) pers;
				for (Vacuna vac : pac.getVacunas()) {
					String nombreVac = vac.getNombre();
					vacunasMap.put(nombreVac, vacunasMap.getOrDefault(nombreVac, 0)+1);
				}
			}
		}
		return ordenarHashMapPorValor(vacunasMap);
	}

	public HashMap<String, Integer> enfermedadesMasFrecuentes(){
		HashMap<String, Integer> enfermedadesMap = new HashMap<>();
		for (Persona pers : personas) {
			if(pers instanceof Paciente) {
				Paciente pac = (Paciente) pers;
				for (Enfermedad enf : pac.getEnfermedades()) {
					String nombreEnf = enf.getNombre();
					enfermedadesMap.put(nombreEnf, enfermedadesMap.getOrDefault(nombreEnf, 0)+1);
				}
			}
		}
		return ordenarHashMapPorValor(enfermedadesMap);
	}

	public HashMap<String, Integer> consultasByEspecialidad(){
		HashMap<String, Integer> consultasMap = new HashMap<>();
		for (Cita cita : citas) {
			if(cita instanceof Consulta) {
				String especialidad = cita.getMedico().getEspecialidad();
				consultasMap.put(especialidad, consultasMap.getOrDefault(especialidad, 0)+1);

			}
		}
		return ordenarHashMapPorValor(consultasMap);
	}

	public HashMap<String, Integer> estadoCitas() {
		HashMap<String, Integer> citasMap = new HashMap<>();
		int pendientes = 0;
		int completadas = 0;

		for (Cita cita : citas) {
			if (cita.isEstado()) {
				completadas++;
			} else {
				pendientes++;
			}
		}
		citasMap.put("Citas Completadas", completadas);
		citasMap.put("Citas Pendientes", pendientes);
		return citasMap;
	}

	public HashMap<String, Integer> medicosMasConsultas(){
		HashMap<String, Integer> masConsultas = new HashMap<>();
		for (Cita cita : citas) {
			if(cita instanceof Consulta) {
				Medico med = cita.getMedico();
				String nombreMedico = med.getNombres() + " " + med.getApellidos();
				masConsultas.put(nombreMedico, masConsultas.getOrDefault(nombreMedico, 0)+1);
			}
		}
		return ordenarHashMapPorValor(masConsultas);

	}

	private HashMap<String, Integer> ordenarHashMapPorValor(HashMap<String, Integer> map) {
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1,
						LinkedHashMap::new
						));
	}

}
