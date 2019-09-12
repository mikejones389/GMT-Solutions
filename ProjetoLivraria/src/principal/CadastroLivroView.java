package principal;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

	public class CadastroLivroView extends JFrame{

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

		
		private String nomeLivro;
		private String nomeAutor;
		private String editora;
		private String genero;
		private String anoLivro;
		private String edicao;
		private String precoVenda;
		private String cdFornecedor;
		private String qntLivro;
		private String imgLink;
		
		
		//M�todo CadastroLivros
		public CadastroLivroView() {
			//Titulo da Janela
			super("Cadastro de Livros");
			
			criarFormulario();
		}
			
			//Funcao criarFormulario
			private void criarFormulario() {
				//Configura��o da estrutura do layout
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
				
				JLabel cdFornecedorLabel = new JLabel ("C�digo do Fornecedor");
				cdFornecedorLabel.setFont(new Font("Arial", Font.BOLD, 16));
				gbc.gridx=0;
				gbc.gridy=1;
				gbc.insets= new Insets(10,0,0,5);
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_END;
				add(cdFornecedorLabel, gbc);
				
				cdFornecedorField = new JTextField(30);
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
				gbc.gridx=1;
				gbc.gridy=4;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_START;
				add(editoraField, gbc);
				
				JLabel generoLabel = new JLabel ("G�nero");
				generoLabel.setFont(new Font("Arial", Font.BOLD, 16));
				gbc.gridx=0;
				gbc.gridy=5;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_END;
				add(generoLabel, gbc);
				
				generoField = new JTextField(30);
				gbc.gridx=1;
				gbc.gridy=5;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_START;
				add(generoField, gbc);
				
				JLabel anoLabel = new JLabel ("Ano de Lan�amento");
				anoLabel.setFont(new Font("Arial", Font.BOLD, 16));
				gbc.gridx=0;
				gbc.gridy=6;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_END;
				add(anoLabel, gbc);
				
				anoField = new JTextField(30);
				gbc.gridx=1;
				gbc.gridy=6;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_START;
				add(anoField, gbc);
				
				JLabel edicaoLabel = new JLabel ("Edi��o");
				edicaoLabel.setFont(new Font("Arial", Font.BOLD, 16));
				gbc.gridx=0;
				gbc.gridy=7;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_END;
				add(edicaoLabel, gbc);
				
				edicaoField = new JTextField(30);
				gbc.gridx=1;
				gbc.gridy=7;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_START;
				add(edicaoField, gbc);
				
				JLabel precoVendaLabel = new JLabel ("Pre�o de Venda");
				precoVendaLabel.setFont(new Font("Arial", Font.BOLD, 16));
				gbc.gridx=0;
				gbc.gridy=8;
				gbc.insets= new Insets(10,0,0,5);
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.LINE_END;
				add(precoVendaLabel, gbc);
				
				precoVendaField = new JTextField(30);
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
				
				
				
				
				ActionSalvar actionSalvar = new ActionSalvar();
				ActionSair actionSair = new ActionSair();
				
				JButton botaoSalvar = new JButton("Salvar");
				botaoSalvar.addActionListener(actionSalvar);
				JButton botaoSair = new JButton("Sair");
				botaoSair.addActionListener(actionSair);
				
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
		
		private class ActionSalvar implements ActionListener{

			public void actionPerformed(ActionEvent Event) {
				nomeLivro = nomeLivroField.getText();
				//System.out.print("Nome do Livro: ");
				//System.out.println(nomeLivro);
				nomeAutor = nomeAutorField.getText();
				//System.out.print("Nome do Autor: ");
				//System.out.println(nomeAutor);
				editora = editoraField.getText();
				//System.out.print("Editora: ");
				//System.out.println(editora);
				genero = generoField.getText();
				//System.out.print("G�nero: ");
				//System.out.println(genero);
				anoLivro = anoField.getText();
				//System.out.print("Ano: ");
				//System.out.println(anoLivro);
				edicao = edicaoField.getText();
				//System.out.print("Edi��o: ");
				//System.out.println(edicao);
				precoVenda = precoVendaField.getText();
				//System.out.print("Pre�o de Venda: ");
				//System.out.println(precoVenda);
				cdFornecedor = cdFornecedorField.getText();
				//System.out.print("C�digo do Fornecedor: ");
				//System.out.println(cdFornecedor);
				qntLivro = qntLivroField.getText();
				//System.out.print("Quantidade: ");
				//System.out.println(qntLivro);
				imgLink = imgLinkField.getText();
				//System.out.print("Link Imagem: ");
				//System.out.println(imgLink);
				
				BancoDeDados bdd = new BancoDeDados();
				bdd.inserirLivro(nomeLivro, nomeAutor , editora, genero, anoLivro, edicao, precoVenda, qntLivro, cdFornecedor, imgLink);

				
				
				nomeLivroField.setText(" ");
				nomeAutorField.setText(" ");
				editoraField.setText(" ");
				generoField.setText(" ");
				anoField.setText(" ");
				edicaoField.setText(" ");
				precoVendaField.setText(" ");
				cdFornecedorField.setText(" ");
				
			}

			
		}
		private class ActionSair implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
			
		}
			
	
	}
	
	


