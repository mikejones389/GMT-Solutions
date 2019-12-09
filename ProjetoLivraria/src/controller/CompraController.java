package controller;

import java.sql.SQLException;
import dao.CompraDAO;
import model.Compra;

public class CompraController {
	
	public int cadastro(Compra compra) {
		int idCompra = 0;
		CompraDAO compraDAO = new CompraDAO();
		try {
			idCompra = compraDAO.inserirCompra(compra);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idCompra;
	}
}