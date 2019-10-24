package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.LivroDAO;
import model.Livro;

public class ListarLivroView extends JPanel{
	
	//LivroTableModel tableModel = new LivroTableModel();
	LivroDAO ld = new LivroDAO();
	
	
	public ListarLivroView() {
		//jtLivro.setModel(tableModel);
		
		janelaPrincipal();
		
		
	}
	public void janelaPrincipal(){
		
		//String i = ld.Listar();
		LivroDAO ld = new LivroDAO();
		
		
		List<Livro> livros = new ArrayList<Livro>();
		
		livros = (List<Livro>) ld.Listar();
		
		for (int i = 0; i < livros.size(); i++) {
			System.out.println(livros.get(i).getNomeLivro());

		}
		
		//System.out.println(livro.getNomeLivro());
		
		
		//String dados[] = ld.Listar();
		//System.out.println(dados);
		//String rows[][] = {{teste, "test2"}, {"jsgdljag","dgsgeg"}};
		
		//String nmL = livro.getNomeLivro();
		//String autorLivro = livro.getAutorLivro();
		
		String rows[][]= {};
//		for(int i=0;i<10;i++) {
//			rows[0][i]= "Teste";
//		}
	    String headers[] = { "Código do Livro", "Nome Do Livro" };
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		//panelSuperior.setBackground(Color.BLUE);
				
		JLabel titulo = new JLabel("Livros Cadastrados",SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		panelSuperior.add(titulo);
	
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1,2));
		panelCentral.setBackground(Color.BLACK);
		
		JLabel cdLivro = new JLabel("Código", SwingConstants.CENTER);
		cdLivro.setFont(new Font("Arial", Font.BOLD, 30));
		//panelCentral.add(cdLivro);
		
		JLabel nmLivro = new JLabel("Título", SwingConstants.HORIZONTAL);
		nmLivro.setFont(new Font("Arial", Font.BOLD, 30));
		//panelCentral.add(nmLivro);
		
		JTable jtLivros = new JTable(new DefaultTableModel(rows, headers));
		JScrollPane scrollPane = new JScrollPane(jtLivros);
				
		this.setLayout(new GridLayout(2,1));
		this.add(panelSuperior, BorderLayout.NORTH);
		panelCentral.add(scrollPane,BorderLayout.NORTH);
		this.add(panelCentral);
		
		
	}
	
	
	
}
