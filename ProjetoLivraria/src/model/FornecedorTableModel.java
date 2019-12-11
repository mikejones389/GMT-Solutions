package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class FornecedorTableModel extends AbstractTableModel{
	private List<Fornecedor> dados = new ArrayList<Fornecedor>();
	private String[] colunas = {"ID", "Nome Fornecedor", "Telefone", "E-mail", "Status"};	
	
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
			return dados.get(linha).getCodigo();
		case 1:
			return dados.get(linha).getNmFornecedor();
		case 2:
			return dados.get(linha).getTelefone();
		case 3:
			return dados.get(linha).getEmail();
		case 4:
			if(dados.get(linha).getStatus() == 1) {
				String status = "Ativo";
				return status;
			}
			else {
				String status = "Não ativo";
				return status;
			}
		case 5:
			return dados.get(linha).getRzSocial();
		case 6:
			return dados.get(linha).getCnpj();	
		case 7:
			return dados.get(linha).getCelular();
		case 8:
			return dados.get(linha).getNmFantasia();
		}
		return null;
	}
	
	public void addROw(Fornecedor f) {
		this.dados.add(f);
	}

	public int getSelectRow(int linha) {
		return dados.get(linha).getCodigo();
	}
	
	public void removeAll() {
		this.dados.clear();
	}
} 