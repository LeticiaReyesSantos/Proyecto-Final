package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class ListarConsultas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel barPanel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarConsultas dialog = new ListarConsultas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarConsultas() {
		setUndecorated(true);
		setBounds(100, 100, 989, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(248, 248, 255));
		fondo.setBounds(0, 0, 989, 481);
		contentPanel.add(fondo);
		fondo.setLayout(null);

		barPanel = new JPanel();
		barPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				//toma la posicion actual de la ventana
				x2= arg0.getXOnScreen();
				y2 = arg0.getYOnScreen();
				//actualiza la posicion de la ventana
				setLocation(x2-x1, y2-y1);
			}
		});
		barPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Toma la posicion actual del panel
				x1= e.getX();
				y1 = e.getY();
			}
		});
		barPanel.setBounds(0, 0, 989, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(45, 51, 107));

		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(950, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
		cerrarPanel.add(label);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 24, 169, 457);
		fondo.add(panel);
		
		JPanel seleccionPanel = new JPanel();
		seleccionPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		seleccionPanel.setBackground(new Color(120, 134, 199));
		seleccionPanel.setBounds(19, 232, 135, 35);
		panel.add(seleccionPanel);
		
		JLabel label_1 = new JLabel("Seleccionar");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		seleccionPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Men\u00FA");
		label_2.setForeground(new Color(169, 181, 223));
		label_2.setFont(new Font("Verdana", Font.BOLD, 18));
		label_2.setBounds(52, 16, 55, 23);
		panel.add(label_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(45, 51, 107));
		separator.setBackground(new Color(45, 51, 107));
		separator.setBounds(9, 48, 145, 2);
		panel.add(separator);
		
		JPanel diagnosticoPanel = new JPanel();
		diagnosticoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		diagnosticoPanel.setBackground(new Color(120, 134, 199));
		diagnosticoPanel.setBounds(22, 96, 132, 35);
		panel.add(diagnosticoPanel);
		
		JLabel lblVerDiagn = new JLabel("Ver Diagn\u00F3stico");
		lblVerDiagn.setForeground(Color.WHITE);
		lblVerDiagn.setFont(new Font("Verdana", Font.PLAIN, 14));
		diagnosticoPanel.add(lblVerDiagn);
		
		JLabel lblListaDeConsultas = new JLabel("LISTA DE CONSULTAS");
		lblListaDeConsultas.setForeground(new Color(120, 134, 199));
		lblListaDeConsultas.setFont(new Font("Verdana", Font.BOLD, 28));
		lblListaDeConsultas.setBounds(448, 41, 373, 35);
		fondo.add(lblListaDeConsultas);
		
		JPanel volverPanel = new JPanel();
		volverPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		volverPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volverPanel.setBackground(new Color(169, 181, 223));
		volverPanel.setBounds(842, 437, 114, 28);
		fondo.add(volverPanel);
		
		JLabel label_3 = new JLabel("Volver");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		volverPanel.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 107, 736, 309);
		fondo.add(scrollPane);
	}
}
