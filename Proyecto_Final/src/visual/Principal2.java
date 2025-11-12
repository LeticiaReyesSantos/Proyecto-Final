package visual;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal2 extends JFrame {

	private JPanel contentPane;
	private JLabel fechaLabel;
	private JLabel horaLabel;
	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 531);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, 924, 531);
		contentPane.add(fondo);
		fondo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 204));
		panel.setBounds(0, 0, 924, 25);
		fondo.add(panel);
		panel.setLayout(null);
		
		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(885, 0, 39, 26);
		panel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(102, 0, 153));
		menuPanel.setBounds(0, 25, 141, 506);
		fondo.add(menuPanel);
		menuPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 0, 102));
		panel_1.setBounds(0, 0, 141, 49);
		menuPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MediCare \r\n");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(48, 5, 91, 23);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel_1.add(lblNewLabel);
		
		JLabel lblClinica = new JLabel("Clinica");
		lblClinica.setForeground(new Color(255, 255, 255));
		lblClinica.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblClinica.setBounds(48, 24, 91, 23);
		panel_1.add(lblClinica);
		
		JPanel fondo2 = new JPanel();
		fondo2.setBackground(new Color(51, 0, 102));
		fondo2.setBounds(153, 53, 741, 478);
		fondo.add(fondo2);
		fondo2.setLayout(null);
		
		JPanel medicoPanel = new JPanel();
		medicoPanel.setBackground(new Color(240, 248, 255));
		medicoPanel.setBounds(23, 41, 706, 400);
		fondo2.add(medicoPanel);
		medicoPanel.setLayout(null);
		
		JPanel bievenidaPanel = new JPanel();
		bievenidaPanel.setBackground(new Color(204, 204, 255));
		bievenidaPanel.setBounds(92, 13, 548, 92);
		medicoPanel.add(bievenidaPanel);
		bievenidaPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenido/a");
		lblNewLabel_1.setForeground(new Color(102, 0, 153));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel_1.setBounds(12, 13, 204, 30);
		bievenidaPanel.add(lblNewLabel_1);
		
		JPanel borderBienvenidaPanel = new JPanel();
		borderBienvenidaPanel.setLayout(null);
		borderBienvenidaPanel.setBackground(new Color(123, 104, 238));
		borderBienvenidaPanel.setBounds(88, 9, 556, 100);
		medicoPanel.add(borderBienvenidaPanel);
		
		JPanel AdminPanel = new JPanel();
		AdminPanel.setBounds(23, 41, 706, 400);
		fondo2.add(AdminPanel);
		
		JPanel pacientePanel = new JPanel();
		pacientePanel.setBounds(23, 41, 706, 400);
		fondo2.add(pacientePanel);
		
		JPanel fechaPanel = new JPanel();
		fechaPanel.setBackground(new Color(153, 0, 255));
		fechaPanel.setBounds(535, 2, 104, 26);
		fondo2.add(fechaPanel);
		

		fechaLabel = new JLabel(LocalDate.now().toString());
		fechaLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		fechaLabel.setForeground(new Color(255, 255, 255));
		fechaPanel.add(fechaLabel);
		
		JPanel horaPanel = new JPanel();
		horaPanel.setBackground(new Color(153, 0, 255));
		horaPanel.setBounds(647, 2, 62, 26);
		fondo2.add(horaPanel);
		
		horaLabel = new JLabel();
		horaLabel.setForeground(new Color(255, 255, 255));
		horaLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		horaPanel.add(horaLabel);
		
		actualizarHora();
		
		Timer timer = new Timer(1000, e -> actualizarHora());
		timer.setInitialDelay(0);
		timer.start();
		
	}
	
	private void actualizarHora() {
		
		horaLabel.setText(LocalTime.now().format(formatoHora).toString());
	}
}
