package com.gmt.makeyourbook.model;

public class Projeto {

    private String nmUsuario;
    private int cd_usuario, cd_projeto;
    private String titulo;
    private String genero;
    private String historia;
    private int avatar;
    private Double valorTotal;
    private Double valorArrecadado;

    public Projeto(int cd_projeto, String nmUsuario, int cd_usuario, String titulo, String genero, String historia, int avatar, Double valorTotal, Double valorArrecadado) {
        this.nmUsuario = nmUsuario;
        this.cd_usuario= cd_usuario;
        this.cd_projeto= cd_projeto;
        this.titulo = titulo;
        this.genero = genero;
        this.historia = historia;
        this.avatar = avatar;
        this.valorTotal = valorTotal;
        this.valorArrecadado = valorArrecadado;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public int getCd_projeto() {
        return cd_projeto;
    }

    public void setCd_projeto(int cd_projeto) {
        this.cd_projeto = cd_projeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(Double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }
}
