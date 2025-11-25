package visual;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Persona;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;



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
	private JPanel menuPanelAdmin;
	private JPanel menuPanelMedico;
	private JPanel fechaPanel;
	private JPanel citasPanel;
	private JPanel registrarMedicoPanel;
	private JPanel listarMedicoPanel;
	private JPanel agendarCitaPanel;

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
		//Clinica.getInstance().load();
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
		barPanel.setBackground(new Color(102, 0, 204));
		barPanel.setBounds(0, 0, dim.width, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		
		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Clinica.getInstance().save();
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
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(dim.width-39, 0, 39, 26);
		barPanel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		JPanel bienvenidoPanel = new JPanel();
		bienvenidoPanel.setBackground(new Color(102, 0, 204));
		bienvenidoPanel.setBounds(249, 100, 1839, 67);
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
		
		JLabel label_2 = new JLabel("[]");
		if(usuario.getUser().getTipo().equals("Administrador")) {
			label_2.setText("[A]");
		}else if(usuario.getUser().getTipo().equals("Medico")) {
			label_2.setText("[M]");
		}else
			label_2.setText("[S]");
		
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 40));
		label_2.setBounds(dim.width-650, 0, 70, 66);
		bienvenidoPanel.add(label_2);
		
		menuPanelAdmin = new JPanel();
		menuPanelAdmin.setBackground(new Color(102, 0, 204));
		menuPanelAdmin.setBounds(0, 24, 251, 1080);
		fondo.add(menuPanelAdmin);
		menuPanelAdmin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medicos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel.setBounds(69, 125, 120, 24);
		menuPanelAdmin.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(12, 162, 227, 2);
		menuPanelAdmin.add(separator);
		
		registrarMedicoPanel = new JPanel();
		registrarMedicoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RegistrarPersona regiP = new RegistrarPersona();
				regiP.setModal(true);
				regiP.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				registrarMedicoPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				registrarMedicoPanel.setBackground(new Color(138, 43, 226));
			}
		});
		registrarMedicoPanel.setBounds(12, 177, 229, 28);
		menuPanelAdmin.add(registrarMedicoPanel);
		registrarMedicoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		registrarMedicoPanel.setBackground(new Color(138, 43, 226));
		
		JLabel label_3 = new JLabel("Registrar");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		registrarMedicoPanel.add(label_3);
		
		listarMedicoPanel = new JPanel();
		listarMedicoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarPersonas listPerson = new ListarPersonas();
				listPerson.setModal(true);
				listPerson.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				listarMedicoPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				listarMedicoPanel.setBackground(new Color(138, 43, 226));
			}
		});
		listarMedicoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		listarMedicoPanel.setBackground(new Color(138, 43, 226));
		listarMedicoPanel.setBounds(10, 218, 229, 28);
		menuPanelAdmin.add(listarMedicoPanel);
		
		JLabel lblListaDeMedicos = new JLabel("Lista de medicos");
		lblListaDeMedicos.setForeground(Color.WHITE);
		lblListaDeMedicos.setFont(new Font("Verdana", Font.PLAIN, 14));
		listarMedicoPanel.add(lblListaDeMedicos);
		
		JLabel lblCitas_1 = new JLabel("Citas");
		lblCitas_1.setForeground(Color.WHITE);
		lblCitas_1.setFont(new Font("Verdana", Font.BOLD, 24));
		lblCitas_1.setBounds(93, 259, 77, 24);
		menuPanelAdmin.add(lblCitas_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(12, 294, 227, 2);
		menuPanelAdmin.add(separator_2);
		
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
				agendarCitaPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				agendarCitaPanel.setBackground(new Color(138, 43, 226));
			}
		});
		agendarCitaPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		agendarCitaPanel.setBackground(new Color(138, 43, 226));
		agendarCitaPanel.setBounds(10, 309, 229, 28);
		menuPanelAdmin.add(agendarCitaPanel);
		
		JLabel lblAgendarCita = new JLabel("Agendar Cita");
		lblAgendarCita.setForeground(Color.WHITE);
		lblAgendarCita.setFont(new Font("Verdana", Font.PLAIN, 14));
		agendarCitaPanel.add(lblAgendarCita);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(new Color(138, 43, 226));
		panel_2.setBounds(10, 350, 229, 28);
		menuPanelAdmin.add(panel_2);
		
		JLabel lblListaGeneralDe = new JLabel("Lista general de citas");
		lblListaGeneralDe.setForeground(Color.WHITE);
		lblListaGeneralDe.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_2.add(lblListaGeneralDe);
		
		JLabel lblEnfermedades = new JLabel("Enfermedades");
		lblEnfermedades.setForeground(Color.WHITE);
		lblEnfermedades.setFont(new Font("Verdana", Font.BOLD, 24));
		lblEnfermedades.setBounds(32, 397, 192, 24);
		menuPanelAdmin.add(lblEnfermedades);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(12, 434, 227, 2);
		menuPanelAdmin.add(separator_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_5.setBackground(new Color(138, 43, 226));
		panel_5.setBounds(12, 455, 229, 28);
		menuPanelAdmin.add(panel_5);
		
		JLabel lblRegistrar = new JLabel("Registrar");
		lblRegistrar.setForeground(Color.WHITE);
		lblRegistrar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_5.add(lblRegistrar);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_6.setBackground(new Color(138, 43, 226));
		panel_6.setBounds(12, 496, 229, 28);
		menuPanelAdmin.add(panel_6);
		
		JLabel lblLista = new JLabel("Lista");
		lblLista.setForeground(Color.WHITE);
		lblLista.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_6.add(lblLista);
		
		JLabel lblVacunas = new JLabel("Vacunas");
		lblVacunas.setForeground(Color.WHITE);
		lblVacunas.setFont(new Font("Verdana", Font.BOLD, 24));
		lblVacunas.setBounds(69, 555, 120, 24);
		menuPanelAdmin.add(lblVacunas);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBounds(12, 592, 227, 2);
		menuPanelAdmin.add(separator_4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_7.setBackground(new Color(138, 43, 226));
		panel_7.setBounds(10, 607, 229, 28);
		menuPanelAdmin.add(panel_7);
		
		JLabel label_5 = new JLabel("Registrar");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_7.add(label_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_8.setBackground(new Color(138, 43, 226));
		panel_8.setBounds(12, 650, 229, 28);
		menuPanelAdmin.add(panel_8);
		
		JLabel label_4 = new JLabel("Lista");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_8.add(label_4);
		
		JPanel panel_9 = new JPanel();
		panel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				Clinica.getInstance().save();
				dispose();
			}
		});
		panel_9.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_9.setBackground(new Color(138, 43, 226));
		panel_9.setBounds(12, 765, 229, 28);
		menuPanelAdmin.add(panel_9);
		
		JLabel lblCerrarSesion = new JLabel("Cambiar de usuario");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_9.add(lblCerrarSesion);
		
		JPanel panel_10 = new JPanel();
		panel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clinica.getInstance().save();
				dispose();
			}
		});
		panel_10.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_10.setBackground(new Color(138, 43, 226));
		panel_10.setBounds(12, 806, 229, 28);
		menuPanelAdmin.add(panel_10);
		
		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_10.add(lblSalir);
		
		menuPanelMedico = new JPanel();
		menuPanelMedico.setLayout(null);
		menuPanelMedico.setBackground(new Color(138, 43, 226));
		menuPanelMedico.setBounds(0, 24, 251, 1080);
		fondo.add(menuPanelMedico);
		
		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setForeground(Color.WHITE);
		lblCitas.setFont(new Font("Verdana", Font.BOLD, 24));
		lblCitas.setBounds(93, 125, 120, 24);
		menuPanelMedico.add(lblCitas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(12, 162, 227, 2);
		menuPanelMedico.add(separator_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(102, 0, 153));
		panel_3.setBounds(0, 177, 251, 35);
		menuPanelMedico.add(panel_3);
		
		JLabel lblHacerCita = new JLabel("Hacer cita");
		lblHacerCita.setForeground(Color.WHITE);
		lblHacerCita.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_3.add(lblHacerCita);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(102, 0, 153));
		panel_4.setBounds(0, 225, 251, 35);
		menuPanelMedico.add(panel_4);
		
		JLabel lblMisCitas = new JLabel("Mis citas");
		lblMisCitas.setForeground(Color.WHITE);
		lblMisCitas.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_4.add(lblMisCitas);
		
		fechaPanel = new JPanel();
		fechaPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fechaPanel.setBackground(new Color(153, 0, 255));
		if(isDesignTime())
			fechaPanel.setBounds(498, 164, 1049, 61);
		else
			fechaPanel.setBounds(489, 164, dim.width, 61);
		fondo.add(fechaPanel);
		fechaPanel.setLayout(null);
		
		JLabel lblHoyEs = new JLabel("Hoy es");
		lblHoyEs.setBounds(25, 0, 152, 50);
		fechaPanel.add(lblHoyEs);
		lblHoyEs.setForeground(Color.WHITE);
		lblHoyEs.setFont(new Font("Verdana", Font.BOLD, 40));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(486, 363, 841, 453);
		fondo.add(scrollPane);
		
		if(usuario.getUser().getTipo().equals("Administrador"))
			scrollPane.setVisible(false);
		
		citasPanel = new JPanel();
		citasPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		citasPanel.setBackground(new Color(248, 248, 255));
		scrollPane.setViewportView(citasPanel);
		citasPanel.setLayout(new BoxLayout(citasPanel, BoxLayout.Y_AXIS));
		citasPanel.setAlignmentX(LEFT_ALIGNMENT);
		


		
		
		if(usuario.getUser().getTipo().equals("Administrador")) {
			menuPanelMedico.setVisible(false);
		}else if(usuario.getUser().getTipo().equals("Medico")) {
			menuPanelAdmin.setVisible(false);
		}
		if(usuario.getUser().getTipo().equals("Medico")) 
			cargarCitas();
		
	}
	
	private boolean isDesignTime() {
	    return java.beans.Beans.isDesignTime();
	}
	
	private JPanel crearPanelCita(Cita c) {
		JPanel nuevo = new JPanel();
		
		nuevo.setLayout(new BorderLayout());
		nuevo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuevo.setBackground(new Color(240, 248, 255));
		nuevo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		nuevo.setAlignmentX(LEFT_ALIGNMENT);

		
		
	    JLabel lblInfo = new JLabel(
	            "<html><b>Paciente:</b> " + c.getPersona().getNombres() +
	            "<br><b>Médico:</b> " + c.getMedico().getNombres() +
	            "<br><b>Codigo:</b> " + c.getCodigo() + "</html>"
	        );
	       
	        nuevo.add(lblInfo, BorderLayout.CENTER);
		
		return nuevo;
	}
	
	private void cargarCitas() {
		citasPanel.removeAll();
		
		ArrayList<Cita> citas = ((Medico)usuario).citasPendientesHoy();
		
	    for(Cita c : citas) {
	        JPanel panelCita = crearPanelCita(c);
	        citasPanel.add(panelCita);
	        citasPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        System.out.println("Cita cargada");
	    }

	    citasPanel.revalidate();
	    citasPanel.repaint();
	}
}
