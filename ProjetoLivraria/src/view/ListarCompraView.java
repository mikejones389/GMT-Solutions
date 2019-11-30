package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.CompraDAO;
import model.Compra;
import model.CompraTableModel;

public class ListarCompraView extends JPanel implements ActionListener{
	
	CompraTableModel tableModel = new CompraTableModel();
	CompraDAO cd = new CompraDAO();
	String rows[][] = {};
	String headers[] = {};
	JTable jtCompras = new JTable(new DefaultTableModel(rows, headers));
	int n;
	ArrayList<Compra> compras;
	public ListarCompraView() {
		jtCompras.setModel(tableModel);
		inicializar();
		
	}
	public void inicializar() {
		JPanel panelSuperior = new JPanel();
		JPanel panelCentral = new JPanel();
		
		JLabel titulo = new JLabel("Histórico de Compras", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		
		compras = new ArrayList<Compra>();
		compras = (ArrayList<Compra>) cd.Listar();
		System.out.println("Listar compra view " + compras.size());
		for (int i = 0; i < compras.size(); i++) {
			System.out.println("Entrei no For");
			System.out.println(compras.get(i).getCdCompra());
			System.out.println(compras.get(i).getDtCompra());
			System.out.println(compras.get(i).getDtEntrega());
			System.out.println(compras.get(i).getLivro().getCdLivro());
			System.out.println(compras.get(i).getFornecedor().getCodigo());
			System.out.println(compras.get(i).getPreco());
			
			tableModel.addRow(compras.get(i));
		}
		
		JScrollPane scrollPane = new JScrollPane(jtCompras);
		
		JButton jbGerarArq = new JButton("Gerar Arquivo");
		jbGerarArq.addActionListener(this);
		jbGerarArq.setActionCommand("gerar arquivo");
		
		this.setLayout(new BorderLayout());
		
		panelSuperior.add(titulo);
		panelCentral.add(scrollPane, BorderLayout.NORTH);
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(jbGerarArq, BorderLayout.EAST);
	}
	
	public void gerarArq() {
		CompraDAO cd = new CompraDAO();
		n++;
		Date data = new Date(System.currentTimeMillis());
		String arq = ""+n+"RelatórioDeCompra-"+data+".txt";
		ArrayList texto = (ArrayList) compras;
		if(cd.gerarArq(arq, texto)) {
			JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso");
		}
		else {
			JOptionPane.showMessageDialog(null, "Falha ao gerar arquivo");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		}
	}
	
	
}
