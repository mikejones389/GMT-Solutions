package controller;

import java.sql.SQLException;

import dao.CompraDAO;
import model.Compra;

public class CompraController {
	
	public boolean cadastro(Compra compra) {
		
		System.out.println("Cheguei no ControllerCompra");
		CompraDAO compraDAO = new CompraDAO();
		try {
			compraDAO.inserirCompra(compra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
