package controller;

import java.sql.SQLException;

import dao.LivroDAO;
import model.Livro;

public class LivroController {
	//método para cadastrar livro
	public boolean cadastro(Livro livro) throws SQLException {
		//validação de dados
		
		System.out.println("Cheguei no controller cadastrar livros");
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.inserir(livro);
		
		
		return true;
		
//		if(true) {
//			LivroDAO livroDAO = new LivroDAO();
//			
//			if(livroDAO.inserir(livro)) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
//		else {
//			return false;
//		}
	}
	
	public void ListarLivros(){
		System.out.println("Cheguei no controller listar livros");
		
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.Listar();
		
		
	}
	
	public boolean atualizar(Livro livro, int id) throws SQLException{
		LivroDAO ld = new LivroDAO();
		ld.atualizar(livro, id);
		
		return true;
	}
}

