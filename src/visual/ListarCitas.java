package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;

import javax.swing.JButton;
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

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Medico;
import logico.Persona;
import logico.Vacuna;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ListarCitas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private JPanel barPanel;
	private JTable table;
	private DefaultTableModel model;

	private Persona usuario = Clinica.getLoginUser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarCitas dialog = new ListarCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCitas() {
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
			public void mouseEntered(MouseEvent arg0) {
				cerrarPanel.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cerrarPanel.setBackground(new Color(45, 51, 107));
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

		JPanel seleccionarPanel = new JPanel();
		seleccionarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>-1)
					dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				seleccionarPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				seleccionarPanel.setBackground(new Color(120, 134, 199));
			}
		});
		seleccionarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		seleccionarPanel.setBackground(new Color(120, 134, 199));
		seleccionarPanel.setBounds(22, 247, 135, 35);
		seleccionarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(seleccionarPanel);

		JLabel seleccionarlbl = new JLabel("Seleccionar");
		seleccionarlbl.setForeground(Color.WHITE);
		seleccionarlbl.setFont(new Font("Verdana", Font.PLAIN, 14));
		seleccionarPanel.add(seleccionarlbl);

		JPanel reagendarPanel = new JPanel();
		reagendarPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una cita primero", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String codigoCita = (String) table.getValueAt(selectedRow, 0);
				Cita cita = Clinica.getInstance().buscarCitaByCode(codigoCita);

				if (cita == null) {
					JOptionPane.showMessageDialog(null, "No se encontró la cita seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (cita.isEstado()) { 
					JOptionPane.showMessageDialog(null, "No se puede reagendar una cita finalizada o cancelada", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				RegistrarCita reagendarCita = new RegistrarCita(1, cita);
				reagendarCita.setModal(true);
				reagendarCita.setLocationRelativeTo(null);
				reagendarCita.setVisible(true);
				actualizarAdmin();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				reagendarPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reagendarPanel.setBackground(new Color(120, 134, 199));
			}
		});
		reagendarPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		reagendarPanel.setBackground(new Color(120, 134, 199));
		reagendarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reagendarPanel.setBounds(22, 82, 132, 35);
		panel.add(reagendarPanel);

		JLabel lblReagendar = new JLabel("Reagendar");
		lblReagendar.setForeground(Color.WHITE);
		lblReagendar.setFont(new Font("Verdana", Font.PLAIN, 14));
		reagendarPanel.add(lblReagendar);

		JPanel cancelarCita = new JPanel();
		cancelarCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una cita primero", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String codigoCita = (String) table.getValueAt(selectedRow, 0);
				String nombrePersona = (String) table.getValueAt(selectedRow, 1);
				String medicoNombre = (String) table.getValueAt(selectedRow, 2);
				String fechaCita = (String) table.getValueAt(selectedRow, 3);

				String mensaje = "¿Está seguro que desea cancelar la cita?\n\n" +"Código: " + codigoCita + "\n" +"Persona: " + nombrePersona + "\n" +"Médico: " + medicoNombre + "\n" +"Fecha: " + fechaCita;

				int confirmacion = JOptionPane.showConfirmDialog(null, mensaje,"Confirmar Cancelación",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				if (confirmacion == JOptionPane.YES_OPTION) {
					if (Clinica.getInstance().cancelarCita(codigoCita)) {
						JOptionPane.showMessageDialog(null,"Cita cancelada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						actualizarAdmin();

					} else {
						JOptionPane.showMessageDialog(null, "No se pudo cancelar la cita.\n" +"La cita ya fue completada o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelarCita.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cancelarCita.setBackground(new Color(120, 134, 199));
			}
		});
		cancelarCita.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cancelarCita.setBackground(new Color(120, 134, 199));
		cancelarCita.setBounds(25, 133, 132, 35);
		cancelarCita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(cancelarCita);

		JLabel lblCancelar = new JLabel("Cancelar");	   
		lblCancelar.setForeground(Color.WHITE);
		lblCancelar.setFont(new Font("Verdana", Font.PLAIN, 14));
		cancelarCita.add(lblCancelar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(45, 51, 107));
		separator_1.setBackground(new Color(45, 51, 107));
		separator_1.setBounds(0, 0, 2, 457);
		panel.add(separator_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(45, 51, 107));
		separator_4.setBackground(new Color(45, 51, 107));
		separator_4.setBounds(-334, 455, 1001, 2);
		panel.add(separator_4);

		JLabel lblListaDeCitas = new JLabel("LISTA DE CITAS");
		lblListaDeCitas.setForeground(new Color(120, 134, 199));
		lblListaDeCitas.setFont(new Font("Verdana", Font.BOLD, 28));
		lblListaDeCitas.setBounds(421, 61, 373, 35);
		fondo.add(lblListaDeCitas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(202, 112, 736, 309);
		fondo.add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
						"Codigo", "Persona", "Medico", "Fecha"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);


		JPanel volverPanel = new JPanel();
		volverPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				volverPanel.setBackground(new Color(45, 51, 107));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				volverPanel.setBackground(new Color(169, 181, 223));
			}
		});

		if (usuario instanceof Medico) {
		    actualizarMedico();
		} else {
		    actualizarAdmin();
		}


		volverPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volverPanel.setBackground(new Color(169, 181, 223));
		volverPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		volverPanel.setBounds(824, 437, 114, 28);
		fondo.add(volverPanel);

		JLabel label_2 = new JLabel("Volver");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		volverPanel.add(label_2);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(166, 24, 2, 457);
		fondo.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(167, 8, 2, 473);
		fondo.add(separator_3);
	}

	private void actualizarAdmin() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);
		for (Cita cita : cl.getCitas()) {
			if(cita != null && !(cita instanceof Consulta) && !cita.isEstado() && (cita.getFecha().isAfter(LocalDate.now()) || 
					cita.getFecha().equals(LocalDate.now()))) {
				Object[] fila = {cita.getCodigo(), cita.getPersona().getNombres(),cita.getMedico().getNombres(), cita.getFecha()};
				model.addRow(fila);
			}
		}
	}

	private void actualizarMedico() {
		Clinica cl = Clinica.getInstance();
		model.setRowCount(0);
		if(usuario instanceof Medico) {

			for (Cita cita : usuario.getHistorial()) {
				if(!(cita instanceof Consulta) && !cita.isEstado() && (cita.getFecha().isAfter(LocalDate.now()) || 
						cita.getFecha().equals(LocalDate.now()))) {
					Object[] fila = {cita.getCodigo(), cita.getPersona().getNombres(),cita.getMedico().getNombres(), cita.getFecha()};
					model.addRow(fila);
				}
			}
		}
	}

}
