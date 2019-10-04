package controller;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorController {
		public boolean cadastro(Fornecedor fornecedor) {
			if(true) {
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				
				if(FornecedorDAO.inserir(fornecedor)) {
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
