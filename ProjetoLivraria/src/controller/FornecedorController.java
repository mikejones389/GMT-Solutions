package controller;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorController {
		public boolean cadastro(Fornecedor fornecedor) {
			System.out.println(" Cliquei no cadastro {controller}");
			if(true) {
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				
				if(FornecedorDAO.inserir(fornecedor)) {
					System.out.println(" fornecedorDAO okay");
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
