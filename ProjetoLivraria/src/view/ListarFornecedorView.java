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

import dao.FornecedorDAO;
import model.Fornecedor;
import model.FornecedorTableModel;

public class ListarFornecedorView extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FornecedorTableModel tableModel = new FornecedorTableModel();
	FornecedorDAO fd = new FornecedorDAO();
	String rows[][]= {};
	String headers[]= {};
	JTable jtFornecedor = new JTable(new DefaultTableModel(rows, headers));
	int cdFornecedor;
	List<Fornecedor> fornecedores;
	int n;
	public ListarFornecedorView() {
		jtFornecedor.setModel(tableModel);
		
		janelaPrincipal();
	}
	
	public void janelaPrincipal() {
		FornecedorDAO fd = new FornecedorDAO();
		
		
		fornecedores = (List<Fornecedor>) fd.Listar();
		Fornecedor f = new Fornecedor();
				
		for (int i = 0; i < fornecedores.size(); i++) {
			System.out.println(fornecedores.get(i).getNmFornecedor());
			System.out.println(fornecedores.get(i).getTelefone());
			
			tableModel.addROw(fornecedores.get(i));
			
			
		}
		
		JPanel panelSuperior = new JPanel();
//		panelSuperior.setLayout(new BorderLayout());
		
		JLabel titulo = new JLabel ("Fornecedores Cadastrados", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		panelSuperior.add(titulo);
		
		JPanel panelCentral = new JPanel();
//		panelCentral.setLayout(new GridLayout(1,2));
//		panelCentral.setBackground(Color.BLACK);
		
		JLabel cdLivro = new JLabel("Cï¿½digo", SwingConstants.CENTER);
		cdLivro.setFont(new Font("Arial", Font.BOLD, 30));
				
		JLabel nmLivro = new JLabel("Tï¿½tulo", SwingConstants.HORIZONTAL);
		nmLivro.setFont(new Font("Arial", Font.BOLD, 30));
				
		//JTable jtLivros = new JTable(new DefaultTableModel(rows, headers));
		JScrollPane scrollPane = new JScrollPane(jtFornecedor);
				
		JButton jbDeletar = new JButton("Deletar");
		jbDeletar.addActionListener(this);
		jbDeletar.setActionCommand("deletar");
	 
		JButton jbGerarArq = new JButton("Gerar Arquivo");
		jbGerarArq.addActionListener(this);
		jbGerarArq.setActionCommand("gerar arquivo");
		
		this.setLayout(new BorderLayout());
		this.add(panelSuperior, BorderLayout.NORTH);
		panelCentral.add(scrollPane,BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(jbDeletar, BorderLayout.WEST);
		this.add(jbGerarArq, BorderLayout.EAST);
		
	}
	
	public void deletar() {
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = fornecedores.get(jtFornecedor.getSelectedRow()).getCodigo();
		FornecedorDAO fd = new FornecedorDAO();
		fd.deletar(cd);
	}
	
	public void gerarArq() {
		FornecedorDAO fd = new FornecedorDAO();
		n++;
		Date data = new Date(System.currentTimeMillis());
		String arq = ""+n+"RelatórioDeFornecedores-"+data+".txt";
		ArrayList texto = (ArrayList) fornecedores;
		if(fd.gerarArq(arq, texto)) {
			JOptionPane.showMessageDialog(null, "Arquivo gerado com Sucesso");
		}
		else {
			JOptionPane.showMessageDialog(null, "Falha ao gerar Arquivo");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("deletar")) {
			deletar();
			ListarFornecedorView lfv = new ListarFornecedorView();
			lfv.repaint();
			lfv.validate();
		}
		else if(e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		}
		
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		cdFornecedor = jtFornecedor.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
		System.out.println(cdFornecedor);
	}
}
