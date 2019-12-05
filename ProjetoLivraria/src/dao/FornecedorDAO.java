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
import model.Fornecedor;

public class FornecedorDAO {
	//static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	int cd;
	List<Fornecedor> fornecedores;
	
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
		fornecedores = new ArrayList<Fornecedor>();
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
				fornecedor.setNmFantasia(rs.getString("nm_fantasia"));
				fornecedor.setRzSocial(rs.getString("rz_social"));
				fornecedor.setCnpj(rs.getInt("cnpj"));
				fornecedor.setEmail(rs.getString("email"));
				fornecedor.setTelefone(rs.getInt("telefone"));
				fornecedor.setCelular(rs.getInt("celular"));
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
	public boolean gerarArq(String caminho, List<Fornecedor> fornecedores) {
		this.fornecedores= fornecedores;
		Date data = new Date(System.currentTimeMillis());
		try {
			FileWriter arq = new FileWriter(caminho);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println("# RELATÓRIO DOS FORNECEDORES CADASTRADOS GERADO EM "+ data + " #");
			for(int i = 0; i<fornecedores.size(); i++) {
				gravarArq.print("ID: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getCodigo());
				gravarArq.print("Nome: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getNmFornecedor());
				gravarArq.print("Nome Fantasia: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getNmFantasia());
				gravarArq.print("Razão Social: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getRzSocial());
				gravarArq.print("CNPJ: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getCnpj());
				gravarArq.print("E-mail: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getEmail());
				gravarArq.print("Telefone: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getTelefone());
				gravarArq.print("Celular: ");
				gravarArq.println(((Fornecedor) fornecedores.get(i)).getCelular());
				gravarArq.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			}
			gravarArq.close();
			return true;
		}catch(Exception e) {
			System.out.println("ERRO: "+ e.getMessage());
			return false;
		}
	}
	
	public Fornecedor getFornecedor(int cd) {
		Connection bdd = BancoDeDados.conectar();
		fornecedores = new ArrayList<Fornecedor>();
		Fornecedor fornecedor = null;
		String texto = null;
		try {
			String sql = "SELECT * FROM fornecedor WHERE cd_fornecedor = "+cd+"; ";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				fornecedor = new Fornecedor();
				fornecedor.setNmFornecedor(rs.getString("nm_fornecedor"));
				System.out.println(fornecedor.getCodigo());
			}
		}catch(Exception e) {
			System.out.println("ERRO: "+ e.getMessage());
		}
		return fornecedor;
	}
	
	public ArrayList<Fornecedor> buscar(int cd){
		Connection bdd = BancoDeDados.conectar();
		fornecedores = new ArrayList<Fornecedor>();
		try {
			String sql = "SELECT * FROM fornecedor WHERE cd_fornecedor = "+cd+";";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setCodigo(rs.getInt("cd_fornecedor"));
				f.setNmFornecedor(rs.getString("nm_fornecedor"));
				f.setNmFantasia(rs.getString("nm_fantasia"));
				f.setRzSocial(rs.getString("rz_social"));
				f.setCnpj(rs.getInt("cnpj"));
				f.setEmail(rs.getString("email"));
				f.setTelefone(rs.getInt("telefone"));
				f.setCelular(rs.getInt("celualr"));
				fornecedores.add(f);
			}
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		return (ArrayList<Fornecedor>) fornecedores;
	}

	public void atualizar(Fornecedor fornecedor, int id) throws SQLException{
		Connection bdd = BancoDeDados.conectar();
		try {
			String sql = "UPDATE fornecedor SET nm_fornecedor = ?, nm_fantasia = ?, rz_social = ?, cnpj = ?, email = ?, telefone = ?, celular = ? WHERE cd_fornecedor = "+id+";";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			smt.setString(1, fornecedor.getNmFornecedor());
			smt.setString(2, fornecedor.getNmFantasia());
			smt.setString(3, fornecedor.getRzSocial());
			smt.setInt(4, fornecedor.getCnpj());
			smt.setString(5, fornecedor.getEmail());
			smt.setInt(6, fornecedor.getTelefone());
			smt.setInt(7, fornecedor.getCelular());
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		bdd.close();
	}
}
