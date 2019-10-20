package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.FornecedorController;
import controller.LivroController;

public class MenuView extends JFrame implements ActionListener{
	
	JMenuBar menuBar;
		JMenu menuCadastro, menuCompra,menuListar;
		JMenuItem itemLivro, itemFornecedor, itemCompra, itemListarLivro, itemListarFornecedor;
		
		ImageIcon imagem1 = new ImageIcon(getClass().getResource("Logo2.png"));
		JLabel imagem = new JLabel(imagem1);
		
		public MenuView(){
			this.inicializar();
			this.construir();
			add(imagem);
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
		
		//public void formulario() {
			
			
			/*setLayout(new GridLayout(3,4));
			GridBagLayout gbl = new GridBagLayout();
			gbl.getLayoutDimensions();
						
			JLabel titulo = new JLabel("GMT");
			titulo.setFont(new Font("Arial",Font.CENTER_BASELINE, 100));
			
			JLabel subTitulo = new JLabel("Solutions");
			subTitulo.setFont(new Font("Arial", Font.LAYOUT_NO_LIMIT_CONTEXT, 20));
			
			add(titulo);
			add(subTitulo);
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx=0.1;
			gbc.weighty=0.1;
			
			JLabel titulo = new JLabel("GMT");
			titulo.setFont(new Font("Arial", Font.ITALIC, 240));
							
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.LINE_START;
			gbc.anchor = GridBagConstraints.NORTH;
			add(titulo,gbc);
			
			JLabel cdFornecedorLabel = new JLabel ("SOLUTIONS");
			cdFornecedorLabel.setFont(new Font("Arial", Font.BOLD, 40));
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.insets= new Insets(10,0,0,5);
			//gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.fill = GridBagConstraints.SOUTH;
			gbc.anchor = GridBagConstraints.CENTER;
			add(cdFornecedorLabel, gbc);
			
			JLabel textoMovimento = new JLabel ("Versão 0.0.1");
			textoMovimento.setFont(new Font("Arial", Font.BOLD, 40));
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.insets= new Insets(10,0,0,5);
			//gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.fill = GridBagConstraints.SOUTH;
			gbc.anchor = GridBagConstraints.CENTER;
			add(textoMovimento,gbc);
			*/
		//}
		
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


