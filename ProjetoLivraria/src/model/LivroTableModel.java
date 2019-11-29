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
		case 3: 
			return dados.get(linha).getPrecoVenda();
		case 4:
			return dados.get(linha).getAnoLivro();
		case 5:
			return dados.get(linha).getAutorLivro();
		case 6:
			return dados.get(linha).getCdFornecedor();
		case 7: 
			return dados.get(linha).getEdicaoLivro();
		case 8:
			return dados.get(linha).getEditoraLivro();
		case 9:
			return dados.get(linha).getGeneroLivro();
		case 10:
			return dados.get(linha).getLinkImg();
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
