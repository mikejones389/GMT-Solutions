package dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import bdd.BancoDeDados;
import model.Usuario;

public class UsuarioDAO {
	int cd;
	List<Usuario> usuarios;
	public ArrayList<Usuario> Listar(){
		Connection bdd = BancoDeDados.conectar();
		usuarios = new ArrayList<Usuario>();
		int linha = 0;
		String texto = null;
		try {
			String sql = "SELECT * FROM usuario ORDER BY cd_usuario";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCdUsuario(rs.getInt("cd_usuario"));
				usuario.setNmUsuario(rs.getString("nm_usuario"));
				usuario.setCpfUsuario(rs.getInt("cpf_usuario"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setDtNascimento(rs.getString("data_nascimento"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCelular(rs.getInt("celular"));
				usuario.setPerfil(rs.getString("perfil"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setStatus(rs.getInt("status"));
				usuarios.add(usuario);
			}			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());	
		}
		return (ArrayList<Usuario>) usuarios;
	}
	
	public void desativar(int cd) {
		Connection bdd = BancoDeDados.conectar();
		this.cd = cd;
		try {
			String sql = "update usuario set status = 0 where cd_usuario = "+cd+";";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			smt.executeUpdate();
		}catch(Exception e ) {
			System.out.println("ERRO: "+ e.getMessage());
		}
	}
	
	public void ativar(int cd) {
		Connection bdd = BancoDeDados.conectar();
		this.cd = cd;
		try {
			String sql = "update usuario set status = 1 where cd_usuario = "+cd+";";
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			smt.executeUpdate();
		}catch(Exception e ) {
			System.out.println("ERRO: "+ e.getMessage());
		}
	}
	public boolean gerarArq(String caminho, List<Usuario> usuarios) {
		this.usuarios=usuarios;
		Date data = new Date(System.currentTimeMillis());
		try {
			FileWriter arq = new FileWriter(caminho);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println("# RELATÓRIO DOS CLIENTES CADASTRADOS GERADO EM "+data+" #");
			for(int i = 0; i<usuarios.size(); i++) {
				gravarArq.print("ID: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getCdUsuario());
				gravarArq.print("Nome: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getNmUsuario());
				gravarArq.print("CPF: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getCpfUsuario());
				gravarArq.print("Celular: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getCelular());
				gravarArq.print("Sexo: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getSexo());
				gravarArq.print("Data de Nascimento: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getDtNascimento());
				gravarArq.print("E-mail: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getEmail());
				gravarArq.print("Perfil: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getPerfil());
				gravarArq.print("Login: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getLogin());
				gravarArq.print("Senha: ");
				gravarArq.println(((Usuario) usuarios.get(i)).getSenha());
				gravarArq.print("Status: ");
				if(((Usuario) usuarios.get(i)).getStatus() == 1) {
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
}