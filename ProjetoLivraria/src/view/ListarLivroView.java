package view;

import java.awt.BorderLayout;
import java.awt.Font;
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
	Livro l;
	List<Livro> livros;
	int n;
	
	public ListarLivroView() {
		jtLivros.setModel(tableModel);
		janelaPrincipal();
	}
	
	public void janelaPrincipal(){
		livros = (ArrayList<Livro>) ld.Listar();
		for (int i = 0; i < livros.size(); i++) {
			tableModel.addRow(livros.get(i));
		}		
		
		JPanel panelSuperior = new JPanel();		
		JLabel titulo = new JLabel("Livros Cadastrados",SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		panelSuperior.add(titulo);
		
		JPanel panelCentral = new JPanel();
		JLabel cdLivro = new JLabel("C�digo", SwingConstants.CENTER);
		cdLivro.setFont(new Font("Arial", Font.BOLD, 30));
		JLabel nmLivro = new JLabel("T�tulo", SwingConstants.HORIZONTAL);
		nmLivro.setFont(new Font("Arial", Font.BOLD, 30));
		
		JPanel panelButton1 = new JPanel();
		JButton btGerarArq = new JButton("Gerar Arquivo");
		btGerarArq.addActionListener(this);
		btGerarArq.setActionCommand("gerar arquivo");
		//btGerarArq.setSize(100, 100);
		panelButton1.add(btGerarArq);
		
		JPanel panelButton2 = new JPanel();
		JButton btAtualizar = new JButton("Atualizar");
		btAtualizar.addActionListener(this);
		btAtualizar.setActionCommand("atualizar");
		panelButton2.add(btAtualizar);
		
		JScrollPane scrollPane = new JScrollPane(jtLivros);
		this.setLayout(new BorderLayout());
		this.add(panelSuperior, BorderLayout.NORTH);
		panelCentral.add(scrollPane,BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelButton1, BorderLayout.WEST);
		this.add(panelButton2, BorderLayout.EAST);
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
	
	public void atualizar() {
		if (jtLivros.getSelectedRow() != -1) {
			MouseEvent evt = null;
			tableModelMouseClicked(evt);
			int id = livros.get(jtLivros.getSelectedRow()).getCdLivro();
			AtualizarLivroView alv = new AtualizarLivroView(id);
			this.add(alv);
			this.revalidate();	
			this.repaint();
		}
		else {
			JOptionPane.showMessageDialog(null, "Selecione algum livro da tabela");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		}
		else if(e.getActionCommand().equals("atualizar")) {
			atualizar();
		}
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		cdLivro = jtLivros.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
	}	
}