package logico;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Consulta extends Cita {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double precio;
	private Paciente paciente;
	private boolean visibilidad;
	private Diagnostico diagonistco;
	private ArrayList<Vacuna> vacunas;

	public Consulta(String codigo, Persona persona, Medico medico, LocalDateTime fecha, Double precio,
			Paciente paciente, boolean visibilidad, Diagnostico diagonistco) {
		super(codigo, persona, medico, fecha);
		this.precio = precio;
		this.paciente = paciente;
		this.visibilidad = visibilidad;
		this.diagonistco = diagonistco;
		vacunas = new ArrayList<>();
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean isVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	public Diagnostico getDiagonistco() {
		return diagonistco;
	}

	public void setDiagonistco(Diagnostico diagonistco) {
		this.diagonistco = diagonistco;
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(Vacuna aux) {
		vacunas.add(aux);
	}

}
