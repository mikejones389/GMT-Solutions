package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dao.LivroDAO;
import model.Livro;
import model.LivroTableModel;

public class ListarLivroView extends JPanel implements ActionListener{
	
	LivroTableModel tableModel = new LivroTableModel();
	LivroDAO ld = new LivroDAO();
	String rows[][]= {};
	String headers[] = {};
	JTable jtLivros = new JTable(new DefaultTableModel(rows, headers));
	int cdLivro;
	List<Livro> livros;
	int n;
	public ListarLivroView() {
		jtLivros.setModel(tableModel);
		
		janelaPrincipal();
		
		
	}
	public void janelaPrincipal(){
		
		//String i = ld.Listar();
		//LivroDAO ld = new LivroDAO();
		
		
		livros = (ArrayList<Livro>) ld.Listar();
		//Livro l = new Livro();	
		System.out.println("Listar livro view");
		for (int i = 0; i < livros.size(); i++) {
			System.out.println(livros.get(i).getNomeLivro());
			System.out.println(livros.get(i).getPrecoVenda());
					
			
			tableModel.addRow(livros.get(i));
		}
		//System.out.println(livro.getNomeLivro());
		
		
		//String dados[] = ld.Listar();
		//System.out.println(dados);
		//String rows[][] = {{teste, "test2"}, {"jsgdljag","dgsgeg"}};
		
		//String nmL = livro.getNomeLivro();
		//String autorLivro = livro.getAutorLivro();
				
		JPanel panelSuperior = new JPanel();
//		panelSuperior.setLayout(new BorderLayout());
		//panelSuperior.setBackground(Color.BLUE);
				
		JLabel titulo = new JLabel("Livros Cadastrados",SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		panelSuperior.add(titulo);
	
		JPanel panelCentral = new JPanel();
//		panelCentral.setLayout(new GridLayout(1,2));
//		panelCentral.setBackground(Color.BLACK);
		
		JLabel cdLivro = new JLabel("C�digo", SwingConstants.CENTER);
		cdLivro.setFont(new Font("Arial", Font.BOLD, 30));
				
		JLabel nmLivro = new JLabel("T�tulo", SwingConstants.HORIZONTAL);
		nmLivro.setFont(new Font("Arial", Font.BOLD, 30));
		
		JButton btDeletar= new JButton("Deletar");
		btDeletar.addActionListener(this);
		btDeletar.setActionCommand("deletar");
		
		JButton btGerarArq = new JButton("Gerar Arquivo");
		btGerarArq.addActionListener(this);
		btGerarArq.setActionCommand("gerar arquivo");
		
		JButton btAtualizar = new JButton("Atualizar");
		btAtualizar.addActionListener(this);
		btAtualizar.setActionCommand("atualizar");
		
		
		//JTable jtLivros = new JTable(new DefaultTableModel(rows, headers));
		JScrollPane scrollPane = new JScrollPane(jtLivros);
		
		JPanel panelWest = new JPanel();
		panelWest.setLayout(new GridLayout(2,1));
		this.setLayout(new BorderLayout());
		this.add(panelSuperior, BorderLayout.NORTH);
		panelCentral.add(scrollPane,BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		panelWest.add(btDeletar);
		panelWest.add(btAtualizar);
		this.add(panelWest, BorderLayout.WEST);
		this.add(btGerarArq, BorderLayout.EAST);
		
		//int linhaSel = jtLivros.getSelectedRow(); int colunaSel = jtLivros.getSelectedColumn();
	}

	public void deletar() {
		
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = livros.get(jtLivros.getSelectedRow()).getCdLivro();
		LivroDAO ld = new LivroDAO();
		
		ld.deletar(cd);
	}
	
	public void gerarArq() {
		LivroDAO ld = new LivroDAO();
		n++;
		Date data = new Date(System.currentTimeMillis());
		String arq = ""+n+"RelatorioDeLivros-"+data+".txt";
		ArrayList texto = (ArrayList) livros;
		if(ld.gerarArq(arq, texto)) {
			JOptionPane.showMessageDialog(null, "Arquivo gerado com Sucesso");	
		}
		else {
			JOptionPane.showMessageDialog(null, "Falha ao gerar Arquivo");
		}
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("deletar")) {
			deletar();
			//this.repaint();
			//this.validate();
		}
		else if(e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		}
		else if(e.getActionCommand().equals("atualizar")) {
			if (jtLivros.getSelectedRow() != -1) {
				MouseEvent evt = null;
				tableModelMouseClicked(evt);
				int id = jtLivros.getSelectedRow();
				AtualizarLivroView alv = new AtualizarLivroView(id);
				this.add(alv);
				this.revalidate();	
				this.repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "Selecione algum livro da tabela");
			}
		}
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		System.out.println("Teste");
		cdLivro = jtLivros.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
		System.out.println(cdLivro);
	}
	
	
}
