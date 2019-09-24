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
	        this.nmLivro = nmLivro;
	    }
	 
	 public String getAutorLivro() {
		 return autorLivro;
	 }
	 
	 public void setAutorLivro(String autor) {
		 this.autorLivro = autorLivro;
	 }
	 
	 public String getEditoraLivro() {
		 return editoraLivro;
	 }
	 
	 public void setEditoraLivro(String editora) {
		 this.editoraLivro = editoraLivro;
	 }
	 
	 public String getGeneroLivro(){
		 return generoLivro;
	 }
	
	 public void setGeneroLivro(String genero) {
		 this.generoLivro = generoLivro;
	 }
	 
	 public int getAnoLivro() {
		 return anoLivro;
	 }
	 
	 public void setAnoLivro(int ano) {
		 this.anoLivro = anoLivro;
	 }
	 
	 public int getEdicaoLivro() {
		 return edicaoLivro;
	 }
	 
	 public void setEdicaoLivro(int edicao) {
		 this.edicaoLivro = edicaoLivro;
	 }

	 public double getPrecoVenda() {
		 return precoVenda;
	 }
	 
	 public void setPrecoVenda(double preco) {
		 this.precoVenda=precoVenda;
	 }
	
	 public int getQntLivro() {
		 return qntLivro;
	 }
	 
	 public void setQntLivro(int qnt) {
		 this.qntLivro=qntLivro;
	 }

	 public int setCdFornecedor() {
		 return cdFornecedor;
	 }
	 
	 public void getCdFornecedor(int fornecedor) {
		 this.cdFornecedor=cdFornecedor;
	 }

	 public String setLinkImg() {
		 return linkImg;
	 }
	 
	 public void getLinkImg(String link) {
		 this.linkImg=linkImg;
	 }
}


