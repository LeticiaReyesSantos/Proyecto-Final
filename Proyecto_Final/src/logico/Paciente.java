package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Persona {
	
	private ArrayList<Vacuna> vacunas;
	private String tipoSangre;

	public Paciente(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String tipoSangre, User user) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento,genero, telefono, direccion, email, user);
		
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
}
