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
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.SoftBevelBorder;
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
	private JPanel menuPanelAdmin;
	private JPanel menuPanelMedico;
	private JPanel fechaPanel;

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
		lblNewLabel_1.setBounds(12, 0, 367, 41);
		bienvenidoPanel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel(usuario.getNombres());
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
		label_2.setBounds(1066, 0, 101, 66);
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
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarPersona regiP = new RegistrarPersona();
				regiP.setModal(true);
				regiP.setVisible(true);
			}
		});
		panel.setBackground(new Color(102, 0, 153));
		panel.setBounds(0, 177, 251, 35);
		menuPanelAdmin.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Registrar");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarPersonas listPerson = new ListarPersonas();
				listPerson.setModal(true);
				listPerson.setVisible(true);
			}
		});
		panel_1.setBackground(new Color(102, 0, 153));
		panel_1.setBounds(0, 225, 251, 35);
		menuPanelAdmin.add(panel_1);
		
		JLabel lblListar = new JLabel("Listar");
		lblListar.setForeground(Color.WHITE);
		lblListar.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_1.add(lblListar);
		
		menuPanelMedico = new JPanel();
		menuPanelMedico.setLayout(null);
		menuPanelMedico.setBackground(new Color(153, 50, 204));
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
		
		
		if(usuario.getUser().getTipo().equals("Administrador")) {
			menuPanelMedico.setVisible(false);
		}else if(usuario.getUser().getTipo().equals("Medico")) {
			menuPanelAdmin.setVisible(false);
		}
		
	}
	
	private boolean isDesignTime() {
	    return java.beans.Beans.isDesignTime();
	}
}
