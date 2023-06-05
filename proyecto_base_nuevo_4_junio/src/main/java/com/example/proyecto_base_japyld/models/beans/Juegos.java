package com.example.proyecto_base_japyld.models.beans;

public class Juegos {
    private int idJuegos;
    private String nombreJuegos;

    public int getIdJuegos() {
        return idJuegos;
    }

    public void setIdJuegos(int idJuegos) {
        this.idJuegos = idJuegos;
    }

    public String getNombreJuegos() {
        return nombreJuegos;
    }

    public void setNombreJuegos(String nombreJuegos) {
        this.nombreJuegos = nombreJuegos;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstaoJuego() {
        return estaoJuego;
    }

    public void setEstaoJuego(String estaoJuego) {
        this.estaoJuego = estaoJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_imaggen() {
        return id_imaggen;
    }

    public void setId_imaggen(int id_imaggen) {
        this.id_imaggen = id_imaggen;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    private int stock;
    private double precio;
    private String estaoJuego;
    private String descripcion;
    private int id_imaggen;
    private int id_categoria;
}
