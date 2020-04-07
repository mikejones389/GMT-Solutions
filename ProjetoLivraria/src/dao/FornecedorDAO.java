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
import com.mysql.jdbc.Statement;

import bdd.BancoDeDados;
import model.Fornecedor;
import model.Usuario;

public class FornecedorDAO {
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	int cd;
	int cdList;
	List<Fornecedor> fornecedores;
	
	public void inserir(Fornecedor fornecedor) throws SQLException {
		Connection bdd = BancoDeDados.conectar();	
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
	
	public ArrayList<Fornecedor> Listar(int cdList){
		Connection bdd = BancoDeDados.conectar();
		fornecedores = new ArrayList<Fornecedor>();
		int linha = 0;
		String texto = null;
		try {
			this.cdList = cdList;
			switch (cdList) {
			//LISTAR SOMENTE OS FORNECEDORES ATIVOS
			case 1: 
				String sql1 =  "SELECT * FROM fornecedor WHERE status = 1 ORDER BY cd_fornecedor;";
				PreparedStatement smt1 = (PreparedStatement) bdd.prepareStatement(sql1);
				ResultSet rs1 = smt1.executeQuery();
				while(rs1.next()) {
					Fornecedor fornecedor = new Fornecedor();
					fornecedor.setCodigo(rs1.getInt("cd_fornecedor"));
					fornecedor.setNmFornecedor(rs1.getString("nm_fornecedor"));
					fornecedor.setNmFantasia(rs1.getString("nm_fantasia"));
					fornecedor.setRzSocial(rs1.getString("rz_social"));
					fornecedor.setCnpj(rs1.getInt("cnpj"));
					fornecedor.setEmail(rs1.getString("email"));
					fornecedor.setTelefone(rs1.getInt("telefone"));
					fornecedor.setCelular(rs1.getInt("celular"));
					fornecedor.setStatus(rs1.getInt("status"));
					fornecedores.add(fornecedor);
				}
				break;
			//LISTAR TODOS OS FORNECEDORES ATIVOS E NAO ATIVOS
			case 2:
				String sql2 = "SELECT * FROM fornecedor ORDER BY cd_fornecedor;";
				PreparedStatement smt2 = (PreparedStatement) bdd.prepareStatement(sql2);
				ResultSet rs2 = smt2.executeQuery();
				while(rs2.next()) {
					Fornecedor fornecedor = new Fornecedor();
					fornecedor.setCodigo(rs2.getInt("cd_fornecedor"));
					fornecedor.setNmFornecedor(rs2.getString("nm_fornecedor"));
					fornecedor.setNmFantasia(rs2.getString("nm_fantasia"));
					fornecedor.setRzSocial(rs2.getString("rz_social"));
					fornecedor.setCnpj(rs2.getInt("cnpj"));
					fornecedor.setEmail(rs2.getString("email"));
					fornecedor.setTelefone(rs2.getInt("telefone"));
					fornecedor.setCelular(rs2.getInt("celular"));
					fornecedor.setStatus(rs2.getInt("status"));
					fornecedores.add(fornecedor);
				}
				break;
			}
			
			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return (ArrayList<Fornecedor>) fornecedores;	
	}
	
	public void desativar(int cd) {
		Connection  bdd = BancoDeDados.conectar();
		this.cd = cd;
		try {
			String sql = "update fornecedor set status = 0 where cd_fornecedor = "+cd+";";	
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			smt.executeUpdate();
		}catch(Exception e) {
			System.out.println("ERRO: "+ e.getMessage());
		}
	}
	
	public void ativar(int cd) {
		Connection  bdd = BancoDeDados.conectar();
		this.cd = cd;
		try {
			String sql = "update fornecedor set status = 1 where cd_fornecedor = "+cd+";";	
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
				gravarArq.print("Status: ");
				if(((Fornecedor) fornecedores.get(i)).getStatus() == 1) {
					gravarArq.println("Ativo");
				}
				else {
					gravarArq.println("Não ativo");
				}
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
				f.setCelular(rs.getInt("celular"));
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
			smt.executeUpdate();		
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		bdd.close();
	}
}