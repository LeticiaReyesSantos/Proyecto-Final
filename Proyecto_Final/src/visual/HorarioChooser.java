package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Horario;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseMotionAdapter;

public class HorarioChooser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Horario horario = new Horario();
	Set<DayOfWeek> dias = new HashSet<>();
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JComboBox finalBox;
	private JComboBox inicioBox;
	private JPanel guardarPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HorarioChooser dialog = new HorarioChooser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HorarioChooser() {
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, 530, 323);
		contentPanel.add(fondo);
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
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(102, 0, 204));
		barPanel.setBounds(0, 0, 450, 25);
		fondo.add(barPanel);
		
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
		cerrarPanel.setBounds(411, 0, 39, 26);
		cerrarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		barPanel.add(cerrarPanel);
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(102, 0, 204));
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		guardarPanel = new JPanel();
		guardarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String inicio = (String)inicioBox.getSelectedItem();
				String fin = (String)finalBox.getSelectedItem();
				LocalTime horaIni = LocalTime.parse(inicio);
				LocalTime horaFin = LocalTime.parse(fin);
				
				for (DayOfWeek d : dias) 
					horario.addDia(d, horaIni, horaFin);
				
				
			}
		});
		guardarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		guardarPanel.setBackground(new Color(138, 43, 226));
		guardarPanel.setBounds(256, 259, 85, 28);
		fondo.add(guardarPanel);
		
		JLabel lblGuardar = new JLabel("Guardar");
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.setFont(new Font("Verdana", Font.PLAIN, 14));
		guardarPanel.add(lblGuardar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBackground(new Color(138, 43, 226));
		panel_3.setBounds(353, 259, 85, 28);
		fondo.add(panel_3);
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setForeground(Color.WHITE);
		lblCancelar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_3.add(lblCancelar);
		
		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_4.setBackground(new Color(138, 43, 226));
		panel_4.setBounds(152, 259, 97, 28);
		fondo.add(panel_4);
		
		JLabel lblSeleccionar = new JLabel("Crear horario");
		lblSeleccionar.setForeground(Color.WHITE);
		lblSeleccionar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_4.add(lblSeleccionar);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_5.setBackground(new Color(138, 43, 226));
		panel_5.setBounds(10, 259, 130, 28);
		fondo.add(panel_5);
		
		JLabel lblModeloEstandar = new JLabel("Modelo estandar");
		lblModeloEstandar.setForeground(Color.WHITE);
		lblModeloEstandar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_5.add(lblModeloEstandar);
		
		inicioBox = new JComboBox();
		cargarHorasInicio();
		inicioBox.setSelectedIndex(0);
		
		inicioBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarHorasFinal();
			}
		});
		
		
		inicioBox.setBackground(new Color(240, 248, 255));
		inicioBox.setBounds(25, 177, 167, 22);
		
		
		fondo.add(inicioBox);
		
		finalBox = new JComboBox();
		
		finalBox.setBackground(new Color(240, 248, 255));
		finalBox.setBounds(256, 177, 173, 22);
		fondo.add(finalBox);
		
		JLabel lblHoraDeEntrada = new JLabel("Hora de entrada");
		lblHoraDeEntrada.setFont(new Font("Verdana", Font.BOLD, 14));
		lblHoraDeEntrada.setBounds(25, 148, 167, 16);
		fondo.add(lblHoraDeEntrada);
		
		cargarHorasFinal();
		
		JLabel lblHoraDeSalida = new JLabel("Hora de salida");
		lblHoraDeSalida.setFont(new Font("Verdana", Font.BOLD, 14));
		lblHoraDeSalida.setBounds(256, 149, 167, 16);
		fondo.add(lblHoraDeSalida);
		
		JLabel lblSeleccioneLosDias = new JLabel("Seleccione los dias");
		lblSeleccioneLosDias.setForeground(new Color(102, 0, 204));
		lblSeleccioneLosDias.setFont(new Font("Verdana", Font.BOLD, 28));
		lblSeleccioneLosDias.setBounds(76, 42, 362, 25);
		fondo.add(lblSeleccioneLosDias);
		
		JCheckBox lunes = new JCheckBox("L");
		lunes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lunes.isSelected()) 
					dias.add(DayOfWeek.MONDAY);
				else
					dias.remove(DayOfWeek.MONDAY);
			}
		});
		lunes.setBackground(new Color(138, 43, 226));
		lunes.setBounds(50, 94, 35, 25);
		fondo.add(lunes);
		
		JCheckBox martes = new JCheckBox("M");
		martes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(martes.isSelected()) 
					dias.add(DayOfWeek.TUESDAY);
				else
					dias.remove(DayOfWeek.TUESDAY);
			}
		});
		martes.setBackground(new Color(138, 43, 226));
		martes.setBounds(100, 94, 39, 25);
		fondo.add(martes);
		
		JCheckBox miercoles = new JCheckBox("W");
		miercoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(miercoles.isSelected())
					dias.add(DayOfWeek.WEDNESDAY);
				else
					dias.remove(DayOfWeek.WEDNESDAY);
			}
		});
		miercoles.setBackground(new Color(138, 43, 226));
		miercoles.setBounds(150, 94, 40, 25);
		fondo.add(miercoles);
		
		JCheckBox jueves = new JCheckBox("J");
		jueves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jueves.isSelected())
					dias.add(DayOfWeek.THURSDAY);
				else
					dias.remove(DayOfWeek.THURSDAY);
			}
		});
		jueves.setBackground(new Color(138, 43, 226));
		jueves.setBounds(200, 94, 35, 25);
		fondo.add(jueves);
		
		JCheckBox viernes = new JCheckBox("V");
		viernes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(viernes.isSelected())
					dias.add(DayOfWeek.FRIDAY);
				else
					dias.remove(DayOfWeek.FRIDAY);
			}
		});
		viernes.setBackground(new Color(138, 43, 226));
		viernes.setBounds(250, 94, 39, 25);
		fondo.add(viernes);
		
		JCheckBox sabado = new JCheckBox("S");
		sabado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sabado.isSelected())
					dias.add(DayOfWeek.SATURDAY);
				else
					dias.remove(DayOfWeek.SATURDAY);
			}
		});
		sabado.setBackground(new Color(138, 43, 226));
		sabado.setBounds(300, 94, 38, 25);
		fondo.add(sabado);
		
		JCheckBox domingo = new JCheckBox("D");
		domingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(domingo.isSelected())
					dias.add(DayOfWeek.SUNDAY);
				else
					dias.remove(DayOfWeek.SUNDAY);
			}
		});
		domingo.setBackground(new Color(138, 43, 226));
		domingo.setBounds(350, 94, 38, 25);
		fondo.add(domingo);
	}
	
	
	private void cargarHorasInicio() {
		
		LocalTime hora = LocalTime.of(7, 0);
	    LocalTime fin = LocalTime.of(14, 0);

	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:mm");

	    while (hora.isBefore(fin)) {
	        inicioBox.addItem(hora.format(fmt));
	        hora = hora.plusHours(1);
	    }
	}
	
	private void cargarHorasFinal() {
		finalBox.removeAllItems();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:mm");
		
		String horaInicio = (String) inicioBox.getSelectedItem();
		
		LocalTime hora = LocalTime.parse(horaInicio, fmt);
		hora = hora.plusHours(8);
	    LocalTime fin = LocalTime.of(23, 0);

	    

	    while (hora.isBefore(fin)) {
	        finalBox.addItem(hora.format(fmt));
	        hora = hora.plusHours(1);
	    }
	} 
	
	public Horario createHorario () {
		return horario;
	}

}
