package logico;

import java.net.InterfaceAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Clinica {

	private ArrayList <Medico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList <Diagnostico> diagnosticos;
	private ArrayList <Enfermedad> enfermedades;
	private ArrayList <Vacuna> vacunas;
	private ArrayList <Cita> citas;

	public static int genMedico = 1;
	public static int genPaciente = 1;
	public static int genDiagnostico = 1;
	public static int genEnfermedad = 1;
	public static int genVacuna = 1;
	public static int genCita = 1;


	private static Clinica clinica = null;

	public Clinica() {
		super();
		medicos = new ArrayList<>();
		pacientes = new ArrayList<>();
		diagnosticos = new ArrayList<>();
		enfermedades = new ArrayList<>();
		vacunas = new ArrayList<>();
		citas = new ArrayList<>();
	}

	public static Clinica getInstance() {
		if(clinica == null) {
			clinica = new Clinica();
		}
		return clinica;
	}

	public ArrayList<Medico> getMedicos() {
		return medicos;
	}

	public void addMedico(Medico aux) {
		medicos.add(aux);
		genMedico++;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void addPaciente(Paciente aux) {
		pacientes.add(aux);
		genPaciente++;
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

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}

	public void addVacuna(Vacuna aux) {
		vacunas.add(aux);
		genVacuna++;
	}


	public Paciente buscarPacienteByCedula(String cedula) {
		Paciente aux = null ;
		int i = 0;
		while(i < pacientes.size() && aux != null){
			if(cedula.equalsIgnoreCase(pacientes.get(i).getCedula())) {
				aux = pacientes.get(i);
			}
			i++;
		}
		return aux;
	}

	//Buscar los medicos de la misma especialidad para buscar cual se adapta a la fecha de la persona
	public ArrayList<Medico> medicosByEspecialidad(String especialidad){
		ArrayList<Medico> MedicosEspecialidad;
		MedicosEspecialidad = new ArrayList<>();
		for (Medico med : medicos) {
			if(med.getEspecialidad().equalsIgnoreCase(especialidad)) {
				MedicosEspecialidad.add(med);
			}
		}
		return MedicosEspecialidad;
	}

	public Medico disponible(LocalDateTime fecha) {
		Medico aux = null;

		return aux;
	}

	public boolean hacerCita (String cedula, String nombre, String apellido,String especialidad,LocalDateTime fecha) {
		boolean realizado = false;
		Persona aux = buscarPacienteByCedula(cedula);
		Medico med = disponible(fecha);
		if(aux == null && med != null) {
			aux = new Persona("", cedula, nombre, apellido, null, ' ', "", "", "");
			Cita cita = new Cita("Ci-"+genCita, aux, med , fecha);
			citas.add(cita); 
			med.addHistorial(cita);
			realizado = true;
		}else if(aux != null && med != null) {
			Cita cita = new Cita("Ci-"+genCita, aux, med , fecha); //cambie a true para indicar que esta activa
			citas.add(cita); //modifique para que se agregue a la lista de citas
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

	/*Funcion: buscarCitaByMedico
	 * Parametro: Medico medico
	 * Retorna: Cita*/
	public Cita buscarCitaByMedico(Medico medico) {
		int index = 0;
		Cita found = null;
		while(index < citas.size() && found == null) {
			if(citas.get(index).getMedico().equals(medico)) {
				found = citas.get(index);
			}
			index++;
		}
		return found;
	}

	/*Funcion: buscarCitaByPaciente
	 * Parametro: Paciente paciente
	 * Retorna: Cita*/
	public Cita buscarCitaByPaciente(Paciente paciente) {
		int index = 0;
		Cita found = null;
		while(index < citas.size() && found == null) {
			if(citas.get(index).getPersona().equals(paciente)) {
				found = citas.get(index);
			}
			index++;
		}
		return found;
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
	
	/*Funcion: personaEsPaciente
	 * Parametro: Persona p
	 * Retorna: Boolean*/
	public boolean personaEsPaciente(Persona p) {
		int index = 0;
		boolean esPaciente = false;
		while(index < citas.size() && esPaciente == false) {
			Cita c = citas.get(index);
			if(c instanceof Consulta && c.getPersona().equals(p)) {
				esPaciente = true;
			}
			index++;
		}
		return esPaciente;
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
	
	/*Funcion: marcarVacunaControlada
	 * Parametro: codigo de vacuna
	 * Retorna: Boolean*/
	
	public boolean marcarVacunaControlada(String code) {
		for (Vacuna vac : vacunas) {
			if(vac.getCodigo().equalsIgnoreCase(code)) {
				vac.setControlada(true);
				return true;
			}
			
		}
		return false;
	}
	
	/*Funcion: marcarVacunaAplicada
	 * Parametro: codigo de vacuna
	 * Retorna: Boolean*/
	public boolean marcarVacunaAplicada(String code) {
		for (Vacuna aplicada : vacunas) {
			if(aplicada.getCodigo().equalsIgnoreCase(code)) {
				aplicada.setAplicada(true);
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
			LocalDate date = cita.getFecha().toLocalDate();
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
					Paciente p = cons.getPaciente();
					if(!lista.contains(p)) {
						lista.add(p);
					}
				}
			}
		}
		return lista;
	}
	
}
