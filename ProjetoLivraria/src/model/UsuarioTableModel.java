package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel{
	private List<Usuario> dados = new  ArrayList<Usuario>();
	private String[] colunas = {"ID","Nome", "CPF", "Celular", "Status"};
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
		case 0:
			return dados.get(linha).getCdUsuario();
		case 1:
			return dados.get(linha).getNmUsuario();
		case 2:
			return dados.get(linha).getCpfUsuario();
		case 3:
			return dados.get(linha).getCelular();
		case 4:
			if(dados.get(linha).getStatus() == 1) {
				String status = "Ativo";
				return status;
			}
			else {
				String status = "Não ativo";
				return status;
			}
			//return dados.get(linha).getStatus();
		case 5:
			return dados.get(linha).getDtNascimento();
		case 6:
			return dados.get(linha).getEmail();
		case 7:
			return dados.get(linha).getPerfil();
		case 8:
			return dados.get(linha).getLogin();
		case 9: 
			return dados.get(linha).getSenha();	
		case 10:
			return dados.get(linha).getSexo();
		}
		return null;
	}
	
	public void addRow(Usuario u) {
		this.dados.add(u);
	}
	
	public int getSelectRow(int linha) {
		return dados.get(linha).getCdUsuario();
	}
	
	public void removeAll() {
		this.dados.clear();
	}
}