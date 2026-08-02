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

		genMedico = obtenerSiguienteCodigo("MEDICO");
		genPaciente = obtenerSiguienteCodigo("PACIENTE");
		genDiagnostico = obtenerSiguienteCodigo("DIAGNOSTICO");
		genEnfermedad = obtenerSiguienteCodigo("ENFERMEDAD");
		genVacuna = obtenerSiguienteCodigo("VACUNA");
		genCita = obtenerSiguienteCodigo("CITA");
		genAdmin = obtenerSiguienteCodigoAdmin();
	}

	private int obtenerSiguienteCodigo(String tabla) {
		String sql = "SELECT codigo FROM " + tabla;
		int max = 0;
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String numStr = rs.getString("codigo").replaceAll("[^0-9]", "");
				if (!numStr.isEmpty()) {
					int num = Integer.parseInt(numStr);
					if (num > max) max = num;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max + 1;
	}

	private int obtenerSiguienteCodigoAdmin() {
		String sql = "SELECT per.codigo FROM PERSONA per JOIN USUARIO u ON per.codigo = u.codigo_persona WHERE u.tipo = 'Administrador'";
		int max = 0;
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String numStr = rs.getString("codigo").replaceAll("[^0-9]", "");
				if (!numStr.isEmpty()) {
					int num = Integer.parseInt(numStr);
					if (num > max) max = num;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max + 1;
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
		ArrayList<Persona> lista = new ArrayList<>();
		String sql = "SELECT codigo FROM PERSONA";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			PersonaDAO dao = new PersonaDAO();
			while (rs.next()) {
				lista.add(dao.buscarPersonaCompleta(rs.getString("codigo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
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
	public ArrayList<Medico> medicosByEspecialidad(String especialidad) {
		ArrayList<Medico> lista = new ArrayList<>();
		String sql = "SELECT codigo FROM MEDICO WHERE especialidad ILIKE ? AND activo = true";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, especialidad);
			ResultSet rs = stmt.executeQuery();
			PersonaDAO dao = new PersonaDAO();
			while (rs.next()) {
				lista.add((Medico) dao.buscarPersonaCompleta(rs.getString("codigo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	//Busca los medicos disponibles para la fecha solicitada
	public ArrayList<Medico> disponible(String especialidad, LocalDate fecha) {
		ArrayList<Medico> meddisp = new ArrayList<>();
		ArrayList<Medico> medEsp = medicosByEspecialidad(especialidad);
		for (Medico med : medEsp) {
			if (med.isPosible(fecha)) {
				meddisp.add(med);
			}
		}
		return meddisp;
	}

	public boolean hacerCita(String cedula, String nombre, String apellido, String telefono, Medico med, LocalDate fecha) {
		boolean realizado = false;
		Paciente aux = buscarPacienteByCedula(cedula);

		if (aux == null) {
			String codigoGenerico = "P-" + System.currentTimeMillis();
			Persona nuevaPersona = new Persona(codigoGenerico, cedula, nombre, apellido,
					LocalDate.now(), ' ', telefono, "", "", null);

			new PersonaDAO().insertar(nuevaPersona);

			Cita cita = new Cita("C-" + genCita, nuevaPersona, med, fecha);
			addCita(cita);
			realizado = true;
		} else {
			Cita cita = new Cita("C-" + genCita, aux, med, fecha);
			addCita(cita);
			realizado = true;
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


	public ArrayList<Consulta> historialConsultaByPaciente(Paciente pac) {
		return new ConsultaDAO().listarPorPaciente(pac.getCodigo());
	}

	public ArrayList<Consulta> historialPacienteByMed(Paciente p, Medico med) {
		return new ConsultaDAO().listarPorPacienteYMedico(p.getCodigo(), med.getCodigo());
	}

	public ArrayList<Paciente> pacientesByMedico(Medico med) {
		return new ConsultaDAO().listarPacientesPorMedico(med.getCodigo());
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

	public boolean crearConsulta(String codigoCita, Double precio, ArrayList<String> sintomas,
								 String tratamiento, ArrayList<Enfermedad> enfermedades, Vacuna vacAplicada) {

		Cita aux = buscarCitaByCode(codigoCita);
		if (aux == null || aux.isEstado()) return false;

		Paciente pac = (Paciente) aux.getPersona();
		if (enfermedades == null) enfermedades = new ArrayList<>();

		String codigoDiagnostico = "D-" + genDiagnostico;

		boolean creada = new ConsultaDAO().crearConsulta(
				codigoCita, precio, sintomas, tratamiento, enfermedades, vacAplicada,
				pac.getCodigo(), codigoDiagnostico
		);

		if (creada) {
			genDiagnostico++;
		}

		return creada;
	}



	public boolean modificarPersona(String id, String nuevaDireccion, String nuevoTelefono, String nuevoEmail) {
		String sql = "UPDATE PERSONA SET telefono = ?, direccion = ?, email = ? WHERE codigo = ?";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, nuevoTelefono);
			stmt.setString(2, nuevaDireccion);
			stmt.setString(3, nuevoEmail);
			stmt.setString(4, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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


	public boolean desactivarMedico(String codigoMedico) {
		CitaDAO citaDAO = new CitaDAO();
		ArrayList<Cita> citas = citaDAO.listarPorMedico(codigoMedico);
		for (Cita c : citas) {
			if (!c.isEstado() && !c.getFecha().isBefore(LocalDate.now())) {
				return false;
			}
		}
		return new MedicoDAO().desactivar(codigoMedico);
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


	public ArrayList<Vacuna> getVacunas() {
		return new VacunaDAO().listarTodas();
	}

	public void addVacuna(Vacuna aux) {
		new VacunaDAO().insertar(aux);
		genVacuna++;
	}

	public Vacuna buscarVacByCode(String code) {
		return new VacunaDAO().buscarPorCodigo(code);
	}

	public boolean modificarVacuna(String codigo, String nuevaDescripcion) {
		return new VacunaDAO().actualizarDescripcion(codigo, nuevaDescripcion);
	}

	public boolean eliminarVacuna(String codigoVac) {
		return new VacunaDAO().eliminar(codigoVac);
	}

	public ArrayList<Vacuna> getVacunasControladas() {
		ArrayList<Vacuna> controladas = new ArrayList<>();
		for (Vacuna vacuna : getVacunas()) {
			if (vacuna.isControlada()) {
				controladas.add(vacuna);
			}
		}
		return controladas;
	}



}
