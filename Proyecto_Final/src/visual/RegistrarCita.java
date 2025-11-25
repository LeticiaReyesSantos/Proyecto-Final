package visual;

import java.awt.BorderLayout;


import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import logico.Clinica;
import logico.Medico;
import logico.Persona;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class RegistrarCita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField cedulaField;
	private Persona paciente;
	private Medico medico;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField telefonoField;
	private JTextField medicoField;
	private JPanel buscarMedicoPanel;
	private JPanel buscarPanel;
	private JLabel mensajeLabel;
	private JPanel agendarPanel;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita() {
		setUndecorated(true);
		setBounds(100, 100, 889, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(248, 248, 255));
		fondo.setBounds(0, 0, 889, 500);
		contentPanel.add(fondo);
		fondo.setLayout(null);

		JPanel barPanel = new JPanel();
		barPanel.setBounds(-96, 0, 985, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(102, 0, 204));

		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(946, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
		cerrarPanel.add(label);

		JLabel label_1 = new JLabel("Cedula");
		label_1.setFont(new Font("Verdana", Font.BOLD, 18));
		label_1.setBounds(57, 121, 80, 16);
		fondo.add(label_1);

		cedulaField = new JTextField();
		cedulaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		cedulaField.addActionListener(e -> {
			paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaField.getText());
			if(paciente != null){
				cargarPersona();
				deshabilitarEdicionDeCampos();
				mensajeLabel.setVisible(false);
			}else {
				habilitarEdicionDeCampos();
				mensajeLabel.setVisible(false);
			}

		});
		cedulaField.setBounds(135, 121, 190, 22);
		fondo.add(cedulaField);
		cedulaField.setColumns(10);

		JLabel lblCrearCitas = new JLabel("REALIZAR CITAS");
		lblCrearCitas.setForeground(new Color(138, 43, 226));
		lblCrearCitas.setFont(new Font("Verdana", Font.BOLD, 28));
		lblCrearCitas.setBounds(308, 51, 282, 35);
		fondo.add(lblCrearCitas);

		JPanel generalPanel = new JPanel();
		generalPanel.setLayout(null);
		generalPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		generalPanel.setBackground(new Color(240, 248, 255));
		generalPanel.setBounds(55, 176, 772, 244);
		fondo.add(generalPanel);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNombre.setBounds(12, 13, 80, 16);
		generalPanel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblApellido.setBounds(12, 77, 93, 22);
		generalPanel.add(lblApellido);

		nombreField = new JTextField();
		nombreField.setEnabled(false);
		nombreField.setColumns(10);
		nombreField.setBounds(12, 42, 190, 22);
		generalPanel.add(nombreField);

		apellidoField = new JTextField();
		apellidoField.setEnabled(false);
		apellidoField.setColumns(10);
		apellidoField.setBounds(12, 111, 190, 22);
		generalPanel.add(apellidoField);

		telefonoField = new JTextField();
		telefonoField.setEnabled(false);
		telefonoField.setColumns(10);
		telefonoField.setBounds(12, 181, 190, 22);
		generalPanel.add(telefonoField);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblTelefono.setBounds(12, 146, 93, 22);
		generalPanel.add(lblTelefono);

		JLabel lblMedico = new JLabel("Medico");
		lblMedico.setBounds(404, 13, 80, 16);
		generalPanel.add(lblMedico);
		lblMedico.setFont(new Font("Verdana", Font.BOLD, 18));

		medicoField = new JTextField();
		medicoField.setBounds(404, 52, 190, 22);
		generalPanel.add(medicoField);
		medicoField.setEnabled(false);
		medicoField.setColumns(10);

		buscarMedicoPanel = new JPanel();
		buscarMedicoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListarPersonas listPerson = new ListarPersonas();
				listPerson.setModal(true);
				listPerson.setVisible(true);
				medico = (Medico)listPerson.objectoSeleccionado();

				if(medico != null) {
					medicoField.setText(medico.getNombres());
				}

			}
		});
		buscarMedicoPanel.setBounds(628, 46, 114, 28);
		generalPanel.add(buscarMedicoPanel);
		buscarMedicoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		buscarMedicoPanel.setBackground(new Color(138, 43, 226));

		JLabel label_2 = new JLabel("Buscar");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		buscarMedicoPanel.add(label_2);

		JLabel lblHoraEstimadaDe = new JLabel("Fecha");
		lblHoraEstimadaDe.setFont(new Font("Verdana", Font.BOLD, 18));
		lblHoraEstimadaDe.setBounds(404, 111, 103, 16);
		generalPanel.add(lblHoraEstimadaDe);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(404, 146, 152, 22);
		generalPanel.add(dateChooser);

		buscarPanel = new JPanel();
		buscarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaField.getText());
				if(paciente != null){
					cargarPersona();
					deshabilitarEdicionDeCampos();
					mensajeLabel.setVisible(false);
				}else {
					habilitarEdicionDeCampos();
					mensajeLabel.setVisible(true);
				}
			}
		});
		buscarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		buscarPanel.setBackground(new Color(138, 43, 226));
		buscarPanel.setBounds(349, 115, 135, 28);
		fondo.add(buscarPanel);

		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Verdana", Font.PLAIN, 14));
		buscarPanel.add(lblBuscar);

		mensajeLabel = new JLabel("Paciente no encontrado*");
		mensajeLabel.setForeground(new Color(255, 0, 0));
		mensajeLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		mensajeLabel.setBounds(496, 124, 177, 16);
		mensajeLabel.setVisible(false);
		fondo.add(mensajeLabel);

		agendarPanel = new JPanel();
		agendarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(validarCampos()) {
					LocalDate fecha = dateToLocalDate();
					
					if(Clinica.getInstance().hacerCita(cedulaField.getText(), nombreField.getText(), apellidoField.getText(), telefonoField.getText(), medico, fecha)) {
						JOptionPane.showMessageDialog(null, "Se ha guardado la cita con exito");
					}else {
						JOptionPane.showMessageDialog(null, "Ha surgido un error al guardar la cita");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Aun hay campos por rellenar");
				}

			}
		});
		agendarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		agendarPanel.setBackground(new Color(138, 43, 226));
		agendarPanel.setBounds(622, 459, 114, 28);
		fondo.add(agendarPanel);

		JLabel lblAgendar = new JLabel("Agendar");
		lblAgendar.setForeground(Color.WHITE);
		lblAgendar.setFont(new Font("Verdana", Font.PLAIN, 14));
		agendarPanel.add(lblAgendar);

		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(new Color(138, 43, 226));
		panel_2.setBounds(763, 459, 114, 28);
		fondo.add(panel_2);

		JLabel lblVolver = new JLabel("Volver");
		lblVolver.setForeground(Color.WHITE);
		lblVolver.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_2.add(lblVolver);
	}

	private void cargarPersona() {
		nombreField.setText(paciente.getNombres());
		apellidoField.setText(paciente.getApellidos());
		telefonoField.setText(paciente.getTelefono());

		nombreField.setForeground(Color.white);
		apellidoField.setForeground(Color.white);
		telefonoField.setForeground(Color.white);

	}

	private void habilitarEdicionDeCampos() {
		nombreField.setEnabled(true);
		apellidoField.setEnabled(true);
		telefonoField.setEnabled(true);
	}

	private void deshabilitarEdicionDeCampos() {
		nombreField.setEnabled(false);
		apellidoField.setEnabled(false);
		telefonoField.setEnabled(false);
	}

	private boolean validarCampos() {
		return !nombreField.getText().isEmpty() && !apellidoField.getText().isEmpty() && !telefonoField.getText().isEmpty() &&
				!cedulaField.getText().isEmpty() && !medicoField.getText().isEmpty() && dateChooser.getDate() != null;
	}
	
	private LocalDate dateToLocalDate() {
		return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}

