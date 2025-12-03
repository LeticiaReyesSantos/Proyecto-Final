package logico;

import java.time.LocalDate;
import java.util.ArrayList;


public class Medico extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;
	private int maxCitas;
	private ArrayList<Paciente> pacientes;
	private boolean activo;


	public Medico(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String especialidad, int maxCitas, User user) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento, genero, telefono, direccion, email, user);

		this.especialidad = especialidad;
		this.maxCitas = maxCitas;
		pacientes = new ArrayList<>();
		this.activo = true;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean puedeEjercer() {
		return getEdad() >=24;
	}
	
	public ArrayList<Cita> citasPendientesHoy() {
	    ArrayList<Cita> citas = new ArrayList<>();
	    LocalDate hoy = LocalDate.now();

	    for(Cita c : historial) {
	        if(c.getFecha().equals(hoy)
	           && !c.isEstado()) {
	            citas.add(c);
	        }
	    }

	    return citas;
	}


	public boolean isPosible(LocalDate fecha){
		return cantCitasDia(fecha) < maxCitas;
	}

	public int cantCitasDia(LocalDate fecha) {
		int cant = 0;
		for (Cita cita : historial) {
			if(!cita.isEstado() && cita.fecha.equals(fecha)) {
				cant++;
			}
		}
		return cant;
	}
	

}
