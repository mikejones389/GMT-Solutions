package model;

import java.util.Date;

public class Compra {
	
	private int cdCompra;
	private String dtCompra;
	private String dtEntrega;
	private int cdFornecedor;
	private int cdLivro;
	private int quantidade;
	private double preco;
	
	public Compra(){
		System.out.println("Construtor de Compra");
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
	public int getCdFornecedor() {
		 return cdFornecedor;
	}
	public void setCdFornecedora(int cdFornecedor) {
		 this.cdFornecedor = cdFornecedor;
	}
	public int getCdLivro() {
		return cdLivro;
	}
	public void setCdLivro(int cdLivro) {
		this.cdLivro = cdLivro;
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
	@Override
	public String toString() {
		return "Compra [cdCompra=" + cdCompra + ", dtCompra=" + dtCompra + ", dtEntrega=" + dtEntrega
				+ ", cdFornecedor=" + cdFornecedor + ", cdLivro=" + cdLivro + ", quantidade=" + quantidade + ", preco="
				+ preco + "]";
	}
	
	
}

