package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bdd.BancoDeDados;
import model.Compra;

public class CompraDAO{
	
	private static PreparedStatement st = null;
	private static ResultSet rs = null;
	
	public void inserirCompra(Compra compra) throws SQLException{
		Connection bdd = BancoDeDados.conectar();
		System.out.println("Entrou no gerarCompraDAO");
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		java.util.Date dataUtil = new java.util.Date(); try { dataUtil = df.parse( compra.getDtCompra() ); } catch (ParseException ex) {

		} java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
		
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");

		java.util.Date dataUtil2 = new java.util.Date(); try { dataUtil = df.parse( compra.getDtEntrega() ); } catch (ParseException ex) {

		} java.sql.Date dataSql2 = new java.sql.Date(dataUtil.getTime());
		
		String sql1 = "insert into compra (dt_compra, dt_entrega, cd_fornecedor) values (?,?,?);";
		
		//final PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
//		System.out.println("Data 1 " + compra.getDtCompra());
//		System.out.println("Data 2 " + dataUtil);
//		System.out.println("Data 3 " + dataSql);
		smt.setDate(1, dataSql);
		smt.setDate(2, dataSql2);
		smt.setInt(3, compra.getFornecedor().getCodigo());
		smt.executeUpdate();
		final ResultSet rs1 = smt.getGeneratedKeys();
		 int lastId = 0;
		if (rs1.next()) {
		   lastId = rs1.getInt(1);
		    System.out.println(" ID da compra " + lastId);
		}
				
		String sql2 = "insert into item_compra (cd_livro, cd_compra, quantidade, preco_unitario) values (?,?,?,?)";
		
		PreparedStatement smt2 = (PreparedStatement) bdd.prepareStatement(sql2);
		smt2.setInt(1, compra.getLivro().getCdLivro());
		smt2.setInt(2, lastId);
		smt2.setInt(3, compra.getQuantidade());
		smt2.setDouble(4, compra.getPreco());
		
		smt2.executeUpdate();
	}
	
	public ArrayList<Compra> Listar(){
		Connection bdd = BancoDeDados.conectar();
		ArrayList<Compra> compras = new ArrayList<Compra>();
		int linha = 0;
		String texto = null;
		System.out.println("Entrou no CompraDAO");
		try {
			String sql = "SELECT * FROM compra as c JOIN item_compra as ic JOIN livro as l JOIN fornecedor as f ON f.cd_fornecedor = c.cd_fornecedor and ic.cd_compra = c.cd_compra and l.cd_livro = ic.cd_livro ORDER BY c.cd_compra;";
			
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Compra compra = new Compra();
				compra.setCdCompra(rs.getInt("cd_Compra"));
				compra.setDtCompra(rs.getString("dt_Compra"));
				compra.setDtEntrega(rs.getString("dt_Entrega"));
				compra.getLivro().setNomeLivro(rs.getString("nm_Livro"));
				compra.getFornecedor().setNmFornecedor(rs.getString("nm_Fornecedor"));
				compra.setPreco(rs.getDouble("preco_unitario"));
				
				compras.add(compra);
				
			}
			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return compras;
		
	}
	
}
