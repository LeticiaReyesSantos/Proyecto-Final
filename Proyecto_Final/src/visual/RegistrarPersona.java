package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreField;
	private JTextField textField;
	private JTextField cedulaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPersona dialog = new RegistrarPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPersona() {
		setBounds(100, 100, 850, 477);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, 850, 477);
		contentPanel.add(fondo);
		fondo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 54, 826, 354);
		fondo.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre ");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(12, 13, 86, 16);
		panel.add(lblNewLabel_1);
		
		nombreField = new JTextField();
		nombreField.setBounds(120, 13, 116, 22);
		panel.add(nombreField);
		nombreField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblApellido.setBounds(12, 46, 86, 16);
		panel.add(lblApellido);
		
		textField = new JTextField();
		textField.setBounds(120, 46, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("No cedula");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(12, 75, 116, 16);
		panel.add(lblNewLabel_2);
		
		cedulaField = new JTextField();
		cedulaField.setColumns(10);
		cedulaField.setBounds(120, 75, 116, 22);
		panel.add(cedulaField);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblFechaDeNacimiento.setBounds(12, 110, 204, 16);
		panel.add(lblFechaDeNacimiento);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electronico");
		lblCorreoElectronico.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblCorreoElectronico.setBounds(12, 169, 186, 16);
		panel.add(lblCorreoElectronico);
		
		JLabel lblNumeroTelefonico = new JLabel("Numero telefonico");
		lblNumeroTelefonico.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNumeroTelefonico.setBounds(12, 140, 186, 16);
		panel.add(lblNumeroTelefonico);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblDireccion.setBounds(12, 198, 116, 16);
		panel.add(lblDireccion);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblGenero.setBounds(12, 234, 116, 16);
		panel.add(lblGenero);
		
		JPanel registrarBoton = new JPanel();
		registrarBoton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		registrarBoton.setBackground(new Color(153, 0, 255));
		registrarBoton.setBounds(24, 421, 127, 43);
		fondo.add(registrarBoton);
		
		JLabel Regis = new JLabel("Registrar");
		Regis.setFont(new Font("Verdana", Font.PLAIN, 18));
		Regis.setForeground(Color.WHITE);
		registrarBoton.add(Regis);
		
		JPanel cancelPanel = new JPanel();
		cancelPanel.setBackground(SystemColor.controlHighlight);
		cancelPanel.setBounds(174, 421, 127, 43);
		fondo.add(cancelPanel);
		
		JLabel lblNewLabel = new JLabel("Cancelar");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		cancelPanel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(816, 0, 34, 26);
		fondo.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("X");
		panel_1.add(lblNewLabel_3);
	}
}
