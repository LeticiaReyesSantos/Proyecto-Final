package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logico.Clinica;
import logico.Reporte;

public class MostrarReportes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabs;
	private JPanel stat1, stat2, stat3, stat4;
	private Clinica clinica;
	private Reporte reporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarReportes dialog = new MostrarReportes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarReportes() {

		clinica = Clinica.getInstance();
		reporte = new Reporte(clinica);
		setUndecorated(true);
		setBounds(100, 100, 889, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(255, 255, 255));
		fondo.setBounds(0, 0, 889, 500);
		contentPanel.add(fondo);
		fondo.setLayout(null);

		JPanel barPanel = new JPanel();
		barPanel.setBounds(-96, 0, 985, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(45, 51, 107));

		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(946, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
		cerrarPanel.add(label);

		JLabel lblNewLabel = new JLabel("Reportes Estad\u00EDsticos");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(109, 0, 224, 20);
		barPanel.add(lblNewLabel);

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(15, 41, 859, 443);
		fondo.add(tabs);

		stat1 = new JPanel();
		stat2 = new JPanel();
		stat3 = new JPanel();
		stat4 = new JPanel();

		tabs.addTab("Vacunas Más Aplicadas", stat1);
		tabs.addTab("Enfermedades Frecuentes", stat2);
		tabs.addTab("Consultas por Especialidad", stat3);
		tabs.addTab("Estado de Citas", stat4);
		tabs.setFont(new Font("Verdana", Font.PLAIN, 12));

		graphs();

	}

	private void graphs() {
		graficoVacunas();
		graficoEnfermedades();
		graficoConsultas();
		graficoCitas();
	}

	private void graficoVacunas() {
		DefaultPieDataset data = reporte.generarVacunasMasAplicadas();
		JFreeChart chart = ChartFactory.createPieChart("Cantidad", data, true, true, false);
		ChartPanel vacPanel = new ChartPanel(chart);
		stat1.setLayout(new BorderLayout());
	    stat1.add(vacPanel, BorderLayout.CENTER);
	}
	
	private void graficoEnfermedades() {
		DefaultCategoryDataset data = reporte.generarFrecuenciaEnfermedades();
		JFreeChart chart = ChartFactory.createBarChart("Frecuencia", "Enfermedades", "Cantidad", data);
		ChartPanel enfPanel = new ChartPanel(chart);
		stat2.setLayout(new BorderLayout());
	    stat2.add(enfPanel, BorderLayout.CENTER);
	}
	
	private void graficoConsultas() {
	    DefaultCategoryDataset data = reporte.consultasPorEsp();
	    JFreeChart chart = ChartFactory.createBarChart("Consultas por Especialidad", "Especialidades", "Número de Consultas", data);
	    ChartPanel consPanel = new ChartPanel(chart);
	    stat3.setLayout(new BorderLayout());
	    stat3.add(consPanel, BorderLayout.CENTER);
	}
	
	private void graficoCitas() {
		DefaultPieDataset data = reporte.citasByEstado();
		JFreeChart chart = ChartFactory.createPieChart("Estado", data, true, true, false);
		ChartPanel citaPanel = new ChartPanel(chart);
		stat4.setLayout(new BorderLayout());
	    stat4.add(citaPanel, BorderLayout.CENTER);
	}

}
