package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.EmbargosController;
import modelo.Demandado;
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
	public GUIResultado(String mensajePasarela,String mensaje, GUIEmbargos parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 429);
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
				for(Demandado demandado:parent.getEmbargo().getDemandados()) {
					ArrayList<Intento> intentos=new ArrayList<>();
					Intento intento=new Intento(LocalDate.now(), true,mensajePasarela, demandado.getCuentas());
					intentos.add(intento);
					demandado.setIntentos(intentos);	
				}
				EmbargosController.guardarEmbargo(parent.getEmbargo());
			}
		});
		btnNewButton.setBounds(112, 356, 149, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("No aplicar medida");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				for(Demandado demandado:parent.getEmbargo().getDemandados()) {
					ArrayList<Intento> intentos=new ArrayList<>();
					Intento intento=new Intento(LocalDate.now(), false,mensajePasarela, demandado.getCuentas());
					intentos.add(intento);
					demandado.setIntentos(intentos);					
				}
				EmbargosController.guardarEmbargo(parent.getEmbargo());
			}
		});
		btnNewButton_1.setBounds(327, 356, 149, 23);
		contentPane.add(btnNewButton_1);
	}

	
	
}
