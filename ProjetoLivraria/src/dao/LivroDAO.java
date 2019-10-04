package dao;

import java.sql.Connection;

import bdd.BancoDeDados;
import model.Livro;

public class LivroDAO {
	//método para inserir livro no bd
	public boolean inserir(Livro livro) {
		Connection bdd = BancoDeDados.conectar();
		if(BancoDeDados.estaConectado()) {
			System.out.println("CONECTADO");
			//bdd.listarLivros();
			//bdd.inserirLivro("As Crônicas de Gelo e o Fogo", "George R R Martin", "Atlas ", "Aventura", 2014, 1, 250.00, 10, 3, "about-02.jpg");
			
			BancoDeDados.inserirLivro(livro.getNomeLivro(),livro.getAutorLivro(), livro.getEditoraLivro(), livro.getGeneroLivro(), livro.getAnoLivro(), livro.getEdicaoLivro(), livro.getPrecoVenda(), livro.getQntLivro(), livro.getCdFornecedor(), livro.getLinkImg());
			//bdd.inserirObjetoLivro(livro);
			//bdd.desconectar();
			return true;
		
		}else {
			return false;
		}
	}
	
	//método para consultar livro no bd

}
