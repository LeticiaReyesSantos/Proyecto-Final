package logico;

public class Vacuna {
	private String codigo;
	private String nombre;
	private Enfermedad enfermedad;
	private String descripcion;
	private boolean aplicada;
	
	public Vacuna(String codigo, String nombre, Enfermedad enfermedad, String descripcion, boolean aplicada) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.descripcion = descripcion;
		this.aplicada = aplicada;
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

}
