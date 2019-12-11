package model;

public class Fornecedor {
	private int codigo;
	private String nmFornecedor;
	private String nmFantasia;
	private String rzSocial;
	private int cnpj;
	private String email;
	private int telefone;
	private int celular;
	private int status;
	
	public Fornecedor(){
	}

	public String getNmFornecedor() {
		return nmFornecedor;
	}
	
	public void setNmFornecedor(String nmFornecedor) {
		this.nmFornecedor=nmFornecedor;
	}
	
	public String getNmFantasia() {
		return nmFantasia;
	}
	
	public void setNmFantasia(String nmFantasia) {
		this.nmFantasia=nmFantasia;
	}
	
	public String getRzSocial() {
		return rzSocial;
	}
	
	public void setRzSocial(String rzSocial) {
		this.rzSocial=rzSocial;
	}
	
	public int getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(int cnpj) {
		this.cnpj=cnpj;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
	public void setTelefone(int telefone) {
		this.telefone=telefone;
	}
	
	public int getCelular() {
		return celular;
	}
	
	public void setCelular(int celular) {
		this.celular=celular;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status=status;
	}
}