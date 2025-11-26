package visual;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import logico.User;
import logico.Clinica;
import logico.Persona;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Panel;
import java.awt.Label;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel cerrarPanel;
	private JPanel barPanel;
	private JPanel fondo;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Image img;
	private ImageIcon icon;
	private JPanel loginPanel;
	private JLabel lblLogo;
	private JPanel usuarioPanel;
	private JPanel contraPanel;
	private JLabel usuarioIcono;
	private JLabel usuarioIcon;
	private JLabel contraIcono;
	private JTextField usuarioField;
	private JSeparator separator1;
	private JPanel loginF;
	private JLabel lblNewLabel_3;
	private JPasswordField passwordField;
	private JCheckBox contraCheck;
	private char mostrar = (char)0;
	private char ocultar;
	private boolean borradoUsuario = false;
	private boolean borradoContra = false;
	private JSeparator separator2;
	private JLabel campoObligatorio;
	private JLabel campoObligatorio2;
	private JLabel lblIniciarSesion;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileOutputStream clinicaEscritura;
				ObjectOutputStream clinicaWritter;


				if(!Clinica.load()) {
					try {
						clinicaEscritura = new  FileOutputStream("clinica.dat");
						clinicaWritter = new ObjectOutputStream(clinicaEscritura);
						User aux = new User("Administrador", "Admin", "Admin");
						Persona person = new Persona("Ad-01","","Admin","",LocalDate.now(), 'N', "000000", "", "", aux);
						Clinica.getInstance().addPersona(person);
						clinicaWritter.writeObject(Clinica.getInstance());
						clinicaEscritura.close();
						clinicaWritter.close();

					}catch (FileNotFoundException e1) {
					}catch (IOException e1) {
						// TODO: handle exception
					}
				}


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

		barPanel = new JPanel();
		//barPanel.setBackground(new Color(25, 25, 112));
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
		
		lblNewLabel_2 = new JLabel("BIENVENIDO");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 40));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(43, 212, 385, 90);
		fondo.add(lblNewLabel_2);
		barPanel.setBounds(0, 0, 923, 25);
		fondo.add(barPanel);
		barPanel.setBackground(new Color(45, 51, 107));
		barPanel.setLayout(null);

		cerrarPanel = new JPanel();
		cerrarPanel.setBounds(883, 0, 39, 26);
		barPanel.add(cerrarPanel);
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
				cerrarPanel.setBackground(new Color(45, 51, 107));
			}
		});
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cerrarPanel.setForeground(Color.BLACK);

		JLabel lblNewLabel = new JLabel("X");
		cerrarPanel.add(lblNewLabel);


		icon = new ImageIcon(Login.class.getResource("/imagenes/principal.jpg"));

		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(240, 248, 255));
		loginPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginPanel.setBounds(441, 86, 467, 402);
		fondo.add(loginPanel);
		loginPanel.setLayout(null);

		campoObligatorio2 = new JLabel("*");
		campoObligatorio2.setVisible(false);
		campoObligatorio2.setFont(new Font("Tahoma", Font.BOLD, 13));
		campoObligatorio2.setBounds(389, 230, 55, 36);
		loginPanel.add(campoObligatorio2);
		campoObligatorio2.setForeground(Color.RED);

		campoObligatorio = new JLabel("*");
		campoObligatorio.setFont(new Font("Tahoma", Font.BOLD, 13));
		campoObligatorio.setBounds(384, 100, 39, 37);
		loginPanel.add(campoObligatorio);
		campoObligatorio.setVisible(false);
		campoObligatorio.setForeground(Color.RED);

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
		usuarioField.addActionListener(e -> {
			passwordField.requestFocusInWindow();
		});
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


				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					passwordField.requestFocusInWindow();
				}

			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(usuarioField.getText().trim().isEmpty()) {
					separator1.setForeground(Color.red);
					separator1.setBackground(Color.red);
					campoObligatorio.setVisible(true);
				}else {
					separator1.setForeground(Color.black);
					separator1.setBackground(Color.black);
					campoObligatorio.setVisible(false);
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

		separator1 = new JSeparator();
		separator1.setForeground(Color.DARK_GRAY);
		separator1.setBackground(Color.DARK_GRAY);
		separator1.setBounds(59, 30, 268, 2);
		usuarioPanel.add(separator1);

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

		separator2 = new JSeparator();
		separator2.setForeground(Color.DARK_GRAY);
		separator2.setBackground(Color.DARK_GRAY);
		separator2.setBounds(59, 30, 268, 24);
		contraPanel.add(separator2);

		passwordField = new JPasswordField();
		passwordField.addActionListener(e -> {
			if(Clinica.getInstance().confirmarLogin(usuarioField.getText(), passwordField.getText())) {
				Principal2 principal = new Principal2();
				dispose();
				principal.setVisible(true);

			}else {
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");
			}

		});
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


				if(e.getKeyCode() == KeyEvent.VK_UP) {
					usuarioField.requestFocusInWindow();
				}

			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(passwordField.getText().trim().isEmpty()) {
					separator2.setForeground(Color.red);
					separator2.setBackground(Color.red);
					campoObligatorio2.setVisible(true);
				}else {
					separator2.setForeground(Color.black);
					separator2.setBackground(Color.black);
					campoObligatorio2.setVisible(false);
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
		loginF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Clinica.getInstance().confirmarLogin(usuarioField.getText(), passwordField.getText())) {
					Principal2 principal = new Principal2();
					dispose();
					principal.setVisible(true);

				}else
					JOptionPane.showMessageDialog(null, "Usuario no encontrado");

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				loginF.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginF.setBackground(new Color(138, 43, 226));
			}
		});
		loginF.setBackground(new Color(45, 51, 107));
		loginF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginF.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginF.setBounds(68, 331, 339, 51);
		loginPanel.add(loginF);

		lblNewLabel_3 = new JLabel("Iniciar Sesion");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 27));
		loginF.add(lblNewLabel_3);

		contraCheck = new JCheckBox("Mostrar contrase\u00F1a");
		contraCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		contraCheck.setFont(new Font("Verdana", Font.PLAIN, 15));
		contraCheck.setBackground(new Color(240, 248, 255));
		contraCheck.setBounds(78, 279, 197, 25);
		loginPanel.add(contraCheck);

		JLabel lblNewLabel_4 = new JLabel("Contrase\u00F1a");
		lblNewLabel_4.setForeground(new Color(120, 134, 199));
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 23));
		lblNewLabel_4.setBounds(159, 197, 158, 28);
		loginPanel.add(lblNewLabel_4);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(120, 134, 199));
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 23));
		lblUsuario.setBounds(159, 68, 116, 28);
		loginPanel.add(lblUsuario);

		lblLogo = new JLabel("logo");
		lblLogo.setBounds(26, 24, 70, 16);
		loginPanel.add(lblLogo);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Verdana", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 16, 354, 502);
		fondo.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\Screenshots\\Screenshot 2025-11-25 212054.png"));
		
		lblIniciarSesion = new JLabel("Iniciar Sesion");
		lblIniciarSesion.setForeground(new Color(120, 134, 199));
		lblIniciarSesion.setFont(new Font("Verdana", Font.BOLD, 30));
		lblIniciarSesion.setBounds(560, 52, 238, 28);
		fondo.add(lblIniciarSesion);
		icon = new ImageIcon(Login.class.getResource("/imagenes/Princi.png"));
		

	}
}
