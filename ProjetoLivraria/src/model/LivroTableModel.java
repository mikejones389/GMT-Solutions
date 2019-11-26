package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class LivroTableModel extends AbstractTableModel{
	
	private List<Livro> dados = new ArrayList<Livro>();
	private String[] colunas = {"Código", "Título","Quantidade"};
	
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
			return dados.get(linha).getCdLivro();
		case 1: 
			return dados.get(linha).getNomeLivro();
		case 2:
			return dados.get(linha).getQntLivro();
		}
		
		return null;
	}
	public void addRow(Livro l) {
		this.dados.add(l);
	}
	public int getSelectRow(int linha) {
		return dados.get(linha).getCdLivro();
	}
	public void removeAll() {
		this.dados.clear();
	}
	
}
