package logico;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
		personas.add(aux);
		if(aux instanceof Medico) {
			genMedico++;
		}else if (aux instanceof Paciente) {
			genPaciente++;
		}
	}

	public ArrayList<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void addDiagnostico(Diagnostico aux) {
		diagnosticos.add(aux);
		genDiagnostico++;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void addEnfermedad(Enfermedad aux) {
		enfermedades.add(aux);
		genEnfermedad++;
	}


	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}


	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public void addCita(Cita aux) {
		citas.add(aux);
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
		for (Persona p : personas) {
			if(p instanceof Paciente) {
				Paciente pac = (Paciente) p;
				if(pac.getCedula().equalsIgnoreCase(cedula)) {
					return pac;
				}
			}
		}
		return null;
	}

	public Persona personaById(String id) {
		Persona aux = null;
		boolean val = false;
		int i = 0;

		while(i<personas.size() && !val) {
			if(personas.get(i).getCodigo().equals(id)) {
				aux = personas.get(i);
				val = true;
			}

			i++;
		}
		return aux;
	}

	public boolean cedulaUnica(String cedula) {
		boolean unico = true;
		int i = 0;

		while(i<personas.size() && unico) {
			if(personas.get(i).getCedula().equals(cedula))
				unico = false;

			i++;
		}
		return unico;
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

	public boolean hacerCita (String cedula, String nombre, String apellido, String telefono, Medico med, LocalDate fecha) {
		boolean realizado = false;
		Persona aux = buscarPacienteByCedula(cedula);
		if(aux != null) {
			Cita cita = new Cita("C-"+genCita, aux, med , fecha); 
			addCita(cita);
			med.addHistorial(cita);
			aux.addHistorial(cita);
			realizado = true;
		}else if(aux == null){
			aux = new Persona("", cedula, nombre, apellido, LocalDate.now(), ' ', telefono, "", "", null);
			Cita cita = new Cita("C-"+genCita, aux, med , fecha);
			addCita(cita); 
			med.addHistorial(cita);
			aux.addHistorial(cita);
			realizado = true;
		}
		return realizado;
	}

	/*Funcion: buscarCitaByCode
	 * Parametro: String -> code
	 * Retorna: Cita*/

	public Cita buscarCitaByCode(String code) {
		int index = 0;
		Cita found = null;
		while(found == null && index < citas.size()) {
			if(citas.get(index).getCodigo().equalsIgnoreCase(code)) {
				found = citas.get(index);
			}
			index++;
		}
		return found;
	}

	/*Funcion: buscarCitasByMedico
	 * Parametro: Medico medico
	 * Retorna: Citas*/
	public ArrayList<Cita> buscarCitasByMedico(Medico medico) {
		ArrayList<Cita> todasLasCitas = new ArrayList<>();
		for (Cita cita : citas) {
			if(cita.getMedico().equals(medico)) {
				todasLasCitas.add(cita);
			}
		}
		return todasLasCitas;
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
		for (Enfermedad enf : enfermedades) {
			if(enf.getCodigo().equalsIgnoreCase(code)) {
				enf.setControlada(true);
				return true;
			}
		}
		return false;
	}

	/*Funcion: getEnfermedadesControladas
	 * Retorna: Lista de enfb controladas*/
	public ArrayList<Enfermedad> getEnfermedadesControladas(){
		ArrayList<Enfermedad> lista = new ArrayList<>();
		for (Enfermedad enf : enfermedades) {
			if(enf.isControlada()) {
				lista.add(enf);
			}
		}
		return lista;
	}

	/*Funcion: getEnfermedadesSinControlar
	 * Retorna: Lista de enfb sin controlar*/
	public ArrayList<Enfermedad> getEnfermedadesSinControlar(){
		ArrayList<Enfermedad> lista = new ArrayList<>();
		for (Enfermedad enf : enfermedades) {
			if(!enf.isControlada()) {
				lista.add(enf);
			}
		}
		return lista;
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
		if(c != null && !c.isEstado()) {
			citas.remove(c);
			return true;
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
	    boolean valido = false;

	    for (Persona p : personas) {

	        User user = p.getUser();
	        if (user == null) continue;

	        if (user.getUserName().equals(usuario) && user.getPass().equals(pass)) {
	            if (p instanceof Medico) {
	                if (!((Medico) p).isActivo()) {
	                    return false;
	                }
	            }
	            personaLogueada = p;
	            valido = true;
	        }
	    }

	    return valido;
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
		if(aux != null) {
			if(!(aux.isEstado()&& aux.getFecha() != fecha)){
				if(fecha.isAfter(aux.getFecha())) {
					aux.setFecha(fecha);
					return true;
				}
			}
		}
		return false;
	}

	public boolean crearConsulta(String codigoCita, Double precio, ArrayList<String> sintomas, String tratamiento) {
	    boolean creada = false;
	    Cita aux = buscarCitaByCode(codigoCita);

	    if (aux != null && !aux.isEstado()) {

	        Paciente pac = (Paciente) aux.getPersona();
	        Diagnostico diag = new Diagnostico("D-" + genDiagnostico, aux.getFecha(), sintomas, tratamiento);

	        Consulta nuevo = new Consulta("CN-" + genCita,aux.getPersona(), aux.getMedico(),aux.getFecha(),precio,pac,false,diag);
	        int index = citas.indexOf(aux);
	        if (index != -1) {
	            citas.set(index, nuevo);
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
		for (Enfermedad enf : enfermedades) {
			if(enf.getCodigo().equalsIgnoreCase(code)) {
				return enf;
			}
		}
		return null;
	}


	public boolean modificarEnfermedad(String codigo, String nuevoTratamiento, boolean nuevoControl, ArrayList<String> nuevosSintomas) {
		Enfermedad enf = buscarEnfByCode(codigo);
		if(enf!= null) {
			enf.setTratamiento(nuevoTratamiento);
			enf.setControlada(nuevoControl);
			enf.setSintomas(nuevosSintomas);
			return true;
		}
		return false;
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
		Enfermedad enf = buscarEnfByCode(codeEnf);
		for (Persona pers : personas) {
			if(pers instanceof Paciente) {
				Paciente pac = (Paciente) pers;
				for (Enfermedad enfermedad : pac.getEnfermedades()) {
					if(enfermedad.getCodigo().equalsIgnoreCase(codeEnf)) {
						return false;
					}
				}
			}
		}
		enfermedades.remove(enf);
		return true;
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
