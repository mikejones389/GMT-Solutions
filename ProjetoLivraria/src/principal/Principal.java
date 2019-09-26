package principal;

import javax.swing.JFrame;

import bdd.BancoDeDados;

import view.CadastroLivroView;

public class Principal extends JFrame{

	public static void main(String[] args) {
		CadastroLivroView cadastroLivroView = new CadastroLivroView();
		
		cadastroLivroView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroLivroView.setSize(800,500);
		cadastroLivroView.setVisible(true);
				
		
	}

}
