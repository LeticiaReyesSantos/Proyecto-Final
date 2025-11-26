package visual;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Persona;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.time.format.DateTimeFormatter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


import java.awt.event.MouseMotionAdapter;

import javax.swing.border.BevelBorder;




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
	private JPanel fechaPanel;
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
		barPanel.setBackground(new Color(45, 51, 107));
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
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(dim.width-39, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
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

		JLabel lblHoyEs = new JLabel("Hoy es");
		lblHoyEs.setBounds(25, 0, 152, 50);
		fechaPanel.add(lblHoyEs);
		lblHoyEs.setForeground(Color.WHITE);
		lblHoyEs.setFont(new Font("Verdana", Font.BOLD, 40));

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(64, 178, 512, 252);
		fondo.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(946, 178, 512, 252);
		fondo.add(panel_1);

		JPanel panel_9 = new JPanel();
		panel_9.setBounds(340, 666, 229, 28);
		fondo.add(panel_9);
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
		panel_9.setBackground(new Color(169, 181, 223));

		JLabel lblCerrarSesion = new JLabel("Cambiar de usuario");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_9.add(lblCerrarSesion);

		JPanel panel_10 = new JPanel();
		panel_10.setBounds(340, 704, 229, 28);
		fondo.add(panel_10);
		panel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clinica.getInstance().save();
				dispose();
			}
		});
		panel_10.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_10.setBackground(new Color(169, 181, 223));

		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_10.add(lblSalir);

		registrarMedicoPanel = new JPanel();
		registrarMedicoPanel.setBounds(597, 704, 229, 28);
		fondo.add(registrarMedicoPanel);
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
		registrarMedicoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		registrarMedicoPanel.setBackground(new Color(169, 181, 223));

		JLabel label_3 = new JLabel("Registrar");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		registrarMedicoPanel.add(label_3);

		listarMedicoPanel = new JPanel();
		listarMedicoPanel.setBounds(607, 753, 229, 28);
		fondo.add(listarMedicoPanel);
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
		listarMedicoPanel.setBackground(new Color(169, 181, 223));

		JLabel lblListaDeMedicos = new JLabel("Lista de medicos");
		lblListaDeMedicos.setForeground(Color.WHITE);
		lblListaDeMedicos.setFont(new Font("Verdana", Font.PLAIN, 14));
		listarMedicoPanel.add(lblListaDeMedicos);

		agendarCitaPanel = new JPanel();
		agendarCitaPanel.setBounds(587, 666, 229, 28);
		fondo.add(agendarCitaPanel);
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
		agendarCitaPanel.setBackground(new Color(169, 181, 223));
		agendarCitaPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblAgendarCita = new JLabel("Agendar Cita");
		lblAgendarCita.setForeground(Color.WHITE);
		lblAgendarCita.setFont(new Font("Verdana", Font.PLAIN, 14));
		agendarCitaPanel.add(lblAgendarCita);

	}

	private boolean isDesignTime() {
		return java.beans.Beans.isDesignTime();
	}
}
