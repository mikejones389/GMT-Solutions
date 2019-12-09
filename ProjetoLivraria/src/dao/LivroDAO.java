package dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import bdd.BancoDeDados;
import model.Livro;

public class LivroDAO {
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	List<Livro> livros;
	int linha = 0;
	int cd;

	public void inserir(Livro livro) throws SQLException {
		Connection bdd = BancoDeDados.conectar();		
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
	
	public ArrayList<Livro> buscar(int cd){
		Connection bdd = BancoDeDados.conectar();
		livros = new ArrayList<Livro>();
		try {
			String sql = "SELECT * FROM livro WHERE cd_livro = "+cd+";";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setCdLivro(rs.getInt("cd_Livro"));
				livro.setNomeLivro(rs.getString("nm_Livro"));
				livro.setAutorLivro(rs.getString("autor"));
				livro.setEditoraLivro(rs.getString("editora"));
				livro.setGeneroLivro(rs.getString("genero"));
				livro.setAnoLivro(rs.getInt("ano_livro"));
				livro.setEdicaoLivro(rs.getInt("edicao"));
				livro.setPrecoVenda(rs.getDouble("preco_venda"));
				livro.setQntLivro(rs.getInt("qnt_livro"));
				livro.setCdFornecedor(rs.getInt("cd_fornecedor"));
				livro.setLinkImg(rs.getString("link_img"));
				livros.add(livro);
			}
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		return (ArrayList<Livro>) livros;
	}
	
	public ArrayList<Livro>  Listar(){
		Connection bdd = BancoDeDados.conectar();
		livros = new ArrayList<Livro>();	
		String texto = null;
		try {
			String sql = "SELECT * FROM livro ORDER BY cd_livro";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setCdLivro(rs.getInt("cd_Livro"));
				livro.setNomeLivro(rs.getString("nm_Livro"));
				livro.setAutorLivro(rs.getString("autor"));
				livro.setEditoraLivro(rs.getString("editora"));
				livro.setGeneroLivro(rs.getString("genero"));
				livro.setAnoLivro(rs.getInt("ano_livro"));
				livro.setEdicaoLivro(rs.getInt("edicao"));
				livro.setPrecoVenda(rs.getDouble("preco_venda"));
				livro.setQntLivro(rs.getInt("qnt_livro"));
				livro.setCdFornecedor(rs.getInt("cd_fornecedor"));
				livro.setLinkImg(rs.getString("link_img"));
				livros.add(livro);
			}
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
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			smt.executeUpdate();
		}catch(Exception e){
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public boolean gerarArq( String caminho, List<Livro> livros) {
		this.livros = livros;
		Date data = new Date(System.currentTimeMillis());
		try {
			FileWriter arq = new FileWriter(caminho);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println("#RELATÓRIO DOS LIVROS CADASTRADOS GERADO EM "+ data +"#");
			for (int i = 0; i < livros.size(); i++) {
				gravarArq.print("ID: ");
				gravarArq.println(((Livro) livros.get(i)).getCdLivro());
				gravarArq.print("Título: ");
				gravarArq.println(((Livro) livros.get(i)).getNomeLivro());
				gravarArq.print("Autor: ");
				gravarArq.println(((Livro) livros.get(i)).getAutorLivro());
				gravarArq.print("Editora: ");
				gravarArq.println(((Livro) livros.get(i)).getEditoraLivro());
				gravarArq.print("Gênero: ");
				gravarArq.println(((Livro) livros.get(i)).getGeneroLivro());
				gravarArq.print("Ano: ");
				gravarArq.println(((Livro) livros.get(i)).getAnoLivro());
				gravarArq.print("Ediçao: ");
				gravarArq.println(((Livro) livros.get(i)).getEdicaoLivro());
				gravarArq.print("Preço de Venda");
				gravarArq.println(((Livro) livros.get(i)).getPrecoVenda());
				gravarArq.print("Quantidade: ");
				gravarArq.println(((Livro) livros.get(i)).getQntLivro());
				gravarArq.print("Código do Fornecedor: ");
				gravarArq.println(((Livro) livros.get(i)).getCdFornecedor());
				gravarArq.print("Link Imagem");
				gravarArq.println(((Livro) livros.get(i)).getLinkImg());
				gravarArq.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			}		
			gravarArq.close();
			return true;
		}catch(Exception e) {
			System.out.println("ERRO: "+ e.getMessage());
			return false;
		}
	}
	
	public Livro getLivro (int cd){
		Connection bdd = BancoDeDados.conectar();
		livros = new ArrayList<Livro>();
		Livro livro= null;
		String texto = null;
		try {
			String sql = "SELECT * FROM livro WHERE cd_livro = "+cd+";";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				System.out.println("ENTROU3");
				livro = new Livro();
				livro.setNomeLivro(rs.getString("nm_Livro"));					
			}
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		return livro;
	}
	
	public void atualizar(Livro livro, int id) throws SQLException{
		Connection bdd = BancoDeDados.conectar();
		try {
			String sql = "UPDATE livro SET nm_livro = ?, autor = ?, editora = ?,genero = ? , ano_livro = ?, edicao = ?,preco_venda = ?,qnt_livro = ?,cd_fornecedor = ?,link_img  = ? WHERE cd_livro = "+id+";";
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
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		bdd.close();	
	}
}