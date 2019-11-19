package controller;

import java.sql.SQLException;

import dao.CompraDAO;
import model.Compra;

public class CompraController {
	
	public boolean cadastro(Compra compra) throws SQLException{
		
		System.out.println("Cheguei no ControllerCompra");
		CompraDAO compraDAO = new CompraDAO();
		compraDAO.gerarCompra(compra);
		return false;
	}
}
