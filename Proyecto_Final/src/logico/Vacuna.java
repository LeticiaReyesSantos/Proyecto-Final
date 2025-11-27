package logico;

import java.io.Serializable;

public class Vacuna implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private Enfermedad enfermedad;
	private String descripcion;
	private boolean aplicada;
	private boolean controlada;
	
	public Vacuna(String codigo, String nombre, Enfermedad enfermedad, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.descripcion = descripcion;
		this.aplicada = false;
		this.controlada = BajoControl();
	}

	public boolean isControlada() {
		return controlada;
	}

	public void setControlada(boolean controlada) {
		this.controlada = controlada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isAplicada() {
		return aplicada;
	}

	public void setAplicada(boolean aplicada) {
		this.aplicada = aplicada;
	}
	
	private boolean BajoControl() {
		return enfermedad.isControlada();
	}
}
