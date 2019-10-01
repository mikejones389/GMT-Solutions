package principal;

import javax.swing.JFrame;

import view.CadastroFornecedorView;
import view.CadastroLivroView;

public class Principal extends JFrame{

	public static void main(String[] args) {
		CadastroLivroView cadastroLivroView = new CadastroLivroView();
		
		cadastroLivroView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroLivroView.setSize(800,500);
		cadastroLivroView.setVisible(true);
		
		
		CadastroFornecedorView cadastroFornecedorView = new CadastroFornecedorView();
		
		cadastroFornecedorView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroFornecedorView.setSize(800,500);
		cadastroFornecedorView.setVisible(true);
		
	}
	

}
