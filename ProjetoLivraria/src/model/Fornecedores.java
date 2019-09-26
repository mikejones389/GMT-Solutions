package model;

public class Fornecedores {
	private int cdFornecdor;
	private String nmFornecedor;
	private String nmFantasia;
	private String rzSocial;
	private int cnpj;
	private String email;
	private int telefone;
	private int celular;
	
	
	public Fornecedores(){
        System.out.println("Construtor do Fornecedor\n");
	}
	public String getNomeFornecedor() {
	        return nmFornecedor;
	}
	 
	public void setNomeFornecedor(String nmFornecedor) {
	        this.nmFornecedor = nmFornecedor;
	}
} 