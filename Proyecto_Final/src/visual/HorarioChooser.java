package visual;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Horario;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseMotionAdapter;

public class HorarioChooser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Horario horario = new Horario();
	private Set<DayOfWeek> dias = new HashSet<>();
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JComboBox finalBox;
	private JComboBox inicioBox;
	private JPanel addPanel;
	private JPanel cancelPanel;
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:mm");
	private JCheckBox lunes;
	private JCheckBox martes;
	private JCheckBox miercoles;
	private JCheckBox jueves;
	private JCheckBox viernes;
	private JCheckBox sabado;
	private JCheckBox domingo;
	private JPanel estandarPanel;
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
		fondo.setBackground(new Color(240, 248, 255));
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
		
		addPanel = new JPanel();
		addPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!dias.isEmpty()) {
					String inicio = (String)inicioBox.getSelectedItem();
					String fin = (String)finalBox.getSelectedItem();
					LocalTime horaIni = LocalTime.parse(inicio, fmt);
					LocalTime horaFin = LocalTime.parse(fin, fmt);
				
					for (DayOfWeek d : dias) 
						horario.addDia(d, horaIni, horaFin);
				
					JOptionPane.showMessageDialog(null, "Horario agregado con exito");
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				addPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addPanel.setBackground(new Color(138, 43, 226));
			}
		});
		addPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		addPanel.setBackground(new Color(138, 43, 226));
		addPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addPanel.setBounds(152, 259, 85, 28);
		fondo.add(addPanel);
		
		JLabel lblGuardar = new JLabel("A\u00F1adir");
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.setFont(new Font("Verdana", Font.PLAIN, 14));
		addPanel.add(lblGuardar);
		
		cancelPanel = new JPanel();
		cancelPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cancelPanel.setBackground(new Color(138, 43, 226));
			}
		});
		cancelPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cancelPanel.setBackground(new Color(138, 43, 226));
		cancelPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelPanel.setBounds(353, 259, 85, 28);
		fondo.add(cancelPanel);
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setForeground(Color.WHITE);
		lblCancelar.setFont(new Font("Verdana", Font.PLAIN, 14));
		cancelPanel.add(lblCancelar);
		
		guardarPanel = new JPanel();
		guardarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!horario.isEmpty())
					dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				guardarPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				guardarPanel.setBackground(new Color(138, 43, 226));
			}
		});
		guardarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		guardarPanel.setBackground(new Color(138, 43, 226));
		guardarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		guardarPanel.setBounds(249, 259, 97, 28);
		fondo.add(guardarPanel);
		
		JLabel lblSeleccionar = new JLabel("Guardar");
		lblSeleccionar.setForeground(Color.WHITE);
		lblSeleccionar.setFont(new Font("Verdana", Font.PLAIN, 14));
		guardarPanel.add(lblSeleccionar);
		
		estandarPanel = new JPanel();
		estandarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				estandarPanel.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				estandarPanel.setBackground(new Color(138, 43, 226));
			}
		});
		estandarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		estandarPanel.setBackground(new Color(138, 43, 226));
		estandarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		estandarPanel.setBounds(10, 259, 130, 28);
		fondo.add(estandarPanel);
		
		JLabel lblModeloEstandar = new JLabel("Modelo estandar");
		lblModeloEstandar.setForeground(Color.WHITE);
		lblModeloEstandar.setFont(new Font("Verdana", Font.PLAIN, 14));
		estandarPanel.add(lblModeloEstandar);
		
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
		
		lunes = new JCheckBox("L");
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
		
		martes = new JCheckBox("M");
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
		
		miercoles = new JCheckBox("W");
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
		
		jueves = new JCheckBox("J");
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
		
		viernes = new JCheckBox("V");
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
		
		sabado = new JCheckBox("S");
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
		
		domingo = new JCheckBox("D");
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
		
		
		String horaInicio = (String) inicioBox.getSelectedItem();
		
		LocalTime hora = LocalTime.parse(horaInicio, fmt);
		hora = hora.plusHours(8);
	    LocalTime fin = LocalTime.of(23, 0);

	    

	    while (hora.isBefore(fin)) {
	        finalBox.addItem(hora.format(fmt));
	        hora = hora.plusHours(1);
	    }
	} 
	
	public Horario getHorario () {
		if(!horario.isEmpty())
			return horario;
		else
			return null;
	}
	

}
