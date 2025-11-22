package visual;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import logico.Control;
import logico.Persona;
import logico.User;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;

public class Principal2 extends JFrame {

	private JPanel contentPane;
	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	private Dimension dim;
	private Persona usuario = Control.getLoginUser();
	private int x1;
	private int x2;
	private int y1;
	private int y2;

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
		setResizable(false);
		dim = getToolkit().getScreenSize();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		int ancho = (int)dim.width;
		int largo = (int)dim.height;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 531);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, ancho, largo);
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
		barPanel.setBounds(0, 0, ancho, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		
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
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(ancho-39, 0, 39, 26);
		barPanel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		JPanel bienvenidoPanel = new JPanel();
		bienvenidoPanel.setBackground(new Color(102, 0, 204));
		bienvenidoPanel.setBounds(249, 24, 1839, 67);
		fondo.add(bienvenidoPanel);
		bienvenidoPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenid@");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 40));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(324, 13, 255, 41);
		bienvenidoPanel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel(usuario.getNombres());
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 40));
		label_1.setBounds(615, 13, 255, 41);
		bienvenidoPanel.add(label_1);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(102, 0, 204));
		menuPanel.setBounds(0, 24, 251, 1080);
		fondo.add(menuPanel);
		menuPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medicos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel.setBounds(69, 125, 120, 24);
		menuPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(12, 162, 227, 2);
		menuPanel.add(separator);
		
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
		menuPanel.add(panel);
		
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
		menuPanel.add(panel_1);
		
		JLabel lblListar = new JLabel("Listar");
		lblListar.setForeground(Color.WHITE);
		lblListar.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_1.add(lblListar);
		
	}

}
