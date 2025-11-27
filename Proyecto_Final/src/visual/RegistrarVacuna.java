package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Vacuna;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class RegistrarVacuna extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField textField_2;
	private JTextField textField_3;

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
		BotonX.setForeground(Color.BLACK);
		BotonX.setBackground(new Color(45, 51, 107));
		BotonX.setBounds(551, 0, 39, 26);
		barra.add(BotonX);
		
		JLabel label_1 = new JLabel("X");
		BotonX.add(label_1);
		
		JLabel Titulo = new JLabel("Registrar Vacuna");
		if(vac == null) {
			Titulo = new JLabel("Registrar Vacuna");
			txtCodigo.setText("V-"+Clinica.getInstance().genVacuna);
		}
		else{
			Titulo = new JLabel("Modificar Vacuna");
			cargar(vac);
			txtNombre.setEnabled(false);
		}
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
		txtCodigo.setEnabled(false);
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
		label_5.setBounds(28, 168, 104, 16);
		Informacion.add(label_5);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setForeground(Color.WHITE);
		lblEnfermedad.setFont(new Font("Verdana", Font.BOLD, 14));
		lblEnfermedad.setBounds(318, 13, 112, 16);
		Informacion.add(lblEnfermedad);
		
		JLabel label_7 = new JLabel("Tipo:");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Verdana", Font.BOLD, 14));
		label_7.setBounds(318, 91, 79, 16);
		Informacion.add(label_7);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBounds(28, 120, 181, 22);
		Informacion.add(txtNombre);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBounds(318, 42, 181, 22);
		Informacion.add(textField_2);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(635, 46, 56, 16);
		Informacion.add(label_8);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(318, 64, 181, 2);
		Informacion.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(318, 140, 181, 2);
		Informacion.add(separator_3);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(318, 120, 181, 22);
		Informacion.add(comboBox);
		
		JRadioButton radioButton = new JRadioButton("Si");
		radioButton.setSelected(true);
		radioButton.setForeground(Color.WHITE);
		radioButton.setFont(new Font("Verdana", Font.PLAIN, 14));
		radioButton.setBackground(new Color(169, 181, 223));
		radioButton.setBounds(131, 165, 50, 25);
		Informacion.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("No");
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		radioButton_1.setBackground(new Color(169, 181, 223));
		radioButton_1.setBounds(192, 165, 56, 25);
		Informacion.add(radioButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_4.setBackground(new Color(120, 134, 199));
		panel_4.setBounds(28, 311, 536, 156);
		fondo.add(panel_4);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(45, 51, 107));
		separator_4.setBackground(new Color(45, 51, 107));
		separator_4.setBounds(28, 140, 473, 2);
		panel_4.add(separator_4);
		
		JLabel panelDescripcion = new JLabel("Descripcion:");
		panelDescripcion.setForeground(Color.WHITE);
		panelDescripcion.setFont(new Font("Verdana", Font.BOLD, 14));
		panelDescripcion.setBounds(28, 13, 118, 16);
		panel_4.add(panelDescripcion);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		textField_3.setBounds(28, 42, 473, 100);
		panel_4.add(textField_3);
		
		JLabel label_10 = new JLabel("");
		label_10.setBounds(635, 46, 56, 16);
		panel_4.add(label_10);
		
		JPanel Registrar = new JPanel();
		Registrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Registrar.setBackground(new Color(169, 181, 223));
		Registrar.setBounds(348, 488, 85, 28);
		fondo.add(Registrar);
		
		JLabel label_11 = new JLabel("Registrar");
		label_11.setForeground(Color.BLACK);
		label_11.setFont(new Font("Verdana", Font.PLAIN, 14));
		Registrar.add(label_11);
		
		JPanel Cancelar = new JPanel();
		Cancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Cancelar.setBackground(new Color(169, 181, 223));
		Cancelar.setBounds(463, 488, 85, 28);
		fondo.add(Cancelar);
		
		JLabel label_12 = new JLabel("Cancelar");
		label_12.setForeground(Color.BLACK);
		label_12.setFont(new Font("Verdana", Font.PLAIN, 14));
		Cancelar.add(label_12);
	}
}
