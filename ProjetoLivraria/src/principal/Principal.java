package principal;

import javax.swing.JFrame;

public class Principal extends JFrame{

	public static void main(String[] args) {
		CadastroLivroView cadastroLivroView = new CadastroLivroView();
		
		cadastroLivroView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroLivroView.setSize(800,500);
		cadastroLivroView.setVisible(true);
		
		BancoDeDados bdd = new BancoDeDados();
		bdd.conectar();
		if(bdd.estaConectado()) {
			System.out.println("CONECTADO");
			bdd.listarContatos();
			//bdd.inserirLivro(1,"ddddd", "dsafdsa", "dasfadsf", "dsagasgs", 2002, 1, 10.00, 10, 3, "hp10.jpg");
			
			
			//bdd.desconectar();
		
		}else {
			System.out.println("Não foi possível conectar com o Banco de Dados");
		}
		
		
	}

}
