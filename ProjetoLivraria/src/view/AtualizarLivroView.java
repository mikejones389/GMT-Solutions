package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import controller.LivroController;
import dao.FornecedorDAO;
import dao.LivroDAO;
import model.Fornecedor;
import model.FornecedorTableModel;
import model.Livro;

public class AtualizarLivroView extends JFrame implements ActionListener {
	private JTextField nomeLivroField;
	private JTextField nomeAutorField;
	private JTextField editoraField;
	private JTextField generoField;
	private JTextField anoField;
	private JTextField edicaoField;
	private JTextField precoVendaField;
	private JTextField cdFornecedorField;
	private JTextField psEstoqueField;
	private JTextField qntLivroField;
	private JTextField imgLinkField;
	JFrame frameList;
	private Livro livro;
	ImageIcon imagem1 = new ImageIcon(getClass().getResource("teste.png"));
	JLabel imagem = new JLabel(imagem1);
	FornecedorTableModel tableModel = new FornecedorTableModel();
	JTable jtFornecedor;
	private int id;
	ArrayList<Livro> livros;

	public AtualizarLivroView(int id) {
		this.setSize(800,500);
		this.setResizable(false);
		this.setVisible(true);
		this.id=id;
		construir();		
	}
	
	public void construir() {
		LivroDAO ld = new LivroDAO();
		ld.buscar(id);
		livros = (ArrayList<Livro>) ld.buscar(id);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		JLabel titulo = new JLabel("Cadastro de Livros");
		titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
		gbc.gridx=0; 
		gbc.gridy=0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.LINE_START;
		gbc.anchor = GridBagConstraints.NORTH;
		add(titulo,gbc);
		JLabel cdFornecedorLabel = new JLabel ("Código do Fornecedor");
		cdFornecedorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(cdFornecedorLabel, gbc);
		cdFornecedorField = new JTextField(30);
		cdFornecedorField.setEditable(false);
		String cdFornecedor = String.valueOf(livros.get(0).getCdFornecedor());
		cdFornecedorField.setText(cdFornecedor);
		cdFornecedorField.setEditable(false);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(cdFornecedorField, gbc);
		JLabel nomeLivroLabel = new JLabel("Nome ");
		nomeLivroLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.LINE_END;
		gbc.insets= new Insets(5,0,0,5);
		add(nomeLivroLabel, gbc); 	
		nomeLivroField = new JTextField(30);
		nomeLivroField.setText(livros.get(0).getNomeLivro());
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(nomeLivroField, gbc);
		JLabel nomeAutorLabel = new JLabel("Autor ");
		nomeAutorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(nomeAutorLabel, gbc);
		nomeAutorField = new JTextField(30);
		nomeAutorField.setText(livros.get(0).getAutorLivro());
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(nomeAutorField, gbc);
		JLabel editoraLabel = new JLabel ("Editora");
		editoraLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(editoraLabel, gbc);
		editoraField = new JTextField(30);
		editoraField.setText(livros.get(0).getEditoraLivro());
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(editoraField, gbc);
		JLabel generoLabel = new JLabel ("Gênero");
		generoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=5;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(generoLabel, gbc);
		generoField = new JTextField(30);
		generoField.setText(livros.get(0).getGeneroLivro());
		gbc.gridx=1;
		gbc.gridy=5;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(generoField, gbc);
		JLabel anoLabel = new JLabel ("Ano de Lançamento");
		anoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=6;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(anoLabel, gbc);
		anoField = new JTextField(30);
		String ano = String.valueOf(livros.get(0).getAnoLivro());
		anoField.setText(ano);
		gbc.gridx=1;
		gbc.gridy=6;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(anoField, gbc);
		JLabel edicaoLabel = new JLabel ("Edição");
		edicaoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=7;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(edicaoLabel, gbc);
		edicaoField = new JTextField(30);
		String edicao = String.valueOf(livros.get(0).getEdicaoLivro());
		edicaoField.setText(edicao);
		gbc.gridx=1;
		gbc.gridy=7;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(edicaoField, gbc);
		JLabel precoVendaLabel = new JLabel ("Preço de Venda");
		precoVendaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=8;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(precoVendaLabel, gbc);
		precoVendaField = new JTextField(30);
		String preco = String.valueOf(livros.get(0).getPrecoVenda());
		precoVendaField.setText(preco);
		gbc.gridx=1;
		gbc.gridy=8;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(precoVendaField, gbc);
		JLabel qntLivroLabel = new JLabel ("Quantidade");
		qntLivroLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=9;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(qntLivroLabel, gbc);
		qntLivroField = new JTextField(30);
		String qnt = String.valueOf(livros.get(0).getQntLivro());
		qntLivroField.setText(qnt);
		gbc.gridx=1;
		gbc.gridy=9;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(qntLivroField, gbc);
		JLabel imgLinkLabel = new JLabel ("Link Imagem");
		imgLinkLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=10;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(imgLinkLabel, gbc);
		imgLinkField = new JTextField(30);
		imgLinkField.setText(livros.get(0).getLinkImg());
		gbc.gridx=1;
		gbc.gridy=10;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(imgLinkField, gbc);			
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(this);
		botaoSalvar.setActionCommand("salvar");
		JLabel cr = new JLabel("Copyright �2019 Todos os direitos reservados | GMT Group");
		cr.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx=1;
		gbc.gridy=12;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets=new Insets(10,0,0,5);
		gbc.anchor = GridBagConstraints.LINE_START;
		add(botaoSalvar, gbc);
		gbc.gridx=1;
		gbc.gridy=12;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets=new Insets(10,135,0,5);
		gbc.anchor = GridBagConstraints.LINE_START;
		add(cr, gbc);
	}
		
	public void salvar() {
		Livro livro = new Livro();
		livro.setNomeLivro(nomeLivroField.getText());
		livro.setAutorLivro(nomeAutorField.getText());
		livro.setEditoraLivro(editoraField.getText());
		livro.setGeneroLivro(generoField.getText());
		livro.setAnoLivro(Integer.parseInt(anoField.getText()));
		livro.setEdicaoLivro(Integer.parseInt(edicaoField.getText()));
		livro.setPrecoVenda(Double.parseDouble(precoVendaField.getText()));
		livro.setCdFornecedor(Integer.parseInt(cdFornecedorField.getText()));
		livro.setQntLivro(Integer.parseInt(qntLivroField.getText()));
		livro.setLinkImg(imgLinkField.getText());		
		LivroController livroController = new LivroController();
		try {
			if(livroController.atualizar(livro,id)) {
				JOptionPane.showMessageDialog(null, "Cadastro de livro realizado com sucesso");
				this.dispose();
				this.setVisible(false);
				ListarLivroView llv = new ListarLivroView();
				llv.revalidate();
				llv.repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "Problema ao realizar cadastro de livro!");
			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("salvar")) {
			salvar();
		}
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		int i = jtFornecedor.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
		cdFornecedorField.setText(jtFornecedor.getValueAt(i, 0).toString());
	}
}