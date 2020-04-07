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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractButton;
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
import dao.LivroDAO;
import model.Compra;
import model.Fornecedor;
import model.FornecedorTableModel;
import model.Livro;
import model.LivroTableModel;
import principal.Principal;

public class GerarCompraView extends JPanel implements ActionListener {
	private JTextField dtCompraField;
	private JTextField dtEntregaField;
	private JTextField cdFornecedorField;
	private JTextField cdLivroField;
	private JFrame frame;
	public static String codLivro;
	public static String codFornecedor;
	public static String dtCompra;
	public static String dtEntrega;
	JDateChooser dataCompra;
	JDateChooser dataEntrega;
	JFrame frameList = new JFrame();
	LivroTableModel tableModelLivro = new LivroTableModel();
	FornecedorTableModel tableModelFornecedor = new FornecedorTableModel();
	int rows;
	int headers;
	private Compra compra;
	JTable jtFornecedor = new JTable(new DefaultTableModel(rows, headers));
	JTable jtLivro = new JTable(new DefaultTableModel(rows, headers));
	
	public GerarCompraView(JFrame principal) {
		this.frame = principal;
		criarFormulario();
	}

	public void criarFormulario() {
		this.setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Compra", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
		this.add(titulo, BorderLayout.NORTH);
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(10, 0, 0, 5);
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel espaco1 = new JLabel("");
		espaco1.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCentral.add(espaco1, gbc);
		JLabel cdFornecedor = new JLabel("Código do Fornecedor");
		cdFornecedor.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCentral.add(cdFornecedor, gbc);
		cdFornecedorField = new JTextField(20);
		cdFornecedorField.setEditable(false);
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(cdFornecedorField, gbc);
		JButton botaoFornecedor = new JButton("Selecionar");
		botaoFornecedor.addActionListener(this);
		botaoFornecedor.setActionCommand("listarFornecedor");
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelCentral.add(botaoFornecedor, gbc);
		JLabel cdLivro = new JLabel("Código do Livro");
		cdLivro.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(cdLivro, gbc);
		cdLivroField = new JTextField(20);
		cdLivroField.setEditable(false);
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(cdLivroField, gbc);
		JButton botaoLivro = new JButton("Selecionar");
		botaoLivro.addActionListener(this);
		botaoLivro.setActionCommand("listarLivro");
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelCentral.add(botaoLivro, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel dtCompraLabel = new JLabel("Data da Compra");
		dtCompraLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panelCentral.add(dtCompraLabel, gbc);
		gbc.gridx = 2;
		dataCompra = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dataCompra.setFont(new Font("Arial", Font.BOLD, 16));
		dataCompra.setPreferredSize(new Dimension(200, 20));
		dataCompra.setDate(new Date());
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(dataCompra, gbc);
		JLabel dtEntregaLabel = new JLabel("Data de Entrega");
		dtEntregaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(dtEntregaLabel, gbc);
		dataEntrega = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dataEntrega.setFont(new Font("Arial", Font.BOLD, 16));
		dataEntrega.setPreferredSize(new Dimension(200, 20));
		dataEntrega.setDate(new Date());
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentral.add(dataEntrega, gbc);
		this.add(panelCentral, BorderLayout.CENTER);
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(this);
		botaoSalvar.setActionCommand("salvar");
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(botaoSalvar, gbc);
		JLabel cr = new JLabel("Copyright ©2019 Todos os direitos reservados | GMT Group");
		cr.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelCentral.add(cr, gbc);
	}
		
	public void acaoSalvar() {
		codLivro = cdLivroField.getText();
		compra = new Compra();
		compra.getFornecedor().setCodigo(Integer.parseInt(cdFornecedorField.getText()));
		compra.getLivro().setCdLivro(Integer.parseInt(cdLivroField.getText()));
		Date dtCompra= dataCompra.getDate();
		String dataCompra= DateFormat.getDateInstance().format(dtCompra);
		compra.setDtCompra(dataCompra);
		Date dtEntrega= dataEntrega.getDate();
		String dataEntrega = DateFormat.getDateInstance().format(dtEntrega);
		compra.setDtEntrega(dataEntrega);
		FinalizarCompraView fcv = new FinalizarCompraView(compra);
		fcv.criarFormulario();
		fcv.setVisible(true);
		this.frame.getContentPane().removeAll();
		this.frame.getContentPane().add(fcv);
		this.frame.revalidate();
		this.frame.repaint();
	}

	public void acaoListarFornecedor() {
		jtFornecedor.setModel(tableModelFornecedor);
		frameList = new JFrame();
		frameList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameList.setVisible(true);
		frameList.setSize(300, 450);
		frameList.setResizable(false);
		frameList.setLocationRelativeTo(cdFornecedorField);
		frameList.setLayout(new GridLayout(3, 1));
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new GridLayout(1, 1));
		JLabel titulo = new JLabel("Selecione o Fornecedor", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		panelTitulo.add(titulo);
		frameList.add(panelTitulo);
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		FornecedorDAO fd = new FornecedorDAO();
		int cdList = 1;
		fornecedores = (List<Fornecedor>) fd.Listar(cdList);
		tableModelFornecedor.removeAll();
		for (int i = 0; i < fornecedores.size(); i++) {
			tableModelFornecedor.addROw(fornecedores.get(i));
		}
		jtFornecedor.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				int row = jtFornecedor.rowAtPoint(e.getPoint());
				row = jtFornecedor.getSelectedRow();
				jtFornecedor.getSelectionModel().setSelectionInterval(row, row);
				cdFornecedorField.setText("" + tableModelFornecedor.getSelectRow(row));
				cdFornecedorField.setEditable(false);
				if (e.getButton() == MouseEvent.BUTTON3) {
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(jtFornecedor);
		frameList.add(scrollPane);
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 10);
		JButton jbConcluir = new JButton("Concluir");
		jbConcluir.addActionListener(this);
		jbConcluir.setActionCommand("concluir");
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(this);
		jbCancelar.setActionCommand("cancelarFornecedor");
		panelInferior.add(jbConcluir, gbc);
		gbc.gridx = 1;
		panelInferior.add(jbCancelar, gbc);
		frameList.add(panelInferior);
		MouseEvent evt = null;
		tableModelMouseClickedFornecedor(evt);
	}
	
	public void acaoConcluir(){
		frameList.setVisible(false);
		frameList.dispose();
	}

	public void acaoCancelarFornecedor() {
		cdFornecedorField.setText("");
		frameList.setVisible(false);
		frameList.dispose();
	}

	public void acaoCancelarLivro() {
		cdLivroField.setText("");
		frameList.setVisible(false);
		frameList.dispose();
	}
	
	public void acaoListarLivro(){
		jtLivro.setModel(tableModelLivro);
		frameList = new JFrame();
		frameList.setVisible(true);
		frameList.setSize(300, 450);
		frameList.setResizable(false);
		frameList.setLocationRelativeTo(cdLivroField);
		frameList.setLayout(new GridLayout(3, 1));
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new GridLayout(1, 1));
		JLabel titulo = new JLabel("Selecione o Livro", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		panelTitulo.add(titulo);
		frameList.add(panelTitulo);
		List<Livro> livros = new ArrayList<Livro>();
		LivroDAO ld = new LivroDAO();
		livros = (List<Livro>) ld.Listar();
		tableModelLivro.removeAll();
		for (int i = 0; i < livros.size(); i++) {
			tableModelLivro.addRow(livros.get(i));
		}
		jtLivro.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = jtLivro.rowAtPoint(e.getPoint());
				row = jtLivro.getSelectedRow();
				jtLivro.getSelectionModel().setSelectionInterval(row, row);
				cdLivroField.setText("" + tableModelLivro.getSelectRow(row));
				cdLivroField.setEditable(false);
				if (e.getButton() == MouseEvent.BUTTON3) {
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(jtLivro);
		frameList.add(scrollPane);
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 10);
		JButton jbConcluir = new JButton("Concluir");
		jbConcluir.addActionListener(this);
		jbConcluir.setActionCommand("concluir");
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(this);
		jbCancelar.setActionCommand("cancelarLivro");
		panelInferior.add(jbConcluir, gbc);
		gbc.gridx = 1;
		panelInferior.add(jbCancelar, gbc);
		frameList.add(panelInferior);
		MouseEvent evt = null;
		tableModelMouseClickedLivro(evt);
	}

	private void tableModelMouseClickedFornecedor(java.awt.event.MouseEvent evt) {
		int i = jtFornecedor.getSelectedRow();
		TableModelListener[] model = tableModelFornecedor.getTableModelListeners();
		cdFornecedorField.setText(jtFornecedor.getValueAt(i, 0).toString());
	}
	
	private void tableModelMouseClickedLivro(java.awt.event.MouseEvent evt) {
		int i = jtLivro.getSelectedRow();
		TableModelListener[] model = tableModelLivro.getTableModelListeners();
		cdLivroField.setText(jtLivro.getValueAt(i, 0).toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("salvar")) {
			acaoSalvar();
		} 
		else if (e.getActionCommand().equals("listarLivro")) {
			 acaoListarLivro();
		}
		else if (e.getActionCommand().equals("listarFornecedor")) {
			acaoListarFornecedor();
		}
		else if (e.getActionCommand().equals("concluir")) {
			acaoConcluir();
		}
		else if (e.getActionCommand().equals("cancelarFornecedor")) {
			acaoCancelarFornecedor();
		}
		else if (e.getActionCommand().equals("cancelarLivro")) {
			acaoCancelarLivro();
		}
	}
}