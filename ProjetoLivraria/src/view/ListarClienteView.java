package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import model.Usuario;
import model.UsuarioTableModel;

public class ListarClienteView extends JPanel implements ActionListener{
	String rows[][]= {};
	String headers[]= {};
	UsuarioTableModel tableModel = new UsuarioTableModel();
	JTable jtUsuarios = new JTable(new DefaultTableModel(rows, headers));
	int cdCliente;
	List<Usuario> usuarios;
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
		
		usuarios = (ArrayList<Usuario>) ud.Listar();
		
		System.out.println("Listar Usuario View");
		for (int i=0; i<usuarios.size(); i++) {
			System.out.println(usuarios.get(i).getNmUsuario());
			System.out.println(usuarios.get(i).getCpfUsuario());
			System.out.println(usuarios.get(i).getCelular());
			
			tableModel.addRow(usuarios.get(i));
		
		}
		
		JButton jbDeletar = new JButton("Deletar");
		jbDeletar.addActionListener(this);
		jbDeletar.setActionCommand("deletar");
		
		panelCentral.add(scrollPane);
		panelSuperior.add(titulo);
		this.add(jbDeletar, BorderLayout.WEST);
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);	
	}
	public void deletar() {
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = usuarios.get(jtUsuarios.getSelectedRow()).getCdUsuario();
		UsuarioDAO ud = new UsuarioDAO();
		ud.deletar(cd);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("deletar")) {
			deletar();
		}
		
	}
	
	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		cdCliente = jtUsuarios.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
		System.out.println(cdCliente);
		
	}
	
}
