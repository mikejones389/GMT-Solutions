package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ListarLivroView extends JPanel{
	
	public ListarLivroView() {
		janelaPrincipal();
		
		
	}
	public void janelaPrincipal(){
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.setBackground(Color.BLUE);
				
		JLabel titulo = new JLabel("Livros Cadastrados",SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		panelSuperior.add(titulo);
	
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1,2));
		panelCentral.setBackground(Color.BLACK);
		
		JLabel cdLivro = new JLabel("Código", SwingConstants.CENTER);
		cdLivro.setFont(new Font("Arial", Font.BOLD, 30));
		panelCentral.add(cdLivro);
		
		JLabel nmLivro = new JLabel("Título", SwingConstants.HORIZONTAL);
		nmLivro.setFont(new Font("Arial", Font.BOLD, 30));
		panelCentral.add(nmLivro);
		
		this.setLayout(new GridLayout(10,1));
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral);
		
	}	
	
	
}
