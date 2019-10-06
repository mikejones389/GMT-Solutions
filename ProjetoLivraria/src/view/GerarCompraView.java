package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Compra;

public class GerarCompraView extends JPanel {
	
	private JTextField dtCompraField;
	private JTextField dtEntregaField;
	private JTextField cdFornecedor;
	
	private Compra compra;
	
	//Método Gerar Compra	
	public GerarCompraView () {
		criarFormulario();
	
}
	private void criarFormulario() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		
		
		
		JLabel titulo = new JLabel("Compra");
		titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
						
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.LINE_START;
		gbc.anchor = GridBagConstraints.NORTH;
		add(titulo,gbc);
		
		JLabel dtCompraLabel = new JLabel ("Data da Compra");
		dtCompraLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(dtCompraLabel, gbc);
		
		dtCompraField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(dtCompraField, gbc);
		
		
		
		JLabel dtEntregaLabel = new JLabel ("Data de Entrega");
		dtEntregaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(dtEntregaLabel, gbc);
		
		dtEntregaField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(dtEntregaField, gbc);
		
		
		
		JLabel cdFornecedorLabel = new JLabel ("Código do Fornecedor");
		dtEntregaLabel.setFont(new Font("Arial", Font.BOLD, 16));
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(cdFornecedorLabel, gbc);
		
		cdFornecedorField = new JTextField(30);
		gbc.gridx=1;
		gbc.insets= new Insets(10,0,0,5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(cdFornecedorField, gbc);
		
	}
}
