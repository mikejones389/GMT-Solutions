package controller;

import dao.LivroDAO;
import model.Livro;

public class LivroController {
	//m�todo para cadastrar livro
	public boolean cadastro(Livro livro) {
		//valida��o de dados
		
		if(true) {
			LivroDAO livroDAO = new LivroDAO();
			
			if(livroDAO.inserir(livro)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

}
