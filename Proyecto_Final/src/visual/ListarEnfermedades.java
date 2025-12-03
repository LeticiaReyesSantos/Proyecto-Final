package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;

import javax.swing.ListSelectionModel;

public class ListarEnfermedades extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel modelMultipleSelection;
	private DefaultTableModel modelSingleSelection;

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel barPanel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEnfermedades dialog = new ListarEnfermedades(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public ListarEnfermedades() {
		this(0);
	}

	public ListarEnfermedades(int mode) {
		setUndecorated(true);
		setBounds(100, 100, 989, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(248, 248, 255));
		fondo.setBounds(0, 0, 989, 481);
		contentPanel.add(fondo);
		fondo.setLayout(null);

		barPanel = new JPanel();
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
		barPanel.setBounds(0, 0, 989, 25);
		fondo.add(barPanel);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(45, 51, 107));

		JPanel cerrarPanel = new JPanel();
		cerrarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		cerrarPanel.setForeground(Color.BLACK);
		cerrarPanel.setBackground(new Color(45, 51, 107));
		cerrarPanel.setBounds(950, 0, 39, 26);
		barPanel.add(cerrarPanel);

		JLabel label = new JLabel("X");
		cerrarPanel.add(label);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 24, 169, 457);
		fondo.add(panel);

		JLabel label_1 = new JLabel("Men\u00FA");
		label_1.setForeground(new Color(169, 181, 223));
		label_1.setFont(new Font("Verdana", Font.BOLD, 18));
		label_1.setBounds(57, 5, 55, 23);
		panel.add(label_1);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(45, 51, 107));
		separator.setBackground(new Color(45, 51, 107));
		separator.setBounds(12, 41, 145, 2);
		panel.add(separator);

		JPanel sintomasPanel = new JPanel();
		sintomasPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una enfermedad primero");
					return;
				}

				String codigoEnfermedad = (String) table.getValueAt(selectedRow, 0);
				Clinica cl = Clinica.getInstance();

				for (Enfermedad enf : cl.getEnfermedades()) {
					if (enf.getCodigo().equals(codigoEnfermedad)) {
						if (enf.getSintomas().isEmpty()) {
							JOptionPane.showMessageDialog(null, 
									"No hay síntomas registrados para " + enf.getNombre(),
									"Síntomas", 
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							String sintomasStr = String.join("\n• ", enf.getSintomas());
							JOptionPane.showMessageDialog(null, 
									"Síntomas de " + enf.getNombre() + ":\n\n• " + sintomasStr,
									"Síntomas", 
									JOptionPane.INFORMATION_MESSAGE);
						}
						break;
					}
				}
			}
		});
		sintomasPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sintomasPanel.setBackground(new Color(120, 134, 199));
		sintomasPanel.setBounds(25, 82, 132, 35);
		panel.add(sintomasPanel);

		JLabel lblVerS = new JLabel("Ver S\u00EDntomas");
		lblVerS.setForeground(Color.WHITE);
		lblVerS.setFont(new Font("Verdana", Font.PLAIN, 14));
		sintomasPanel.add(lblVerS);

		JPanel selectionPanel = new JPanel();
		selectionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>-1)
					dispose();
			}
		});
		selectionPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		selectionPanel.setBackground(new Color(120, 134, 199));
		selectionPanel.setBounds(22, 302, 135, 35);
		panel.add(selectionPanel);

		JLabel label_4 = new JLabel("Seleccionar");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Verdana", Font.PLAIN, 14));
		selectionPanel.add(label_4);

		JPanel tratamientosPanel = new JPanel();
		tratamientosPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una enfermedad primero");
					return;
				}

				String codigoEnfermedad = (String) table.getValueAt(selectedRow, 0);
				Clinica cl = Clinica.getInstance();

				for (Enfermedad enf : cl.getEnfermedades()) {
					if (enf.getCodigo().equals(codigoEnfermedad)) {
						String tratamiento = enf.getTratamiento();
						if (tratamiento == null || tratamiento.trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, 
									"No hay tratamiento registrado para " + enf.getNombre(),
									"Tratamiento", 
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, 
									"Tratamiento para " + enf.getNombre() + ":\n\n" + tratamiento,
									"Tratamiento - " + enf.getNombre(), 
									JOptionPane.INFORMATION_MESSAGE);
						}
						break;
					}
				}

			}
		});
		tratamientosPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tratamientosPanel.setBackground(new Color(120, 134, 199));
		tratamientosPanel.setBounds(25, 129, 132, 35);
		panel.add(tratamientosPanel);

		JLabel lblVerTratamientos = new JLabel("Ver tratamientos");
		lblVerTratamientos.setForeground(Color.WHITE);
		lblVerTratamientos.setFont(new Font("Verdana", Font.PLAIN, 14));
		tratamientosPanel.add(lblVerTratamientos);

		JPanel modificarPanel = new JPanel();
		modificarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificarPanel.setBackground(new Color(120, 134, 199));
		modificarPanel.setBounds(25, 180, 132, 35);
		panel.add(modificarPanel);

		JLabel label_2 = new JLabel("Modificar");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		modificarPanel.add(label_2);
		
		//SINGLE SELECTION MODE MANEJAR
		JPanel eliminarPanel = new JPanel();
		eliminarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una enfermedad primero");
					return;
				}

				String codigoEnf = (String) table.getValueAt(selectedRow, 0);
				String nombreEnf = (String) table.getValueAt(selectedRow, 1);

				int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar la enfermedad?\n" + nombreEnf + " (" +codigoEnf + ")","Confirmar Eliminación",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					if (Clinica.getInstance().eliminarEnfermedad(codigoEnf)) {
						JOptionPane.showMessageDialog(null, "Enfermedad eliminada exitosamente");
						cargarMultipleSlection();
						cargarSingleSelection();
					} else {
						JOptionPane.showMessageDialog(null, "No se puede eliminar la enfermedad.\n" +"Fue diagnosticada a uno o más pacientes.");
					}
				}
			}
		});

		eliminarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminarPanel.setBackground(new Color(120, 134, 199));
		eliminarPanel.setBounds(25, 225, 132, 35);
		panel.add(eliminarPanel);
		
		JLabel lblEliminar = new JLabel("Eliminar");
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		eliminarPanel.add(lblEliminar);

		JLabel lblListaDeEnfermedades = new JLabel("LISTA DE ENFERMEDADES");
		lblListaDeEnfermedades.setForeground(new Color(120, 134, 199));
		lblListaDeEnfermedades.setFont(new Font("Verdana", Font.BOLD, 28));
		lblListaDeEnfermedades.setBounds(356, 41, 443, 35);
		fondo.add(lblListaDeEnfermedades);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(219, 109, 715, 282);
		fondo.add(scrollPane);

		table = new JTable();
		modelMultipleSelection = new DefaultTableModel(
				new Object[][] {},
				new String[] {"C\u00F3digo", "Nombre", "Tipo", "Control", "Seleccion"}
				) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		modelSingleSelection = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Tipo", "Control"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		scrollPane.setViewportView(table);
		cambiarMode(mode);

		if (mode == 0) {
			actualizarTableSingle();
		} else if (mode == 1) {
			actualizarTableMultiple();
		}

		JPanel volverPanel = new JPanel();
		volverPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		volverPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volverPanel.setBackground(new Color(169, 181, 223));
		volverPanel.setBounds(853, 437, 85, 28);
		fondo.add(volverPanel);

		JLabel label_3 = new JLabel("Volver");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		volverPanel.add(label_3);
	}

	private void actualizarTableMultiple() {
		Clinica cl = Clinica.getInstance();
		modelMultipleSelection.setRowCount(0);
		for (Enfermedad enf : cl.getEnfermedades()) {
			Object[] fila = {enf.getCodigo(), enf.getNombre(), enf.getTipo(), enf.isControlada(), false};
			modelMultipleSelection.addRow(fila);
		}
	}

	private void actualizarTableSingle() {
		Clinica cl = Clinica.getInstance();
		modelSingleSelection.setRowCount(0);
		for (Enfermedad enf : cl.getEnfermedades()) {
			Object[] fila = {enf.getCodigo(), enf.getNombre(), enf.getTipo(), enf.isControlada(), false};
			modelSingleSelection.addRow(fila);
		}
	}

	private void cambiarMode(int mode) {
		if(mode == 0) {
			table.setModel(modelSingleSelection);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowSelectionAllowed(true);
			cargarSingleSelection();
		}else if(mode == 1) {
			table.setModel(modelMultipleSelection);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table.setRowSelectionAllowed(false);
			cargarMultipleSlection();
		}
	}
	
	private void cargarMultipleSlection() {
		actualizarTableMultiple();
	}
	
	private void cargarSingleSelection() {
		actualizarTableSingle();
	}
}