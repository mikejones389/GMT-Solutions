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
		System.out.println("ENTREI NO ATUALIZAR VIEW");
		LivroDAO ld = new LivroDAO();
		ld.buscar(id);
		livros = (ArrayList<Livro>) ld.buscar(id);
		System.out.println(livros);
		System.out.println(livros.get(0).getNomeLivro());
		//Configura��o da estrutura do layout
		System.out.println("VOLTEI PRO ATUALIZAR VIEW");
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
		//String cdFornecedor = livro.getCdFornecedor();
		String cdFornecedor = String.valueOf(livros.get(0).getCdFornecedor());
		cdFornecedorField.setText(cdFornecedor);
		cdFornecedorField.setEditable(false);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(cdFornecedorField, gbc);
		
		JButton botaoFornecedor= new JButton("Selecionar");
		botaoFornecedor.addActionListener(this);
		botaoFornecedor.setActionCommand("listar");
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill = GridBagConstraints.RELATIVE;
		gbc.insets=new Insets(10,0,0,45);
		gbc.anchor = GridBagConstraints.LINE_END;
		add(botaoFornecedor, gbc);
		
		
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
		gbc.gridx=1;
		gbc.gridy=10;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(imgLinkField, gbc);			
		
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(this);
		botaoSalvar.setActionCommand("salvar");
		
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(this);
		botaoSair.setActionCommand("sair");
		
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
		add(botaoSair, gbc);
		
		
	}
	
	public void selecionarFornecedor() {
		int rows = 0;
		int headers = 0;
		jtFornecedor = new JTable(new DefaultTableModel(rows, headers));
		jtFornecedor.setModel(tableModel);
		
		
		frameList = new JFrame();
		frameList.setVisible(true);
		frameList.setSize(300,450);
		frameList.setResizable(false);
		frameList.setLocationRelativeTo(cdFornecedorField);
		frameList.setLayout(new GridLayout(3,1));
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new GridLayout(1,1));
		JLabel titulo = new JLabel("Selecione o Fornecedor", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		
		panelTitulo.add(titulo);
		frameList.add(panelTitulo);
		
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		FornecedorDAO fd = new FornecedorDAO();
		fornecedores = (List<Fornecedor>) fd.Listar();
		
				
		tableModel.removeAll();
		for (int i = 0; i < fornecedores.size(); i++) {
			System.out.println(fornecedores.get(i).getNmFornecedor());
			System.out.println(fornecedores.get(i).getTelefone());
			
			tableModel.addROw(fornecedores.get(i));
			
			
		}
		
		jtFornecedor.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
			int row = jtFornecedor.rowAtPoint(e.getPoint());
			
			row = jtFornecedor.getSelectedRow();

			jtFornecedor.getSelectionModel().setSelectionInterval(row, row);
			System.out.println("Cheguei no add mouse listener e linha = " + tableModel.getSelectRow(row));
			cdFornecedorField.setText(""+tableModel.getSelectRow(row));
			cdFornecedorField.setEditable(false);
			System.out.println(row);
			if(e.getButton() == MouseEvent.BUTTON3) {
			//popup.show(jtFornecedor, e.getX(), e.getY());
			}
			}
			});

		
		JScrollPane scrollPane = new JScrollPane(jtFornecedor);
		frameList.add(scrollPane);
		//jTFornecedoresMouseClicked();
		JPanel panelInferior = new JPanel();
		//panelInferior.setBackground(Color.cyan);
		panelInferior.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets = new Insets(10,0,10,10);
		
		JButton jbConcluir= new JButton("Concluir");
		jbConcluir.addActionListener(this);
		jbConcluir.setActionCommand("concluir");
		
		JButton jbCancelar= new JButton("Cancelar");
		jbCancelar.addActionListener(this);
		jbCancelar.setActionCommand("cancelar");
		
		
		panelInferior.add(jbConcluir, gbc);
		gbc.gridx=1;
		panelInferior.add(jbCancelar, gbc);
		frameList.add(panelInferior);
		
		//selecionar linha da tabela
		MouseEvent evt = null;
		tableModelMouseClicked(evt);	
		//Fornecedor fornecedor = ((List<Fornecedor>) jtFornecedor).get(rows);
		//cdFornecedorField.setText(fornecedor.getNmFantasia());
		
		
		
		
	}
	
	public void sair() {
		System.exit(0);
	}
	
	public void salvar() {
		Livro livro = new Livro();
		livro.setNomeLivro(nomeLivroField.getText());
		System.out.print("Nome do Livro: ");
		System.out.println(livro.getNomeLivro());
		livro.setAutorLivro(nomeAutorField.getText());
		//System.out.print("Nome do Autor: ");
		//System.out.println(nomeAutor);
		livro.setEditoraLivro(editoraField.getText());
		//System.out.print("Editora: ");
		//System.out.println(editora);
		livro.setGeneroLivro(generoField.getText());
		//System.out.print("G�nero: ");
		//System.out.println(genero);
		livro.setAnoLivro(Integer.parseInt(anoField.getText()));
		//System.out.print("Ano: ");
		//System.out.println(anoLivro);
		livro.setEdicaoLivro(Integer.parseInt(edicaoField.getText()));
		//System.out.print("Edi��o: ");
		//System.out.println(edicao);
		livro.setPrecoVenda(Double.parseDouble(precoVendaField.getText()));
		//System.out.print("Pre�o de Venda: ");
		//System.out.println(precoVenda);
		livro.setCdFornecedor(Integer.parseInt(cdFornecedorField.getText()));
		//System.out.print("C�digo do Fornecedor: ");
		//System.out.println(cdFornecedor);
		livro.setQntLivro(Integer.parseInt(qntLivroField.getText()));
		//System.out.print("Quantidade: ");
		//System.out.println(qntLivro);
		livro.setLinkImg(imgLinkField.getText());
		//System.out.print("Link Imagem: ");
		//System.out.println(imgLink);
		
		
		LivroController livroController = new LivroController();
		try {
			if(livroController.cadastro(livro)) {
				JOptionPane.showMessageDialog(null, "Cadastro de livro realizado com sucesso");
				nomeLivroField.setText(" ");
				nomeAutorField.setText(" ");
				editoraField.setText(" ");
				generoField.setText(" ");
				anoField.setText(" ");
				edicaoField.setText(" ");
				precoVendaField.setText(" ");
				cdFornecedorField.setText(" ");
			}
			else {
				JOptionPane.showMessageDialog(null, "Problema ao realizar cadastro de livro!");
			}
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void concluir() {
		frameList.setVisible(false);
		frameList.dispose();
	}
	
	public void cancelar() {
		cdFornecedorField.setText(" ");
		frameList.setVisible(false);
		frameList.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("listar")) {
			selecionarFornecedor();
		}
		else if(e.getActionCommand().equals("sair")) {
			sair();
		}
		else if(e.getActionCommand().equals("salvar")) {
			salvar();
		}
		else if(e.getActionCommand().equals("concluir")) {
			concluir();
		}
		else if(e.getActionCommand().equals("cancelar")) {
			cancelar();
		}
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		System.out.println("Teste");
		int i = jtFornecedor.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
		cdFornecedorField.setText(jtFornecedor.getValueAt(i, 0).toString());
		System.out.println(i);
	}
	

}
