package logico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Medico extends Persona {
	
	private String especialidad;
	private int maxCitas;
	private ArrayList<Paciente> pacientes;
	private Horario horario;
	
	public Medico(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String especialidad, int maxCitas, Horario horario) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento, genero, telefono, direccion, email);
		
		this.especialidad = especialidad;
		this.maxCitas = maxCitas;
		this.horario = horario;
		pacientes = new ArrayList<>();
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getMaxCitas() {
		return maxCitas;
	}

	public void setMaxCitas(int maxCitas) {
		this.maxCitas = maxCitas;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void addPaciente(Paciente aux) {
		 pacientes.add(aux);
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	public boolean disponibilidadCita(LocalDateTime fecha) {
		//Desarrollar funcion
		return false;
	}
	
}
