package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Livro;
import view.ListarLivroView;

public class LivroDAO {
	
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	List<Livro> livros;
	int linha = 0;
	int cd;
	//m�todo para inserir livro no bd
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
		
		bdd.close();
	}
	//m�todo para listar os livros
	public ArrayList<Livro>  Listar(){
		Connection bdd = BancoDeDados.conectar();
		livros = new ArrayList<Livro>();
		
		String texto = null;
		
		//Livro livro = new Livro();
		
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
				//System.out.println("ENTROU3");
				Livro livro = new Livro();
				livro.setCdLivro(rs.getInt("cd_Livro"));
				livro.setNomeLivro(rs.getString("nm_Livro"));
				livro.setQntLivro(rs.getInt("qnt_livro"));
				livros.add(livro);
				//System.out.println(livro.getNomeLivro());
//				for (int i = 0; i < livros.size(); i++) {
//					System.out.println(livros.get(i).getNomeLivro());
//
//				}
//				System.out.println("/////////////////////////////////////");
//				
			}
//			System.out.println("Listagem dentro do livro Dao");
//			for (int i = 0; i < livros.size(); i++) {
//				System.out.println(livros.get(i).getNomeLivro());
//
//			}
//			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return (ArrayList<Livro>) livros;
		
	}
	
	public void deletar(int cd) {
		Connection bdd = BancoDeDados.conectar();
		this.cd = cd;
		try {
			String sql = "delete from livro where cd_livro = "+ cd +" ;";
		}catch(Exception e){
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	
	
	//m�todo para consultar livro no bd

}


// while testes
//texto = ("ID: " + rs.getString("cd_livro") + " - NOME: " + rs.getString("nm_livro"));
//System.out.println(texto);
//dados[linha] = texto;
//System.out.println(dados[linha]);
////dados[linha][1] = rs.getString("nm_livro");
////System.out.println(rs.getString("cd_livro"));
//
////System.out.println(dados[linha][1]);
//
//
////ListarLivroView lista = new ListarLivroView();
//linha++;
