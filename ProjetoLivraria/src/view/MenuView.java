package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuView extends JFrame implements ActionListener{
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem itemLivro, itemFornecedor;
		
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
			menu = new JMenu("Cadastro");
			itemLivro = new JMenuItem("Cadastrar Livro");
			itemLivro.addActionListener(this);
			itemLivro.setActionCommand("cadastrarLivro");
			
			itemFornecedor = new JMenuItem("Cadastrar Fornecedor");
			itemFornecedor.addActionListener(this);
			itemFornecedor.setActionCommand("cadastrarFornecedor");
						
		}
		
		public void construir() {
			menu.add(itemLivro);
			menu.add(itemFornecedor);
			menuBar.add(menu);
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
		}
		
}


