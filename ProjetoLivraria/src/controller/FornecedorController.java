package controller;

import java.sql.SQLException;
import dao.FornecedorDAO;
import dao.LivroDAO;
import model.Fornecedor;

public class FornecedorController {
	
		public boolean cadastro(Fornecedor fornecedor) throws SQLException {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.inserir(fornecedor);				
			return true;
		}
		
//		public void ListarFornecedor(){
//			FornecedorDAO fornecedorDAO = new FornecedorDAO();
//			fornecedorDAO.Listar();
//		}
		
		public boolean atualizar(Fornecedor fornecedor, int id) throws SQLException{
			FornecedorDAO fd = new FornecedorDAO();
			fd.atualizar(fornecedor, id);
			return true;
		}
}