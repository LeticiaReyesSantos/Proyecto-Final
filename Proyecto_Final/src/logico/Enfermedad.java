package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Enfermedad implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private ArrayList<String> sintomas;
	private String tratamiento;
	private String tipo;
	private boolean controlada;
	
	public Enfermedad(String codigo, String nombre, String tratamiento, String tipo,
			boolean controlada) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.sintomas = new ArrayList<>();
		this.tratamiento = tratamiento;
		this.tipo = tipo;
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

	public ArrayList<String> getSintomas() {
		return sintomas;
	}

	public void setSintomas(ArrayList<String> sintomas) {
		this.sintomas = sintomas;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isControlada() {
		return controlada;
	}

	public void setControlada(boolean controlada) {
		this.controlada = controlada;
	}
	

}
