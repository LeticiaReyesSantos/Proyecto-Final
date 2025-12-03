package logico;

import java.util.HashMap;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
public class Reporte {

	private Clinica clinica;
	
	public Reporte(Clinica clinica) {
		super();
		this.clinica = clinica;
	}

	public DefaultPieDataset generarVacunasMasAplicadas() {
		DefaultPieDataset data = new DefaultPieDataset();
		HashMap<String, Integer> vacunas = clinica.vacunasMasAplicadas();
		 int contador = 0;
		    for (HashMap.Entry<String, Integer> entry : vacunas.entrySet()) {
		        if (contador < 10) {
		            String nombreVacuna = entry.getKey();
		            Integer cantidad = entry.getValue();
		            data.setValue(nombreVacuna, cantidad);
		            contador++;
		        }
		    }
		    
		    return data;
	}
	
	public DefaultCategoryDataset generarFrecuenciaEnfermedades() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		HashMap<String, Integer> enfermedades = clinica.enfermedadesMasFrecuentes();
		int contador = 0;
		 for (HashMap.Entry<String, Integer> entry : enfermedades.entrySet()) {
			 if(contador < 5) {
				 data.addValue(entry.getValue(), "Frecuencia", entry.getKey());
	                contador++;
			 }
		 }
		 return data;
	}
 
    public DefaultCategoryDataset consultasPorEsp() {
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        HashMap<String, Integer> datos = clinica.consultasByEspecialidad();
        
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            data.addValue(entry.getValue(), "Consultas", entry.getKey());
        }
        return data;
    }
    
    public DefaultPieDataset citasByEstado() {
        DefaultPieDataset data = new DefaultPieDataset();
        HashMap<String, Integer> estadoCitas = clinica.estadoCitas();
        
        for (HashMap.Entry<String, Integer> entry : estadoCitas.entrySet()) {
            data.setValue(entry.getKey(), entry.getValue());
        }
        
        return data;
    }
}
