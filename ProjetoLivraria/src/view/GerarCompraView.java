package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.util.Date;
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

import com.toedter.calendar.JDateChooser;

import dao.FornecedorDAO;
import model.Compra;
import model.Fornecedor;
import model.FornecedorTableModel;

public class GerarCompraView extends JPanel {
	
	private JTextField dtCompraField;
	private JTextField dtEntregaField;
	private JTextField cdFornecedorField;
	private JTextField cdLivroField;
	
	private Compra compra;
	FornecedorTableModel tableModel = new FornecedorTableModel();
	int rows;
	int headers;
	JTable jtFornecedor = new JTable(new DefaultTableModel(rows, headers));
	
	//Mï¿½todo Gerar Compra	
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
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel espaco1 = new JLabel("");
		espaco1.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=0;
		panelCentral.add(espaco1, gbc);
		
		JLabel cdFornecedor = new JLabel("Código do Fornecedor");
		cdFornecedor.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=1;
		gbc.gridy=0;
		panelCentral.add(cdFornecedor,gbc);
		
		cdFornecedorField = new JTextField(20);
		cdFornecedorField.setEditable(false);
		gbc.gridx=2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(cdFornecedorField, gbc);
		
		ActionListarFornecedor actionListarFornecedor = new ActionListarFornecedor();
		JButton botaoFornecedor= new JButton("Selecionar");
		botaoFornecedor.addActionListener(actionListarFornecedor);
		gbc.gridx=3;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelCentral.add(botaoFornecedor,gbc);
		
		JLabel cdLivro = new JLabel("Código do Livro");
		cdLivro.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(cdLivro,gbc);
		
		cdLivroField = new JTextField(20);
		cdLivroField.setEditable(false);
		gbc.gridx=2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(cdLivroField, gbc);
		
		ActionListarLivro actionListarLivro = new ActionListarLivro();
		JButton botaoLivro = new JButton("Selecionar");
		botaoFornecedor.addActionListener(actionListarLivro);
		gbc.gridx=3;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelCentral.add(botaoLivro, gbc);
		
		
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel dtCompraLabel = new JLabel ("Data da Compra");
		dtCompraLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panelCentral.add(dtCompraLabel,gbc);
		
		gbc.gridx=2;
		JDateChooser dataCompra;
		dataCompra = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dataCompra.setFont(new Font("Arial", Font.BOLD, 16));
		dataCompra.setPreferredSize(new Dimension(200,20));
		dataCompra.setDate(new Date());
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(dataCompra, gbc);
		
		JLabel dtEntregaLabel = new JLabel ("Data de Entrega");
		dtEntregaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(dtEntregaLabel, gbc);
		
		JDateChooser dataEntrega;
		dataEntrega = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dataEntrega.setFont(new Font("Arial", Font.BOLD, 16));
		dataEntrega.setPreferredSize(new Dimension(200,20));
		dataEntrega.setDate(new Date());
		gbc.gridx=2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(dataEntrega, gbc);
		
		
		this.add(panelCentral, BorderLayout.CENTER);
		
		ActionSalvar actionSalvar = new ActionSalvar();
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(actionSalvar);
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(botaoSalvar, gbc);
		
		ActionCancelar actionCancelar = new ActionCancelar();
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(actionCancelar);
		gbc.gridx=2;
		gbc.anchor= GridBagConstraints.LINE_END;
		panelCentral.add(botaoCancelar,gbc);
		
		
//		JLabel espaco2 = new JLabel("");
//		espaco2.setFont(new Font("Arial", Font.BOLD, 16));
//		gbc.gridx=3;
//		gbc.gridy=0;
//		panelCentral.add(espaco2, gbc);

	}
	
	private class ActionCancelar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			FinalizarCompraView fcv = new FinalizarCompraView();
		}
	}
	
	private class ActionSalvar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			FinalizarCompraView fcv= new FinalizarCompraView();
			fcv.setVisible(true);
			
		}
		
	}
	private class ActionListarFornecedor implements ActionListener{
	
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
				}
				}
				});

			
			JScrollPane scrollPane = new JScrollPane(jtFornecedor);
			frameList.add(scrollPane);
			MouseEvent evt = null;
			tableModelMouseClicked(evt);			
		}
	}
	
	private class ActionListarLivro implements ActionListener{
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
				}
				}
				});

			
			JScrollPane scrollPane = new JScrollPane(jtFornecedor);
			frameList.add(scrollPane);
			MouseEvent evt = null;
			tableModelMouseClicked(evt);			
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
