package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Livro;

public class BancoDeDados {
	private static Connection connection = null;
	private static Statement statement = null;
	private ResultSet resultSet = null;
	
	public static Connection conectar(){
		String servidor = "jdbc:mysql://localhost:3307/projeto_livraria";
		String usuario = "root";
		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(servidor, usuario, senha);
			return connection;
			//statement = (Statement) this.connection.createStatement();
		}catch (Exception e) {
			System.out.println("Erro :" + e.getMessage());
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
	
	/*public static boolean listarLivros() {
		try {
			String query = "SELECT * FROM livro ORDER BY nm_livro";
			this.resultSet = this.statement.executeQuery(query);
			while(this.resultSet.next()) {
				System.out.println("ID: " + this.resultSet.getString("cd_livro") + " - NOME: " + this.resultSet.getString("nm_livro"));
				
			}
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	
	}
	*/
	public static boolean inserirLivro(String nm_livro, String autor, String editora, String genero, int ano_livro, int edicao, double preco_venda, int qnt_livro, int cdFornecedor, String linkImg) {
		
		try {
			String query = "INSERT INTO livro(nm_livro, autor, editora, genero, ano_livro, edicao, preco_venda, qnt_livro, cd_fornecedor, link_img) VALUES ('" + nm_livro +"', '" + autor +"', '" + editora +"', '" + genero + "', '" + ano_livro + "' ,'" + edicao + "','" + preco_venda+"' ,'" + qnt_livro + "', '" + cdFornecedor + "' ,'" + linkImg + "');";
			System.out.println("inserirQuery"+ query);

			statement.executeUpdate(query);
		}catch(Exception e){
			System.out.println("ERRO: "+ e.getMessage());
		}
		return false;
	}
	public static boolean inserirFornecedor(String nm_fornecedor, String nm_fantasia, String rz_social, int cnpj, String email, int telefone, int celular) {
			
		try {
			String query = "INSERT INTO fornecedor(nm_fornecedor, nm_fantasia, rz_social, cnpj, email, telefone, celular) VALUES ('" + nm_fornecedor +"', '" + nm_fantasia +"', '" + rz_social +"', '" + cnpj + "', '" + email + "' ,'" + telefone + "','" + celular +"');";
			System.out.println("inserirQuery"+ query);
				statement.executeUpdate(query);
		}catch(Exception e){
			System.out.println("ERRO: "+ e.getMessage());
		}
		return false;
	}
		
		
}
