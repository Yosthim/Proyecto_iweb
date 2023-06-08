package com.example.proyecto_base_japyld.Manager.Models.Beans;

public class Juegos {
    private int idJuegos;
    private String nombreJuegos;
    private int precio;
    private int stock;
    private String descripcion_juego;
    private String categoria;
    private int id_imagen;
    private String direccion_imagen;

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion_juego() {
        return descripcion_juego;
    }

    public void setDescripcion_juego(String descripcion_juego) {
        this.descripcion_juego = descripcion_juego;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }

    public String getDireccion_imagen() {
        return direccion_imagen;
    }

    public void setDireccion_imagen(String direccion_imagen) {
        this.direccion_imagen = direccion_imagen;
    }
}
