package model;

import java.util.Date;

public class Compra {
	
	private int cdCompra;
	private Date dtCompra;
	private Date dtEntrega;
	private int cdFornecedor;
	
	public Compra(){
		System.out.println("Construtor de Compra");
	}
	public int getCdCompra() {
		 return cdCompra;
	}
	public void setCdCompra(int cdCompra) {
		 this.cdCompra = cdCompra;
	}
	public Date getDtCompra() {
		 return dtCompra;
	}
	public void setDtCompra(Date dtCompra) {
		 this.dtCompra = dtCompra;
	}
	public Date getDtEntrega() {
		 return dtEntrega;
	}
	public void setDtEntrega(Date dtEntrega) {
		 this.dtCompra = dtEntrega;
	}
	public int getCdFornecedor() {
		 return cdFornecedor;
	}
	public void setCdFornecedora(int cdFornecedor) {
		 this.cdFornecedor = cdFornecedor;
	}
	
	
	
}

