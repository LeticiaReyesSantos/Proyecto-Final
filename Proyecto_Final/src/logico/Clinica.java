package logico;

import java.util.ArrayList;

public class Clinica {
	
	private ArrayList <Medico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList<Diagnostico> diagnosticos;
	private ArrayList <Enfermedad> enfermedades;
	private ArrayList <Vacuna> vacunas;
	
	public static int genMedico = 1;
	public static int genPaciente = 1;
	public static int genDiagnostico = 1;
	public static int genEnfermedad = 1;
	public static int genVacuna = 1;
	
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
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}
	
	public void addPaciente(Paciente aux) {
		pacientes.add(aux);
	}


	public ArrayList<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}
	
	public void addDiagnostico(Diagnostico aux) {
		diagnosticos.add(aux);
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}
	
	public void addEnfermedad(Enfermedad aux) {
		enfermedades.add(aux);
	}


	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}
	
	public void addVacuna(Vacuna aux) {
		vacunas.add(aux);
	}

	public static int getGenMedico() {
		return genMedico;
	}

	public static int getGenPaciente() {
		return genPaciente;
	}

	public static int getGenDiagnostico() {
		return genDiagnostico;
	}

	public static int getGenEnfermedad() {
		return genEnfermedad;
	}

	public static int getGenVacuna() {
		return genVacuna;
	}

	public static Clinica getClinica() {
		return clinica;
	}
	
	

}
