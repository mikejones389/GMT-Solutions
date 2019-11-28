package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CompraTableModel extends AbstractTableModel{
	
	private List<Compra> dados = new ArrayList<Compra>();
	private String[] colunas = {"ID da Compra", "Dt de Compra", "Dt de Entrega", "Título", "Fornecedor", "Preco"};
		
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
			return dados.get(linha).getCdCompra();
		case 1:
			return dados.get(linha).getDtCompra();
		case 2:
			return dados.get(linha).getDtEntrega();
		case 3:
			return dados.get(linha).getCdLivro();
		case 4:
			return dados.get(linha).getCdFornecedor();
		case 5:
			return dados.get(linha).getPreco();
				
		}
		return null;
	}
	
	public void addRow(Compra c) {
		this.dados.add(c);
	}
	public int getSelectRow(int linha) {
		return dados.get(linha).getCdCompra();
	}
	public void removeAll() {
		this.dados.clear();
	}
}
