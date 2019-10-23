package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Livro;
import view.ListarLivroView;

public class LivroDAO {
	
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	
	//método para inserir livro no bd
	public void inserir(Livro livro) throws SQLException {
		Connection bdd = BancoDeDados.conectar();
		System.out.println("ENTROU no InserirDAO");
		
		String sql = "INSERT INTO livro(nm_livro, autor, editora, genero, ano_livro, edicao, preco_venda, qnt_livro, cd_fornecedor, link_img) VALUES (?,?,?,?,?,?,?,?,?,?);";
			
		PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
		
		smt.setString(1, livro.getNomeLivro());
		smt.setString(2, livro.getAutorLivro());
		smt.setString(3, livro.getEditoraLivro());
		smt.setString(4, livro.getGeneroLivro());	
		smt.setInt(5, livro.getAnoLivro());	
		smt.setInt(6, livro.getEdicaoLivro());
		smt.setDouble(7, livro.getPrecoVenda());
		smt.setInt(8, livro.getQntLivro());
		smt.setInt(9, livro.getCdFornecedor());
		smt.setString(10, livro.getLinkImg());
		
		smt.executeUpdate();

	}
	//método para listar os livros
	public void Listar(){
		Connection bdd = BancoDeDados.conectar();
		
		
		
		System.out.println("ENTROU no ListarDAO");
		try {
//			String query = "SELECT * FROM livro ORDER BY nm_livro";
//			resultSet = statement.executeQuery(query);
//			while(resultSet.next()) {
//				System.out.println("ENTROU3");
//				System.out.println("ID: " + resultSet.getString("cd_livro") + " - NOME: " + resultSet.getString("nm_livro"));
//				
//			}
			String sql = "SELECT * FROM livro ORDER BY cd_livro";
			
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				System.out.println("ENTROU3");
				//System.out.printl   n("ID: " + rs.getString("cd_livro") + " - NOME: " + rs.getString("nm_livro"));
				ListarLivroView lista = new ListarLivroView();
				
				
				
			}
			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	
	}
	
	//método para consultar livro no bd

}
