package com.gmt.makeyourbook.model;

public class Projeto {

    private String nmUsuario;
    private int id;
    private String titulo;
    private String genero;
    private String historia;

    public Projeto(String nmUsuario, int id, String titulo, String genero, String historia) {
        this.nmUsuario = nmUsuario;
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.historia = historia;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
