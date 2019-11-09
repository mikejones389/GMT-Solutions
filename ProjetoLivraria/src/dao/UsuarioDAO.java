package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Usuario;

public class UsuarioDAO {
	public ArrayList<Usuario> Listar(){
		Connection bdd = BancoDeDados.conectar();
		System.out.println("ENTROU no InserirDAO");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		int linha = 0;
		String texto = null;
		
		System.out.println("Entrou no UsuarioDAO.Listar");
		try {
			String sql = "SELECT * FROM usuario ORDER BY cd_usuario";
			
			PreparedStatement smt = (PreparedStatement) bdd.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNmUsuario(rs.getString("nm_usuario"));
				usuario.setCpfUsuario(rs.getInt("cpf_usuario"));
				usuario.setCelular(rs.getInt("celular"));
				
			}			
		}catch(Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			
		}
		
		return (ArrayList<Usuario>) usuarios;
		
	}

}
