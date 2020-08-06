package com.gmt.makeyourbook.model;

public class Usuario {

    private int cd_usuario;
    private String nm_usuario;
    private String sexo;
    private String data_nascimento;
    private String login;
    private String senha;

    public Usuario(int cd_usuario, String login, String senha){
        this.cd_usuario = cd_usuario;
        this.login = login;
        this.senha = senha;
    }
    public Usuario(String nm_usuario, String sexo, String data_nascimento, String login, String senha) {
        this.nm_usuario = nm_usuario;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
        this.login = login;
        this.senha = senha;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
