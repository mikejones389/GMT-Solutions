package model;

public class Usuario {
	private String nmUsuario;
	private int cpfUsuario;
	private String sexo;
	private String dtNascimento;
	private String email;
	private int celular;
	private String perfil;
	private String login;
	private String senha;
	private int cdUsuario;
	private int status;
	
	public Usuario() {
	}
	
	public int getCdUsuario() {
		return cdUsuario;
	}
	
	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario=cdUsuario;
	}
	
	public String getNmUsuario(){
		return nmUsuario;
	}
	
	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario=nmUsuario;
	}
	
	public int getCpfUsuario() {
		return cpfUsuario;
	}
	
	public void setCpfUsuario(int cpfUsuario) {
		this.cpfUsuario=cpfUsuario;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo=sexo;
	}
	
	public String getDtNascimento(){
		return dtNascimento;
	}
	
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento= dtNascimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	
	public int getCelular() {
		return celular;
	}
	
	public void setCelular(int celular) {
		this.celular=celular;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil=perfil;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha=senha;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status=status;
	}
}