package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.FornecedorController;
import controller.LivroController;

public class MenuView extends JFrame implements ActionListener{
		JMenuBar menuBar;
		JMenu menuCadastro, menuCompra,menuListar;
		JMenuItem itemLivro, itemFornecedor, itemCompra, itemListarLivro, itemListarFornecedor;
		
		public MenuView() {
			this.inicializar();
			this.construir();
			this.setJMenuBar(menuBar);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,600);
			this.setVisible(true);
			
		}
		
		public void inicializar() {
			menuBar = new JMenuBar();
			menuCadastro = new JMenu("Cadastro");
			menuCompra = new JMenu("Compras");
			menuListar = new JMenu("Listar");
			itemLivro = new JMenuItem("Cadastrar Livro");
			itemLivro.addActionListener(this);
			itemLivro.setActionCommand("cadastrarLivro");
			
			itemFornecedor = new JMenuItem("Cadastrar Fornecedor");
			itemFornecedor.addActionListener(this);
			itemFornecedor.setActionCommand("cadastrarFornecedor");
			
			itemCompra = new JMenuItem("Gerar Compra");
			itemCompra.addActionListener(this);
			itemCompra.setActionCommand("gerarCompra");
			
			itemListarLivro = new JMenuItem("Listar Livros");
			itemListarLivro.addActionListener(this);
			itemListarLivro.setActionCommand("listarLivro");
			
			itemListarFornecedor = new JMenuItem("Listar Fornecedor");
			itemListarFornecedor.addActionListener(this);
			itemListarFornecedor.setActionCommand("listarFornecedor");
						
		}
		
		public void construir() {
			menuCadastro.add(itemLivro);
			menuCadastro.add(itemFornecedor);
			menuBar.add(menuCadastro);
			menuCompra.add(itemCompra);
			menuBar.add(menuCompra);
			menuListar.add(itemListarLivro);
			menuListar.add(itemListarFornecedor);
			menuBar.add(menuListar);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("cadastrarLivro")) {
				CadastroLivroView clv = new CadastroLivroView();
				clv.setVisible(true);
				this.getContentPane().removeAll();
				this.getContentPane().add(clv);
				this.revalidate();
				this.repaint();
			}
			else if(e.getActionCommand().equals("cadastrarFornecedor")) {
				CadastroFornecedorView cfv = new CadastroFornecedorView();
				cfv.setVisible(true);
				this.getContentPane().removeAll();
				this.getContentPane().add(cfv);
				this.revalidate();
				this.repaint();
			}
			
			else if(e.getActionCommand().equals("gerarCompra")) {
				GerarCompraView cfv = new GerarCompraView();
				cfv.setVisible(true);
				this.getContentPane().removeAll();
				this.getContentPane().add(cfv);
				this.revalidate();
				this.repaint();
			}
			
			else if(e.getActionCommand().equals("listarLivro")) {
				System.out.println("Cliquei no listar livros");
				LivroController lc = new LivroController(); 
				lc.ListarLivros();
				//ListarLivroView llv = new ListarLivroView();
				//llv.setVisible(true);
				this.getContentPane().removeAll();
				//this.getContentPane().add(llv);
				this.revalidate();
				this.repaint();
			}
			
			else if(e.getActionCommand().equals("listarFornecedor")) {
				System.out.println("Cliquei no listar Fornecedor");
				FornecedorController lc = new FornecedorController(); 
				lc.ListarFornecedor();
				//ListarLivroView llv = new ListarLivroView();
				//llv.setVisible(true);
				this.getContentPane().removeAll();
				//this.getContentPane().add(llv);
				this.revalidate();
				this.repaint();
			}
		}
		
}


