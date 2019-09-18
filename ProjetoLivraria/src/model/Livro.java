package model;

public class Livro {
	private int cdLivro;
	private String nmLivro;
	private String autor;
	private String editora;
	private String genero;
	private int anoLivro;
	private int edicao;
	private double precoVenda;
	private int qntLivro;
	private int cdFornecedor;
	private String linkImg;
	
	
	 public Livro(){
	        System.out.println("Construtor do Livro\n");
	 }
	 
	 public String getNome() {
	        return nmLivro;
	 }
	 
	 public void setNome(String nome) {
	        this.nmLivro = nmLivro;
	    }
	 
	
	
}
