package logico;

import java.time.LocalDateTime;

public class Cita {
	
	//Prueba
	protected String codigo;
	protected Persona persona;
	protected Medico medico;
	protected LocalDateTime fecha;
	protected boolean estado;
	
	public Cita(String codigo, Persona persona, Medico medico, LocalDateTime fecha) {
		super();
		this.codigo = codigo;
		this.persona = persona;
		this.medico = medico;
		this.fecha = fecha;
		this.estado = false; //false, para indicar que esta pendiente
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}