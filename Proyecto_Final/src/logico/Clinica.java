package logico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class Clinica {

	private ArrayList <Medico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList <Diagnostico> diagnosticos;
	private ArrayList <Enfermedad> enfermedades;
	private ArrayList <Vacuna> vacunas;
	private ArrayList <Cita> citas;

	public static int genMedico = 1;
	public static int genPaciente = 1;
	public static int genDiagnostico = 1;
	public static int genEnfermedad = 1;
	public static int genVacuna = 1;
	public static int genCita = 1;


	private static Clinica clinica = null;

	public Clinica() {
		super();
		medicos = new ArrayList<>();
		pacientes = new ArrayList<>();
		diagnosticos = new ArrayList<>();
		enfermedades = new ArrayList<>();
		vacunas = new ArrayList<>();
		citas = new ArrayList<>();
	}

	public static Clinica getInstance() {
		if(clinica == null) {
			clinica = new Clinica();
		}
		return clinica;
	}

	public ArrayList<Medico> getMedicos() {
		return medicos;
	}

	public void addMedico(Medico aux) {
		medicos.add(aux);
		genMedico++;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void addPaciente(Paciente aux) {
		pacientes.add(aux);
		genPaciente++;
	}


	public ArrayList<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void addDiagnostico(Diagnostico aux) {
		diagnosticos.add(aux);
		genDiagnostico++;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void addEnfermedad(Enfermedad aux) {
		enfermedades.add(aux);
		genEnfermedad++;
	}


	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}


	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}

	public void addVacuna(Vacuna aux) {
		vacunas.add(aux);
		genVacuna++;
	}


	public Paciente buscarPacienteByCedula(String cedula) {
		Paciente aux = null ;
		int i = 0;
		while(i < pacientes.size() && aux == null){
			if(cedula.equalsIgnoreCase(pacientes.get(i).getCedula())) {
				aux = pacientes.get(i);
			}
			i++;
		}
		return aux;
	}

	//Buscar los medicos de la misma especialidad para buscar cual se adapta a la fecha de la persona
	public ArrayList<Medico> medicosByEspecialidad(String especialidad){
		ArrayList<Medico> MedicosEspecialidad;
		MedicosEspecialidad = new ArrayList<>();
		for (Medico med : medicos) {
			if(med.getEspecialidad().equalsIgnoreCase(especialidad)) {
				MedicosEspecialidad.add(med);
			}
		}
		return MedicosEspecialidad;
	}

	public Medico disponible(LocalDateTime fecha) {
		Medico aux = null;

		return aux;
	}

	public boolean hacerCita (String cedula, String nombre, String apellido,String especialidad,LocalDateTime fecha) {
		boolean realizado = false;
		Persona aux = buscarPacienteByCedula(cedula);
		Medico med = disponible(fecha);
		if(aux == null && med != null) {
			aux = new Persona("", cedula, nombre, apellido, null, ' ', "", "", "");
			Cita cita = new Cita("Ci-"+genCita, aux, med , fecha);
			citas.add(cita); 
			med.addHistorial(cita);
			realizado = true;
		}else if(aux != null && med != null) {
			Cita cita = new Cita("Ci-"+genCita, aux, med , fecha); //cambie a true para indicar que esta activa
			citas.add(cita); //modifique para que se agregue a la lista de citas
			med.addHistorial(cita);
			aux.addHistorial(cita);
			realizado = true;
		}
		return realizado;
	}

	/*Funcion: buscarCitaByCode
	 * Parametro: String -> code
	 * Retorna: Cita*/

	public Cita buscarCitaByCode(String code) {
		int index = 0;
		Cita found = null;
		while(found == null && index < citas.size()) {
			if(citas.get(index).getCodigo().equalsIgnoreCase(code)) {
				found = citas.get(index);
			}
			index++;
		}
		return found;
	}

	/*Funcion: buscarCitasByMedico
	 * Parametro: Medico medico
	 * Retorna: Citas*/
	public ArrayList<Cita> buscarCitasByMedico(Medico medico) {
		ArrayList<Cita> todasLasCitas = new ArrayList<>();
		for (Cita cita : citas) {
			if(cita.getMedico().equals(medico)) {
				todasLasCitas.add(cita);
			}
		}
		return todasLasCitas;
	}

	/*Funcion: buscarCitasByPaciente
	 * Parametro: Paciente paciente
	 * Retorna: todas las citas*/
	public ArrayList<Cita> buscarCitasByPaciente(Paciente paciente) {
		ArrayList<Cita> todasLasCitas = new ArrayList<>();
		for (Cita cita : citas) {
			if(cita.getPersona().equals(paciente)) {
				todasLasCitas.add(cita);
			}
		}
		return todasLasCitas;
	}


	/*Funcion: marcarEnfermedadControlada
	 * Parametro: codigo de enfermedad
	 * Retorna: Boolean*/
	public boolean marcarEnfermedadControlada(String code) {
		for (Enfermedad enf : enfermedades) {
			if(enf.getCodigo().equalsIgnoreCase(code)) {
				enf.setControlada(true);
				return true;
			}
		}
		return false;
	}

	/*Funcion: marcarVacunaControlada
	 * Parametro: codigo de vacuna
	 * Retorna: Boolean*/

	public boolean marcarVacunaControlada(String code) {
		for (Vacuna vac : vacunas) {
			if(vac.getCodigo().equalsIgnoreCase(code)) {
				vac.setControlada(true);
				return true;
			}

		}
		return false;
	}


	/*Funcion: getEnfermedadesControladas
	 * Retorna: Lista de enfb controladas*/
	public ArrayList<Enfermedad> getEnfermedadesControladas(){
		ArrayList<Enfermedad> lista = new ArrayList<>();
		for (Enfermedad enf : enfermedades) {
			if(enf.isControlada()) {
				lista.add(enf);
			}
		}
		return lista;
	}

	/*Funcion: getEnfermedadesSinControlar
	 * Retorna: Lista de enfb sin controlar*/
	public ArrayList<Enfermedad> getEnfermedadesSinControlar(){
		ArrayList<Enfermedad> lista = new ArrayList<>();
		for (Enfermedad enf : enfermedades) {
			if(!enf.isControlada()) {
				lista.add(enf);
			}
		}
		return lista;
	}

	/*Funcion: dispCitaByFecha
	 * Parametros: Fecha a buscar
	 * Retorna: Lista de cita disponibles para la fecha*/
	public ArrayList<Cita> dispCitaByFecha(LocalDate fecha){
		ArrayList<Cita> disponibles = new ArrayList<>();
		for (Cita cita : citas) {
			LocalDate date = cita.getFecha().toLocalDate();
			if(date.equals(fecha) && cita.isEstado()) {
				disponibles.add(cita);
			}
		}
		return disponibles;
	}

	/*Funcion: pacientesByMedico
	 * Parametros: Medico med
	 * Retorna: Pacientes de ese medico*/
	public ArrayList<Paciente> pacientesByMedico(Medico med){
		ArrayList<Paciente> lista = new ArrayList<>();
		for (Cita c : citas) {
			if( c instanceof Consulta) {
				Consulta cons = (Consulta) c;
				if(cons.getMedico().equals(med)) {
					Paciente p = cons.getPaciente();
					if(!lista.contains(p)) {
						lista.add(p);
					}
				}
			}
		}
		return lista;
	}

	/*Funcion: cancelarCita
	 * Parametros:codigo
	 * Retorna: boolean -> true cancelada, false-> no se pudo cancelar*/

	public boolean cancelarCita(String code) {
		Cita c = buscarCitaByCode(code);
		if(c != null && !c.isEstado()) {
			citas.remove(c);
			return true;
		}
		return false;
	}

	/*Funcion: citasPendientes
	 * Retorna: lista*/
	public ArrayList<Cita> citasPendientes(){
		ArrayList<Cita> lista = new ArrayList<>();
		for (Cita cita : citas) {
			if(!(cita.isEstado())) {
				lista.add(cita);
			}
		}
		return lista;
	}

	/*Funcion: historialConsultasByPaciente
	 * Parametros: Paciente
	 * Retorna: lista*/
	public ArrayList<Consulta> historialConsultaByPaciente(Paciente pac){
		ArrayList<Consulta> historial = new ArrayList<>(); //el historial se ira desarrollando a medida que se creen las consultas
		for (Cita cita : pac.getHistorial()) {
			if(cita instanceof Consulta) {
				Consulta cons = (Consulta) cita;
				historial.add(cons);
			}
		}
		return historial;
	}

	//REVISAR
	/*Funcion: historialPacienteByMed
	 * Parametro: Paciente, Medico
	 * Retorna: lista*/
	public ArrayList<Consulta> historialPacienteByMed(Paciente p, Medico med){
		ArrayList<Consulta> lista = new ArrayList<>();
		for (Cita cita : p.getHistorial()) {
			if(cita instanceof Consulta) {
				Consulta cons = (Consulta) cita;
				if(cons.getMedico().equals(med)) {
					lista.add(cons);
				}
			}
		}
		return lista;
	}

	/*Funcion: historialPacienteByMed
	 * Parametro: Paciente, Medico
	 * Retorna: lista*/
	public ArrayList<Consulta> consultasMedicoByMes(Medico med, LocalDateTime fecha){
		ArrayList<Consulta> consultasMensuales = new ArrayList<>();
		Month month = fecha.getMonth();
		int year = fecha.getYear();
		for (Cita cita : med.getHistorial()) {
			if(cita instanceof Consulta) {
				Consulta cons = (Consulta) cita;
				if(cita.getFecha().getMonth() == month && cita.getFecha().getYear() == year) {
					consultasMensuales.add(cons);
				}
			}
		}
		return consultasMensuales;
	}//se le agrega las consultas al historial y el usuario las busca por mes de ese annio
	

	/*Funcion: getVacunasControladas
	 * Retorna: Lista*/
	public ArrayList<Vacuna> getVacunasControladas(){
		ArrayList<Vacuna> controladas = new ArrayList<>();
		for (Vacuna vacuna : vacunas) {
			if(vacuna.isControlada()) {
				controladas.add(vacuna);
			}
		}
		return controladas;
	}
	
	/*Funcion: marcarVacunaAplicada
	 * Parametro: codigo de vacuna
	 * Retorna: Boolean*/
	public boolean marcarVacunaAplicada(String code) {
		for (Vacuna aplicada : vacunas) {
			if(aplicada.getCodigo().equals(code)) {
				aplicada.setAplicada(true);
				return true;
			}
		}
		return false;
	}
	
	
	/*Funcion: crearConsulta boolean o consulta
	 * Parametros: Fecha, Diagnostico, Cita, Precio, Vacunas
	 * Retorna: Lista*/
	public boolean crearConsulta(LocalDateTime fechaConsulta, Double precio, Diagnostico diag, String code) {
		boolean realizado = false;
		Cita c = buscarCitaByCode(code);
		if(c!= null && !(c.isEstado())) {
			Paciente paciente = (Paciente) c.getPersona();
			Consulta cons = new Consulta("CON-"+genCita,paciente, c.getMedico(), fechaConsulta, precio, paciente, true, diag);
			cons.setEstado(true);
			
			paciente.addHistorial(cons);
			realizado = true;
		}
		return realizado;
	}
	
}
