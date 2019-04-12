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
import modelo.Demandado;
import modelo.DemandadoDian;
import modelo.Demandante;
import modelo.Embargo;
import modelo.EmbargoCoactivo;
import modelo.EmbargoJudicial;
import modelo.Persona;
import simulacion.Pasarela;
import simulacion.SimulacionCasos;
import simulacion.SimulacionPasarelas;
import util.SessionHelper;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class GUIEmbargos extends JFrame {

	private SessionHelper session;
	private EmbargoJudicial embargoJudicial;
	private EmbargoCoactivo embargoCoactivo;
	private JPanel contentPane;
	private JTextField txtIdEmbargoDian;
	private JTextField txtIdAutoridadDian;
	private JTextField txtNumProcesoDian;
	private JTextField txtFechaOficioDian;
	private JTextField txtTipoEmbargoDian;
	private JTextField txtNumCuentaAgrarioDian;
	private JTextField txtCiudadCuentaAgrarioDian;
	private JTextField txtDepartamentoCuentaAgrarioDian;
	private JTable tblDemandadosDian;
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
		////
		session=new SessionHelper();
		SimulacionPasarelas simulacionPasarela=new SimulacionPasarelas();
		////
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

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
		DefaultTableModel demandantesModel=new DefaultTableModel(null,
				new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion" });
		tblDemandantes.setModel(demandantesModel);
		scrollPane_3.setViewportView(tblDemandantes);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 392, 599, 151);
		pnlCrear.add(scrollPane_4);

		tblDemandados = new JTable();
		DefaultTableModel demandadosModel=new DefaultTableModel(null,
				new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion", "Monto Embargo" });
		tblDemandados.setModel(demandadosModel);
		scrollPane_4.setViewportView(tblDemandados);

		JButton button = new JButton("Cargar embargo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				embargoJudicial=(EmbargoJudicial) SimulacionCasos.generarEmbargoNormal();
				txtIdEmbargo.setText(embargoJudicial.getIdEmbargo());
				txtIdAutoridad.setText(embargoJudicial.getIdAutoridad());
				txtNumProceso.setText(embargoJudicial.getNumProceso());
				txtNumOficio.setText(embargoJudicial.getNumOficio());
				txtFechaOficio.setText(embargoJudicial.getFechaOficio().toString());
				txtTipoEmbargo.setText(embargoJudicial.getTipoEmbargo().toString());
				txtMontoEmbargo.setText(embargoJudicial.getMontoAEmbargar().toString());
				txtNumCuentaAgrario.setText(embargoJudicial.getNumCuentaAgrario());
				txtCiudadCuentaAgrario.setText(embargoJudicial.getCiudadCuentaAgrario());
				txtDepartamentoCuentaAgrario.setText(embargoJudicial.getDepartamentoCuentaAgrario());
				ArrayList<Demandante> demandantes=embargoJudicial.getDemandantes();
				
				DefaultTableModel demandantesModel=new DefaultTableModel(null,
						new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion" });
				tblDemandantes.setModel(demandantesModel);
				for (int i = 0; i < demandantes.size(); i++) {
					Demandante tmp=demandantes.get(i);
					demandantesModel.addRow(new String[] {tmp.getNombres(),tmp.getApellidos(),tmp.getTipoIdentificacion().toString(),tmp.getIdentificacion()});
				}
				
				DefaultTableModel demandadosModel=new DefaultTableModel(null,
						new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion", "Monto Embargo" });
				tblDemandados.setModel(demandadosModel);
				ArrayList<Demandado> demandados=embargoJudicial.getDemandados();
				for (int i = 0; i < demandados.size(); i++) {
					Demandado tmp=demandados.get(i);
					tblDemandados.setModel(demandadosModel);
					demandadosModel.addRow(new String[] {tmp.getNombres(),tmp.getApellidos(),tmp.getTipoIdentificacion().toString(),tmp.getIdentificacion(),tmp.getMontoAEmbargar().toString()});
				}			
			}
		});
		button.setBounds(113, 572, 143, 23);
		pnlCrear.add(button);

		JButton button_1 = new JButton("Aplicar embargo");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KieSession sessionStatefull= session.obtenerSesion();				
			    String mensajePasarela=simulacionPasarela.llamarPasarelas(embargoJudicial.getDemandados());
				sessionStatefull.insert(embargoJudicial);
				sessionStatefull.fireAllRules();
				session.cerrarSesion(sessionStatefull);
				GUIResultado gui=new GUIResultado(mensajePasarela,imprimir(embargoJudicial),embargoJudicial);
				gui.setVisible(true);
				
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

		txtIdEmbargoDian = new JTextField();
		txtIdEmbargoDian.setBounds(113, 18, 143, 20);
		pnlCrearDian.add(txtIdEmbargoDian);
		txtIdEmbargoDian.setColumns(10);

		txtIdAutoridadDian = new JTextField();
		txtIdAutoridadDian.setColumns(10);
		txtIdAutoridadDian.setBounds(113, 55, 143, 20);
		pnlCrearDian.add(txtIdAutoridadDian);

		txtNumProcesoDian = new JTextField();
		txtNumProcesoDian.setColumns(10);
		txtNumProcesoDian.setBounds(113, 93, 143, 20);
		pnlCrearDian.add(txtNumProcesoDian);

		txtFechaOficioDian = new JTextField();
		txtFechaOficioDian.setColumns(10);
		txtFechaOficioDian.setBounds(113, 129, 143, 20);
		pnlCrearDian.add(txtFechaOficioDian);

		txtTipoEmbargoDian = new JTextField();
		txtTipoEmbargoDian.setColumns(10);
		txtTipoEmbargoDian.setBounds(417, 18, 177, 20);
		pnlCrearDian.add(txtTipoEmbargoDian);

		txtNumCuentaAgrarioDian = new JTextField();
		txtNumCuentaAgrarioDian.setColumns(10);
		txtNumCuentaAgrarioDian.setBounds(417, 58, 177, 20);
		pnlCrearDian.add(txtNumCuentaAgrarioDian);

		txtCiudadCuentaAgrarioDian = new JTextField();
		txtCiudadCuentaAgrarioDian.setColumns(10);
		txtCiudadCuentaAgrarioDian.setBounds(417, 94, 177, 20);
		pnlCrearDian.add(txtCiudadCuentaAgrarioDian);

		txtDepartamentoCuentaAgrarioDian = new JTextField();
		txtDepartamentoCuentaAgrarioDian.setColumns(10);
		txtDepartamentoCuentaAgrarioDian.setBounds(417, 133, 177, 20);
		pnlCrearDian.add(txtDepartamentoCuentaAgrarioDian);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 175, 599, 189);
		pnlCrearDian.add(scrollPane_1);
		
		tblDemandadosDian = new JTable();
		DefaultTableModel demandadosDianModel=new DefaultTableModel(null,
				new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion", "Res Embargo","Fecha Resolucion","Monto Embargo" });
		tblDemandadosDian.setModel(demandadosDianModel);
		scrollPane_1.setViewportView(tblDemandadosDian);

		JButton btnNewButton = new JButton("Cargar embargo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				embargoCoactivo=(EmbargoCoactivo) SimulacionCasos.generarEmbargoDian();
				txtIdEmbargoDian.setText(embargoCoactivo.getIdEmbargo());
				txtIdAutoridadDian.setText(embargoCoactivo.getIdAutoridad());
				txtNumProcesoDian.setText(embargoCoactivo.getNumProceso());				
				txtFechaOficioDian.setText(embargoCoactivo.getFechaOficio().toString());
				txtTipoEmbargoDian.setText(embargoCoactivo.getTipoEmbargo().toString());				
				txtNumCuentaAgrarioDian.setText(embargoCoactivo.getNumCuentaAgrario());
				txtCiudadCuentaAgrarioDian.setText(embargoCoactivo.getCiudadCuentaAgrario());
				txtDepartamentoCuentaAgrarioDian.setText(embargoCoactivo.getDepartamentoCuentaAgrario());

				
				DefaultTableModel demandadosDianModel=new DefaultTableModel(null,
						new String[] { "Nombres", "Apellidos", "Tipo identificacion", "Identificacion", "Res Embargo","Fecha Resolucion","Monto Embargo" });
				tblDemandadosDian.setModel(demandadosDianModel);
				ArrayList<Demandado> demandados=embargoCoactivo.getDemandados();
				for (int i = 0; i < demandados.size(); i++) {
					DemandadoDian tmp=(DemandadoDian) demandados.get(i);
					tblDemandadosDian.setModel(demandadosDianModel);
					demandadosDianModel.addRow(new String[] {tmp.getNombres(),tmp.getApellidos(),tmp.getTipoIdentificacion().toString(),tmp.getIdentificacion(),tmp.getResEmbargo(),tmp.getFechaResolucion().toString(),tmp.getMontoAEmbargar().toString()});
				}	
			}
		});
		btnNewButton.setBounds(148, 572, 111, 23);
		pnlCrearDian.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Aplicar embargo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KieSession sessionStatefull= session.obtenerSesion();				
			    String mensajePasarela=simulacionPasarela.llamarPasarelas(embargoCoactivo.getDemandados());
				sessionStatefull.insert(embargoCoactivo);
				sessionStatefull.fireAllRules();
				session.cerrarSesion(sessionStatefull);
				GUIResultado gui=new GUIResultado(mensajePasarela,imprimir(embargoCoactivo),embargoCoactivo);
				gui.setVisible(true);
			}
		});
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
	
	public EmbargoJudicial getEmbargoJudicial() {
		return embargoJudicial;
	}

	public void setEmbargoJudicial(EmbargoJudicial embargoJudicial) {
		this.embargoJudicial = embargoJudicial;
	}

	public EmbargoCoactivo getEmbargoCoactivo() {
		return embargoCoactivo;
	}

	public void setEmbargoCoactivo(EmbargoCoactivo embargoCoactivo) {
		this.embargoCoactivo = embargoCoactivo;
	}

	public String imprimir(Embargo embargo) {
		// Create a stream to hold the output
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		System.out.println(
				"Embargo de tipo: " + embargo.getTipoEmbargo() + " del: " + embargo.getFechaOficio());
		System.out.println("=============================================");
		for (Demandado demandado : embargo.getDemandados()) {
			System.out.println("Persona: " + demandado.getTipoIdentificacion() + " con identificacion: "
					+ demandado.getIdentificacion());
			System.out.println("Con un monto a Embargar de: " + demandado.getMontoAEmbargar());
			System.out.println("La(s) siguiente(s) cuenta(s)  ");
			System.out.println("-------------------------------------------------");

			if (demandado.getMontoAEmbargar().compareTo(demandado.getMontoEmbargado()) == 1) {
				demandado.getCuentas().stream().forEach(c -> c.setEstado(EstadoCuenta.BLOQUEADA));
			}

			for (Cuenta cuenta : demandado.getCuentas()) {
				if (demandado.getMontoEmbargado().compareTo(new BigDecimal(0)) > 0) {
					System.out.println("La cuenta:" + cuenta.getIdCuenta() + " de:" + cuenta.getTipoCuenta() + " de "
							+ cuenta.getSubTipoCuenta());
					System.out.println("Fecha de creacion: " + cuenta.getFechaCreacion());
					System.out.println("Embargada por un monto de: " + cuenta.getMontoEmbargado());
					System.out.println("Con saldo a la fecha de:" + cuenta.getSaldoCuentaFecha());
					System.out.println(" Estado de la cuenta: " + cuenta.getEstado());
					System.out.println("  en base a las siguientes leyes:");
				} else {
					System.out.println("La cuenta:" + cuenta.getIdCuenta() + " de:" + cuenta.getTipoCuenta() + " de "
							+ cuenta.getSubTipoCuenta());
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
			BigDecimal montoPorEmbargar = demandado.getMontoAEmbargar().subtract(demandado.getMontoEmbargado());
			if (montoPorEmbargar.compareTo(new BigDecimal(0)) == 1) {
				System.out.println("La(s) cuenta(s) de la Persona con identificacion: " + demandado.getIdentificacion()
						+ " fueron bloqueada(s)");
				System.out.println("Por un faltante por embargar de: " + montoPorEmbargar);
			}
			System.out.println("=============================================");
		}
		System.out.flush();
		System.setOut(old);
		return baos.toString();
	}

}
