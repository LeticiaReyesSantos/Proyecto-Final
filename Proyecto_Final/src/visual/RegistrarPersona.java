package visual;

import java.awt.BorderLayout;



import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import logico.Clinica;
import logico.Horario;
import logico.Medico;
import logico.Persona;
import logico.User;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;


public class RegistrarPersona extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField cedulaField;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField telefonoField;
	private JTextField direccionField;
	private JTextField correoField;
	private JRadioButton fRadioBt;
	private JRadioButton mRadioBt;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Calendar calendario = Calendar.getInstance();
	private JTextField especialidadField;
	private JDateChooser dateChooser;

	private User user = Clinica.getLoginUser().getUser();
	private JSpinner maxCitasSpinner;
	private JPanel aceptarPanel;
	private JRadioButton medicoRadio;
	private JRadioButton adminRadio;
	private JLabel lblDatosDelMedico;
	private JPanel medicoPanel;
	private JLabel lblCanitdadMaximasDe;
	private JLabel lblNewLabel_3;
	private JSeparator separator_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPersona dialog = new RegistrarPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPersona() {
		setUndecorated(true);
		setBounds(100, 100, 845, 664);
		setLocation(420, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(248, 248, 255));
		fondo.setBounds(0, 0, 844, 664);
		contentPanel.add(fondo);
		fondo.setLayout(null);

		JPanel generalPanel = new JPanel();
		generalPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		generalPanel.setBackground(new Color(120, 134, 199));
		generalPanel.setBounds(53, 93, 746, 336);
		fondo.add(generalPanel);
		generalPanel.setLayout(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(45, 51, 107));
		separator_1.setBackground(new Color(45, 51, 107));
		separator_1.setBounds(12, 128, 181, 2);
		generalPanel.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(45, 51, 107));
		separator.setForeground(new Color(45, 51, 107));
		separator.setBounds(12, 66, 181, 2);
		generalPanel.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Cedula");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel_1.setBounds(12, 13, 63, 16);
		generalPanel.add(lblNewLabel_1);

		cedulaField = new JTextField();
		cedulaField.setBorder(null);
		cedulaField.setBounds(12, 43, 181, 22);
		generalPanel.add(cedulaField);
		cedulaField.setColumns(10);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setForeground(new Color(255, 255, 255));
		lblNombres.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombres.setBounds(12, 78, 79, 16);
		generalPanel.add(lblNombres);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(new Color(255, 255, 255));
		lblApellidos.setFont(new Font("Verdana", Font.BOLD, 14));
		lblApellidos.setBounds(12, 139, 79, 16);
		generalPanel.add(lblApellidos);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setForeground(new Color(255, 255, 255));
		lblGenero.setFont(new Font("Verdana", Font.BOLD, 14));
		lblGenero.setBounds(12, 225, 79, 16);
		generalPanel.add(lblGenero);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTelefono.setBounds(497, 77, 79, 16);
		generalPanel.add(lblTelefono);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setForeground(new Color(255, 255, 255));
		lblDireccion.setFont(new Font("Verdana", Font.BOLD, 14));
		lblDireccion.setBounds(497, 138, 79, 16);
		generalPanel.add(lblDireccion);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(new Color(255, 255, 255));
		lblCorreo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCorreo.setBounds(497, 220, 79, 16);
		generalPanel.add(lblCorreo);

		nombreField = new JTextField();
		nombreField.setColumns(10);
		nombreField.setBorder(null);
		nombreField.setBounds(12, 104, 181, 22);
		generalPanel.add(nombreField);

		apellidoField = new JTextField();
		apellidoField.setColumns(10);
		apellidoField.setBorder(null);
		apellidoField.setBounds(12, 167, 181, 22);
		generalPanel.add(apellidoField);

		telefonoField = new JTextField();
		telefonoField.setColumns(10);
		telefonoField.setBorder(null);
		telefonoField.setBounds(497, 106, 181, 22);
		generalPanel.add(telefonoField);

		direccionField = new JTextField();
		direccionField.setColumns(10);
		direccionField.setBorder(null);
		direccionField.setBounds(497, 166, 181, 22);
		generalPanel.add(direccionField);

		correoField = new JTextField();
		correoField.setColumns(10);
		correoField.setBorder(null);
		correoField.setBounds(497, 249, 181, 22);
		generalPanel.add(correoField);

		mRadioBt = new JRadioButton("Masculino");
		mRadioBt.setForeground(Color.WHITE);
		mRadioBt.setSelected(true);
		mRadioBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mRadioBt.isSelected())
					fRadioBt.setSelected(false);
				mRadioBt.setSelected(true);
			}
		});
		mRadioBt.setBackground(new Color(169, 181, 223));
		mRadioBt.setFont(new Font("Verdana", Font.PLAIN, 14));
		mRadioBt.setBounds(99, 221, 99, 25);
		generalPanel.add(mRadioBt);

		fRadioBt = new JRadioButton("Femenino");
		fRadioBt.setForeground(Color.WHITE);
		fRadioBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fRadioBt.isSelected())
					mRadioBt.setSelected(false);

				fRadioBt.setSelected(true);
			}
		});
		fRadioBt.setBackground(new Color(169, 181, 223));
		fRadioBt.setFont(new Font("Verdana", Font.PLAIN, 14));
		fRadioBt.setBounds(202, 221, 99, 25);
		generalPanel.add(fRadioBt);

		JLabel lblNewLabel_2 = new JLabel("Fecha de nacimiento");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel_2.setBounds(497, 13, 181, 16);
		generalPanel.add(lblNewLabel_2);

		calendario.set(1900, Calendar.JANUARY, 1);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 13));
		dateChooser.setMinSelectableDate(calendario.getTime());

		calendario.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth());
		dateChooser.setMaxSelectableDate(calendario.getTime());

		dateChooser.setBorder(null);
		dateChooser.setBounds(497, 42, 181, 22);
		generalPanel.add(dateChooser);
		
		JSeparator separator_10 = new JSeparator();
		dateChooser.add(separator_10, BorderLayout.SOUTH);
		separator_10.setForeground(new Color(45, 51, 107));
		separator_10.setBackground(new Color(45, 51, 107));

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(12, 190, 181, 2);
		generalPanel.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(497, 129, 181, 2);
		generalPanel.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(45, 51, 107));
		separator_4.setBackground(new Color(45, 51, 107));
		separator_4.setBounds(497, 189, 181, 2);
		generalPanel.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(45, 51, 107));
		separator_5.setBackground(new Color(45, 51, 107));
		separator_5.setBounds(497, 272, 181, 2);
		generalPanel.add(separator_5);
		
		JLabel lblRango = new JLabel("Rango:");
		lblRango.setForeground(new Color(255, 255, 255));
		lblRango.setFont(new Font("Verdana", Font.BOLD, 14));
		lblRango.setBounds(12, 277, 79, 22);
		generalPanel.add(lblRango);
		
		adminRadio = new JRadioButton("Administrador");
		adminRadio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(adminRadio.isSelected()) {
					adminRadio.setSelected(true);
					medicoRadio.setSelected(false);
					deshabilitarMedicoPanel();
				}
			}
		});
		adminRadio.setForeground(Color.WHITE);
		adminRadio.setFont(new Font("Verdana", Font.PLAIN, 14));
		adminRadio.setBackground(new Color(169, 181, 223));
		adminRadio.setBounds(202, 276, 144, 25);
		generalPanel.add(adminRadio);
		
		medicoRadio = new JRadioButton("Medico");
		medicoRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(medicoRadio.isSelected()) {
					adminRadio.setSelected(false);
					medicoRadio.setSelected(true);
					habilitarMedicoPanel();
				}
			}
		});
		medicoRadio.setForeground(Color.WHITE);
		medicoRadio.setSelected(true);
		medicoRadio.setFont(new Font("Verdana", Font.PLAIN, 14));
		medicoRadio.setBackground(new Color(169, 181, 223));
		medicoRadio.setBounds(99, 276, 99, 25);
		generalPanel.add(medicoRadio);

		JPanel barPanel = new JPanel();
		barPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x1= e.getX();
				y1 = e.getY();
			}
		});
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
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(45, 51, 107));
		barPanel.setBounds(0, 0, 844, 25);
		fondo.add(barPanel);

		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				cerrarPanel.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cerrarPanel.setBackground(new Color(102, 0, 204));
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(805, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");

		cerrarPanel.add(label);

		JLabel lblNewLabel = new JLabel("Datos Generales");
		lblNewLabel.setForeground(new Color(120, 134, 199));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 28));
		lblNewLabel.setBounds(291, 38, 278, 25);
		fondo.add(lblNewLabel);

		medicoPanel = new JPanel();
		medicoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		medicoPanel.setBackground(new Color(120, 134, 199));
		medicoPanel.setBounds(53, 470, 746, 140);
		fondo.add(medicoPanel);
		medicoPanel.setLayout(null);
		
				separator_6 = new JSeparator();
				separator_6.setForeground(new Color(45, 51, 107));
				separator_6.setBackground(new Color(45, 51, 107));
				separator_6.setBounds(553, 69, 181, 2);
				medicoPanel.add(separator_6);
		
		maxCitasSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 30, 1));
		maxCitasSpinner.setBounds(162, 48, 118, 22);
		medicoPanel.add(maxCitasSpinner);

		lblCanitdadMaximasDe = new JLabel("Citas maximas");
		lblCanitdadMaximasDe.setForeground(new Color(255, 255, 255));
		lblCanitdadMaximasDe.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCanitdadMaximasDe.setBounds(32, 49, 118, 18);
		medicoPanel.add(lblCanitdadMaximasDe);

		lblNewLabel_3 = new JLabel("Especialidad");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel_3.setBounds(431, 50, 118, 16);
		medicoPanel.add(lblNewLabel_3);

		especialidadField = new JTextField();
		especialidadField.setColumns(10);
		especialidadField.setBorder(null);
		especialidadField.setBounds(553, 48, 181, 22);
		medicoPanel.add(especialidadField);

		lblDatosDelMedico = new JLabel("Datos del Medico");
		lblDatosDelMedico.setForeground(new Color(120, 134, 199));
		lblDatosDelMedico.setFont(new Font("Verdana", Font.BOLD, 27));
		lblDatosDelMedico.setBounds(291, 442, 278, 25);
		fondo.add(lblDatosDelMedico);

		aceptarPanel = new JPanel();
		aceptarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!camposGeneralesVacios()) {
					Persona aux = null;
					String codigo ="Me-"+Clinica.getInstance().genMedico;
					String nombres = nombreField.getText();
					String apellidos = apellidoField.getText();
					String cedula = cedulaField.getText();
					String direccion = direccionField.getText();
					String telefono = telefonoField.getText();
					String email = correoField.getText();
					char genero = genero();
					LocalDate fechaNacimiento = dateToLocalDate();



					if(user.getTipo().equals("Administrador")) {

						if(!camposMedicoVacios()) {
							String especialidad= especialidadField.getText();
							int maxCitas = (int) maxCitasSpinner.getValue();
							User usuario = new User("Medico", codigo, codigo);

							aux = new Medico(codigo, cedula, nombres, apellidos, 
									fechaNacimiento, genero, telefono, direccion, email, especialidad, maxCitas, usuario);
							
							
							
							Clinica.getInstance().addPersona(aux);
							
							JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
							JOptionPane.showMessageDialog(null, "Usuario generado: "+usuario.getUserName()+" Contraseña: "+usuario.getPass());
						}	
					}	
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				aceptarPanel.setBackground(new Color(169, 181, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				aceptarPanel.setBackground(new Color(169, 181, 223));
			}
		});
		aceptarPanel.setBackground(new Color(169, 181, 223));
		aceptarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		aceptarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aceptarPanel.setBounds(566, 623, 85, 28);
		fondo.add(aceptarPanel);

		JLabel Registrar = new JLabel("Registrar");
		Registrar.setForeground(new Color(255, 255, 255));
		Registrar.setFont(new Font("Verdana", Font.PLAIN, 14));
		aceptarPanel.add(Registrar);

		JPanel cancelarPanel = new JPanel();
		cancelarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelarPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cancelarPanel.setBackground(new Color(138, 43, 226));
			}
		});
		cancelarPanel.setBackground(new Color(169, 181, 223));
		cancelarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cancelarPanel.setBounds(681, 623, 85, 28);
		fondo.add(cancelarPanel);

		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setForeground(Color.WHITE);
		lblCancelar.setFont(new Font("Verdana", Font.PLAIN, 14));
		cancelarPanel.add(lblCancelar);
		
		JSeparator border1 = new JSeparator();
		border1.setOrientation(SwingConstants.VERTICAL);
		border1.setForeground(new Color(0, 0, 128));
		border1.setBackground(new Color(0, 0, 128));
		border1.setBounds(1, 25, 20, 639);
		fondo.add(border1);
		
		JSeparator border2 = new JSeparator();
		border2.setOrientation(SwingConstants.VERTICAL);
		border2.setForeground(new Color(45, 51, 107));
		border2.setBackground(new Color(45, 51, 107));
		border2.setBounds(841, 25, 62, 639);
		fondo.add(border2);
		
		JSeparator border3 = new JSeparator();
		border3.setForeground(new Color(45, 51, 107));
		border3.setBackground(new Color(45, 51, 107));
		border3.setBounds(0, 662, 844, 2);
		fondo.add(border3);
	}

	private boolean camposGeneralesVacios() {

		return nombreField.getText().trim().isEmpty() || apellidoField.getText().trim().isEmpty() || cedulaField.getText().trim().isEmpty()  ||
				telefonoField.getText().trim().isEmpty() || direccionField.getText().trim().isEmpty() 
				|| correoField.getText().trim().isEmpty() || dateChooser.getDate() == null;
	}

	private boolean camposMedicoVacios() {
		return especialidadField.getText().trim().isEmpty();
	}

	private char genero() {
		char gender = 'M';

		if(fRadioBt.isSelected())
			gender= 'F';

		return gender;
	}

	private LocalDate dateToLocalDate() {
		return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	private void habilitarMedicoPanel() {
		lblDatosDelMedico.setForeground(new Color(102, 0, 204));
		medicoPanel.setBackground(new Color(240, 248, 255));
		medicoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		maxCitasSpinner.setValue(5);
		maxCitasSpinner.setEnabled(true);
		especialidadField.setEnabled(true);
		especialidadField.setText("");
		separator_6.setForeground(new Color(72, 61, 139));
		separator_6.setBackground(new Color(72, 61, 139));
		
	}
	
	private void deshabilitarMedicoPanel() {
		lblDatosDelMedico.setForeground(Color.GRAY);
		medicoPanel.setBackground(Color.LIGHT_GRAY);
		medicoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		maxCitasSpinner.setValue(5);
		maxCitasSpinner.setEnabled(false);
		especialidadField.setEnabled(false);
		especialidadField.setText("");
		separator_6.setForeground(Color.BLACK);
		separator_6.setBackground(Color.BLACK);	
	}
}
