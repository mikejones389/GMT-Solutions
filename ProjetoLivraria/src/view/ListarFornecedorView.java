package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import dao.FornecedorDAO;
import model.Fornecedor;
import model.FornecedorTableModel;

public class ListarFornecedorView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	FornecedorTableModel tableModel = new FornecedorTableModel();
	FornecedorDAO fd = new FornecedorDAO();
	String rows[][] = {};
	String headers[] = {};
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
		int cdList = 2;
		fornecedores = (List<Fornecedor>) fd.Listar(cdList);
		Fornecedor f = new Fornecedor();
		for (int i = 0; i < fornecedores.size(); i++) {
			tableModel.addROw(fornecedores.get(i));
		}

		JPanel panelSuperior = new JPanel();
		JLabel titulo = new JLabel("Fornecedores Cadastrados", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		panelSuperior.add(titulo);
		JPanel panelCentral = new JPanel();
		JLabel cdLivro = new JLabel("Cï¿½digo", SwingConstants.CENTER);
		cdLivro.setFont(new Font("Arial", Font.BOLD, 30));
		JLabel nmLivro = new JLabel("Tï¿½tulo", SwingConstants.HORIZONTAL);
		nmLivro.setFont(new Font("Arial", Font.BOLD, 30));
		JScrollPane scrollPane = new JScrollPane(jtFornecedor);
		JButton jbDesativar = new JButton("Desativar");
		jbDesativar.addActionListener(this);
		jbDesativar.setActionCommand("deletar");
		JButton jbGerarArq = new JButton("Gerar Arquivo");
		jbGerarArq.addActionListener(this);
		jbGerarArq.setActionCommand("gerar arquivo");
		JButton jbAtualizar = new JButton("Atualizar");
		jbAtualizar.addActionListener(this);
		jbAtualizar.setActionCommand("atualizar");
		JButton jbAtivar = new JButton("Ativar");
		jbAtivar.addActionListener(this);
		jbAtivar.setActionCommand("ativar");
		JPanel panelWest = new JPanel();
		JPanel panelEast = new JPanel();
		panelWest.setLayout(new GridLayout(2,1));
		panelEast.setLayout(new GridLayout(2,1));
		this.setLayout(new BorderLayout());
		this.add(panelSuperior, BorderLayout.NORTH);
		panelCentral.add(scrollPane, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		panelWest.add(jbDesativar);
		panelWest.add(jbAtualizar);
		panelEast.add(jbAtivar);
		panelEast.add(jbGerarArq);
		this.add(panelWest, BorderLayout.WEST);
		this.add(panelEast, BorderLayout.EAST);
	}
	
	public void desativar() {
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = fornecedores.get(jtFornecedor.getSelectedRow()).getCodigo();
		FornecedorDAO fd = new FornecedorDAO();
		fd.desativar(cd);
	}

	public void gerarArq() {
		FornecedorDAO fd = new FornecedorDAO();
		n++;
		Date data = new Date(System.currentTimeMillis());
		String arq = "" + n + "RelatórioDeFornecedores-" + data + ".txt";
		ArrayList texto = (ArrayList) fornecedores;
		if (fd.gerarArq(arq, texto)) {
			JOptionPane.showMessageDialog(null, "Arquivo gerado com Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao gerar Arquivo");
		}
	}

	public void atualizar() {
		if (jtFornecedor.getSelectedRow() != -1) {
			MouseEvent evt = null;
			tableModelMouseClicked(evt);
			int id = fornecedores.get(jtFornecedor.getSelectedRow()).getCodigo();
			AtualizarFornecedorView afv = new AtualizarFornecedorView(id);
			this.add(afv);
			this.revalidate();
			this.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Selecione algum fornecedor da tabela");
		}
	}

	public void ativar() {
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = fornecedores.get(jtFornecedor.getSelectedRow()).getCodigo();
		FornecedorDAO fd = new FornecedorDAO();
		fd.ativar(cd);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("deletar")) {
			desativar();
		} else if (e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		} else if (e.getActionCommand().equals("atualizar")) {
			atualizar();
		} else if (e.getActionCommand().equals("ativar")) {
			ativar();
		}
	}

	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		cdFornecedor = jtFornecedor.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
	}
}