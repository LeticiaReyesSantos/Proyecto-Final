package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Enfermedad;
import logico.Vacuna;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class RegistrarVacuna extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextArea txtDescripcion;
	private Enfermedad enfermedad;
	private JRadioButton rdbtSi;
	private JRadioButton rdbtNo;
	private JLabel ingresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarVacuna dialog = new RegistrarVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarVacuna(Vacuna vac) {
		setUndecorated(true);
		setBounds(100, 100, 590, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setLayout(null);
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, 590, 540);
		contentPanel.add(fondo);
		
		JPanel barra = new JPanel();
		barra.setLayout(null);
		barra.setBackground(new Color(45, 51, 107));
		barra.setBounds(0, 0, 590, 25);
		fondo.add(barra);
		
		JPanel BotonX = new JPanel();
		BotonX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BotonX.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				BotonX.setBackground(new Color(45, 51, 107));
			}
		});
		BotonX.setForeground(Color.BLACK);
		BotonX.setBackground(new Color(45, 51, 107));
		BotonX.setBounds(551, 0, 39, 26);
		barra.add(BotonX);
		
		JLabel label_1 = new JLabel("X");
		BotonX.add(label_1);
		
		JLabel Titulo = new JLabel("Registrar Vacuna");

		Titulo.setForeground(new Color(120, 134, 199));
		Titulo.setFont(new Font("Verdana", Font.BOLD, 28));
		Titulo.setBounds(143, 38, 278, 35);
		fondo.add(Titulo);
		
		JPanel Informacion = new JPanel();
		Informacion.setLayout(null);
		Informacion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Informacion.setBackground(new Color(120, 134, 199));
		Informacion.setBounds(28, 86, 536, 212);
		fondo.add(Informacion);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(45, 51, 107));
		separator.setBackground(new Color(45, 51, 107));
		separator.setBounds(28, 140, 181, 2);
		Informacion.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(45, 51, 107));
		separator_1.setBackground(new Color(45, 51, 107));
		separator_1.setBounds(28, 64, 181, 2);
		Informacion.add(separator_1);
		
		JLabel label_3 = new JLabel("Codigo:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Verdana", Font.BOLD, 14));
		label_3.setBounds(28, 13, 63, 22);
		Informacion.add(label_3);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBorder(null);
		txtCodigo.setBounds(28, 42, 181, 22);
		Informacion.add(txtCodigo);
		
		JLabel label_4 = new JLabel("Nombre:");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Verdana", Font.BOLD, 14));
		label_4.setBounds(28, 91, 79, 16);
		Informacion.add(label_4);
		
		JLabel label_5 = new JLabel("Controlada:");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Verdana", Font.BOLD, 14));
		label_5.setBounds(258, 122, 104, 16);
		Informacion.add(label_5);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setForeground(Color.WHITE);
		lblEnfermedad.setFont(new Font("Verdana", Font.BOLD, 14));
		lblEnfermedad.setBounds(318, 13, 112, 16);
		Informacion.add(lblEnfermedad);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBounds(28, 120, 181, 22);
		Informacion.add(txtNombre);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(635, 46, 56, 16);
		Informacion.add(label_8);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(318, 64, 181, 2);
		Informacion.add(separator_2);
		
		rdbtSi = new JRadioButton("Si");
		rdbtSi.setEnabled(false);
		rdbtSi.setSelected(true);
		rdbtSi.setForeground(Color.WHITE);
		rdbtSi.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtSi.setBackground(new Color(169, 181, 223));
		rdbtSi.setBounds(380, 118, 50, 25);
		Informacion.add(rdbtSi);
		
		rdbtNo = new JRadioButton("No");
		rdbtNo.setEnabled(false);
		rdbtNo.setForeground(Color.WHITE);
		rdbtNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtNo.setBackground(new Color(169, 181, 223));
		rdbtNo.setBounds(443, 118, 56, 25);
		Informacion.add(rdbtNo);
		
		JPanel enfermedadPanel = new JPanel();
		enfermedadPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarEnfermedades list = new ListarEnfermedades(0);
				list.setModal(true);
				list.setVisible(true);
				enfermedad = list.getSelectedEnfermedad();
				if(enfermedad == null)
					return;
				
				if(enfermedad.isControlada()) {
					rdbtSi.setSelected(true);
					rdbtNo.setSelected(false);
				}else {
					rdbtNo.setSelected(true);
					rdbtSi.setSelected(false);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				enfermedadPanel.setBackground(new Color (120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				enfermedadPanel.setBackground(new Color(169, 181, 223));
			}
		});
		enfermedadPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		enfermedadPanel.setBackground(new Color(169, 181, 223));
		enfermedadPanel.setBounds(318, 38, 181, 28);
		Informacion.add(enfermedadPanel);
		
		JLabel label = new JLabel("Seleccionar");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		enfermedadPanel.add(label);
		
		JPanel Descripcion = new JPanel();
		Descripcion.setLayout(null);
		Descripcion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Descripcion.setBackground(new Color(120, 134, 199));
		Descripcion.setBounds(28, 311, 536, 156);
		fondo.add(Descripcion);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(45, 51, 107));
		separator_4.setBackground(new Color(45, 51, 107));
		separator_4.setBounds(28, 140, 473, 2);
		Descripcion.add(separator_4);
		
		JLabel panelDescripcion = new JLabel("Descripcion:");
		panelDescripcion.setForeground(Color.WHITE);
		panelDescripcion.setFont(new Font("Verdana", Font.BOLD, 14));
		panelDescripcion.setBounds(28, 13, 118, 16);
		Descripcion.add(panelDescripcion);
		
		JLabel label_10 = new JLabel("");
		label_10.setBounds(635, 46, 56, 16);
		Descripcion.add(label_10);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setBounds(28, 42, 471, 98);
		Descripcion.add(txtDescripcion);
		
		JPanel Registrar = new JPanel();
		Registrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String descripcion = txtDescripcion.getText();
				if(descripcion.trim().isEmpty())
					descripcion = "No hay descripcion actualmente";
				
				if(txtNombre.getText().trim().isEmpty() || enfermedad == null) {
					JOptionPane.showMessageDialog(null, "Existen campos invalidos, asegure de llenar la información");
				}else {
					if(vac == null) {
						Vacuna aux = new Vacuna(txtCodigo.getText(), txtNombre.getText(), enfermedad, descripcion);
						aux.setControlada(enfermedad.isControlada());
						Clinica.getInstance().addVacuna(aux);
						JOptionPane.showMessageDialog(null, "Se ha registrado con exito la vacuna "+aux.getCodigo());
						limpiar();
					}else {
						vac.setControlada(enfermedad.isControlada());
						vac.setDescripcion(descripcion);
						vac.setEnfermedad(enfermedad);
						JOptionPane.showMessageDialog(null, "Se ha modificado con exito la enfermedad "+vac.getCodigo());
						dispose();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Registrar.setBackground(new Color(120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Registrar.setBackground(new Color(169, 181, 223));
			}
		});
		Registrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Registrar.setBackground(new Color(169, 181, 223));
		Registrar.setBounds(348, 488, 85, 28);
		fondo.add(Registrar);
		
		ingresar = new JLabel("Registrar");
		ingresar.setForeground(Color.BLACK);
		ingresar.setFont(new Font("Verdana", Font.PLAIN, 14));
		Registrar.add(ingresar);
		
		JPanel Cancelar = new JPanel();
		Cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cancelar.setBackground(new Color(120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cancelar.setBackground(new Color(169, 181, 223));
			}
		});
		Cancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Cancelar.setBackground(new Color(169, 181, 223));
		Cancelar.setBounds(463, 488, 85, 28);
		fondo.add(Cancelar);
		
		JLabel label_12 = new JLabel("Cancelar");
		label_12.setForeground(Color.BLACK);
		label_12.setFont(new Font("Verdana", Font.PLAIN, 14));
		Cancelar.add(label_12);
		
		if(vac == null) {
			Titulo = new JLabel("Registrar Vacuna");
			txtCodigo.setText("V-"+Clinica.getInstance().genVacuna);
			ingresar.setText("Registrar");
		}
		else{
			Titulo = new JLabel("Modificar Vacuna");
			cargar(vac);
			ingresar.setText("Modificar");
		}
	}
	
	private void cargar(Vacuna vac) {
		txtCodigo.setText(vac.getCodigo());
		txtDescripcion.setText(vac.getDescripcion());
		txtNombre.setText(vac.getCodigo());
		txtNombre.setEnabled(false);
		enfermedad = vac.getEnfermedad();
		if(vac.isControlada()) {
			rdbtSi.setSelected(true);
			rdbtNo.setSelected(false);
		}else {
			rdbtNo.setSelected(true);
			rdbtSi.setSelected(false);
		}
	}
	
	public void limpiar() {
		txtCodigo.setText("V-"+Clinica.getInstance().genVacuna);
		txtNombre.setText("");
		txtDescripcion.setText("");
		enfermedad = null;
		rdbtSi.setSelected(true);
		rdbtNo.setSelected(false);
	}
}
