package logico;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Horario {
	private Map <DayOfWeek, LocalTime[]> horario;

	public Horario() {
		super();
		horario = new TreeMap<>();
	} 
	
	public void addDia(DayOfWeek dia, LocalTime horaIni, LocalTime horaFin) {
		horario.put(dia, new LocalTime[] {horaIni, horaFin});
	}
	
	public void eliminarDia (DayOfWeek dia) {
		horario.remove(dia);
	}
	
	//verifica si en ese dia hay un horario definido, si hay entonces se trabaja
	public boolean trabajaDia(DayOfWeek dia) {
		return horario.containsKey(dia);
	}
	//Retorna la hora de inicio y fin de un dia
	public LocalTime[] horarioByDia(DayOfWeek dia) {
		return horario.get(dia);
	}
	
	//Valida que la fecha este en horario laborable
	public boolean fechaEnHorarioLaboral(LocalDateTime fecha) {
		boolean valido = false; 
		DayOfWeek dia = fecha.getDayOfWeek();
		LocalTime hora = fecha.toLocalTime();
		
		if(trabajaDia(dia)) {
			if(horaEnRango(hora, horarioByDia(dia))) {
				valido = true;
			}
		}
		
		return valido;
	}
	
	private boolean horaEnRango(LocalTime hora, LocalTime[]rango) {
		return((hora.isAfter(rango[0])|| hora.equals(rango[0])) && (hora.isBefore(rango[1])));
	}
	
}
