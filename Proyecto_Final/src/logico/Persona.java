package logico;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Persona {
	
	protected String codigo;
	protected String cedula;
	protected String nombres;
	protected String apellidos;
	protected LocalDate fechaNacimiento;
	protected int edad;
	protected char genero;
	protected String telefono;
	protected String direccion;
	protected String email;
	protected ArrayList<Cita> historial;
	
	public Persona(String codigo,String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,char genero,
			String telefono, String direccion, String email) {
		super();
		this.codigo = codigo;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = calcEdad();
		this.genero = genero;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		historial = new ArrayList<>();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.cedula = codigo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Cita> getHistorial() {
		return historial;
	}
	
	public void addHistorial(Cita aux) {
		historial.add(aux);
	}
	
	
	public int calcEdad() {
		Period edad = Period.between(fechaNacimiento, LocalDate.now());
		return edad.getYears();
	}
}