package controller;

import java.sql.SQLException;

import dao.FornecedorDAO;
import dao.LivroDAO;
import model.Fornecedor;

public class FornecedorController {
		public boolean cadastro(Fornecedor fornecedor) throws SQLException {
			System.out.println("Cheguei no controller cadastrar livros");
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.inserir(fornecedor);			
			
			return false;
		}
		
		public void ListarFornecedor(){
			System.out.println("Cheguei no controller listar Fornecedores");
			
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.Listar();
			
		}

	
}
