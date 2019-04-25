package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.LoginSingleton;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUILogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdAutoridad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUILogin frame = new GUILogin();
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
	public GUILogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(202, 12, 50, 15);
		contentPane.add(lblInicio);
		
		JLabel lblIdAutoridad = new JLabel("Id Autoridad:");
		lblIdAutoridad.setBounds(26, 56, 101, 15);
		contentPane.add(lblIdAutoridad);
		
		txtIdAutoridad = new JTextField();
		txtIdAutoridad.setBounds(151, 54, 176, 19);
		contentPane.add(txtIdAutoridad);
		txtIdAutoridad.setColumns(10);
		
		JLabel lblCertificado = new JLabel("Certificado:");
		lblCertificado.setBounds(26, 103, 101, 15);
		contentPane.add(lblCertificado);
		
		JLabel lblCertificadoautser = new JLabel("certificadoAut.ser");
		lblCertificadoautser.setBounds(151, 103, 151, 15);
		contentPane.add(lblCertificadoautser);
		
		JButton btnCrearAutoridad = new JButton("Crear autoridad");
		btnCrearAutoridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUICrearAutoridad gui=new GUICrearAutoridad();
				gui.setVisible(true);
			}
		});
		btnCrearAutoridad.setBounds(136, 189, 166, 25);
		contentPane.add(btnCrearAutoridad);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginSingleton.getInstance(txtIdAutoridad.getText());
				GUIEmbargos gui=new GUIEmbargos(txtIdAutoridad.getText());
				gui.setVisible(true);
			}
		});
		btnIniciarSesion.setBounds(151, 152, 139, 25);
		contentPane.add(btnIniciarSesion);
	}

}
