package logico;

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
		for (Medico med : MedicosEspecialidad) {
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
			Cita cita = new Cita("Ci-"+genCita, aux, med , fecha, false);
			med.addHistorial(cita);
			realizado = true;
		}else if(aux != null && med != null) {
			Cita cita = new Cita("Ci-"+genCita, aux, med , fecha, false);
			med.addHistorial(cita);
			aux.addHistorial(cita);
			realizado = true;
		}
		return realizado;
	}
}
