package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.CompraDAO;
import model.Compra;
import model.CompraTableModel;

public class ListarCompraView extends JPanel {
	
	CompraTableModel tableModel = new CompraTableModel();
	CompraDAO cd = new CompraDAO();
	String rows[][] = {};
	String headers[] = {};
	JTable jtCompras = new JTable(new DefaultTableModel(rows, headers));
	
	public ListarCompraView() {
		jtCompras.setModel(tableModel);
		inicializar();
		
	}
	public void inicializar() {
		JPanel panelSuperior = new JPanel();
		JPanel panelCentral = new JPanel();
		
		JLabel titulo = new JLabel("Histórico de Compras", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		
		List<Compra> compras = new ArrayList<Compra>();
		compras = (ArrayList<Compra>) cd.Listar();
		System.out.println("Listar compra view");
		for (int i = 0; i < compras.size(); i++) {
			System.out.println("Entrei no For");
			System.out.println(compras.get(i).getCdCompra());
			System.out.println(compras.get(i).getDtCompra());
			System.out.println(compras.get(i).getDtEntrega());
			//System.out.println(compras.get(i).getCdLivro());
			System.out.println(compras.get(i).getCdFornecedor());
			//System.out.println(compras.get(i).getPreco());
			
			tableModel.addRow(compras.get(i));
		}
		
		JScrollPane scrollPane = new JScrollPane(jtCompras);
		
		this.setLayout(new BorderLayout());
		
		panelSuperior.add(titulo);
		panelCentral.add(scrollPane, BorderLayout.NORTH);
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		
	}
	
	
}
