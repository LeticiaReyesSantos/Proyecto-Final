package visual;


import java.awt.EventQueue;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Persona;
import servidor.Servidor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseMotionAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;




public class Principal2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	private Dimension dim;
	private Persona usuario = Clinica.getLoginUser();
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel menuPersonaPanel;
	private Image img;
	private ImageIcon icon;
	private JLabel personaIcon;
	private JLabel usuarioIcon;
	private JPanel regMedicoPanel;
	private JPanel listMedicoPanel;
	private JPanel cerrarPanel;
	private JLabel rangoLbl;
	private JLabel enfermedadIcon;
	private JLabel calendarioIcon;
	private JPanel citasAdminPanel;
	private JPanel agendarCitaPanel;
	private JPanel listCitasPanel;
	private JPanel listEnfermedadPanel;
	private JPanel regEnfermedadPanel;
	private JPanel enfermedadPanel;
	private JPanel vacunaPanel;
	private JPanel reportePanel;
	private JPanel registrarVacunaPanel;
	private JPanel listVacunasPanel;
	private JLabel lblPersona;
	private JLabel vacunaIcon;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JLabel label_3;

	private JPanel configuracionPanel;
	private JPanel loadRespaldoPanel;
	private JLabel lblCargarRespaldo;
	private JPanel changePassPanel;
	private JLabel lblCambiarContrasea;
	private JPanel changeUserPanel;
	private JPanel salirPanel;
	private JPanel respaldoPanel;

	private Servidor servidor = new Servidor(9000);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal2 frame = new Principal2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal2() {
		Clinica.load();
		servidor.iniciar();

		setResizable(false);
		if (isDesignTime()) {
			dim = new Dimension(1400, 900); 
		} else {
			dim = getToolkit().getScreenSize();
		}
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (isDesignTime()) {
			setBounds(100, 100, 1400, 900);
		} else {
			setBounds(0, 0, dim.width, dim.height);
		}
		if (!isDesignTime()) {
			setUndecorated(true);
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, dim.width, dim.height);
		contentPane.add(fondo);
		fondo.setLayout(null);

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

		configuracionPanel = new JPanel();
		configuracionPanel.setVisible(false);
		configuracionPanel.setBackground(new Color(45, 51, 107));
		configuracionPanel.setBounds(dim.width-260, 92, 256, 375);
		fondo.add(configuracionPanel);
		configuracionPanel.setLayout(null);

		respaldoPanel = new JPanel();
		respaldoPanel.setBounds(12, 85, 229, 28);
		configuracionPanel.add(respaldoPanel);
		respaldoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				realizarRespaldo();
			}
		});
		respaldoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		respaldoPanel.setBackground(new Color(120, 134, 199));

		JLabel lblRespaldo = new JLabel("Hacer Respaldo");
		lblRespaldo.setForeground(Color.WHITE);
		lblRespaldo.setFont(new Font("Verdana", Font.PLAIN, 14));
		respaldoPanel.add(lblRespaldo);

		changeUserPanel = new JPanel();
		changeUserPanel.setBounds(12, 293, 229, 28);
		configuracionPanel.add(changeUserPanel);
		changeUserPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				servidor.detener();
				Clinica.getInstance().save();
				Login login = new Login();
				login.setVisible(true);

				dispose();
			}
		});
		changeUserPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		changeUserPanel.setBackground(new Color(120, 134, 199));

		JLabel lblCerrarSesion = new JLabel("Cambiar de usuario");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		changeUserPanel.add(lblCerrarSesion);

		salirPanel = new JPanel();
		salirPanel.setBounds(12, 334, 229, 28);
		configuracionPanel.add(salirPanel);
		salirPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clinica.getInstance().save();
				servidor.detener();
				dispose();
			}
		});
		salirPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		salirPanel.setBackground(new Color(120, 134, 199));

		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		salirPanel.add(lblSalir);

		loadRespaldoPanel = new JPanel();
		loadRespaldoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File archivo = buscarRespaldo();
				if(archivo!= null) {
					int respuesta  = JOptionPane.showConfirmDialog(null, "Estas seguro de querer sobreescribir el archivo actual?");

					if(respuesta == JOptionPane.YES_OPTION) {
						Clinica.getInstance().save();
						cargarRespaldo(archivo);
					}
				}
			}
		});
		loadRespaldoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loadRespaldoPanel.setBackground(new Color(120, 134, 199));
		loadRespaldoPanel.setBounds(12, 126, 229, 28);
		configuracionPanel.add(loadRespaldoPanel);

		lblCargarRespaldo = new JLabel("Cargar Respaldo");
		lblCargarRespaldo.setForeground(Color.WHITE);
		lblCargarRespaldo.setFont(new Font("Verdana", Font.PLAIN, 14));
		loadRespaldoPanel.add(lblCargarRespaldo);

		changePassPanel = new JPanel();
		changePassPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		changePassPanel.setBackground(new Color(120, 134, 199));
		changePassPanel.setBounds(12, 252, 229, 28);
		configuracionPanel.add(changePassPanel);

		lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a");
		lblCambiarContrasea.setForeground(Color.WHITE);
		lblCambiarContrasea.setFont(new Font("Verdana", Font.PLAIN, 14));
		changePassPanel.add(lblCambiarContrasea);

		JLabel lblNewLabel = new JLabel("Opciones");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(84, 13, 110, 28);
		configuracionPanel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(22, 54, 222, 2);
		configuracionPanel.add(separator);
		barPanel.setBackground(new Color(45, 51, 107));
		barPanel.setBounds(0, 0, dim.width, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);

		cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Clinica.getInstance().save();
				servidor.detener();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				cerrarPanel.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cerrarPanel.setBackground(new Color(45, 51, 107));
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(dim.width-39, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
		label.setForeground(Color.WHITE);
		cerrarPanel.add(label);

		JPanel bienvenidoPanel = new JPanel();
		bienvenidoPanel.setBackground(new Color(120, 134, 199));
		bienvenidoPanel.setBounds(0, 24, 2087, 67);
		fondo.add(bienvenidoPanel);
		bienvenidoPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Bienvenido/a:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 40));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(12, 13, 367, 41);
		bienvenidoPanel.add(lblNewLabel_1);

		JLabel label_1 = new JLabel(usuario.getNombres()+" "+usuario.getApellidos());
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 40));
		label_1.setBounds(371, 13, 255, 41);
		bienvenidoPanel.add(label_1);

		usuarioIcon = new JLabel("");
		usuarioIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!configuracionPanel.isVisible()) {
					configuracionPanel.setVisible(true);
				}else {
					configuracionPanel.setVisible(false);
				}
			}
		});
		usuarioIcon.setBounds(dim.width-60, 11, 53, 54);
		bienvenidoPanel.add(usuarioIcon);
		usuarioIcon.setIcon(new ImageIcon(Principal2.class.getResource("/imagenes/Usuario.png")));
		icon = (ImageIcon)usuarioIcon.getIcon();
		img = icon.getImage().getScaledInstance(usuarioIcon.getWidth(), usuarioIcon.getHeight(), Image.SCALE_SMOOTH);
		usuarioIcon.setIcon(new ImageIcon(img));


		JLabel lblHoyEs = new JLabel("Hoy es");
		lblHoyEs.setBounds(25, 0, 152, 50);
		lblHoyEs.setForeground(Color.WHITE);
		lblHoyEs.setFont(new Font("Verdana", Font.BOLD, 40));

		menuPersonaPanel = new JPanel();
		menuPersonaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!regMedicoPanel.isVisible()) {
					regMedicoPanel.setVisible(true);
					listMedicoPanel.setVisible(true);
				}else {
					regMedicoPanel.setVisible(false);
					listMedicoPanel.setVisible(false);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				menuPersonaPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuPersonaPanel.setBackground(new Color(120, 134, 199));
			}
		});
		menuPersonaPanel.setBackground(new Color(120, 134, 199));
		menuPersonaPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuPersonaPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		menuPersonaPanel.setBounds(142, 188, 386, 146);
		fondo.add(menuPersonaPanel);
		menuPersonaPanel.setLayout(null);


		lblPersona = new JLabel("Medico");
		lblPersona.setForeground(Color.WHITE);
		lblPersona.setFont(new Font("Verdana", Font.BOLD, 40));
		lblPersona.setBounds(36, 54, 203, 41);
		menuPersonaPanel.add(lblPersona);


		personaIcon = new JLabel("");
		personaIcon.setBounds(233, 13, 141, 120);
		menuPersonaPanel.add(personaIcon);
		personaIcon.setIcon(new ImageIcon(Principal2.class.getResource("/imagenes/medicoIcon.png")));
		icon = (ImageIcon)personaIcon.getIcon();
		img = icon.getImage().getScaledInstance(personaIcon.getWidth(), personaIcon.getHeight(), Image.SCALE_SMOOTH);
		personaIcon.setIcon(new ImageIcon(img));

		regMedicoPanel = new JPanel();
		regMedicoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarPersona regiP = new RegistrarPersona();
				regiP.setModal(true);
				regiP.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				regMedicoPanel.setBackground(new Color (120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				regMedicoPanel.setBackground(new Color(169, 181, 223));
			}
		});
		regMedicoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regMedicoPanel.setBackground(new Color(169, 181, 223));
		regMedicoPanel.setBounds(142, 333, 386, 67);
		regMedicoPanel.setVisible(false);
		fondo.add(regMedicoPanel);

		JLabel lblRegistrar = new JLabel("Registrar");
		lblRegistrar.setForeground(Color.WHITE);
		lblRegistrar.setFont(new Font("Verdana", Font.PLAIN, 40));
		regMedicoPanel.add(lblRegistrar);
		icon = new ImageIcon(Login.class.getResource("/imagenes/Usuario.png"));

		listMedicoPanel = new JPanel();
		listMedicoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarPersonas listPerson = new ListarPersonas();
				listPerson.setModal(true);
				listPerson.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				listMedicoPanel.setBackground(new Color(120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				listMedicoPanel.setBackground(new Color(169, 181, 223));
			}
		});
		listMedicoPanel.setVisible(false);
		listMedicoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listMedicoPanel.setBackground(new Color(169, 181, 223));
		listMedicoPanel.setBounds(142, 400, 386, 67);
		fondo.add(listMedicoPanel);

		JLabel lblLista = new JLabel("Lista");
		lblLista.setForeground(Color.WHITE);
		lblLista.setFont(new Font("Verdana", Font.PLAIN, 40));
		listMedicoPanel.add(lblLista);

		rangoLbl = new JLabel("[]");
		rangoLbl.setBounds(10, 87, 70, 66);
		fondo.add(rangoLbl);
		if(usuario.getUser().getTipo().equals("Administrador")) {
			rangoLbl.setText("[A]");
		}else if(usuario.getUser().getTipo().equals("Medico")) {
			rangoLbl.setText("[M]");
		}else
			rangoLbl.setText("[S]");

		rangoLbl.setForeground(new Color(45, 51, 107));
		rangoLbl.setFont(new Font("Verdana", Font.PLAIN, 40));

		enfermedadPanel = new JPanel();
		enfermedadPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!regEnfermedadPanel.isVisible()) {
					regEnfermedadPanel.setVisible(true);
					listEnfermedadPanel.setVisible(true);
				}else {
					regEnfermedadPanel.setVisible(false);
					listEnfermedadPanel.setVisible(false);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				enfermedadPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				enfermedadPanel.setBackground(new Color(120, 134, 199));
			}
		});
		enfermedadPanel.setLayout(null);
		enfermedadPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enfermedadPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		enfermedadPanel.setBackground(new Color(120, 134, 199));
		enfermedadPanel.setBounds(271, 557, 434, 146);
		fondo.add(enfermedadPanel);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setForeground(Color.WHITE);
		lblEnfermedad.setFont(new Font("Verdana", Font.BOLD, 40));
		lblEnfermedad.setBounds(12, 61, 287, 41);
		enfermedadPanel.add(lblEnfermedad);

		enfermedadIcon = new JLabel("");
		enfermedadIcon.setIcon(new ImageIcon(Principal2.class.getResource("/imagenes/enfermedadIcon.png")));
		enfermedadIcon.setBounds(293, 13, 141, 120);
		icon = (ImageIcon)enfermedadIcon.getIcon();
		img = icon.getImage().getScaledInstance(enfermedadIcon.getWidth(), enfermedadIcon.getHeight(), Image.SCALE_SMOOTH);
		enfermedadIcon.setIcon(new ImageIcon(img));

		enfermedadPanel.add(enfermedadIcon);

		regEnfermedadPanel = new JPanel();
		regEnfermedadPanel.setVisible(false);
		regEnfermedadPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarEnfermedad regEnf = new RegistrarEnfermedad(null);
				regEnf.setModal(true);
				regEnf.setVisible(true);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				regEnfermedadPanel.setBackground(new Color(120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				regEnfermedadPanel.setBackground(new Color(169, 181, 223));
			}
		});
		regEnfermedadPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regEnfermedadPanel.setBackground(new Color(169, 181, 223));
		regEnfermedadPanel.setBounds(271, 702, 434, 67);
		fondo.add(regEnfermedadPanel);

		JLabel label_2 = new JLabel("Registrar");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 40));
		regEnfermedadPanel.add(label_2);

		listEnfermedadPanel = new JPanel();
		listEnfermedadPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarEnfermedades listarEnfermedades = new ListarEnfermedades(0); //0 como single selection
				listarEnfermedades.setModal(true); 
				listarEnfermedades.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				listEnfermedadPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				listEnfermedadPanel.setBackground(new Color(169, 181, 223));
			}
		});
		listEnfermedadPanel.setVisible(false);
		listEnfermedadPanel.setBackground(new Color(169, 181, 223));
		listEnfermedadPanel.setBounds(271, 769, 434, 67);
		fondo.add(listEnfermedadPanel);

		JLabel lblLista_1 = new JLabel("Lista");
		lblLista_1.setForeground(Color.WHITE);
		lblLista_1.setFont(new Font("Verdana", Font.PLAIN, 40));
		listEnfermedadPanel.add(lblLista_1);

		citasAdminPanel = new JPanel();
		citasAdminPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!agendarCitaPanel.isVisible()) {
					agendarCitaPanel.setVisible(true);
					listCitasPanel.setVisible(true);
				}else {
					agendarCitaPanel.setVisible(false);
					listCitasPanel.setVisible(false);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				citasAdminPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				citasAdminPanel.setBackground(new Color(120, 134, 199));
			}
		});
		citasAdminPanel.setLayout(null);
		citasAdminPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		citasAdminPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		citasAdminPanel.setBackground(new Color(120, 134, 199));
		citasAdminPanel.setBounds(685, 188, 313, 146);
		fondo.add(citasAdminPanel);

		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setForeground(Color.WHITE);
		lblCitas.setFont(new Font("Verdana", Font.BOLD, 40));
		lblCitas.setBounds(12, 61, 112, 41);
		citasAdminPanel.add(lblCitas);

		calendarioIcon = new JLabel("");
		calendarioIcon.setBounds(160, 13, 141, 120);
		calendarioIcon.setIcon(new ImageIcon(Principal2.class.getResource("/imagenes/calendarioIcon.png")));
		icon = (ImageIcon)calendarioIcon.getIcon();
		img = icon.getImage().getScaledInstance(calendarioIcon.getWidth(), calendarioIcon.getHeight(), Image.SCALE_SMOOTH);
		calendarioIcon.setIcon(new ImageIcon(img));

		citasAdminPanel.add(calendarioIcon);

		agendarCitaPanel = new JPanel();

		agendarCitaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarCita regCita = new RegistrarCita();
				regCita.setModal(true);
				regCita.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				agendarCitaPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				agendarCitaPanel.setBackground(new Color(169, 181, 223));
			}
		});
		agendarCitaPanel.setBackground(new Color(169, 181, 223));
		agendarCitaPanel.setVisible(false);
		agendarCitaPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		agendarCitaPanel.setBounds(685, 333, 313, 67);
		fondo.add(agendarCitaPanel);

		JLabel lblAgendar = new JLabel("Agendar");
		lblAgendar.setForeground(Color.WHITE);
		lblAgendar.setFont(new Font("Verdana", Font.PLAIN, 40));
		agendarCitaPanel.add(lblAgendar);

		listCitasPanel = new JPanel();
		listCitasPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				listCitasPanel.setBackground(new Color(120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				listCitasPanel.setBackground(new Color(169, 181, 223));
			}
		});
		listCitasPanel.setBackground(new Color(169, 181, 223));
		listCitasPanel.setVisible(false);
		listCitasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listCitasPanel.setBounds(685, 400, 313, 67);
		fondo.add(listCitasPanel);

		JLabel lblListar = new JLabel("Ver");
		lblListar.setForeground(Color.WHITE);
		lblListar.setFont(new Font("Verdana", Font.PLAIN, 40));
		listCitasPanel.add(lblListar);

		vacunaPanel = new JPanel();
		vacunaPanel.setLayout(null);
		vacunaPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		vacunaPanel.setBackground(new Color(120, 134, 199));
		vacunaPanel.setBounds(1132, 188, 386, 146);
		fondo.add(vacunaPanel);

		JLabel lblReportes = new JLabel("Vacunas");
		lblReportes.setForeground(Color.WHITE);
		lblReportes.setFont(new Font("Verdana", Font.BOLD, 40));
		lblReportes.setBounds(36, 54, 209, 41);
		vacunaPanel.add(lblReportes);

		vacunaIcon = new JLabel("");
		vacunaIcon.setBounds(209, 0, 197, 148);
		vacunaIcon.setIcon(new ImageIcon(Principal2.class.getResource("/imagenes/jeringaIcon.png")));
		icon = (ImageIcon)vacunaIcon.getIcon();
		img = icon.getImage().getScaledInstance(vacunaIcon.getWidth(), vacunaIcon.getHeight(), Image.SCALE_SMOOTH);
		vacunaIcon.setIcon(new ImageIcon(img));
		vacunaPanel.add(vacunaIcon);

		reportePanel = new JPanel();
		reportePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MostrarReportes reportes = new MostrarReportes();
				reportes.setModal(true);
				reportes.setVisible(true);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				reportePanel.setBackground(new Color(45, 51, 107));
			}


			@Override
			public void mouseExited(MouseEvent e) {
				reportePanel.setBackground(new Color(120, 134, 199));
			}
		});
		reportePanel.setLayout(null);
		reportePanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		reportePanel.setBackground(new Color(120, 134, 199));
		reportePanel.setBounds(942, 557, 458, 146);
		fondo.add(reportePanel);

		JLabel lblReportes_1 = new JLabel("Ver reportes");
		lblReportes_1.setForeground(Color.WHITE);
		lblReportes_1.setFont(new Font("Verdana", Font.BOLD, 40));
		lblReportes_1.setBounds(36, 54, 302, 41);
		reportePanel.add(lblReportes_1);

		registrarVacunaPanel = new JPanel();
		registrarVacunaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarVacuna regVacuna = new RegistrarVacuna(null);
				regVacuna.setModal(true); 
				regVacuna.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				registrarVacunaPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				registrarVacunaPanel.setBackground(new Color(169, 181, 223));
			}
		});
		registrarVacunaPanel.setVisible(false);
		registrarVacunaPanel.setBackground(new Color(169, 181, 223));
		registrarVacunaPanel.setBounds(1132, 333, 386, 67);
		fondo.add(registrarVacunaPanel);

		JLabel lblRegistrar_1 = new JLabel("Registrar");
		lblRegistrar_1.setForeground(Color.WHITE);
		lblRegistrar_1.setFont(new Font("Verdana", Font.PLAIN, 40));
		registrarVacunaPanel.add(lblRegistrar_1);

		listVacunasPanel = new JPanel();
		listVacunasPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarVacuna listVac = new ListarVacuna();
				listVac.setModal(true); 
				listVac.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				listVacunasPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				listVacunasPanel.setBackground(new Color(169, 181, 223));
			}
		});
		listVacunasPanel.setVisible(false);
		listVacunasPanel.setBackground(new Color(169, 181, 223));
		listVacunasPanel.setBounds(1132, 400, 386, 67);
		fondo.add(listVacunasPanel);

		JLabel label_4 = new JLabel("Lista");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Verdana", Font.PLAIN, 40));
		listVacunasPanel.add(label_4);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(361, 615, 995, 272);
		fondo.add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Codigo", "Persona", "Fecha", "Estado"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(model);
		scrollPane.setViewportView(table);

		label_3 = new JLabel("Citas para hoy");
		label_3.setVisible(false);
		label_3.setForeground(new Color(45, 51, 107));
		label_3.setFont(new Font("Verdana", Font.BOLD, 40));
		label_3.setBounds(667, 503, 367, 41);
		fondo.add(label_3);
		scrollPane.setVisible(false);

		if(usuario.getUser().getTipo().equals("Medico"))
			cargarMedico();


	}

	private boolean isDesignTime() {
		return java.beans.Beans.isDesignTime();
	}

	private void cargarMedico() {
		personaIcon.setIcon(new ImageIcon(Principal2.class.getResource("/imagenes/pacienteIcon.png")));
		icon = (ImageIcon)personaIcon.getIcon();
		img = icon.getImage().getScaledInstance(personaIcon.getWidth(), personaIcon.getHeight(), Image.SCALE_SMOOTH);
		personaIcon.setIcon(new ImageIcon(img));
		lblPersona.setText("Paciente");
		scrollPane.setVisible(true);
		enfermedadPanel.setVisible(false);
		reportePanel.setVisible(false);
		label_3.setVisible(true);
		cargarCitasActuales();

	}

	private void cargarCitasActuales() {
		model.setRowCount(0);
		ArrayList<Cita> citas = usuario.getHistorial();


		for(Cita c: citas) {
			if(c.getFecha().equals(LocalDate.now())) {
				Object[] fila = {c.getCodigo(), c.getPersona().getNombres()+" "+c.getPersona().getApellidos(), 
						c.getFecha(), c.isEstado() ? "Pendiente" : "Completada"};
				model.addRow(fila);
			}
		}
	}

	private void realizarRespaldo() {


		File archivo = new File("clinica.dat");
		try (Socket sc = new Socket("127.0.0.1", 9000)){



			DataOutputStream ou = new DataOutputStream(sc.getOutputStream());
			DataInputStream in = new DataInputStream(sc.getInputStream());
			FileInputStream f = new FileInputStream(archivo);

			ou.writeUTF(archivo.getName());
			int unByte;

			while((unByte = f.read())!= -1) {
				ou.write(unByte);
			}

			ou.flush();
			sc.shutdownOutput();
			String respuesta = in.readUTF();
			JOptionPane.showMessageDialog(this, "Respaldo completado: " + respuesta);
			f.close();
			in.close();
			ou.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al enviar respaldo: " + e.getMessage());
			e.printStackTrace();
		}

	}


	private File buscarRespaldo() {
		File archivo = new File("Backup");
		File archivoSeleccionado = null;
		if(!archivo.exists() || archivo.list().length <=0) {
			JOptionPane.showMessageDialog(null, "No existen respaldos ahora mismo");
			return archivoSeleccionado;
		}

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Selecciona un archivo");
		chooser.setCurrentDirectory(archivo);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Respaldos .dat", "dat");
		chooser.setFileFilter(filtro);
		int resultado = chooser.showOpenDialog(null);


		if (resultado == JFileChooser.APPROVE_OPTION) {
			archivoSeleccionado = chooser.getSelectedFile();
		}

		return archivoSeleccionado;
	}

	private void cargarRespaldo(File archivo) {

		if(archivo != null) {
			ObjectInputStream objeto;
			FileInputStream file;

			try {
				file = new FileInputStream(archivo);
				objeto = new ObjectInputStream(file);
				Clinica aux = (Clinica)objeto.readObject();
				Clinica.setClinica(aux);
				objeto.close();
				file.close();
				JOptionPane.showMessageDialog(null, "Se ha cargado con exito");
			}catch (FileNotFoundException e) {
				// TODO: handle exception
			}catch (IOException e) {

			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
