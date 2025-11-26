package visual;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Enfermedad;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarEnfermedad extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtSintomas;
	private JTextField txtTratamiento;
	private JComboBox<String> cbxTipo;
	
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
		setBounds(100, 100, 590, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBounds(0, 0, 590, 540);
		fondo.setBackground(Color.WHITE);
		contentPanel.add(fondo);
		fondo.setLayout(null);
		{
			JPanel barra = new JPanel();
			barra.setLayout(null);
			barra.setBackground(new Color(45, 51, 107));
			barra.setBounds(0, 0, 590, 25);
			fondo.add(barra);
			{
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
						BotonX.setBackground(new Color(102, 0, 204));
					}
					
				});
				BotonX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				BotonX.setForeground(Color.BLACK);
				BotonX.setBackground(new Color(45, 51, 107));
				BotonX.setBounds(551, 0, 39, 26);
				barra.add(BotonX);
				{
					JLabel label = new JLabel("X");
					BotonX.add(label);
				}
			}
		}
		{
			JLabel Titulo = new JLabel("Registrar Enfermedad");
			Titulo.setForeground(new Color(120, 134, 199));
			Titulo.setFont(new Font("Verdana", Font.BOLD, 28));
			Titulo.setBounds(118, 38, 350, 35);
			fondo.add(Titulo);
		}
		
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
		separator_1.setBounds(28, 66, 181, 2);
		Informacion.add(separator_1);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setForeground(new Color(255, 255, 255));
		lblCodigo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCodigo.setBounds(28, 13, 63, 22);
		Informacion.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBorder(null);
		txtCodigo.setBounds(28, 42, 181, 22);
		Informacion.add(txtCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombre.setBounds(28, 91, 79, 16);
		Informacion.add(lblNombre);
		
		JLabel lblCon = new JLabel("Controlada:");
		lblCon.setForeground(new Color(255, 255, 255));
		lblCon.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCon.setBounds(28, 168, 104, 16);
		Informacion.add(lblCon);
		
		JLabel lblSintomas_1 = new JLabel("Sintomas:");
		lblSintomas_1.setForeground(new Color(255, 255, 255));
		lblSintomas_1.setFont(new Font("Verdana", Font.BOLD, 14));
		lblSintomas_1.setBounds(318, 13, 79, 16);
		Informacion.add(lblSintomas_1);
		
		JLabel lblSintomas = new JLabel("Tipo:");
		lblSintomas.setForeground(new Color(255, 255, 255));
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
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(318, 66, 181, 2);
		Informacion.add(separator_3);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(45, 51, 107));
		separator_5.setBackground(new Color(45, 51, 107));
		separator_5.setBounds(318, 140, 181, 2);
		Informacion.add(separator_5);
		
		cbxTipo = new JComboBox<String>();
		cbxTipo.setModel(new DefaultComboBoxModel<>(new String[] {"<<Seleccionar>>","Virus","Parasito","Bacteria"}));
		cbxTipo.setBounds(318, 120, 181, 22);
		Informacion.add(cbxTipo);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setSelected(true);
		rdbtnSi.setForeground(Color.WHITE);
		rdbtnSi.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnSi.setBackground(new Color(169, 181, 223));
		rdbtnSi.setBounds(131, 165, 50, 25);
		Informacion.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setForeground(Color.WHITE);
		rdbtnNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnNo.setBackground(new Color(169, 181, 223));
		rdbtnNo.setBounds(192, 165, 63, 25);
		Informacion.add(rdbtnNo);
		
		JPanel paneltratamiento = new JPanel();
		paneltratamiento.setLayout(null);
		paneltratamiento.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		paneltratamiento.setBackground(new Color(120, 134, 199));
		paneltratamiento.setBounds(28, 311, 536, 156);
		fondo.add(paneltratamiento);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(28, 140, 473, 2);
		paneltratamiento.add(separator_2);
		
		JLabel lblTratamiento = new JLabel("Tratamiento:");
		lblTratamiento.setForeground(new Color(255, 255, 255));
		lblTratamiento.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTratamiento.setBounds(28, 13, 118, 16);
		paneltratamiento.add(lblTratamiento);
		
		txtTratamiento = new JTextField();
		txtTratamiento.setColumns(10);
		txtTratamiento.setBorder(null);
		txtTratamiento.setBounds(28, 42, 473, 100);
		paneltratamiento.add(txtTratamiento);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(635, 46, 56, 16);
		paneltratamiento.add(label_5);
		
		JPanel Registrar = new JPanel();
		Registrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String tipo = cbxTipo.getItemAt(cbxTipo.getSelectedIndex()).toString();
				boolean controlada;
				if(rdbtnSi.isSelected())
					controlada = true;
				else
					controlada = false;
				Enfermedad aux = new Enfermedad(txtCodigo.getText(),txtNombre.getText(), txtTratamiento.getText(), tipo , controlada);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Registrar.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Registrar.setBackground(new Color(138, 43, 226));
			}
			
		});
		Registrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Registrar.setBackground(new Color(169, 181, 223));
		Registrar.setBounds(348, 488, 85, 28);
		fondo.add(Registrar);
		
		JLabel label = new JLabel("Registrar");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		Registrar.add(label);
		
		JPanel Cancelar = new JPanel();
		Cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cancelar.setBackground(new Color(102, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cancelar.setBackground(new Color(138, 43, 226));
			}
		});
		Cancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Cancelar.setBackground(new Color(169, 181, 223));
		Cancelar.setBounds(463, 488, 85, 28);
		fondo.add(Cancelar);
		
		JLabel label_1 = new JLabel("Cancelar");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		Cancelar.add(label_1);
	}
}
