package visual;

import static logico.Clinica.getLoginUser;


import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Medico;
import logico.Paciente;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JRadioButton;

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
	private JComboBox<String> filtroBox;
	private JPanel deshabilitarPanel;
	private JLabel lblDeshabilitar;
	private ButtonGroup grupo1 = new ButtonGroup();
	private JRadioButton habilitadoRadio;
	private JRadioButton retiradoRadio;

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
		setLocation(320, 250);
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

		tituloLabel = new JLabel("LISTA DE MEDICOS");

		if(user != null) {
			if(user.getUser().getTipo().equals("Medico"))
				tituloLabel.setText("LISTA DE PACIENTES");
		}

		tituloLabel.setForeground(new Color(120, 134, 199));
		tituloLabel.setFont(new Font("Verdana", Font.BOLD, 28));
		tituloLabel.setBounds(431, 24, 322, 35);
		fondo.add(tituloLabel);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(255, 255, 255));
		menuPanel.setBounds(0, 24, 169, 457);
		fondo.add(menuPanel);
		menuPanel.setLayout(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(45, 51, 107));
		separator_1.setBackground(new Color(45, 51, 107));
		separator_1.setBounds(0, 455, 1001, 2);
		menuPanel.add(separator_1);

		JLabel lblMenu = new JLabel("Men\u00FA");
		lblMenu.setBounds(57, 5, 55, 23);
		lblMenu.setForeground(new Color(169, 181, 223));
		lblMenu.setFont(new Font("Verdana", Font.BOLD, 18));
		menuPanel.add(lblMenu);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(45, 51, 107));
		separator.setBackground(new Color(45, 51, 107));
		separator.setBounds(12, 41, 145, 2);
		menuPanel.add(separator);

		JPanel detallarPanel = new JPanel();
		detallarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String codigo = table.getValueAt(selectedRow, 0).toString();
					Persona persona = Clinica.getInstance().personaById(codigo);

					if (persona != null) {
						String info = "Información detallada:\n\n" +"Código: " + persona.getCodigo() + "\n" +"Nombre: " + persona.getNombres() + " " + persona.getApellidos() + "\n" +"Cédula: " + persona.getCedula() + "\n" +"Teléfono: " + persona.getTelefono() + "\n" +"Email: " + persona.getEmail() + "\n" +"Dirección: " + persona.getDireccion();

						if (persona instanceof Medico) {
							Medico medico = (Medico) persona;
							info += "\nEspecialidad: " + medico.getEspecialidad() + "\n" +"Estado: " + (medico.isActivo() ? "Activo" : "Inactivo");
						} else if (persona instanceof Paciente) {
							Paciente paciente = (Paciente) persona;
							info += "\nTipo de Sangre: " + paciente.getTipoSangre();
						}

						JOptionPane.showMessageDialog(null, info, "Detalles", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una persona primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				detallarPanel.setBackground(new Color(100, 120, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				detallarPanel.setBackground(new Color(120, 134, 199));
			}
		});
		detallarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		detallarPanel.setBackground(new Color(120, 134, 199));
		detallarPanel.setBounds(25, 82, 132, 35);
		menuPanel.add(detallarPanel);

		JLabel label_2 = new JLabel("Detallar");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		detallarPanel.add(label_2);

		JPanel verUsuarioPanel = new JPanel();
		verUsuarioPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()>-1) {
					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
					Persona aux = Clinica.getInstance().personaById(id);

					JOptionPane.showMessageDialog(null, "El usuario es: "+aux.getUser().getUserName()+" La contraseña es: "+aux.getUser().getPass());;


				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				verUsuarioPanel.setBackground(new Color(100, 120, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				verUsuarioPanel.setBackground(new Color(120, 134, 199));
			}
		});

		verUsuarioPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		verUsuarioPanel.setBackground(new Color(120, 134, 199));
		verUsuarioPanel.setBounds(25, 254, 132, 35);
		menuPanel.add(verUsuarioPanel);

		JLabel lblVerUsuario = new JLabel("Ver usuario");
		lblVerUsuario.setForeground(Color.WHITE);
		lblVerUsuario.setFont(new Font("Verdana", Font.PLAIN, 14));
		verUsuarioPanel.add(lblVerUsuario);

		JPanel seleccionarPanel = new JPanel();
		seleccionarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()>-1)
					dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				seleccionarPanel.setBackground(new Color(100, 120, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				seleccionarPanel.setBackground(new Color(120, 134, 199));
			}
		});
		seleccionarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		seleccionarPanel.setBackground(new Color(120, 134, 199));
		seleccionarPanel.setBounds(22, 302, 135, 35);
		menuPanel.add(seleccionarPanel);

		JLabel lblSeleccionar = new JLabel("Seleccionar");
		lblSeleccionar.setForeground(Color.WHITE);
		lblSeleccionar.setFont(new Font("Verdana", Font.PLAIN, 14));
		seleccionarPanel.add(lblSeleccionar);

		JPanel modificarPanel = new JPanel();
		modificarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		modificarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificarPanel.setBackground(new Color(120, 134, 199));
		modificarPanel.setBounds(25, 129, 132, 35);
		menuPanel.add(modificarPanel);

		JLabel lblModificar = new JLabel("Modificar");
		lblModificar.setForeground(Color.WHITE);
		lblModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		modificarPanel.add(lblModificar);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(0, 0, 2, 457);
		menuPanel.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(167, -11, 2, 473);
		menuPanel.add(separator_3);
		deshabilitarPanel = new JPanel();
		deshabilitarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {JOptionPane.showMessageDialog(null, "Por favor, seleccione un médico primero", "Error", JOptionPane.WARNING_MESSAGE);
				return;
				}

				String codigoMedico = (String) table.getValueAt(selectedRow, 0);
				String nombreMedico = (String) table.getValueAt(selectedRow, 1) + (String) table.getValueAt(selectedRow, 2);
				int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea desactivar al médico?\n" + nombreMedico + " (" + codigoMedico + ")\n\n","Confirmar Desactivación",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					boolean resultado = Clinica.getInstance().desactivarMedico(codigoMedico);
					if (resultado) {
						JOptionPane.showMessageDialog(null, "Médico desactivado exitosamente\n", "Éxito",JOptionPane.INFORMATION_MESSAGE);
						actualizarTableMedicosActivo();
						actualizarTableMedicosInactivos();
					} else {
						JOptionPane.showMessageDialog(null, "No se puede desactivar el médico porque ya tiene citas.\n", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				deshabilitarPanel.setBackground(Color.RED);
			}

		});

		deshabilitarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		deshabilitarPanel.setBackground(new Color(120, 134, 199));
		deshabilitarPanel.setBounds(25, 176, 132, 35);
		menuPanel.add(deshabilitarPanel);

		lblDeshabilitar = new JLabel("Deshabilitar");
		lblDeshabilitar.setForeground(Color.WHITE);
		lblDeshabilitar.setFont(new Font("Verdana", Font.PLAIN, 14));
		deshabilitarPanel.add(lblDeshabilitar);

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
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(model);
		scrollPane.setViewportView(table);

		JPanel volverPanel = new JPanel();
		volverPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		volverPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volverPanel.setBackground(new Color(169, 181, 223));
		volverPanel.setBounds(874, 449, 85, 28);
		fondo.add(volverPanel);

		JLabel lblVolver = new JLabel("Volver");
		lblVolver.setForeground(Color.BLACK);
		lblVolver.setFont(new Font("Verdana", Font.PLAIN, 14));
		volverPanel.add(lblVolver);

		textField = new JTextField();
		textField.setBounds(361, 449, 303, 22);
		fondo.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(new Color(169, 181, 223));
		panel_1.setBounds(676, 449, 85, 28);
		fondo.add(panel_1);

		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_1.add(lblBuscar);

		filtroBox = new JComboBox<String>(new DefaultComboBoxModel<>(new String[] {"Medico","Administrador"}));
		filtroBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String seleccion = filtroBox.getSelectedItem().toString();
				if(seleccion.equals("Administrador")) {
					actualizarTableAdmin();
					habilitadoRadio.setVisible(false);
					retiradoRadio.setVisible(false);
				}
				else {
					actualizarTableMedicosActivo();
					habilitadoRadio.setVisible(true);
					retiradoRadio.setVisible(true);
				}
			}
		});
		filtroBox.setBounds(226, 91, 125, 22);
		fondo.add(filtroBox);

		JLabel lblNewLabel = new JLabel("Filtro");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setBounds(181, 93, 56, 16);
		fondo.add(lblNewLabel);

		habilitadoRadio = new JRadioButton("En accion");
		habilitadoRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(habilitadoRadio.isSelected()) 
					actualizarTableMedicosActivo();
			}
		});
		habilitadoRadio.setBackground(new Color(120, 134, 199));
		habilitadoRadio.setSelected(true);
		habilitadoRadio.setBounds(361, 90, 85, 25);
		fondo.add(habilitadoRadio);

		retiradoRadio = new JRadioButton("retirado");
		retiradoRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(retiradoRadio.isSelected())
					actualizarTableMedicosInactivos();
			}
		});
		retiradoRadio.setBackground(new Color(120, 134, 199));
		retiradoRadio.setBounds(458, 90, 85, 25);
		fondo.add(retiradoRadio);

		grupo1.add(retiradoRadio);
		grupo1.add(habilitadoRadio);

		actualizarTableMedicosActivo();

		if(user.getUser().getTipo().equals("Medico"))
			cargarListaPacientes();
	}


	private void actualizarTableMedicosActivo() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);

		for(Persona p: cl.getPersonas()) {
			if(p instanceof Medico) {
				if(((Medico) p).isActivo()) {
					Object[] fila = {p.getCodigo(), p.getNombres(), p.getApellidos(), p.getCedula(), p.getEdad(), p.getGenero(), p.getTelefono()};
					model.addRow(fila);
				}
			}
		}
	}

	private void actualizarTableMedicosInactivos() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);

		for(Persona p: cl.getPersonas()) {
			if(p instanceof Medico) {
				if(!((Medico) p).isActivo()) {
					Object[] fila = {p.getCodigo(), p.getNombres(), p.getApellidos(), p.getCedula(), p.getEdad(), p.getGenero(), p.getTelefono()};
					model.addRow(fila);
				}
			}
		}
	}

	private void actualizarTableAdmin() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);

		for(Persona p: cl.getPersonas()) {
			if(p.getUser().getTipo().equals("Administrador")) {
				Object[] fila = {p.getCodigo(), p.getNombres(), p.getApellidos(), p.getCedula(), p.getEdad(), p.getGenero(), p.getTelefono()};
				model.addRow(fila);
			}
		}
	}

	private void actualizarTablePacientes() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);

		for(Persona p: cl.getPersonas()) {
			if(p instanceof Paciente) {
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
