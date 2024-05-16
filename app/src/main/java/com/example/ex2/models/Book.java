package com.example.ex2.models;

public class Book {
    private String libroId;   // Unique identifier for the book, if necessary
    private String titulo;
    private String autor;
    private double costo;
    private int stock;
    private String edicion;
    private int temaId;       // ID of the theme (tema) the book belongs to

    // Constructor with all parameters
    public Book(String libroId, String titulo, String autor, double costo, int stock, String edicion, int temaId) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.autor = autor;
        this.costo = costo;
        this.stock = stock;
        this.edicion = edicion;
        this.temaId = temaId;
    }

    // Getters and setters for each field
    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public int getTemaId() {
        return temaId;
    }

    public void setTemaId(int temaId) {
        this.temaId = temaId;
    }

    // ToString method for easier debugging and logging
    @Override
    public String toString() {
        return "Book{" +
                "libroId='" + libroId + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", costo=" + costo +
                ", stock=" + stock +
                ", edicion='" + edicion + '\'' +
                ", temaId=" + temaId +
                '}';
    }
}
