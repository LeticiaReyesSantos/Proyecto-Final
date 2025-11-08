package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Login extends JFrame {
	private JPanel contentPane;
	private JPanel cerrarPanel;
	private JPanel barraPanel;
	private JPanel fondo;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Image img;
	private ImageIcon icon;
	private JLabel imagenPrincipal;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel loginPanel;
	private JLabel lblLogo;
	private JPanel usuarioPanel;
	private JPanel contraPanel;
	private JLabel usuarioIcono;
	private JLabel usuarioIcon;
	private JLabel contraIcono;
	private JTextField contraField;
	private JTextField textField;
	private JSeparator separator_1;
	private JPanel loginF;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setUndecorated(true);//Quitar la barra superior
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 923, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fondo = new JPanel();
		fondo.setBackground(new Color(255, 255, 255));
		fondo.setBounds(0, 0, 923, 518);
		contentPane.add(fondo);
		fondo.setLayout(null);
		
		barraPanel = new JPanel();
		barraPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				//toma la posicion actual de la ventana
				x2= arg0.getXOnScreen();
				y2 = arg0.getYOnScreen();
				//actualiza la posicion de la ventana
				setLocation(x2-x1, y2-y1);
			}
		});
		barraPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Toma la posicion actual del panel
				x1= e.getX();
				y1 = e.getY();
			}
		});
		
		lblLogo = new JLabel("logo");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Verdana", Font.BOLD, 13));
		lblLogo.setBounds(166, 76, 134, 16);
		fondo.add(lblLogo);
		
		lblNewLabel_2 = new JLabel("texto de ejemplo");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(115, 290, 134, 16);
		fondo.add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("BIENVENIDO");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 48));
		lblNewLabel_1.setBounds(24, 177, 378, 82);
		fondo.add(lblNewLabel_1);
		barraPanel.setBounds(0, 0, 923, 25);
		fondo.add(barraPanel);
		barraPanel.setBackground(new Color(102, 0, 204));
		barraPanel.setLayout(null);
		
		cerrarPanel = new JPanel();
		cerrarPanel.setBounds(883, 0, 39, 26);
		barraPanel.add(cerrarPanel);
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cerrarPanel.setBackground(new Color(255, 0, 51));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cerrarPanel.setBackground(new Color(102, 0, 204));
			}
		});
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cerrarPanel.setForeground(Color.BLACK);
		
		JLabel lblNewLabel = new JLabel("X");
		cerrarPanel.add(lblNewLabel);
		
		imagenPrincipal = new JLabel("");
		
		imagenPrincipal.setBounds(0, 13, 402, 505);
		icon = new ImageIcon(Login.class.getResource("/imagenes/principal.jpg"));
		img = icon.getImage().getScaledInstance(imagenPrincipal.getWidth(), imagenPrincipal.getHeight(), Image.SCALE_SMOOTH);
		fondo.add(imagenPrincipal);
		
		imagenPrincipal.setIcon(new ImageIcon(Login.class.getResource("/imagenes/principal.jpg")));
		
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(240, 248, 255));
		loginPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginPanel.setBounds(430, 62, 467, 443);
		fondo.add(loginPanel);
		loginPanel.setLayout(null);
		
		usuarioPanel = new JPanel();
		usuarioPanel.setBackground(new Color(255, 255, 255));
		usuarioPanel.setBounds(68, 70, 339, 37);
		loginPanel.add(usuarioPanel);
		usuarioPanel.setLayout(null);
		
		usuarioIcono = new JLabel("");
		usuarioIcono.setBounds(0, 0, 56, 37);
		icon = new ImageIcon(Login.class.getResource("/imagenes/icono de usuario.jpg"));
		img = icon.getImage().getScaledInstance(usuarioIcono.getWidth(), usuarioIcono.getHeight(), Image.SCALE_SMOOTH);
		usuarioIcono.setIcon(new ImageIcon(img));
		usuarioPanel.add(usuarioIcono);
		
		usuarioIcon = new JLabel("");
		usuarioIcon.setBounds(0, 0, 56, 37);
		icon = new ImageIcon(Login.class.getResource("/imagenes/icono de usuario.jpg"));
		img = icon.getImage().getScaledInstance(usuarioIcon.getWidth(), usuarioIcon.getHeight(), Image.SCALE_SMOOTH);
		usuarioIcon.setIcon(new ImageIcon(img));
		usuarioPanel.add(usuarioIcon);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(59, 0, 268, 24);
		usuarioPanel.add(textField);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(59, 30, 268, 2);
		usuarioPanel.add(separator_1);
		
		contraPanel = new JPanel();
		contraPanel.setBackground(Color.WHITE);
		contraPanel.setBounds(68, 197, 339, 37);
		loginPanel.add(contraPanel);
		contraPanel.setLayout(null);
		
		contraIcono = new JLabel("");
		contraIcono.setBounds(0, 0, 55, 37);
		contraIcono.setIcon(new ImageIcon(Login.class.getResource("/imagenes/iconoCandado.jpg")));
		icon = new ImageIcon(Login.class.getResource("/imagenes/iconoCandado.jpg"));
		img = icon.getImage().getScaledInstance(contraIcono.getWidth(), contraIcono.getHeight(), Image.SCALE_SMOOTH);
		contraIcono.setIcon(new ImageIcon(img));
		contraPanel.add(contraIcono);
		
		contraField = new JTextField();
		contraField.setBorder(null);
		contraField.setBounds(59, 0, 268, 24);
		contraPanel.add(contraField);
		contraField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(59, 28, 268, 2);
		contraPanel.add(separator);
		
		loginF = new JPanel();
		loginF.setBackground(new Color(0, 191, 255));
		loginF.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginF.setBounds(68, 302, 339, 51);
		loginPanel.add(loginF);
		
		lblNewLabel_3 = new JLabel("Iniciar Sesion");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 28));
		loginF.add(lblNewLabel_3);
		
	}
}
