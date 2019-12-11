package principal;

import javax.swing.JFrame;
import view.CadastroFornecedorView;
import view.CadastroLivroView;
import view.MenuView;

public class Principal extends JFrame{

	public static void main(String[] args) {
		MenuView menu = new MenuView();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(800,500);
		menu.setResizable(false);
		menu.setVisible(true);
	}
}