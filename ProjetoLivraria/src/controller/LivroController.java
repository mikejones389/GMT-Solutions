package controller;

import java.sql.SQLException;
import dao.LivroDAO;
import model.Livro;

public class LivroController {
	
	public boolean cadastro(Livro livro) throws SQLException {
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.inserir(livro);
		return true;
	}
	
	public void ListarLivros(){
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.Listar();
	}
	
	public boolean atualizar(Livro livro, int id) throws SQLException{
		LivroDAO ld = new LivroDAO();
		ld.atualizar(livro, id);
		return true;
	}
}