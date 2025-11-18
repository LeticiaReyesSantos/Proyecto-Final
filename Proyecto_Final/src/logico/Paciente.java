package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Persona {
	
	private ArrayList<Vacuna> vacunas;
	private String tipoSangre;

	public Paciente(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String tipoSangre) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento,genero, telefono, direccion, email);
		
		this.tipoSangre = tipoSangre;
		vacunas = new ArrayList<>();
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
	
	/*Funcion: getVacunasPaciente
	 * Retorna: list*/
	public ArrayList<Vacuna> getVacunasPaciente(){
		ArrayList<Vacuna> vacunas = new ArrayList<>();
		for (Vacuna vacuna : vacunas) {
			if(vacuna.isAplicada()) {
				vacunas.add(vacuna);
			}
		}
		return vacunas;
	}
}
