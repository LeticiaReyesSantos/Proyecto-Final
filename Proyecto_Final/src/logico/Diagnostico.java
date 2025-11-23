package logico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Diagnostico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String codigo;
	protected LocalDateTime fecha;
	protected ArrayList <String> sintomas;
	protected String tratamiento;
	
	public Diagnostico(String codigo, LocalDateTime fecha, ArrayList<String> sintomas, String tratamiento) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.sintomas = sintomas;
		this.tratamiento = tratamiento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
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
	
	
	
	

}
