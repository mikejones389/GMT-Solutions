package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FornecedorController;
import model.Fornecedor;


public class CadastroFornecedorView extends JPanel{
	
	private JTextField nmFornecedorField;
	private JTextField nmFantasiaField;
	private JTextField rzSocialField;
	private JTextField cnpjField;
	private JTextField emailField;
	private JTextField telefoneField;
	private JTextField celularField;
	
	
	
	private Fornecedor fornecedor;
	
	public CadastroFornecedorView() {
		criarFormulario();
	}
	
	private void criarFormulario() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		
		JLabel titulo = new JLabel("Cadastro de Fornecedor");
		titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.LINE_START;
		gbc.anchor = GridBagConstraints.NORTH;
		add(titulo,gbc);
		
		JLabel nmFornecedorLabel = new JLabel ("Nome do Fornecedor");
		nmFornecedorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=1; 
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(nmFornecedorLabel, gbc);
		
		nmFornecedorField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(nmFornecedorField, gbc);
		
		JLabel nmFantasiaLabel = new JLabel ("Nome Fantasia");
		nmFantasiaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(nmFantasiaLabel, gbc);
		
		nmFantasiaField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(nmFantasiaField, gbc);
		
		JLabel rzSocialLabel = new JLabel ("razão Social");
		rzSocialLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(rzSocialLabel, gbc);
		
		rzSocialField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(rzSocialField, gbc);
		
		JLabel cnpjLabel = new JLabel ("CNPJ");
		cnpjLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(cnpjLabel, gbc);
		
		cnpjField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(cnpjField, gbc);
		
		JLabel emailLabel = new JLabel ("Email");
		emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=5;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(emailLabel, gbc);
		
		emailField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(emailField, gbc);
		
		JLabel telefoneLabel = new JLabel ("Telefone");
		telefoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=6;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(telefoneLabel, gbc);
		
		telefoneField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(telefoneField, gbc);
		
		JLabel celularLabel = new JLabel ("Celular");
		celularLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=7;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(celularLabel, gbc);
		
		celularField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(celularField, gbc);
		
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
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setNmFornecedor(nmFornecedorField.getText());
			fornecedor.setNmFantasia(nmFantasiaField.getText());
			fornecedor.setRzSocial(rzSocialField.getText());
			fornecedor.setCnpj(Integer.parseInt(cnpjField.getText()));
			fornecedor.setEmail(emailField.getText());
			fornecedor.setTelefone(Integer.parseInt(telefoneField.getText()));
			fornecedor.setCelular(Integer.parseInt(celularField.getText()));
			
			
			FornecedorController fornecedorController = new FornecedorController();
			if(fornecedorController.cadastro(fornecedor)) {
				JOptionPane.showMessageDialog(null, "Cadastro do fornecedor realizado com sucesso");
				nmFornecedorField.setText(" ");
				nmFantasiaField.setText(" ");
				rzSocialField.setText(" ");
				cnpjField.setText(" ");
				emailField.setText(" ");
				telefoneField.setText(" ");
				celularField.setText(" ");
			}
			else {
				JOptionPane.showMessageDialog(null, "Problema ao realizar cadastro do fornecedor!");
			}
			

			
			/*BancoDeDados bdd = new BancoDeDados();
			bdd.conectar();
			if(bdd.estaConectado()) {
				System.out.println("CONECTADO");
				//bdd.listarLivros();
				//bdd.inserirLivro("As Crônicas de Gelo e o Fogo", "George R R Martin", "Atlas ", "Aventura", 2014, 1, 250.00, 10, 3, "about-02.jpg");
				
				bdd.inserirFornecedor(fornecedor.getNmFornecedor(),fornecedor.getNmFantasia(),fornecedor.getRzSocial(), fornecedor.getCnpj(), fornecedor.getEmail(), fornecedor.getTelefone(), fornecedor.getCelular());
				//bdd.inserirObjetoLivro(livro);
				//bdd.desconectar();
			
			}else {
				System.out.println("Não foi possível conectar com o Banco de Dados");
			}*/
		}
			
	}		
	private class ActionSair implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}
