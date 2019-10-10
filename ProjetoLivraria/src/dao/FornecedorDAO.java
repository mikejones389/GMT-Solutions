package dao;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

import bdd.BancoDeDados;
import model.Fornecedor;

public class FornecedorDAO {
	//static Connection connection = null;
	static PreparedStatement statement = null;
	
	
	public static boolean inserir(Fornecedor fornecedor) {
		Connection bdd = BancoDeDados.conectar();
		System.out.println(bdd);
		if(BancoDeDados.estaConectado()) {
			System.out.println("CONECTADO fornecedorDAO");
			
			
			/*private static void inserirFornecedor(int cdFornecedor, String nmFornecedor, String nmFantasia, String rzSocial,
					int cnpj, String email, int telefone, int celular) {
				System.out.println( "Cheguei no inserir");
				System.out.println( cd_fornecedor + nm_fornecedor +  nm_fantasia + rz_social + cnpj + email + telefone + celular );
			try {
				String query = "INSERT INTO fornecedor(  cd_fornecedor ,nm_fornecedor, nm_fantasia, rz_social, cnpj, email, telefone, celular) VALUES ('" + cd_fornecedor +"' , '" + nm_fornecedor +"', '" + nm_fantasia +"', '" + rz_social +"', '" + cnpj + "', '" + email + "' ,'" + telefone + "','" + celular +"');";
				System.out.println("inserirQuery"+ query);
				statement.executeUpdate(query);
			}catch(Exception e){
				System.out.println("ERRO: "+ e.getMessage());
			}
			return false;
			*/
			BancoDeDados.listarLivros();
			/*bdd.inserirLivro("As Crônicas de Gelo e o Fogo", "George R R Martin", "Atlas ", "Aventura", 2014, 1, 250.00, 10, 3, "about-02.jpg");*/
			
			//BancoDeDados.inserirFornecedor(fornecedor.getCdFornecedor(), fornecedor.getNmFornecedor(),fornecedor.getNmFantasia(), fornecedor.getRzSocial(), fornecedor.getCnpj(), fornecedor.getEmail(), fornecedor.getTelefone(), fornecedor.getCelular());
			
			//bdd.inserirObjetoLivro(livro);
			//bdd.desconectar();
			/*return true;
			}*/
		}else {
			return false;
		}
		return false;
	}


	
}
