package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.EmbargosController;
import modelo.Autoridad;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUICrearAutoridad extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdAutoridad;
	private JTextField txtTipoAutoridad;
	private JTextField txtNombreAutoridad;
	private JTextField txtDireccionAutoridad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICrearAutoridad frame = new GUICrearAutoridad();
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
	public GUICrearAutoridad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCrearAutoridad = new JLabel("Crear Autoridad");
		lblCrearAutoridad.setBounds(189, 12, 123, 15);
		contentPane.add(lblCrearAutoridad);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 53, 70, 15);
		contentPane.add(lblId);
		
		txtIdAutoridad = new JTextField();
		txtIdAutoridad.setBounds(62, 51, 114, 19);
		contentPane.add(txtIdAutoridad);
		txtIdAutoridad.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 98, 70, 15);
		contentPane.add(lblTipo);
		
		txtTipoAutoridad = new JTextField();
		txtTipoAutoridad.setBounds(62, 96, 114, 19);
		contentPane.add(txtTipoAutoridad);
		txtTipoAutoridad.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(220, 53, 70, 15);
		contentPane.add(lblNombre);
		
		txtNombreAutoridad = new JTextField();
		txtNombreAutoridad.setBounds(311, 51, 182, 19);
		contentPane.add(txtNombreAutoridad);
		txtNombreAutoridad.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(220, 98, 70, 15);
		contentPane.add(lblDireccion);
		
		txtDireccionAutoridad = new JTextField();
		txtDireccionAutoridad.setBounds(311, 96, 182, 19);
		contentPane.add(txtDireccionAutoridad);
		txtDireccionAutoridad.setColumns(10);
		
		JButton btnCrearAutoridad = new JButton("Crear autoridad");
		btnCrearAutoridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				EmbargosController.guardarAutoridad(new Autoridad(txtIdAutoridad.getText(), txtTipoAutoridad.getText(), txtNombreAutoridad.getText(), txtDireccionAutoridad.getText()));
				dispose();
			}
		});
		btnCrearAutoridad.setBounds(91, 176, 147, 25);
		contentPane.add(btnCrearAutoridad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(314, 176, 117, 25);
		contentPane.add(btnCancelar);
	}
}
