package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		compras = new ArrayList<Compra>();
		compras = (ArrayList<Compra>) cd.Listar();
		for (int i = 0; i < compras.size(); i++) {
			tableModel.addRow(compras.get(i));
		}
		JScrollPane scrollPane = new JScrollPane(jtCompras);
		JPanel jpGerarArq = new JPanel();
		JButton jbGerarArq = new JButton("Gerar Arquivo");
		jbGerarArq.addActionListener(this);
		jbGerarArq.setActionCommand("gerar arquivo");
		jpGerarArq.add(jbGerarArq);
		
		this.setLayout(new BorderLayout());
		panelSuperior.setLayout(new GridLayout(1,3));
		JLabel vazio = new JLabel("");
		panelSuperior.add(vazio);
		panelSuperior.add(titulo);
		panelSuperior.add(jpGerarArq);
		panelCentral.add(scrollPane, BorderLayout.NORTH);
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
//		this.add(jpGerarArq, BorderLayout.EAST);
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
		if(e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		}
	}
}