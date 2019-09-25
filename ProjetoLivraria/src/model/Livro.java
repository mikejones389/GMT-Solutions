package model;

public class Livro {
	private int cdLivro;
	private String nmLivro;
	private String autorLivro;
	private String editoraLivro;
	private String generoLivro;
	private int anoLivro;
	private int edicaoLivro;
	private double precoVenda;
	private int qntLivro;
	private int cdFornecedor;
	private String linkImg;
	
	 public Livro(){
	        System.out.println("Construtor do Livro\n");
	 }
	 
	 public String getNomeLivro() {
	        return nmLivro;
	 }
	 
	 public void setNomeLivro(String nome) {
	        this.nmLivro = nome;
	    }
	 
	 public String getAutorLivro() {
		 return autorLivro;
	 }
	 
	 public void setAutorLivro(String autorLivro) {
		 this.autorLivro = autorLivro;
	 }
	 
	 public String getEditoraLivro() {
		 return editoraLivro;
	 }
	 
	 public void setEditoraLivro(String editoraLivro) {
		 this.editoraLivro = editoraLivro;
	 }
	 
	 public String getGeneroLivro(){
		 return generoLivro;
	 }
	
	 public void setGeneroLivro(String generoLivro) {
		 this.generoLivro = generoLivro;
	 }
	 
	 public int getAnoLivro() {
		 return anoLivro;
	 }
	 
	 public void setAnoLivro(int anoLivro) {
		 this.anoLivro = anoLivro;
	 }
	 
	 public int getEdicaoLivro() {
		 return edicaoLivro;
	 }
	 
	 public void setEdicaoLivro(int edicaoLivro) {
		 this.edicaoLivro = edicaoLivro;
	 }

	 public double getPrecoVenda() {
		 return precoVenda;
	 }
	 
	 public void setPrecoVenda(double precoLivro) {
		 this.precoVenda=precoVenda;
	 }
	
	 public int getQntLivro() {
		 return qntLivro;
	 }
	 
	 public void setQntLivro(int qntLivro) {
		 this.qntLivro=qntLivro;
	 }

	 public int getCdFornecedor() {
		 return cdFornecedor;
	 }
	 
	 public void setCdFornecedor(int cdFornecedor) {
		 this.cdFornecedor=cdFornecedor;
	 }

	 public String getLinkImg() {
		 return linkImg;
	 }
	 
	 public void setLinkImg(String linkImg) {
		 this.linkImg=linkImg;
	 }
}


