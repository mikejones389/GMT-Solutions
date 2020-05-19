package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BancoDeDados {
	private static Connection connection = null;
	
	public static Connection conectar(){
//		String servidor = "jdbc:mysql://localhost:3306/projeto_livraria";
		String servidor = "jdbc:mysql://162.241.39.192:3306/gmtmarke_projeto_livraria";
//		String usuario = "root";
		String usuario = "gmtmarke_teste";
		String senha = "@gmtifsp";
//		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(servidor, usuario, senha);
			return connection;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, " FALHA AO CONECTAR O BANCO DE DADOS (ERRO: " + e.getMessage()+")");
		}
		return connection;
	}
		
	public static boolean estaConectado() {
		if(connection != null) {
			return true;
		}else {
			return false;
		}
	}
			
}