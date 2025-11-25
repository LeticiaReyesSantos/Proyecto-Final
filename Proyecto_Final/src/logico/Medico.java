package logico;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;


public class Medico extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;
	private int maxCitas;
	private ArrayList<Paciente> pacientes;
	private Horario horario;


	public Medico(String codigo, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento,
			char genero, String telefono, String direccion, String email, String especialidad, int maxCitas, User user) {
		super(codigo, cedula, nombres, apellidos, fechaNacimiento, genero, telefono, direccion, email, user);

		this.especialidad = especialidad;
		this.maxCitas = maxCitas;
		this.horario =  new Horario();
		pacientes = new ArrayList<>();
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getMaxCitas() {
		return maxCitas;
	}

	public void setMaxCitas(int maxCitas) {
		this.maxCitas = maxCitas;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void addPaciente(Paciente aux) {
		pacientes.add(aux);
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public boolean puedeEjercer() {
		return getEdad() >=24;
	}

	public boolean disponibilidadCita(LocalDateTime fecha) {
		boolean disponible = false;
		if(horario.fechaEnHorarioLaboral(fecha) && UnicaCita(fecha) && isPosible(fecha.toLocalDate())) {
			disponible = true;
		}
		return disponible;
	}

	public boolean UnicaCita(LocalDateTime fecha) {
		boolean disponible = true;
		int i = 0; 
		while(i< historial.size() && disponible) {
			if(historial.get(i).getFecha().equals(fecha) && rangoDefinidoCita(fecha)) {
				disponible = false;
			}
			i++;
		}
		return disponible;
	}

	public boolean rangoDefinidoCita(LocalDateTime fecha) {
		boolean valido = true;
		int intervalo = intervaloMinimoEntreCita(fecha.getDayOfWeek());
		int i = 0;

		while (i<historial.size() && valido) {
			if(fecha.toLocalDate().equals(historial.get(i).getFecha().toLocalDate())) {
				LocalDateTime fI = historial.get(i).getFecha();
				LocalDateTime fF = fI.plusMinutes(intervalo);

				if(fecha.isBefore(fF) && fI.isBefore(fecha.plusMinutes(intervalo)))
					valido = false;
			}
			i++;
		}
		return valido;
	}



	public boolean isPosible(LocalDate fecha){
		return cantCitasDia(fecha) < maxCitas;
	}

	public int cantCitasDia(LocalDate fecha) {
		int cant = 0;
		for (Cita cita : historial) {
			if(!cita.estado && cita.fecha.toLocalDate().equals(fecha)) {
				cant++;
			}
		}
		return cant;
	}

	public ArrayList<DayOfWeek> getDiasDisponibles() {
		ArrayList<DayOfWeek> dias = new ArrayList<>();

		for(DayOfWeek dia: horario.getHorario().keySet()) {

			int cont = 0;

			for (Cita c : historial) 
				if(c.getFecha().getDayOfWeek().equals(dia))
					cont++;


			if(cont < maxCitas)
				dias.add(dia);
		}

		return dias;
	}

	public ArrayList<LocalTime> getHorasDisponibles(DayOfWeek dia) {
		ArrayList<LocalTime> horas = new ArrayList<>();

		LocalTime horaInicial = horario.horarioByDia(dia)[0];
		LocalTime horaFinal = horario.horarioByDia(dia)[1];

		if(horaInicial != null && horaFinal != null) {

			while(horaInicial.isBefore(horaFinal)) {

				if(!horaOcupada(dia, horaInicial)) {
					horas.add(horaInicial);
				}

				horaInicial= horaInicial.plusMinutes(intervaloMinimoEntreCita(dia));
			}

		}
		return horas;
	}

	public boolean horaOcupada(DayOfWeek dia, LocalTime hora) {
		boolean ocupado = false;
		int i = 0;

		while(i<historial.size() && !ocupado) {
			Cita c = historial.get(i);
			if(c.getFecha().getDayOfWeek().equals(dia) && c.getFecha().toLocalTime().equals(hora)) {
				ocupado = true;
			}

			i++;
		}

		return ocupado;
	}

	public int intervaloMinimoEntreCita(DayOfWeek dia) {
		return (int)horario.duracionDeHorario(dia)/maxCitas;
	}
}
