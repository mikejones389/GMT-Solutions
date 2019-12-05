package controller;

import java.sql.SQLException;

import dao.CompraDAO;
import model.Compra;

public class CompraController {
	
	public int cadastro(Compra compra) {
		int idCompra = 0;
		System.out.println("Cheguei no ControllerCompra");
		CompraDAO compraDAO = new CompraDAO();
		try {
			idCompra = compraDAO.inserirCompra(compra);
			System.out.println(idCompra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idCompra;
	}
}
