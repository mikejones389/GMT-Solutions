package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Fornecedor;

public class FornecedorDAO {
	//static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	
	
	public void inserir(Fornecedor fornecedor) throws SQLException {
		Connection bdd = BancoDeDados.conectar();
		System.out.println(bdd);
		System.out.println("CONECTADO fornecedorDAO");
			
		String sql = "INSERT INTO fornecedor(nm_fornecedor, nm_fantasia, rz_social, cnpj, email, telefone, celular) VALUES (?,?,?,?,?,?,?);";
		
		PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
		
		smt.setString(1, fornecedor.getNmFornecedor());
		smt.setString(2, fornecedor.getNmFantasia());
		smt.setString(3, fornecedor.getRzSocial());
		smt.setInt(4, fornecedor.getCnpj());
		smt.setString(5, fornecedor.getEmail());
		smt.setInt(6, fornecedor.getTelefone());
		smt.setInt(7, fornecedor.getCelular());
		
		smt.executeUpdate();

	}
	
	public void Listar() {
		Connection bdd = BancoDeDados.conectar();
		
		System.out.println("ENTROU no ListarDAO");
		try {

			String sql = "SELECT * FROM fornecedor ORDER BY cd_fornecedor";
			
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				System.out.println("ENTROU4");
				System.out.println("ID: " + rs.getString("cd_fornecedor") + " - NOME: " + rs.getString("nm_fornecedor"));
				
			}
			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
}
