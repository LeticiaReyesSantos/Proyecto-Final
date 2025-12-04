package visual;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class SintomasSelection extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private ArrayList<String> sintomas = new ArrayList<>();
	private ArrayList<String> sintomasSelected = new ArrayList<>();
	private JTable table;
	private DefaultTableModel model;
	private JTextField buscarField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SintomasSelection dialog = new SintomasSelection(new ArrayList<String>());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SintomasSelection(ArrayList<String> s) {
		this.sintomasSelected = s;
		setUndecorated(true);
		setBounds(100, 100, 695, 448);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 41, 630, 350);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarSeleccionGlobal();
			}
		});
		table.setBackground(new Color(230, 230, 250));
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Verdana", Font.PLAIN, 13));
		table.setRowSelectionAllowed(false);
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Selecciona", "Sintomas"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				
				@Override
				public boolean isCellEditable(int row, int col) {
				    return col == 0;  
				}

			};
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel seleccionPanel = new JPanel();
		seleccionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!sintomasSelected.isEmpty()) {
					actualizarSeleccionGlobal();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningun sintoma");
				}
				
			}
		});
		seleccionPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		seleccionPanel.setBackground(new Color(169, 181, 223));
		seleccionPanel.setBounds(499, 407, 85, 28);
		contentPanel.add(seleccionPanel);
		
		JLabel lblAceptar = new JLabel("Seleccionar");
		lblAceptar.setForeground(new Color(0, 0, 0));
		lblAceptar.setFont(new Font("Verdana", Font.PLAIN, 14));
		seleccionPanel.add(lblAceptar);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				desSelectedAll();
				dispose();
			}
		});
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(new Color(169, 181, 223));
		panel_1.setBounds(596, 407, 85, 28);
		contentPanel.add(panel_1);
		
		JLabel lblVolver = new JLabel("Volver");
		lblVolver.setForeground(new Color(0, 0, 0));
		lblVolver.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_1.add(lblVolver);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		//Lista de sintomas
		sintomas.add("Abdomen distendido");
		sintomas.add("Adormecimiento");
		sintomas.add("Agitación");
		sintomas.add("Alergia");
		sintomas.add("Amnesia");
		sintomas.add("Anemia");
		sintomas.add("Ansiedad");
		sintomas.add("Ardor al orinar");
		sintomas.add("Ardor en los ojos");
		sintomas.add("Ardor en la garganta");
		sintomas.add("Ardor en la piel");
		sintomas.add("Arritmias");
		sintomas.add("Artritis");
		sintomas.add("Asfixia");
		sintomas.add("Aumento de apetito");
		sintomas.add("Aumento de sed");
		sintomas.add("Baja presión");
		sintomas.add("Boca seca");
		sintomas.add("Calambres");
		sintomas.add("Cambios de ánimo");
		sintomas.add("Cambios en la visión");
		sintomas.add("Cansancio");
		sintomas.add("Congestión nasal");
		sintomas.add("Confusión");
		sintomas.add("Constipación");
		sintomas.add("Convulsiones");
		sintomas.add("Cólicos");
		sintomas.add("Diarrea");
		sintomas.add("Dificultad para hablar");
		sintomas.add("Dificultad para caminar");
		sintomas.add("Dificultad para concentrarse");
		sintomas.add("Dificultad para escuchar");
		sintomas.add("Dificultad para respirar");
		sintomas.add("Dificultad para tragar");
		sintomas.add("Dolor abdominal");
		sintomas.add("Dolor articular");
		sintomas.add("Dolor de cabeza");
		sintomas.add("Dolor de cuello");
		sintomas.add("Dolor de espalda");
		sintomas.add("Dolor de estómago");
		sintomas.add("Dolor de garganta");
		sintomas.add("Dolor en el pecho");
		sintomas.add("Dolor lumbar");
		sintomas.add("Dolor muscular");
		sintomas.add("Dolor pélvico");
		sintomas.add("Dolor ocular");
		sintomas.add("Escalofríos");
		sintomas.add("Erupciones en la piel");
		sintomas.add("Estreñimiento");
		sintomas.add("Falta de aire");
		sintomas.add("Fatiga");
		sintomas.add("Fiebre");
		sintomas.add("Fiebre alta");
		sintomas.add("Flatulencias");
		sintomas.add("Fracturas");
		sintomas.add("Hinchazón");
		sintomas.add("Hormigueo");
		sintomas.add("Inflamación");
		sintomas.add("Infección urinaria");
		sintomas.add("Insomnio");
		sintomas.add("Irritación en la piel");
		sintomas.add("Lagrimeo");
		sintomas.add("Letargo");
		sintomas.add("Mareos");
		sintomas.add("Memoria débil");
		sintomas.add("Molestias al orinar");
		sintomas.add("Náuseas");
		sintomas.add("Nerviosismo");
		sintomas.add("Obstrucción nasal");
		sintomas.add("Palpitaciones");
		sintomas.add("Pérdida de apetito");
		sintomas.add("Pérdida de conocimiento");
		sintomas.add("Pérdida de equilibrio");
		sintomas.add("Pérdida de memoria");
		sintomas.add("Pérdida de sensibilidad");
		sintomas.add("Picazón");
		sintomas.add("Presión alta");
		sintomas.add("Presión baja");
		sintomas.add("Problemas de coordinación");
		sintomas.add("Problemas de sueño");
		sintomas.add("Resfriado");
		sintomas.add("Rigidez muscular");
		sintomas.add("Ronquera");
		sintomas.add("Sangrado nasal");
		sintomas.add("Sarpullido");
		sintomas.add("Secreción nasal");
		sintomas.add("Sensibilidad a la luz");
		sintomas.add("Sensibilidad al sonido");
		sintomas.add("Sensación de desmayo");
		sintomas.add("Sequedad ocular");
		sintomas.add("Somnolencia");
		sintomas.add("Sudoración excesiva");
		sintomas.add("Temblores");
		sintomas.add("Tensión muscular");
		sintomas.add("Tos");
		sintomas.add("Tos con flema");
		sintomas.add("Tos seca");
		sintomas.add("Traqueítis");
		sintomas.add("Trastornos del equilibrio");
		sintomas.add("Úlceras bucales");
		sintomas.add("Vértigo");
		sintomas.add("Vómitos");
		sintomas.add("Zumbido en los oídos");
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel barPanel = new JPanel();
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
		
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(45, 51, 107));
		barPanel.setBounds(0, 0, 695, 25);
		contentPanel.add(barPanel);
		
		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				desSelectedAll();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cerrarPanel.setBackground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cerrarPanel.setBackground(new Color(45, 51, 107));
			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(656, 0, 39, 26);
		barPanel.add(cerrarPanel);
		
		JLabel label = new JLabel("X");
		cerrarPanel.add(label);
		
		JSeparator border1 = new JSeparator();
		border1.setOrientation(SwingConstants.VERTICAL);
		border1.setForeground(new Color(0, 0, 128));
		border1.setBackground(new Color(0, 0, 128));
		border1.setBounds(0, 25, 20, 423);
		contentPanel.add(border1);
		
		JSeparator border2 = new JSeparator();
		border2.setOrientation(SwingConstants.VERTICAL);
		border2.setForeground(new Color(0, 0, 128));
		border2.setBackground(new Color(0, 0, 128));
		border2.setBounds(693, 13, 20, 435);
		contentPanel.add(border2);
		
		JSeparator border3 = new JSeparator();
		border3.setForeground(new Color(0, 0, 128));
		border3.setBackground(new Color(0, 0, 128));
		border3.setBounds(0, 446, 695, 8);
		contentPanel.add(border3);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(45, 51, 107));
		separator.setBackground(new Color(45, 51, 107));
		separator.setBounds(40, 429, 295, 2);
		contentPanel.add(separator);
		
		buscarField = new JTextField();
		buscarField.addActionListener(e-> {
			if(buscarField.getText().isEmpty()) 
				cargarSintomas();
			else
				buscarSintomas();
		});
		buscarField.setForeground(Color.BLACK);
		buscarField.setColumns(10);
		buscarField.setBorder(null);
		buscarField.setBounds(40, 407, 295, 22);
		contentPanel.add(buscarField);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(buscarField.getText().isEmpty()) 
					cargarSintomas();
				else
					buscarSintomas();
			}
		});
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(new Color(169, 181, 223));
		panel_2.setBounds(347, 407, 85, 28);
		contentPanel.add(panel_2);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(new Color(0, 0, 0));
		lblBuscar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_2.add(lblBuscar);
		cargarSintomas();
	}
	
	private void cargarSintomas() {
		actualizarSeleccionGlobal();	
		
		model.setRowCount(0);
		
		for(String s: sintomas) {
			boolean check = sintomasSelected.contains(s);
			Object[] fila = {check, s};
			model.addRow(fila);
		}		
	}
	
	private void buscarSintomas() {
		
		String busqueda = buscarField.getText().toLowerCase();
		actualizarSeleccionGlobal();
		
		model.setRowCount(0);
		
		for(String s: sintomas) {
			if(s.toLowerCase().contains(busqueda)) {
				boolean check = sintomasSelected.contains(s);
				Object[] fila = {check, s};
				model.addRow(fila);
			}
		}
	}
	
	public ArrayList<String>objectsSelected(){
		return sintomasSelected;
	}
	
	public void actualizarSeleccionGlobal() {
	    for (int i = 0; i < model.getRowCount(); i++) {
	        String sintoma = (String) model.getValueAt(i, 1);
	        boolean check = (boolean) model.getValueAt(i, 0);

	        if (check && !sintomasSelected.contains(sintoma)) {
	        	sintomasSelected.add(sintoma);
	        } else if (!check && sintomasSelected.contains(sintoma)) {
	        	sintomasSelected.remove(sintoma);
	        }
	    }
	}
	
	
	
	private void desSelectedAll() {
		for(int i= 0; i<model.getRowCount(); i++) {
			model.setValueAt(false, i, 0);
		}
		
		actualizarSeleccionGlobal();
	}
}
