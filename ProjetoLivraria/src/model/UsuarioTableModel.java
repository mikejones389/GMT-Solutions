package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel{
	
	private List<Usuario> dados = new  ArrayList<Usuario>();
	private String[] colunas = {"Nome", "CPF", "Celular"};
	
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
			return dados.get(linha).getNmUsuario();
		case 1:
			return dados.get(linha).getCpfUsuario();
		case 2:
			return dados.get(linha).getCelular();
		}
		return null;
	}
	public void addRow(Usuario u) {
		this.dados.add(u);
	}
	
}
