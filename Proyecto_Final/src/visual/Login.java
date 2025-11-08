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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JPanel loginPanel;
	private JLabel lblLogo;
	private JPanel usuarioPanel;
	private JPanel contraPanel;
	private JLabel usuarioIcono;
	private JLabel usuarioIcon;
	private JLabel contraIcono;
	private JTextField usuarioField;
	private JSeparator separator_1;
	private JPanel loginF;
	private JLabel lblNewLabel_3;
	private JPasswordField passwordField;
	private JCheckBox contraCheck;
	private char mostrar = (char)0;
	private char ocultar;
	private boolean borradoUsuario = false;
	private boolean borradoContra = false;
	private JLabel lblNewLabel_5;

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
		loginPanel.setBounds(444, 103, 467, 402);
		fondo.add(loginPanel);
		loginPanel.setLayout(null);

		usuarioPanel = new JPanel();
		usuarioPanel.setBackground(new Color(255, 255, 255));
		usuarioPanel.setBounds(68, 100, 339, 37);
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

		usuarioField = new JTextField();
		usuarioField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(!borradoUsuario) {
					if(e.getKeyCode()== KeyEvent.VK_BACK_SPACE || e.getKeyCode()== KeyEvent.VK_DELETE) {
						e.consume();
					}else {
						usuarioField.setText("");
						usuarioField.setForeground(Color.black);
						borradoUsuario = true;
					}
				}

			}
		});
		usuarioField.setForeground(Color.LIGHT_GRAY);
		usuarioField.setFont(new Font("Verdana", Font.PLAIN, 16));
		usuarioField.setText("EX-00");
		usuarioField.setColumns(10);
		usuarioField.setBorder(null);
		usuarioField.setBounds(59, 6, 268, 24);
		usuarioPanel.add(usuarioField);

		separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(59, 30, 268, 2);
		usuarioPanel.add(separator_1);

		contraPanel = new JPanel();
		contraPanel.setBackground(Color.WHITE);
		contraPanel.setBounds(68, 229, 339, 37);
		loginPanel.add(contraPanel);
		contraPanel.setLayout(null);

		contraIcono = new JLabel("");
		contraIcono.setBounds(0, 0, 55, 37);
		contraIcono.setIcon(new ImageIcon(Login.class.getResource("/imagenes/iconoCandado.jpg")));
		icon = new ImageIcon(Login.class.getResource("/imagenes/iconoCandado.jpg"));
		img = icon.getImage().getScaledInstance(contraIcono.getWidth(), contraIcono.getHeight(), Image.SCALE_SMOOTH);
		contraIcono.setIcon(new ImageIcon(img));
		contraPanel.add(contraIcono);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(59, 30, 268, 24);
		contraPanel.add(separator);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(!borradoContra) {
					if(e.getKeyCode()== KeyEvent.VK_BACK_SPACE || e.getKeyCode()== KeyEvent.VK_DELETE) {
						e.consume();
					}else {
						passwordField.setText("");
						passwordField.setForeground(Color.black);
						borradoContra = true;
					}
				}
				
			}
		});
		ocultar = passwordField.getEchoChar();
		passwordField.setText("ejemplo123");
		passwordField.setForeground(Color.GRAY);
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 16));
		passwordField.setBounds(59, 6, 268, 24);
		passwordField.setBorder(null);
		contraPanel.add(passwordField);

		loginF = new JPanel();
		loginF.setBackground(new Color(138, 43, 226));
		loginF.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginF.setBounds(68, 331, 339, 51);
		loginPanel.add(loginF);

		lblNewLabel_3 = new JLabel("Iniciar Sesion");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 28));
		loginF.add(lblNewLabel_3);

		contraCheck = new JCheckBox("Mostra contrase\u00F1a");
		contraCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(contraCheck.isSelected()) {
					passwordField.setEchoChar(mostrar);
					icon = new ImageIcon(getClass().getResource("/imagenes/iconoCandadoAbierto.png"));
					img = icon.getImage().getScaledInstance(contraIcono.getWidth(), contraIcono.getHeight(), Image.SCALE_SMOOTH);
					contraIcono.setIcon(new ImageIcon(img));
					contraCheck.setText("Ocultar contraseña");
				}else {
					passwordField.setEchoChar(ocultar);
					icon = new ImageIcon(getClass().getResource("/imagenes/iconoCandado.jpg"));
					img = icon.getImage().getScaledInstance(contraIcono.getWidth(), contraIcono.getHeight(), Image.SCALE_SMOOTH);
					contraIcono.setIcon(new ImageIcon(img));
					contraCheck.setText("Mostra contraseña");
				}
			}
		});
		contraCheck.setFont(new Font("Verdana", Font.PLAIN, 13));
		contraCheck.setBorder(null);
		contraCheck.setBackground(new Color(240, 248, 255));
		contraCheck.setBounds(78, 279, 167, 25);
		loginPanel.add(contraCheck);

		JLabel lblNewLabel_4 = new JLabel("Contrase\u00F1a");
		lblNewLabel_4.setForeground(new Color(106, 90, 205));
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 194, 189, 28);
		loginPanel.add(lblNewLabel_4);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(106, 90, 205));
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 24));
		lblUsuario.setBounds(179, 59, 116, 28);
		loginPanel.add(lblUsuario);
		
		lblNewLabel_5 = new JLabel("LOGIN");
		lblNewLabel_5.setForeground(new Color(147, 112, 219));
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 38));
		lblNewLabel_5.setBounds(617, 52, 145, 44);
		fondo.add(lblNewLabel_5);

	}
}
