package logico;

import java.io.Serializable
;
import java.time.LocalDate;
import java.util.ArrayList;

public class Diagnostico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private LocalDate fecha;
	private ArrayList <String> sintomas;
	private String tratamiento;
	private ArrayList<Enfermedad> enfDiagnosticadas;
	
	public Diagnostico(String codigo, LocalDate fecha, ArrayList<String> sintomas, String tratamiento) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.sintomas = sintomas;
		this.tratamiento = tratamiento;
		enfDiagnosticadas = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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

	public ArrayList<Enfermedad> getEnfDiagnosticadas() {
		return enfDiagnosticadas;
	}

	public void setEnfDiagnosticadas(ArrayList<Enfermedad> enfDiagnosticadas) {
		this.enfDiagnosticadas = enfDiagnosticadas;
	}
	
	
	
	

}
