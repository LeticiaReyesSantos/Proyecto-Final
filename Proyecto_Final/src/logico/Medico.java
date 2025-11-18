package logico;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class Medico extends Persona {
	
	private String especialidad;
	private int maxCitas;
	private ArrayList<Paciente> pacientes;
	private Horario horario;
	
	public Medico(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String especialidad, int maxCitas) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento, genero, telefono, direccion, email);
		
		this.especialidad = especialidad;
		this.maxCitas = maxCitas;
		this.horario =  new Horario();
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
	
	public boolean puedeEjercer() {
		return getEdad() >=24;
	}

	public boolean disponibilidadCita(LocalDateTime fecha) {
		boolean disponible = false;
		if(horario.fechaEnHorarioLaboral(fecha) && UnicaCita(fecha) && isPosible(fecha.toLocalDate())) {
			disponible = true;
		}
		return disponible;
	}
	
	public boolean UnicaCita(LocalDateTime fecha) {
		boolean disponible = true;
		int i = 0; 
		while(i< historial.size() && disponible) {
			if(historial.get(i).getFecha().equals(fecha) && rangoDefinidoCita(fecha)) {
				disponible = false;
			}
			i++;
		}
		return disponible;
	}
	
	public boolean rangoDefinidoCita(LocalDateTime fecha) {
		boolean valido = true;
		int intervalo = horario.intervaloMinimoEntreCita(fecha.getDayOfWeek(), maxCitas);
		int i = 0;
		
		while (i<historial.size() && valido) {
			if(fecha.toLocalDate().equals(historial.get(i).getFecha().toLocalDate())) {
				LocalDateTime fI = historial.get(i).getFecha();
				LocalDateTime fF = fI.plusMinutes(intervalo);
				
				if(fecha.isBefore(fF) && fI.isBefore(fecha.plusMinutes(intervalo)))
					valido = false;
			}
			i++;
		}
		return valido;
	}
	
	public boolean isPosible(LocalDate fecha){
		return cantCitasDia(fecha) < maxCitas;
	}
	
	public int cantCitasDia(LocalDate fecha) {
		int cant = 0;
		for (Cita cita : historial) {
			if(!cita.estado && cita.fecha.toLocalDate().equals(fecha)) {
				cant++;
			}
		}
		return cant;
	}
}
