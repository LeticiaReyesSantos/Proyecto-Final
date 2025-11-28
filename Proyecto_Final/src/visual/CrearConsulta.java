package visual;

import static logico.Clinica.getLoginUser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import logico.Clinica;
import logico.Persona;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.JTextArea;
import java.awt.ScrollPane;

public class CrearConsulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<String> sintomas = new ArrayList<>();

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel barPanel;
	private JTextField codigoField;
	private JTextField precioField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearConsulta dialog = new CrearConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearConsulta() {
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
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(946, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
		cerrarPanel.add(label);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 26, 244, 474);
		fondo.add(panel);

		JLabel lblDiagnstico = new JLabel("Diagn\u00F3stico\r\n");
		lblDiagnstico.setForeground(new Color(169, 181, 223));
		lblDiagnstico.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDiagnstico.setBounds(60, 2, 129, 23);
		panel.add(lblDiagnstico);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(45, 51, 107));
		separator.setBackground(new Color(45, 51, 107));
		separator.setBounds(12, 41, 217, 12);
		panel.add(separator);

		JPanel enfermedad = new JPanel();
		enfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarEnfermedades listarEnfermedades = new ListarEnfermedades(1);
				listarEnfermedades.setModal(true); 
				listarEnfermedades.setLocationRelativeTo(null); 
				listarEnfermedades.setVisible(true);

			}
		});
		enfermedad.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		enfermedad.setBackground(new Color(120, 134, 199));
		enfermedad.setBounds(12, 149, 217, 35);
		panel.add(enfermedad);

		JLabel lblDiagnosticarEnfermedad = new JLabel("Diagnosticar Enfermedad");
		lblDiagnosticarEnfermedad.setForeground(new Color(255, 255, 255));
		lblDiagnosticarEnfermedad.setFont(new Font("Verdana", Font.PLAIN, 14));
		enfermedad.add(lblDiagnosticarEnfermedad);

		JPanel vacunas = new JPanel();
		vacunas.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		vacunas.setBackground(new Color(120, 134, 199));
		vacunas.setBounds(12, 210, 217, 35);
		panel.add(vacunas);

		JLabel lblAplicarVacuna = new JLabel("Aplicar Vacuna\r\n");
		lblAplicarVacuna.setForeground(new Color(255, 255, 255));
		lblAplicarVacuna.setFont(new Font("Verdana", Font.PLAIN, 14));
		vacunas.add(lblAplicarVacuna);

		JLabel label_4 = new JLabel("Sintomas:");
		label_4.setBounds(12, 69, 79, 16);
		panel.add(label_4);
		label_4.setForeground(new Color(45, 51, 107));
		label_4.setFont(new Font("Verdana", Font.BOLD, 14));

		JPanel sintomasPanel = new JPanel();
		sintomasPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SintomasSelection sintomasW = new SintomasSelection(sintomas);
				sintomasW.setModal(true);
				sintomasW.setVisible(true);
				sintomas = sintomasW.objectsSelected();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sintomasPanel.setBackground(new Color (120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sintomasPanel.setBackground(new Color(169, 181, 223));
			}
		});
		sintomasPanel.setBounds(12, 93, 217, 28);
		panel.add(sintomasPanel);
		sintomasPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sintomasPanel.setBackground(new Color(120, 134, 199));

		JLabel label_7 = new JLabel("Seleccionar");
		label_7.setForeground(new Color(255, 255, 255));
		label_7.setFont(new Font("Verdana", Font.PLAIN, 14));
		sintomasPanel.add(label_7);

		JLabel lblRealizarConsulta = new JLabel("REALIZAR CONSULTA");
		lblRealizarConsulta.setForeground(new Color(120, 134, 199));
		lblRealizarConsulta.setFont(new Font("Verdana", Font.BOLD, 28));
		lblRealizarConsulta.setBounds(378, 53, 367, 35);
		fondo.add(lblRealizarConsulta);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(295, 136, 541, 238);
		fondo.add(panel_1);

		JTextArea tratamiento = new JTextArea();
		tratamiento.setBounds(35, 114, 473, 84);
		panel_1.add(tratamiento);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(45, 51, 107));
		separator_1.setBackground(new Color(45, 51, 107));
		separator_1.setBounds(35, 197, 473, 2);
		panel_1.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(28, 64, 190, 2);
		panel_1.add(separator_2);

		JLabel label_6 = new JLabel("");
		label_6.setBounds(635, 46, 56, 16);
		panel_1.add(label_6);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(318, 64, 190, 2);
		panel_1.add(separator_3);

		JLabel lblCdigo = new JLabel("C\u00F3digo ");
		lblCdigo.setForeground(new Color(0, 0, 51));
		lblCdigo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCdigo.setBounds(28, 6, 79, 32);
		panel_1.add(lblCdigo);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(new Color(0, 0, 51));
		lblPrecio.setFont(new Font("Verdana", Font.BOLD, 14));
		lblPrecio.setBounds(318, 14, 79, 16);
		panel_1.add(lblPrecio);

		codigoField = new JTextField();
		codigoField.setEnabled(false);
		codigoField.setColumns(10);
		codigoField.setBounds(28, 44, 190, 22);
		panel_1.add(codigoField);

		precioField = new JTextField();
		precioField.setEnabled(false);
		precioField.setColumns(10);
		precioField.setBounds(318, 42, 190, 22);
		panel_1.add(precioField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 114, 473, 84);
		panel_1.add(scrollPane);

		JLabel lblTratamiento = new JLabel("Tratamiento");
		lblTratamiento.setForeground(new Color(0, 0, 51));
		lblTratamiento.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTratamiento.setBounds(28, 82, 109, 16);
		panel_1.add(lblTratamiento);

		JPanel realizarPanel = new JPanel();
		realizarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if(Clinica.getInstance().crearConsulta(codigoField.getText(), precioField.getText(), sintomasPanel, tratamiento))
			}
		});
		realizarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		realizarPanel.setBackground(new Color(169, 181, 223));
		realizarPanel.setBounds(577, 456, 114, 28);
		fondo.add(realizarPanel);

		JLabel lblRealizar = new JLabel("Realizar\r\n");
		lblRealizar.setForeground(new Color(0, 0, 0));
		lblRealizar.setFont(new Font("Verdana", Font.PLAIN, 14));
		realizarPanel.add(lblRealizar);

		JPanel volverPanel = new JPanel();
		volverPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		volverPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volverPanel.setBackground(new Color(169, 181, 223));
		volverPanel.setBounds(722, 456, 114, 28);
		fondo.add(volverPanel);

		JLabel label_2 = new JLabel("Volver");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		volverPanel.add(label_2);

	}
}
