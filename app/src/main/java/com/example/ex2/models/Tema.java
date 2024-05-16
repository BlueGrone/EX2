package com.example.ex2.models;

public class Tema {
    private int idTema;
    private String tema;
    private int nroLibros;

    public Tema(int idTema, String tema, int nroLibros) {
        this.idTema = idTema;
        this.tema = tema;
        this.nroLibros = nroLibros;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getNroLibros() {
        return nroLibros;
    }

    public void setNroLibros(int nroLibros) {
        this.nroLibros = nroLibros;
    }
}
