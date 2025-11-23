package visual;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Persona;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

public class ListarPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Persona user = Clinica.getInstance().getLoginUser();
	private JTable table;
	private JLabel tituloLabel;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPersonas dialog = new ListarPersonas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPersonas() {
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
		
		JPanel barPanel = new JPanel();
		barPanel.setBounds(0, 0, 989, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(102, 0, 204));
		
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
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(950, 0, 39, 26);
		barPanel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		tituloLabel = new JLabel("LISTA DE MEDICOS");
		
		if(user != null) {
			if(user.getUser().getTipo().equals("Medico"))
				tituloLabel.setText("LISTA DE PACIENTES");
		}
		
		tituloLabel.setForeground(new Color(138, 43, 226));
		tituloLabel.setFont(new Font("Verdana", Font.BOLD, 28));
		tituloLabel.setBounds(438, 63, 322, 35);
		fondo.add(tituloLabel);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(102, 0, 204));
		menuPanel.setBounds(0, 24, 169, 457);
		fondo.add(menuPanel);
		menuPanel.setLayout(null);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(57, 5, 55, 23);
		lblMenu.setForeground(new Color(255, 255, 255));
		lblMenu.setFont(new Font("Verdana", Font.BOLD, 18));
		menuPanel.add(lblMenu);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(255, 255, 255));
		separator.setBounds(12, 41, 145, 2);
		menuPanel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(181, 122, 796, 323);
		fondo.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nombre", "Apellido", "Cedula", "Edad", "Genero", "Telefono"
				}
			);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(138, 43, 226));
		panel.setBounds(874, 449, 85, 28);
		fondo.add(panel);
		
		JLabel label_1 = new JLabel("Cancelar");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(new Color(138, 43, 226));
		panel_1.setBounds(777, 449, 85, 28);
		fondo.add(panel_1);
		
		JLabel lblDetallar = new JLabel("Detallar");
		lblDetallar.setForeground(Color.WHITE);
		lblDetallar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_1.add(lblDetallar);
		
		actualizarTable();
	}
	
	
	private void actualizarTable() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);
		
		for(Persona p: cl.getPersonas()) {
			Object[] fila = {p.getCodigo(), p.getNombres(), p.getApellidos(), p.getCedula(), p.getEdad(), p.getGenero(), p.getTelefono()};
			model.addRow(fila);
		}
		
	}
}
