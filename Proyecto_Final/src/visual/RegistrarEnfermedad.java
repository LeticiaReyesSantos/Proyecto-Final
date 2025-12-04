package visual;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Enfermedad;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistrarEnfermedad extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JComboBox<String> cbxTipo;
	private JRadioButton rdbtnSi;
	private JRadioButton rdbtnNo;
	private JLabel Titulo;
	private JTextArea txtTratamiento;
	private ArrayList<String> sintomas = new ArrayList<>();
	private JLabel ingresar;
	private int mode = 0; //0 reg 1 mod
	private Enfermedad enfermedadModificar = null;
	private int x1;
	private int x2;
	private int y1;
	private int y2;

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
		this(0, null);
	}
	
	public RegistrarEnfermedad(int mode, Enfermedad enf) {
		this.mode = mode;
		this.enfermedadModificar = enf;
		
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
			barra.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					x1= e.getX();
					y1 = e.getY();
				}
			});
			barra.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent arg0) {
					//toma la posicion actual de la ventana
					x2= arg0.getXOnScreen();
					y2 = arg0.getYOnScreen();
					//actualiza la posicion de la ventana
					setLocation(x2-x1, y2-y1);
				}
			});
			
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
						BotonX.setBackground(new Color(45, 51, 107));
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
			Titulo = new JLabel("Registrar Enfermedad");
			Titulo.setForeground(new Color(120, 134, 199));


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
		separator_1.setBounds(28, 64, 181, 2);
		Informacion.add(separator_1);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setForeground(new Color(255, 255, 255));
		lblCodigo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCodigo.setBounds(28, 13, 63, 22);
		Informacion.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(Color.WHITE);
		txtCodigo.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtCodigo.setForeground(Color.BLACK);
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
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isAlphabetic(c) && c!= ' ') {
					e.consume();
				}
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBounds(28, 120, 181, 22);
		Informacion.add(txtNombre);

		JLabel label_8 = new JLabel("");
		label_8.setBounds(635, 46, 56, 16);
		Informacion.add(label_8);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(45, 51, 107));
		separator_3.setBackground(new Color(45, 51, 107));
		separator_3.setBounds(318, 64, 181, 2);
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

		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNo.setSelected(false);
				rdbtnSi.setSelected(true);
			}
		});
		rdbtnSi.setSelected(true);
		rdbtnSi.setForeground(Color.WHITE);
		rdbtnSi.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnSi.setBackground(new Color(169, 181, 223));
		rdbtnSi.setBounds(131, 165, 50, 25);
		Informacion.add(rdbtnSi);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(true);
				rdbtnSi.setSelected(false);
			}
		});
		rdbtnNo.setForeground(Color.WHITE);
		rdbtnNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnNo.setBackground(new Color(169, 181, 223));
		rdbtnNo.setBounds(192, 165, 56, 25);
		Informacion.add(rdbtnNo);

		JPanel sintomasPanel = new JPanel();
		sintomasPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SintomasSelection sintomasW = new SintomasSelection(sintomas);
				sintomasW.setModal(true);
				sintomasW.setVisible(true);
				sintomas = sintomasW.objectsSelected();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sintomasPanel.setBackground(new Color (120, 134, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sintomasPanel.setBackground(new Color(169, 181, 223));
			}
		});
		sintomasPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sintomasPanel.setBackground(new Color(169, 181, 223));
		sintomasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sintomasPanel.setBounds(318, 38, 181, 28);
		Informacion.add(sintomasPanel);

		JLabel lblSeleccionar = new JLabel("Seleccionar");
		lblSeleccionar.setForeground(Color.BLACK);
		lblSeleccionar.setFont(new Font("Verdana", Font.PLAIN, 14));
		sintomasPanel.add(lblSeleccionar);
		JPanel panelTratamiento = new JPanel();
		panelTratamiento.setLayout(null);
		panelTratamiento.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelTratamiento.setBackground(new Color(120, 134, 199));
		panelTratamiento.setBounds(28, 311, 536, 156);
		fondo.add(panelTratamiento);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(45, 51, 107));
		separator_2.setBackground(new Color(45, 51, 107));
		separator_2.setBounds(28, 140, 473, 2);
		panelTratamiento.add(separator_2);

		JLabel lblTratamiento = new JLabel("Tratamiento:");
		lblTratamiento.setForeground(new Color(255, 255, 255));
		lblTratamiento.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTratamiento.setBounds(28, 13, 118, 16);
		panelTratamiento.add(lblTratamiento);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(635, 46, 56, 16);
		panelTratamiento.add(label_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 42, 473, 100);
		panelTratamiento.add(scrollPane);

		txtTratamiento = new JTextArea();
		scrollPane.setViewportView(txtTratamiento);

		JPanel Registrar = new JPanel();
		Registrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean controlada;
				String tipo = cbxTipo.getItemAt(cbxTipo.getSelectedIndex()).toString();
				String tratamiento = txtTratamiento.getText();
				if(tratamiento.trim().isEmpty())
					tratamiento = "No hay tratamiento actualmente";
				if(rdbtnSi.isSelected())
					controlada = true;
				else
					controlada = false;
				
				if(txtNombre.getText().trim().isEmpty() || tipo.equals("<<Seleccionar>>") || sintomas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existen campos invalidos, asegure de llenar la información");
				}else {
					if(mode== 0) {
						Enfermedad aux = new Enfermedad(txtCodigo.getText(),txtNombre.getText(), tratamiento, tipo , controlada);
						aux.setSintomas(sintomas);
						Clinica.getInstance().addEnfermedad(aux);
						JOptionPane.showMessageDialog(null, "Se ha registrado con exito la enfermedad "+aux.getCodigo());
						limpiar();
					}else {
						if(enfermedadModificar != null) {
							if(Clinica.getInstance().modificarEnfermedad(enfermedadModificar.getCodigo(), tratamiento, controlada, sintomas)) {
								 JOptionPane.showMessageDialog(null, "Se ha modificado con exito la enfermedad "+enfermedadModificar.getCodigo());
			                        dispose();
			                    } else {
			                        JOptionPane.showMessageDialog(null, "Error al modificar la enfermedad");
			                    }
							}
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
		Registrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Cancelar.setBounds(463, 488, 85, 28);
		fondo.add(Cancelar);

		JLabel label_1 = new JLabel("Cancelar");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		Cancelar.add(label_1);

		if(enf == null) {
			Titulo.setText("Registrar Enfermedad");
			txtCodigo.setText("E-"+Clinica.getInstance().genEnfermedad);
			ingresar.setText("Registrar");
			rdbtnNo.setSelected(true);
			rdbtnSi.setSelected(false);
		}
		else{
			Titulo.setText("Modificar Enfermedad");
			cargar(enf);
			txtNombre.setEnabled(false);
			ingresar.setText("Modificar");
		}
		Titulo.setForeground(new Color(120, 134, 199));
		Titulo.setFont(new Font("Verdana", Font.BOLD, 28));
		Titulo.setBounds(118, 38, 350, 35);
		fondo.add(Titulo);
		
		setLocationRelativeTo(null);
	}

	public void cargar(Enfermedad enf) {
		txtCodigo.setText(enf.getCodigo());
		txtNombre.setText(enf.getNombre());
		txtTratamiento.setText(enf.getTratamiento());
		cbxTipo.setSelectedItem(enf.getTipo());
		sintomas = enf.getSintomas();
		if(enf.isControlada()) {
			rdbtnSi.setSelected(true);
			rdbtnNo.setSelected(false);
		}else{
			rdbtnNo.setSelected(true);
			rdbtnSi.setSelected(false);
		}
	}

	public void limpiar() {
		txtCodigo.setText("E-"+Clinica.getInstance().genEnfermedad);
		txtNombre.setText("");
		txtTratamiento.setText("");
		cbxTipo.setSelectedIndex(0);
		sintomas = new ArrayList<>();
		rdbtnSi.setSelected(true);
		rdbtnNo.setSelected(false);
	}
}
