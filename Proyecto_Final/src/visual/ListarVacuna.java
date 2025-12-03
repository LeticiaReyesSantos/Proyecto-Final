package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Vacuna;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListarVacuna extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel barPanel;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarVacuna dialog = new ListarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarVacuna() {
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

		JPanel detallarPanel = new JPanel();
		detallarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String codigo = (String) table.getValueAt(selectedRow, 0);
					Clinica cl = Clinica.getInstance();

					for (Vacuna vac : cl.getVacunas()) {
						if (vac.getCodigo().equals(codigo)) {
							JOptionPane.showMessageDialog(null, vac.getDescripcion(), 
									"Descripción: " + vac.getNombre(), 
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una vacuna primero");
				}
			}
		});
		detallarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		detallarPanel.setBackground(new Color(120, 134, 199));
		detallarPanel.setBounds(25, 82, 132, 35);
		panel.add(detallarPanel);

		JLabel label_2 = new JLabel("Detallar");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		detallarPanel.add(label_2);

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

		JPanel modificarPanel = new JPanel();
		modificarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificarPanel.setBackground(new Color(120, 134, 199));
		modificarPanel.setBounds(25, 129, 132, 35);
		panel.add(modificarPanel);

		JLabel label_5 = new JLabel("Modificar");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Verdana", Font.PLAIN, 14));
		modificarPanel.add(label_5);

		JPanel eliminarPanel = new JPanel();
		eliminarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una vacuna primero");
					return;
				}

				String codigoVacuna = (String) table.getValueAt(selectedRow, 0);
				String nombreVacuna = (String) table.getValueAt(selectedRow, 1);

				int confirm = JOptionPane.showConfirmDialog(null, 
						"¿Está seguro de que desea eliminar la vacuna?\n" + 
								nombreVacuna + " (" + codigoVacuna + ")",
								"Confirmar Eliminación",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					if (Clinica.getInstance().eliminarVacuna(codigoVacuna)) {
						JOptionPane.showMessageDialog(null, "Vacuna eliminada exitosamente");
						actualizar();
					} else {
						JOptionPane.showMessageDialog(null, 
								"No se puede eliminar la vacuna.\n" +
								"Está aplicada a uno o más pacientes.");
					}
				}
			}
		});
		eliminarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminarPanel.setBackground(new Color(120, 134, 199));
		eliminarPanel.setBounds(25, 177, 132, 35);
		panel.add(eliminarPanel);

		JLabel label_6 = new JLabel("Eliminar");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Verdana", Font.PLAIN, 14));
		eliminarPanel.add(label_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 107, 736, 309);
		fondo.add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(new Object[][] {
		},
				new String[] {
						"C\u00F3digo", "Nombre", "Enfermedad", "Control", "Selecci\u00F3n"
		}
				) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(101);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JPanel volverPanel = new JPanel();
		volverPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		volverPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volverPanel.setBackground(new Color(169, 181, 223));
		volverPanel.setBounds(865, 437, 85, 28);
		fondo.add(volverPanel);

		actualizar();
		
		JLabel label_3 = new JLabel("Volver");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		volverPanel.add(label_3);

		JLabel lblListaDeVacunas = new JLabel("LISTA DE VACUNAS");
		lblListaDeVacunas.setForeground(new Color(120, 134, 199));
		lblListaDeVacunas.setFont(new Font("Verdana", Font.BOLD, 28));
		lblListaDeVacunas.setBounds(414, 41, 322, 35);
		fondo.add(lblListaDeVacunas);


	}

	private void actualizar() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);
		for (Vacuna vac : cl.getVacunas()) {
			Object[] fila = {vac.getCodigo(), vac.getNombre(), vac.getEnfermedad(), vac.isControlada()? "SI": "NO"};
			model.addRow(fila);
		}

	}
}
