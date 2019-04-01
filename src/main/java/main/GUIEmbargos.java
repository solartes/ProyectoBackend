package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import datos.Data;
import enumeraciones.EstadoCuenta;
import enumeraciones.TipoEmbargo;
import enumeraciones.TipoIdentificacion;
import modelo.Cuenta;
import modelo.Embargo;
import modelo.Persona;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUIEmbargos extends JFrame {

	private Pasarela pasarela;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTable table_1;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField txtIdEmbargo;
	private JTextField txtIdAutoridad;
	private JTextField txtNumProceso;
	private JTextField txtNumOficio;
	private JTextField txtFechaOficio;
	private JTextField txtTipoEmbargo;
	private JTextField txtMontoEmbargo;
	private JTextField txtNumCuentaAgrario;
	private JTextField txtCiudadCuentaAgrario;
	private JTextField txtDepartamentoCuentaAgrario;
	private JTable tblDemandantes;
	private JTable tblDemandados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIEmbargos frame = new GUIEmbargos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIEmbargos() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pasarela = new Pasarela();

		KieServices kServices = KieServices.Factory.get();
		KieFileSystem kfs = kServices.newKieFileSystem();
		KieRepository kr = kServices.getRepository();
		File file;
		try {
			file = new File(getClass().getResource("/com/rule/Rules.drl").toURI());
			Resource resource = kServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
			kfs.write(resource);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KieBuilder kb = kServices.newKieBuilder(kfs);
		kb.buildAll();
		KieContainer kContainer = kServices.newKieContainer(kr.getDefaultReleaseId());
		KieSession sessionStatefull = kContainer.newKieSession();
		Data datos = new Data();
		sessionStatefull.setGlobal("datos", datos);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel pnlCrear = new JPanel();
		pnlCrear.setLayout(null);
		tabbedPane.addTab("Crear embargo", null, pnlCrear, null);

		JLabel label = new JLabel("idEmbargo");
		label.setBounds(26, 21, 77, 14);
		pnlCrear.add(label);

		JLabel label_1 = new JLabel("numProceso");
		label_1.setBounds(26, 96, 77, 14);
		pnlCrear.add(label_1);

		JLabel label_2 = new JLabel("numOficio");
		label_2.setBounds(26, 132, 77, 14);
		pnlCrear.add(label_2);

		JLabel label_3 = new JLabel("idAutoridad");
		label_3.setBounds(26, 58, 77, 14);
		pnlCrear.add(label_3);

		JLabel label_4 = new JLabel("fechaOficio");
		label_4.setBounds(26, 171, 77, 14);
		pnlCrear.add(label_4);

		JLabel label_5 = new JLabel("tipoEmbargo");
		label_5.setBounds(266, 21, 146, 14);
		pnlCrear.add(label_5);

		JLabel label_6 = new JLabel("montoEmbargo");
		label_6.setBounds(266, 58, 146, 14);
		pnlCrear.add(label_6);

		JLabel label_7 = new JLabel("numCuentaAgrario");
		label_7.setBounds(266, 96, 146, 14);
		pnlCrear.add(label_7);

		JLabel label_8 = new JLabel("ciudadCuentaAgrario");
		label_8.setBounds(266, 132, 146, 14);
		pnlCrear.add(label_8);

		JLabel label_9 = new JLabel("departamentoCuentaAgrario");
		label_9.setBounds(266, 171, 146, 14);
		pnlCrear.add(label_9);

		txtIdEmbargo = new JTextField();
		txtIdEmbargo.setColumns(10);
		txtIdEmbargo.setBounds(113, 18, 143, 20);
		pnlCrear.add(txtIdEmbargo);

		txtIdAutoridad = new JTextField();
		txtIdAutoridad.setColumns(10);
		txtIdAutoridad.setBounds(113, 55, 143, 20);
		pnlCrear.add(txtIdAutoridad);

		txtNumProceso = new JTextField();
		txtNumProceso.setColumns(10);
		txtNumProceso.setBounds(113, 93, 143, 20);
		pnlCrear.add(txtNumProceso);

		txtNumOficio = new JTextField();
		txtNumOficio.setColumns(10);
		txtNumOficio.setBounds(113, 129, 143, 20);
		pnlCrear.add(txtNumOficio);

		txtFechaOficio = new JTextField();
		txtFechaOficio.setColumns(10);
		txtFechaOficio.setBounds(113, 168, 143, 20);
		pnlCrear.add(txtFechaOficio);

		txtTipoEmbargo = new JTextField();
		txtTipoEmbargo.setColumns(10);
		txtTipoEmbargo.setBounds(417, 15, 177, 20);
		pnlCrear.add(txtTipoEmbargo);

		txtMontoEmbargo = new JTextField();
		txtMontoEmbargo.setColumns(10);
		txtMontoEmbargo.setBounds(417, 52, 177, 20);
		pnlCrear.add(txtMontoEmbargo);

		txtNumCuentaAgrario = new JTextField();
		txtNumCuentaAgrario.setColumns(10);
		txtNumCuentaAgrario.setBounds(417, 90, 177, 20);
		pnlCrear.add(txtNumCuentaAgrario);

		txtCiudadCuentaAgrario = new JTextField();
		txtCiudadCuentaAgrario.setColumns(10);
		txtCiudadCuentaAgrario.setBounds(417, 126, 177, 20);
		pnlCrear.add(txtCiudadCuentaAgrario);

		txtDepartamentoCuentaAgrario = new JTextField();
		txtDepartamentoCuentaAgrario.setColumns(10);
		txtDepartamentoCuentaAgrario.setBounds(417, 165, 177, 20);
		pnlCrear.add(txtDepartamentoCuentaAgrario);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 211, 599, 158);
		pnlCrear.add(scrollPane_3);

		tblDemandantes = new JTable();
		tblDemandantes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion" }));
		scrollPane_3.setViewportView(tblDemandantes);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 392, 599, 151);
		pnlCrear.add(scrollPane_4);

		tblDemandados = new JTable();
		tblDemandados.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion", "Monto Embargo" }));
		scrollPane_4.setViewportView(tblDemandados);

		JButton button = new JButton("Cargar embargo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtIdEmbargo.setText("embargo1");
				txtIdAutoridad.setText("autoridad1");
				txtNumProceso.setText("10111213");
				txtNumOficio.setText("1009");
				txtFechaOficio.setText("01/01/2019");
				txtTipoEmbargo.setText("Judicial");
				txtMontoEmbargo.setText("12000000");
				txtNumCuentaAgrario.setText("123214512");
				txtCiudadCuentaAgrario.setText("Popayan");
				txtDepartamentoCuentaAgrario.setText("Cauca");
				tblDemandantes.getModel().setValueAt("Juan", 0, 0);
				tblDemandantes.getModel().setValueAt("Perez", 0, 1);
				tblDemandantes.getModel().setValueAt("Cedula", 0, 2);
				tblDemandantes.getModel().setValueAt("100919821", 0, 3);
				tblDemandados.getModel().setValueAt("Andres", 0, 0);
				tblDemandados.getModel().setValueAt("Ruiz", 0, 1);
				tblDemandados.getModel().setValueAt("Cedula", 0, 2);
				tblDemandados.getModel().setValueAt("12332111", 0, 3);
				tblDemandados.getModel().setValueAt("12000000", 0, 4);
			}
		});
		button.setBounds(113, 572, 143, 23);
		pnlCrear.add(button);

		JButton button_1 = new JButton("Aplicar embargo");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.parse(txtFechaOficio.getText(), formatter);
				TipoEmbargo tipoEmbargo = null;
				switch (txtTipoEmbargo.getText()) {
				case "Judicial":
					tipoEmbargo = TipoEmbargo.JUDICIAL;
					break;
				case "Coactivo":
					tipoEmbargo = TipoEmbargo.COACTIVO;
					break;
				case "Familiar":
					tipoEmbargo = TipoEmbargo.FAMILIAR;
					break;
				case "Cooperativa":
					tipoEmbargo = TipoEmbargo.COOPERATIVA;
					break;
				}
				Embargo embargo = new Embargo(txtIdEmbargo.getText(), localDate, tipoEmbargo,
						new BigDecimal(txtMontoEmbargo.getText()));
				ArrayList<Persona> personas = new ArrayList<>();
				ArrayList<Cuenta> cuentas= new ArrayList<>();
				for (int i = 0; i < tblDemandados.getModel().getRowCount(); i++) {
					TipoIdentificacion tipoIdentificacion = null;
					switch (tblDemandados.getModel().getValueAt(i, 2).toString()) {
					case "Cedula":
						tipoIdentificacion = TipoIdentificacion.NATURAL;
						break;
					case "NIT":
						tipoIdentificacion = TipoIdentificacion.JURIDICA;
						break;
					}
					personas.add(new Persona(tblDemandados.getModel().getValueAt(i, 3).toString(),
							txtIdEmbargo.getText(), tipoIdentificacion,
							new BigDecimal(tblDemandados.getModel().getValueAt(i, 4).toString())));
					cuentas.addAll(pasarela.consulta(tblDemandados.getModel().getValueAt(i, 3).toString(),
							txtIdEmbargo.getText()));					
				}
				cuentas.stream().forEach(x -> sessionStatefull.insert(x));
				sessionStatefull.insert(embargo);
				personas.stream().forEach(x -> sessionStatefull.insert(x));
				sessionStatefull.fireAllRules();
				imprimir(embargo, personas, cuentas);
			}
		});
		button_1.setBounds(321, 572, 129, 23);
		pnlCrear.add(button_1);

		JPanel pnlCrearDian = new JPanel();
		tabbedPane.addTab("Crear embargo dian", null, pnlCrearDian, null);
		pnlCrearDian.setLayout(null);

		JLabel lblNewLabel = new JLabel("idEmbargo");
		lblNewLabel.setBounds(26, 21, 77, 14);
		pnlCrearDian.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("numProceso");
		lblNewLabel_1.setBounds(26, 96, 77, 14);
		pnlCrearDian.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("idAutoridad");
		lblNewLabel_3.setBounds(26, 58, 77, 14);
		pnlCrearDian.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("fechaOficio");
		lblNewLabel_4.setBounds(26, 132, 77, 14);
		pnlCrearDian.add(lblNewLabel_4);

		JLabel lblTipoembargo = new JLabel("tipoEmbargo");
		lblTipoembargo.setBounds(261, 21, 77, 14);
		pnlCrearDian.add(lblTipoembargo);

		JLabel lblNumcuentaagrario = new JLabel("numCuentaAgrario");
		lblNumcuentaagrario.setBounds(261, 57, 146, 14);
		pnlCrearDian.add(lblNumcuentaagrario);

		JLabel lblCiudadcuentaagrario = new JLabel("ciudadCuentaAgrario");
		lblCiudadcuentaagrario.setBounds(261, 93, 146, 14);
		pnlCrearDian.add(lblCiudadcuentaagrario);

		JLabel lblDepartamentocuentaagrario = new JLabel("departamentoCuentaAgrario");
		lblDepartamentocuentaagrario.setBounds(261, 132, 146, 14);
		pnlCrearDian.add(lblDepartamentocuentaagrario);

		textField = new JTextField();
		textField.setBounds(113, 18, 143, 20);
		pnlCrearDian.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(113, 55, 143, 20);
		pnlCrearDian.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(113, 93, 143, 20);
		pnlCrearDian.add(textField_2);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(113, 129, 143, 20);
		pnlCrearDian.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(417, 18, 177, 20);
		pnlCrearDian.add(textField_5);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(417, 58, 177, 20);
		pnlCrearDian.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(417, 94, 177, 20);
		pnlCrearDian.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(417, 133, 177, 20);
		pnlCrearDian.add(textField_9);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 175, 599, 189);
		pnlCrearDian.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion", "resEmbargo",
						"fechaResolucion", "montoDemandado" }));
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton = new JButton("Cargar embargo");
		btnNewButton.setBounds(148, 572, 111, 23);
		pnlCrearDian.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Aplicar embargo");
		btnNewButton_1.setBounds(321, 572, 129, 23);
		pnlCrearDian.add(btnNewButton_1);

		JPanel pnlConsultas = new JPanel();
		tabbedPane.addTab("Consultas", null, pnlConsultas, null);
		pnlConsultas.setLayout(null);

		textField_10 = new JTextField();
		textField_10.setBounds(160, 60, 143, 20);
		pnlConsultas.add(textField_10);
		textField_10.setColumns(10);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(160, 104, 143, 20);
		pnlConsultas.add(textField_11);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(21, 180, 577, 415);
		pnlConsultas.add(scrollPane_2);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(160, 143, 143, 20);
		pnlConsultas.add(textField_12);

		JButton btnConsutar = new JButton("Consutar");
		btnConsutar.setBounds(99, 26, 89, 23);
		pnlConsultas.add(btnConsutar);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(10, 60, 143, 20);
		pnlConsultas.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(10, 104, 143, 20);
		pnlConsultas.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(10, 143, 143, 20);
		pnlConsultas.add(textField_15);

		JButton btnConsultarEmbargo = new JButton("Consultar embargo id");
		btnConsultarEmbargo.setBounds(388, 11, 152, 23);
		pnlConsultas.add(btnConsultarEmbargo);

		JButton btnConsultarAutoridadId = new JButton("Consultar autoridad id");
		btnConsultarAutoridadId.setBounds(388, 45, 152, 23);
		pnlConsultas.add(btnConsultarAutoridadId);

		JButton btnConsultarPersonaId = new JButton("Consultar persona id");
		btnConsultarPersonaId.setBounds(388, 79, 152, 23);
		pnlConsultas.add(btnConsultarPersonaId);

		textField_16 = new JTextField();
		textField_16.setBounds(368, 112, 186, 20);
		pnlConsultas.add(textField_16);
		textField_16.setColumns(10);

		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(368, 143, 186, 20);
		pnlConsultas.add(textField_17);
	}

	public void imprimir(Embargo embargo, ArrayList<Persona> personas, ArrayList<Cuenta> cuentas) {

		System.out.println("Embargo de tipo: " + embargo.getTipo() + " por un valor: " + embargo.getMontoAEmbargar());
		System.out.println("=============================================");
		for (Persona persona : personas) {
			System.out.println("Persona: " + persona.getTipoId() + " con identificacion: " + persona.getIdPersona());
			System.out.println("Con un monto a Embargar de: " + persona.getMontoAEmbargar());
			System.out.println("La(s) siguiente(s) cuenta(s)  ");
			System.out.println("-------------------------------------------------");
			if (persona.getMontoAEmbargar().compareTo(persona.getMontoEmbargado()) == 1) {
				cuentas.stream().filter(c -> c.getIdPersona().equalsIgnoreCase(persona.getIdPersona()))
						.forEach(c -> c.setEstado(EstadoCuenta.BLOQUEADA));
			}
			for (Cuenta cuenta : cuentas) {
				if (cuenta.getIdPersona().equals(persona.getIdPersona())) {
					if (persona.getMontoEmbargado().compareTo(new BigDecimal(0)) > 0) {
						System.out.println("La cuenta:" + cuenta.getIdCuenta() + " de:" + cuenta.getTipo() + " de "
								+ cuenta.getSubtipo());
						System.out.println("Fecha de creacion: " + cuenta.getFechaCreacion());
						System.out.println("Embargada por un monto de: " + cuenta.getMontoEmbargado());
						System.out.println("Con saldo a la fecha de:" + cuenta.getSaldoCuentaFecha());
						System.out.println(" Estado de la cuenta: " + cuenta.getEstado());
						System.out.println("  en base a las siguientes leyes:");
					} else {
						System.out.println("La cuenta:" + cuenta.getIdCuenta() + " de:" + cuenta.getTipo() + " de "
								+ cuenta.getSubtipo());
						System.out.println("Con saldo a la fecha de:" + cuenta.getSaldoCuentaFecha());
						System.out.println("No se puede embargar");
						System.out.println("Estado de la cuenta: " + cuenta.getEstado());
						if (cuenta.getSaldoCuentaFecha().compareTo(new BigDecimal(0)) == 0) {
							System.out.println("Saldo insuficiente");
						}
						System.out.println("  en base a las siguientes leyes:");
					}
					for (String regla : cuenta.getReglas()) {
						System.out.println("\t" + regla);
					}
					System.out.println("-------------------------------------------------");
				}
			}
			BigDecimal montoPorEmbargar = persona.getMontoAEmbargar().subtract(persona.getMontoEmbargado());
			if (montoPorEmbargar.compareTo(new BigDecimal(0)) == 1) {
				System.out.println("La(s) cuenta(s) de la Persona con identificacion: " + persona.getIdPersona()
						+ " fueron bloqueada(s)");
				System.out.println("Por un faltante por embargar de: " + montoPorEmbargar);
			}
			System.out.println("=============================================");
		}
	}

}
