package visual;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal2 extends JFrame {

	private JPanel contentPane;
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
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(885, 0, 39, 26);
		panel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(0, 13, 149, 518);
		fondo.add(MenuPanel);
		
		actualizarHora();
		
		Timer timer = new Timer(1000, e -> actualizarHora());
		timer.setInitialDelay(0);
		timer.start();
		
	}
	
	private void actualizarHora() {
	}
}
