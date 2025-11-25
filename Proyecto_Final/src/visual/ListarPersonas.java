package visual;

import static logico.Clinica.getLoginUser;


import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Medico;
import logico.Persona;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class ListarPersonas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Persona user = getLoginUser();
	private JTable table;
	private JLabel tituloLabel;
	private DefaultTableModel model;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel barPanel;
	private JTextField textField;

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

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(new Color(138, 43, 226));
		panel_2.setBounds(22, 85, 135, 28);
		menuPanel.add(panel_2);

		JLabel label_2 = new JLabel("Detallar");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_2.add(label_2);

		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()>-1) {
					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
					Persona aux = Clinica.getInstance().personaById(id);
					
					JOptionPane.showMessageDialog(null, "El usuario es: "+aux.getUser().getUserName()+" La contraseña es: "+aux.getUser().getPass());;
					
					
				}
			}
		});
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBackground(new Color(138, 43, 226));
		panel_3.setBounds(22, 183, 135, 28);
		menuPanel.add(panel_3);

		JLabel lblVerUsuario = new JLabel("Ver usuario");
		lblVerUsuario.setForeground(Color.WHITE);
		lblVerUsuario.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_3.add(lblVerUsuario);

		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()>-1)
					dispose();
			}
		});
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_4.setBackground(new Color(138, 43, 226));
		panel_4.setBounds(22, 309, 135, 28);
		menuPanel.add(panel_4);

		JLabel lblSeleccionar = new JLabel("Seleccionar");
		lblSeleccionar.setForeground(Color.WHITE);
		lblSeleccionar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_4.add(lblSeleccionar);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_5.setBackground(new Color(138, 43, 226));
		panel_5.setBounds(22, 132, 135, 28);
		menuPanel.add(panel_5);

		JLabel lblModificar = new JLabel("Modificar");
		lblModificar.setForeground(Color.WHITE);
		lblModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_5.add(lblModificar);

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
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(138, 43, 226));
		panel.setBounds(874, 449, 85, 28);
		fondo.add(panel);

		JLabel lblVolver = new JLabel("Volver");
		lblVolver.setForeground(Color.WHITE);
		lblVolver.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel.add(lblVolver);
		
		textField = new JTextField();
		textField.setBounds(388, 455, 303, 22);
		fondo.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(new Color(138, 43, 226));
		panel_1.setBounds(703, 453, 85, 28);
		fondo.add(panel_1);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_1.add(lblBuscar);

		actualizarTableMedicos();
		
		if(user.getUser().getTipo().equals("Medico"))
			cargarListaPacientes();
	}


	private void actualizarTableMedicos() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);

		for(Persona p: cl.getPersonas()) {
			if(p instanceof Medico) {
				Object[] fila = {p.getCodigo(), p.getNombres(), p.getApellidos(), p.getCedula(), p.getEdad(), p.getGenero(), p.getTelefono()};
				model.addRow(fila);
			}
		}
	}
	
	private void actualizarTablePacientes() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);

		for(Persona p: cl.getPersonas()) {
			if(p instanceof Medico) {
				Object[] fila = {p.getCodigo(), p.getNombres(), p.getApellidos(), p.getCedula(), p.getEdad(), p.getGenero(), p.getTelefono()};
				model.addRow(fila);
			}
		}
	}
	
	public Persona objectoSeleccionado() {
		if(table.getSelectedRow()>-1) 
			return Clinica.getInstance().personaById(table.getValueAt(table.getSelectedRow(), 0).toString());
		else return null;
	}
	
	private void cargarListaPacientes() {
		tituloLabel.setText("Lista de pacientes");
		actualizarTablePacientes();
	}
}
