package model;

public class Compra {
	private int cdCompra;
	private String dtCompra;
	private String dtEntrega;
	private Fornecedor fornecedor;
	private Livro livro;
	private int quantidade;
	private double preco;
	
	public Compra(){
		livro = new Livro();
		fornecedor = new Fornecedor();
	}
	
	public int getCdCompra() {
		 return cdCompra;
	}
	
	public void setCdCompra(int cdCompra) {
		 this.cdCompra = cdCompra;
	}
	
	public String getDtCompra() {
		 return dtCompra;
	}
	
	public void setDtCompra(String dtCompra) {
		 this.dtCompra = dtCompra;
	}
	
	public String getDtEntrega() {
		 return dtEntrega;
	}
	
	public void setDtEntrega(String dtEntrega) {
		 this.dtEntrega = dtEntrega;
	}
	
	public Fornecedor getFornecedor() {
		 return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		 this.fornecedor = fornecedor;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro cdLivro) {
		this.livro = cdLivro;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getIdLivro() {
		return livro.getCdLivro();
	}
	
	public int getIdFornecedor() {
		return fornecedor.getCodigo();
	}
	
	@Override
	public String toString() {
		return "Compra [cdCompra=" + cdCompra + ", dtCompra=" + dtCompra + ", dtEntrega=" + dtEntrega
				+ ", cdFornecedor=" + fornecedor.getCodigo()+ ", cdLivro=" + livro.getCdLivro() + ", quantidade=" + quantidade + ", preco="
				+ preco + "]";
	}	
}