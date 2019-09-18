package principal;

import javax.swing.JFrame;

public class Principal extends JFrame{

	public static void main(String[] args) {
		CadastroLivroView cadastroLivroView = new CadastroLivroView();
		
		cadastroLivroView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroLivroView.setSize(800,500);
		cadastroLivroView.setVisible(true);
		
		//BancoDeDados bdd = new BancoDeDados();
		//bdd.conectar();
		//if(bdd.estaConectado()) {
			//System.out.println("CONECTADO");
			//bdd.listarContatos();
			//bdd.inserirLivro("As Crônicas de Gelo e o Fogo", "George R R Martin", "Atlas ", "Aventura", 2014, 1, 250.00, 10, 3, "about-02.jpg");
			
			//bdd.desconectar();
		
		//}else {
			//System.out.println("Não foi possível conectar com o Banco de Dados");
		//}
		
		
	}

}
