package visual;

import java.awt.BorderLayout;
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
import java.awt.event.MouseMotionAdapter;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HorarioShow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	
	private JLabel lunesI;
	private JLabel martesI;
	private JLabel lunesF;
	private JLabel martesF;
	private JLabel miercolesI;
	private JLabel miercolesF;
	private JLabel juevesI;
	private JLabel juevesF;
	private JLabel viernesI;
	private JLabel viernesF;
	private JLabel sabadoI;
	private JLabel sabadoF;
	private JLabel domingoI;
	private JLabel domingoF;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;

	private Horario horario;
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:mm");
	private JPanel barraPanel;

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
		setLocation(1320, 450);
		setBounds(100, 100, 447, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

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
		barraPanel.setLayout(null);
		barraPanel.setBackground(new Color(102, 0, 204));
		barraPanel.setBounds(0, 0, 450, 25);
		contentPanel.add(barraPanel);

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
		barraPanel.add(cerrarPanel);

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

		lunesI = new JLabel("Libre");
		lunesI.setFont(new Font("Verdana", Font.PLAIN, 18));
		lunesI.setBounds(157, 72, 56, 16);
		contentPanel.add(lunesI);

		lunesF = new JLabel("Libre");
		lunesF.setFont(new Font("Verdana", Font.PLAIN, 18));
		lunesF.setBounds(323, 72, 56, 16);
		contentPanel.add(lunesF);

		martesI = new JLabel("Libre");
		martesI.setFont(new Font("Verdana", Font.PLAIN, 18));
		martesI.setBounds(157, 110, 56, 16);
		contentPanel.add(martesI);

		martesF = new JLabel("Libre");
		martesF.setFont(new Font("Verdana", Font.PLAIN, 18));
		martesF.setBounds(323, 113, 56, 16);
		contentPanel.add(martesF);

		miercolesI = new JLabel("Libre");
		miercolesI.setFont(new Font("Verdana", Font.PLAIN, 18));
		miercolesI.setBounds(157, 152, 56, 16);
		contentPanel.add(miercolesI);

		miercolesF = new JLabel("Libre");
		miercolesF.setFont(new Font("Verdana", Font.PLAIN, 18));
		miercolesF.setBounds(323, 152, 56, 16);
		contentPanel.add(miercolesF);

		juevesI = new JLabel("Libre");
		juevesI.setFont(new Font("Verdana", Font.PLAIN, 18));
		juevesI.setBounds(157, 191, 56, 16);
		contentPanel.add(juevesI);

		juevesF = new JLabel("Libre");
		juevesF.setFont(new Font("Verdana", Font.PLAIN, 18));
		juevesF.setBounds(323, 191, 56, 16);
		contentPanel.add(juevesF);

		viernesI = new JLabel("Libre");
		viernesI.setFont(new Font("Verdana", Font.PLAIN, 18));
		viernesI.setBounds(157, 233, 56, 16);
		contentPanel.add(viernesI);

		viernesF = new JLabel("Libre");
		viernesF.setFont(new Font("Verdana", Font.PLAIN, 18));
		viernesF.setBounds(323, 233, 56, 16);
		contentPanel.add(viernesF);

		sabadoI = new JLabel("Libre");
		sabadoI.setFont(new Font("Verdana", Font.PLAIN, 18));
		sabadoI.setBounds(157, 275, 56, 16);
		contentPanel.add(sabadoI);

		sabadoF = new JLabel("Libre");
		sabadoF.setFont(new Font("Verdana", Font.PLAIN, 18));
		sabadoF.setBounds(323, 275, 56, 16);
		contentPanel.add(sabadoF);

		domingoI = new JLabel("Libre");
		domingoI.setFont(new Font("Verdana", Font.PLAIN, 18));
		domingoI.setBounds(157, 312, 56, 16);
		contentPanel.add(domingoI);

		domingoF = new JLabel("Libre");
		domingoF.setFont(new Font("Verdana", Font.PLAIN, 18));
		domingoF.setBounds(323, 312, 56, 16);
		contentPanel.add(domingoF);

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
		
		actualizarHorario();
	}

	private void actualizarHorario() {
		LocalTime[] rango = new LocalTime[2];


		for (DayOfWeek d : horario.getHorario().keySet()) {
			rango = horario.horarioByDia(d);
			switch (d) {
			
			case MONDAY :
				lunesI.setText(rango[0].format(fmt));
				lunesF.setText(rango[1].format(fmt));
				break;
				
			case TUESDAY:
				martesI.setText(rango[0].format(fmt));
				martesF.setText(rango[1].format(fmt));
				break;
				
			case WEDNESDAY:
				miercolesI.setText(rango[0].format(fmt));
				miercolesF.setText(rango[1].format(fmt));
				break;
				
			case THURSDAY:
				juevesI.setText(rango[0].format(fmt));
				juevesF.setText(rango[1].format(fmt));
				break;

			case FRIDAY:
				viernesI.setText(rango[0].format(fmt));
				viernesF.setText(rango[1].format(fmt));
				break;
			
			case SATURDAY:
				sabadoI.setText(rango[0].format(fmt));
				sabadoF.setText(rango[1].format(fmt));
				break;
			case SUNDAY:
				domingoI.setText(rango[0].format(fmt));
				domingoF.setText(rango[1].format(fmt));
				
			}
		}
	}

}
