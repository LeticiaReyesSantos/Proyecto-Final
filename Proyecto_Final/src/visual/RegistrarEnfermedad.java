package visual;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import javax.swing.JComboBox;

public class RegistrarEnfermedad extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtSintomas;
	private JTextField textField_1;
	private JComboBox cbxTipo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarEnfermedad dialog = new RegistrarEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarEnfermedad() {
		setUndecorated(true);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBounds(0, 0, 800, 500);
		fondo.setBackground(Color.WHITE);
		contentPanel.add(fondo);
		fondo.setLayout(null);
		{
			JPanel barra = new JPanel();
			barra.setLayout(null);
			barra.setBackground(new Color(102, 0, 204));
			barra.setBounds(0, 0, 784, 25);
			fondo.add(barra);
			{
				JPanel BotonX = new JPanel();
				BotonX.setForeground(Color.BLACK);
				BotonX.setBackground(new Color(102, 0, 204));
				BotonX.setBounds(805, 0, 39, 26);
				barra.add(BotonX);
				{
					JLabel label = new JLabel("X");
					BotonX.add(label);
				}
			}
		}
		{
			JLabel Titulo = new JLabel("Registrar Enfermedad");
			Titulo.setForeground(new Color(102, 0, 204));
			Titulo.setFont(new Font("Verdana", Font.BOLD, 28));
			Titulo.setBounds(194, 38, 350, 35);
			fondo.add(Titulo);
		}
		
		JPanel Informacion = new JPanel();
		Informacion.setLayout(null);
		Informacion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Informacion.setBackground(new Color(240, 248, 255));
		Informacion.setBounds(121, 86, 536, 212);
		fondo.add(Informacion);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(72, 61, 139));
		separator.setBackground(new Color(72, 61, 139));
		separator.setBounds(28, 140, 181, 2);
		Informacion.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(72, 61, 139));
		separator_1.setBackground(new Color(72, 61, 139));
		separator_1.setBounds(28, 66, 181, 2);
		Informacion.add(separator_1);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCodigo.setBounds(28, 13, 63, 22);
		Informacion.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBorder(null);
		txtCodigo.setBounds(28, 42, 181, 22);
		Informacion.add(txtCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombre.setBounds(28, 91, 79, 16);
		Informacion.add(lblNombre);
		
		JLabel lblCon = new JLabel("Controlada:");
		lblCon.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCon.setBounds(28, 168, 104, 16);
		Informacion.add(lblCon);
		
		JLabel lblSintomas_1 = new JLabel("Sintomas:");
		lblSintomas_1.setFont(new Font("Verdana", Font.BOLD, 14));
		lblSintomas_1.setBounds(318, 13, 79, 16);
		Informacion.add(lblSintomas_1);
		
		JLabel lblSintomas = new JLabel("Tipo:");
		lblSintomas.setFont(new Font("Verdana", Font.BOLD, 14));
		lblSintomas.setBounds(318, 91, 79, 16);
		Informacion.add(lblSintomas);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBounds(28, 120, 181, 22);
		Informacion.add(txtNombre);
		
		txtSintomas = new JTextField();
		txtSintomas.setColumns(10);
		txtSintomas.setBorder(null);
		txtSintomas.setBounds(318, 42, 181, 22);
		Informacion.add(txtSintomas);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(635, 46, 56, 16);
		Informacion.add(label_8);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(72, 61, 139));
		separator_3.setBackground(new Color(72, 61, 139));
		separator_3.setBounds(318, 66, 181, 2);
		Informacion.add(separator_3);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(72, 61, 139));
		separator_5.setBackground(new Color(72, 61, 139));
		separator_5.setBounds(318, 140, 181, 2);
		Informacion.add(separator_5);
		
		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel<>(new String[] {"<<Seleccionar>>","Virus","Parasito","Bacteria"}));
		cbxTipo.setBounds(318, 120, 181, 22);
		Informacion.add(cbxTipo);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setSelected(true);
		rdbtnSi.setForeground(Color.WHITE);
		rdbtnSi.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnSi.setBackground(new Color(123, 104, 238));
		rdbtnSi.setBounds(131, 165, 50, 25);
		Informacion.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		rdbtnNo.setForeground(Color.WHITE);
		rdbtnNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnNo.setBackground(new Color(123, 104, 238));
		rdbtnNo.setBounds(192, 165, 50, 25);
		Informacion.add(rdbtnNo);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(121, 311, 536, 156);
		fondo.add(panel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(72, 61, 139));
		separator_2.setBackground(new Color(72, 61, 139));
		separator_2.setBounds(28, 140, 473, 2);
		panel.add(separator_2);
		
		JLabel lblTratamiento = new JLabel("Tratamiento:");
		lblTratamiento.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTratamiento.setBounds(28, 13, 118, 16);
		panel.add(lblTratamiento);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(28, 42, 473, 100);
		panel.add(textField_1);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(635, 46, 56, 16);
		panel.add(label_5);
	}
}
