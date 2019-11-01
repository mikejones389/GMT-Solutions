package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dao.FornecedorDAO;
import model.Compra;
import model.Fornecedor;
import model.FornecedorTableModel;

public class GerarCompraView extends JPanel {
	
	private JTextField dtCompraField;
	private JTextField dtEntregaField;
	private JTextField cdFornecedorField;
	
	private Compra compra;
	FornecedorTableModel tableModel = new FornecedorTableModel();
	int rows;
	int headers;
	JTable jtFornecedor = new JTable(new DefaultTableModel(rows, headers));
	
	//M�todo Gerar Compra	
	public GerarCompraView () {
		
		criarFormulario();
	}
	
	private void criarFormulario() {
			
		this.setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Compra",SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
		this.add(titulo, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx= 0.1;
		gbc.weighty= 0.1;
		gbc.insets= new Insets(10,0,0,5);
		
		gbc.gridx=0;
		gbc.gridy=0;
		JLabel dtCompraLabel = new JLabel ("Data da Compra");
		dtCompraLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panelCentral.add(dtCompraLabel,gbc);
		
		gbc.gridx=1;
		dtCompraField = new JTextField(30);
		panelCentral.add(dtCompraField, gbc);
	
		JLabel dtEntregaLabel = new JLabel ("Data de Entrega");
		dtEntregaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=1;
		panelCentral.add(dtEntregaLabel, gbc);
		
		dtEntregaField = new JTextField(30);
		
		this.add(panelCentral, BorderLayout.CENTER);
//		
//		
//		
//		JLabel cdFornecedorLabel = new JLabel ("Código do Fornecedor");
//		cdFornecedorLabel.setFont(new Font("Arial", Font.BOLD, 16));
//		
//		add(cdFornecedorLabel);
//		
//		cdFornecedorField = new JTextField(30);
//		
//		add(cdFornecedorField);
		
		ActionListar actionListar = new ActionListar();
		JButton botaoFornecedor= new JButton("Selecionar");
		botaoFornecedor.addActionListener(actionListar);
		
		//add(botaoFornecedor);
		
	}
	
	private class ActionListar implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			jtFornecedor.setModel(tableModel);
			
			
			
			JFrame frameList = new JFrame();
			frameList.setVisible(true);
			frameList.setSize(300,450);
			frameList.setResizable(false);
			frameList.setLocationRelativeTo(cdFornecedorField);
			frameList.setLayout(new GridLayout(3,1));
			JPanel panelTitulo = new JPanel();
			panelTitulo.setLayout(new GridLayout(1,1));
			JLabel titulo = new JLabel("Selecione o Fornecedor", SwingConstants.CENTER);
			titulo.setFont(new Font("Arial", Font.BOLD, 18));
			
			panelTitulo.add(titulo);
			frameList.add(panelTitulo);
			
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			FornecedorDAO fd = new FornecedorDAO();
			fornecedores = (List<Fornecedor>) fd.Listar();
			
					
			tableModel.removeAll();
			for (int i = 0; i < fornecedores.size(); i++) {
				System.out.println(fornecedores.get(i).getNmFornecedor());
				System.out.println(fornecedores.get(i).getTelefone());
				
				tableModel.addROw(fornecedores.get(i));
				
				
			}
			
			jtFornecedor.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
				int row = jtFornecedor.rowAtPoint(e.getPoint());
				
				row = jtFornecedor.getSelectedRow();

				jtFornecedor.getSelectionModel().setSelectionInterval(row, row);
				System.out.println("Cheguei no add mouse listener e linha = " + tableModel.getSelectRow(row));
				cdFornecedorField.setText(""+tableModel.getSelectRow(row));
				cdFornecedorField.setEditable(false);
				System.out.println(row);
				if(e.getButton() == MouseEvent.BUTTON3) {
				//popup.show(jtFornecedor, e.getX(), e.getY());
				}
				}
				});

			
			JScrollPane scrollPane = new JScrollPane(jtFornecedor);
			frameList.add(scrollPane);
			//jTFornecedoresMouseClicked();
			
			//selecionar linha da tabela
			MouseEvent evt = null;
			tableModelMouseClicked(evt);	
			//Fornecedor fornecedor = ((List<Fornecedor>) jtFornecedor).get(rows);
			//cdFornecedorField.setText(fornecedor.getNmFantasia());
			
		}
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		System.out.println("Teste");
		int i = jtFornecedor.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
		cdFornecedorField.setText(jtFornecedor.getValueAt(i, 0).toString());
		System.out.println(i);
	}
}
