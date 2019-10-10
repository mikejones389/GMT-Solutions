package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BancoDeDados {
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	
	public static Connection conectar(){
		//String servidor = "jdbc:mysql://localhost:3307/projeto_livraria";
		String servidor = "jdbc:mysql://35.198.9.166:3306/projeto_livraria";
		String usuario = "root";
		String senha = "@gmtifsp";
		String driver = "com.mysql.jdbc.Driver";
		System.out.println(" Cliquei na conexão BD ");
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
	
	public static boolean listarLivros() {
		System.out.println("ENTROU");
		try {
			System.out.println("ENTROU2");
//			String query = "SELECT * FROM livro ORDER BY nm_livro";
//			resultSet = statement.executeQuery(query);
//			while(resultSet.next()) {
//				System.out.println("ENTROU3");
//				System.out.println("ID: " + resultSet.getString("cd_livro") + " - NOME: " + resultSet.getString("nm_livro"));
//				
//			}
			ResultSet st = null;
			
			st = (ResultSet) connection.prepareStatement( "SELECT * FROM livro ORDER BY nm_livro");
			((PreparedStatement) st).executeQuery();
			while(st.next()) {
				System.out.println("ENTROU3");
				System.out.println("ID: " + resultSet.getString("cd_livro") + " - NOME: " + resultSet.getString("nm_livro"));
				
			}
			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	
	}
	
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
	public static boolean inserirFornecedor(int cd_fornecedor, String nm_fornecedor, String nm_fantasia, String rz_social, int cnpj, String email, int telefone, int celular ) {
			System.out.println( "Cheguei no inserir");
			System.out.println( cd_fornecedor + nm_fornecedor +  nm_fantasia + rz_social + cnpj + email + telefone + celular );
		try {
			String query = "INSERT INTO fornecedor(  cd_fornecedor ,nm_fornecedor, nm_fantasia, rz_social, cnpj, email, telefone, celular) VALUES ('" + cd_fornecedor +"' , '" + nm_fornecedor +"', '" + nm_fantasia +"', '" + rz_social +"', '" + cnpj + "', '" + email + "' ,'" + telefone + "','" + celular +"');";
			System.out.println("inserirQuery"+ query);
				statement.executeUpdate(query);
		}catch(Exception e){
			System.out.println( cd_fornecedor + nm_fornecedor +  nm_fantasia + rz_social + cnpj + email + telefone + celular );
			System.out.println("ERRO: "+ e.getMessage());
		}
		return false;
	}
		
		
}
