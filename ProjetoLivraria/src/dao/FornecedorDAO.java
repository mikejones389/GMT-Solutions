package dao;

import java.sql.Connection;

import bdd.BancoDeDados;
import model.Fornecedor;

public class FornecedorDAO {
	public static boolean inserir(Fornecedor fornecedor) {
		Connection bdd = BancoDeDados.conectar();
		System.out.println(bdd);
		if(BancoDeDados.estaConectado()) {
			System.out.println("CONECTADO {fornecedorDAO}");
			//bdd.listarLivros();
			//bdd.inserirLivro("As Crônicas de Gelo e o Fogo", "George R R Martin", "Atlas ", "Aventura", 2014, 1, 250.00, 10, 3, "about-02.jpg");
			
			BancoDeDados.inserirFornecedor(fornecedor.getNmFornecedor(),fornecedor.getNmFantasia(), fornecedor.getRzSocial(), fornecedor.getCnpj(), fornecedor.getEmail(), fornecedor.getTelefone(), fornecedor.getCelular());
			
			//bdd.inserirObjetoLivro(livro);
			//bdd.desconectar();
			return true;
		
		}else {
			return false;
		}
	}
}
