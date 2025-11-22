package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Horario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HorarioShow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Horario horario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HorarioShow dialog = new HorarioShow(new Horario());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HorarioShow(Horario horario) {
		this.horario = horario;
		setUndecorated(true);
		setBounds(100, 100, 447, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(102, 0, 204));
		panel.setBounds(0, 0, 450, 25);
		contentPanel.add(panel);
		
		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cerrarPanel.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cerrarPanel.setBackground(new Color(102, 0, 204));
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(102, 0, 204));
		cerrarPanel.setBounds(411, 0, 39, 26);
		panel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(75, 0, 130));
		separator.setBackground(new Color(138, 43, 226));
		separator.setBounds(-7, 57, 457, 2);
		contentPanel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Dia");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(26, 38, 56, 16);
		lblNewLabel.setForeground(new Color(102, 0, 204));
		contentPanel.add(lblNewLabel);
		
		JLabel lblHoraDeEntrada = new JLabel("Hora de entrada");
		lblHoraDeEntrada.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblHoraDeEntrada.setForeground(new Color(102, 0, 204));
		lblHoraDeEntrada.setBounds(121, 38, 168, 16);
		contentPanel.add(lblHoraDeEntrada);
		
		JLabel lblHoraDeSalida = new JLabel("Hora de salida");
		lblHoraDeSalida.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblHoraDeSalida.setBounds(299, 38, 151, 16);
		lblHoraDeSalida.setForeground(new Color(102, 0, 204));
		contentPanel.add(lblHoraDeSalida);
		
		JLabel lblLunes = new JLabel("Lunes");
		lblLunes.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLunes.setBounds(10, 72, 56, 16);
		contentPanel.add(lblLunes);
		
		JLabel lblMartes = new JLabel("Martes");
		lblMartes.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblMartes.setBounds(10, 110, 72, 16);
		contentPanel.add(lblMartes);
		
		JLabel lblMiercoles = new JLabel("Miercoles");
		lblMiercoles.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblMiercoles.setBounds(10, 149, 84, 16);
		contentPanel.add(lblMiercoles);
		
		JLabel lblJueves = new JLabel("Jueves");
		lblJueves.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblJueves.setBounds(10, 188, 72, 16);
		contentPanel.add(lblJueves);
		
		JLabel lblViernes = new JLabel("Viernes");
		lblViernes.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblViernes.setBounds(10, 230, 72, 16);
		contentPanel.add(lblViernes);
		
		JLabel lblSabado = new JLabel("Sabado");
		lblSabado.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblSabado.setBounds(10, 272, 72, 16);
		contentPanel.add(lblSabado);
		
		JLabel lblDomingo = new JLabel("Domingo");
		lblDomingo.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblDomingo.setBounds(10, 303, 101, 29);
		contentPanel.add(lblDomingo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(75, 0, 130));
		separator_1.setBackground(new Color(138, 43, 226));
		separator_1.setBounds(-7, 95, 457, 2);
		contentPanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(75, 0, 130));
		separator_2.setBackground(new Color(138, 43, 226));
		separator_2.setBounds(0, 134, 457, 2);
		contentPanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(75, 0, 130));
		separator_3.setBackground(new Color(138, 43, 226));
		separator_3.setBounds(0, 173, 457, 2);
		contentPanel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(75, 0, 130));
		separator_4.setBackground(new Color(138, 43, 226));
		separator_4.setBounds(0, 217, 457, 2);
		contentPanel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(75, 0, 130));
		separator_5.setBackground(new Color(138, 43, 226));
		separator_5.setBounds(-7, 257, 457, 2);
		contentPanel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(new Color(75, 0, 130));
		separator_6.setBackground(new Color(138, 43, 226));
		separator_6.setBounds(0, 301, 457, 2);
		contentPanel.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setForeground(new Color(75, 0, 130));
		separator_7.setBackground(new Color(138, 43, 226));
		separator_7.setBounds(106, 57, 5, 288);
		contentPanel.add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setForeground(new Color(75, 0, 130));
		separator_8.setBackground(new Color(138, 43, 226));
		separator_8.setBounds(279, 57, 8, 288);
		contentPanel.add(separator_8);
		
		JLabel lblLibre = new JLabel("Libre");
		lblLibre.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLibre.setBounds(157, 72, 56, 16);
		contentPanel.add(lblLibre);
		
		JLabel label_1 = new JLabel("Libre");
		label_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_1.setBounds(323, 72, 56, 16);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Libre");
		label_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_2.setBounds(157, 110, 56, 16);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Libre");
		label_3.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_3.setBounds(323, 113, 56, 16);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Libre");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_4.setBounds(157, 152, 56, 16);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Libre");
		label_5.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_5.setBounds(323, 152, 56, 16);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Libre");
		label_6.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_6.setBounds(157, 191, 56, 16);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Libre");
		label_7.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_7.setBounds(323, 191, 56, 16);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("Libre");
		label_8.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_8.setBounds(157, 233, 56, 16);
		contentPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Libre");
		label_9.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_9.setBounds(323, 233, 56, 16);
		contentPanel.add(label_9);
		
		JLabel label_10 = new JLabel("Libre");
		label_10.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_10.setBounds(157, 275, 56, 16);
		contentPanel.add(label_10);
		
		JLabel label_11 = new JLabel("Libre");
		label_11.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_11.setBounds(323, 275, 56, 16);
		contentPanel.add(label_11);
		
		JLabel label_12 = new JLabel("Libre");
		label_12.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_12.setBounds(157, 312, 56, 16);
		contentPanel.add(label_12);
		
		JLabel label_13 = new JLabel("Libre");
		label_13.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_13.setBounds(323, 312, 56, 16);
		contentPanel.add(label_13);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setForeground(new Color(75, 0, 130));
		separator_9.setBackground(new Color(138, 43, 226));
		separator_9.setBounds(0, 57, 8, 288);
		contentPanel.add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setOrientation(SwingConstants.VERTICAL);
		separator_10.setForeground(new Color(75, 0, 130));
		separator_10.setBackground(new Color(138, 43, 226));
		separator_10.setBounds(445, 57, 8, 288);
		contentPanel.add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setForeground(new Color(75, 0, 130));
		separator_11.setBackground(new Color(138, 43, 226));
		separator_11.setBounds(-7, 343, 457, 2);
		contentPanel.add(separator_11);
	}
	
	private void actualizarHorario() {
		
	}

}
