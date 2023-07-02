package com.example.proyecto_final_base_japyld.BeansGenerales;

import java.math.BigDecimal;

public class Juegos {
    private int idJuegos;
    private String nombreJuegos;
    private int stock;
    private BigDecimal precio;
    private String estadoJuego;
    private String descripcion;
    private int idImagen;
    private Categoria categoria;
    private int id_categoria;
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
    public String getEstaoJuego() {
        return estadoJuego;
    }
    public void setEstaoJuego(String estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(String estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}
