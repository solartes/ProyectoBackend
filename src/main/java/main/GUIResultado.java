package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.EmbargosController;
import modelo.Demandado;
import modelo.Embargo;
import modelo.Intento;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUIResultado extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUIResultado(String mensajePasarela,String mensaje, Embargo embargo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 508, 313);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setText(mensajePasarela+mensaje);
		
		JButton btnNewButton = new JButton("Aplicar medida");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Demandado demandado:embargo.getDemandados()) {
					ArrayList<Intento> intentos=new ArrayList<>();
					Intento intento=new Intento(LocalDate.now(), true,mensajePasarela, demandado.getCuentas());
					intentos.add(intento);
					demandado.setIntentos(intentos);	
				}
				embargo.setEmbargoProcesado(true);
				EmbargosController.guardarEmbargo(embargo);
				dispose();
			}
		});
		btnNewButton.setBounds(100, 409, 149, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("No aplicar medida");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				for(Demandado demandado:embargo.getDemandados()) {
					ArrayList<Intento> intentos=new ArrayList<>();
					Intento intento=new Intento(LocalDate.now(), false,mensajePasarela, demandado.getCuentas());
					intentos.add(intento);
					demandado.setIntentos(intentos);					
				}
				embargo.setEmbargoProcesado(false);
				EmbargosController.guardarEmbargo(embargo);
				dispose();
			}
		});
		btnNewButton_1.setBounds(308, 409, 149, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnAplicarD = new JButton("Aplicar d1");
		btnAplicarD.setBounds(68, 342, 117, 25);
		contentPane.add(btnAplicarD);
		
		JButton btnAplicarD_1 = new JButton("Aplicar d2");
		btnAplicarD_1.setBounds(219, 342, 117, 25);
		contentPane.add(btnAplicarD_1);
		
		JButton btnAplicarD_2 = new JButton("Aplicar d3");
		btnAplicarD_2.setBounds(369, 342, 117, 25);
		contentPane.add(btnAplicarD_2);
	}

	
	
}
