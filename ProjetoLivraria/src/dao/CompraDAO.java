package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Compra;

public class CompraDAO{
	
	private static PreparedStatement st = null;
	private static ResultSet rs = null;
	
	public void gerarCompra(Compra compra) throws SQLException{
		Connection bdd = BancoDeDados.conectar();
		System.out.println("Entrou no gerarCompraDAO");
		
		String sql = "";
		
		PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
		
		
	}
}
