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

import dao.UsuarioDAO;
import model.Usuario;
import model.UsuarioTableModel;

public class ListarClienteView extends JPanel {
	String rows[][]= {};
	String headers[]= {};
	UsuarioTableModel tableModel = new UsuarioTableModel();
	JTable jtUsuarios = new JTable(new DefaultTableModel(rows, headers));
	
	public ListarClienteView() {
		jtUsuarios.setModel(tableModel);
		
		criarFormulario();
	}
	
	public void criarFormulario() {
		this.setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Clientes Cadastrados", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial",Font.BOLD, 40));
		JPanel panelSuperior = new JPanel();
		JPanel panelCentral = new JPanel();
		JScrollPane scrollPane = new JScrollPane(jtUsuarios);
		
		UsuarioDAO ud = new UsuarioDAO();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (ArrayList<Usuario>) ud.Listar();
		
		System.out.println("Listar Usuario View");
		for (int i=0; i<usuarios.size(); i++) {
			System.out.println(usuarios.get(i).getNmUsuario());
			System.out.println(usuarios.get(i).getCpfUsuario());
			System.out.println(usuarios.get(i).getCelular());
			
			tableModel.addRow(usuarios.get(i));
		
		}
		
		panelCentral.add(scrollPane);
		panelSuperior.add(titulo);
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);	
	}
}
