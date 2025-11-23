package logico;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Enfermedad extends Diagnostico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String tipo;
	private boolean controlada;
	
	public Enfermedad(String codigo, LocalDateTime fecha, ArrayList<String> sintomas, String tratamiento, String nombre,
			String tipo, boolean controlada) {
		super(codigo, fecha, sintomas, tratamiento);
		
		this.nombre = nombre;
		this.tipo = tipo;
		this.controlada = false; //no esta controlada hasta que se indique lo contrario
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
