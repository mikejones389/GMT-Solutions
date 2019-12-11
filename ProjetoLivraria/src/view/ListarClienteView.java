package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import model.Usuario;
import model.UsuarioTableModel;

public class ListarClienteView extends JPanel implements ActionListener {
	String rows[][] = {};
	String headers[] = {};
	UsuarioTableModel tableModel = new UsuarioTableModel();
	JTable jtUsuarios = new JTable(new DefaultTableModel(rows, headers));
	int cdCliente;
	List<Usuario> usuarios;
	int n;

	public ListarClienteView() {
		jtUsuarios.setModel(tableModel);
		criarFormulario();
	}

	public void criarFormulario() {
		this.setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Clientes Cadastrados", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 40));
		JPanel panelSuperior = new JPanel();
		JPanel panelCentral = new JPanel();
		JScrollPane scrollPane = new JScrollPane(jtUsuarios);
		UsuarioDAO ud = new UsuarioDAO();
		usuarios = (ArrayList<Usuario>) ud.Listar();
		for (int i = 0; i < usuarios.size(); i++) {
			tableModel.addRow(usuarios.get(i));
		}
		JButton jbDesativar = new JButton("Desativar");
		jbDesativar.addActionListener(this);
		jbDesativar.setActionCommand("deletar");
		JButton jbAtivar = new JButton("Ativar");
		jbAtivar.addActionListener(this);
		jbAtivar.setActionCommand("ativar");
		JButton jbGerarArq = new JButton("Gerar Arquivo");
		jbGerarArq.addActionListener(this);
		jbGerarArq.setActionCommand("gerar arquivo");
		panelCentral.add(scrollPane);
		panelSuperior.add(titulo);
		JPanel panelWest = new JPanel();
		panelWest.setLayout(new GridLayout(2,1));
		panelWest.add(jbAtivar);
		panelWest.add(jbDesativar);
		this.add(panelWest, BorderLayout.WEST);
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(jbGerarArq, BorderLayout.EAST);
	}

	public void desativar() {
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = usuarios.get(jtUsuarios.getSelectedRow()).getCdUsuario();
		UsuarioDAO ud = new UsuarioDAO();
		ud.desativar(cd);
	}

	public void gerarArq() {
		UsuarioDAO ud = new UsuarioDAO();
		n++;
		Date data = new Date(System.currentTimeMillis());
		String arq = "" + n + "RelatórioDeClientes-" + data + ".txt";
		ArrayList texto = (ArrayList) usuarios;
		if (ud.gerarArq(arq, texto)) {
			JOptionPane.showMessageDialog(null, "Arquivo gerado com Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao gerar Arquivo");
		}
	}

	public void ativar() {
		MouseEvent evt = null;
		tableModelMouseClicked(evt);
		int cd = usuarios.get(jtUsuarios.getSelectedRow()).getCdUsuario();
		UsuarioDAO ud = new UsuarioDAO();
		ud.ativar(cd);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("deletar")) {
			desativar();
		}
		else if (e.getActionCommand().equals("gerar arquivo")) {
			gerarArq();
		}
		else if (e.getActionCommand().equals("ativar")) {
			ativar();
		}
	}

	private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {
		cdCliente = jtUsuarios.getSelectedRow();
		TableModelListener[] model = tableModel.getTableModelListeners();
	}
}