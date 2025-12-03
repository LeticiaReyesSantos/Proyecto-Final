package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Persona {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Vacuna> vacunas;
	private ArrayList<Enfermedad> enfermedades;
	private String tipoSangre;

	public Paciente(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String tipoSangre, User user) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento,genero, telefono, direccion, email, user);
		
		this.tipoSangre = tipoSangre;
		vacunas = new ArrayList<>();
		enfermedades = new ArrayList<>();
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void addVacuna(Vacuna aux) {
		vacunas.add(aux);
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void addEnfermedad (Enfermedad aux) {
		enfermedades.add(aux);
	}

	public boolean esMayorDeEdad() {
		return getEdad() >= 18;
	}
	
	public boolean requiereAcompanante() {
		if(!esMayorDeEdad())
			return true;
		return false;
	}
	
	/*Funcion: marcarVacunaAplicada
	 * Parametro: codigo de vacuna
	 * Retorna: Boolean*/
	public boolean marcarVacunaAplicada(String code) {
		boolean aplicada = false;
		for (Vacuna vac : vacunas) {
			if(vac.getCodigo().equals(code)) {
				vac.setAplicada(true);
				aplicada = true;
			}
		}
		return aplicada;
	}

}
