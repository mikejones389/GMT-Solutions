package dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Fornecedor;

public class FornecedorDAO {
	//static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	int cd;
	
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
	
	public ArrayList<Fornecedor> Listar(){
		Connection bdd = BancoDeDados.conectar();
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		int linha = 0;
		String texto = null;
		
		System.out.println("Entrou no ListarDAO Fornecedor");
		try {
			String sql = "SELECT * FROM fornecedor ORDER BY cd_fornecedor";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCodigo(rs.getInt("cd_fornecedor"));
				fornecedor.setNmFornecedor(rs.getString("nm_fornecedor"));
				fornecedor.setTelefone(rs.getInt("telefone"));
				fornecedores.add(fornecedor);
				
			}
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			
		}
		return (ArrayList<Fornecedor>) fornecedores;	
	}
	public void deletar(int cd) {
		Connection  bdd = BancoDeDados.conectar();
		this.cd = cd;
		try {
			System.out.println("cd "+ cd);
			String sql = "delete from fornecedor where cd_fornecedor = " + cd + " " ;
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			smt.executeUpdate();
		}catch(Exception e) {
			System.out.println("ERRO: "+ e.getMessage());
		}
	}
}
