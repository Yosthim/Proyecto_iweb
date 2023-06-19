package com.example.proyecto_base_japyld.Admin.Models.Beans;

public class JuegosEBean {
    private int id_juego;
    private String juegoNombre;
    private String Descripcion;
    private String categoria;
    private int precio;
    private String direccion_imagen;

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getJuegoNombre() {
        return juegoNombre;
    }

    public void setJuegoNombre(String juegoNombre) {
        this.juegoNombre = juegoNombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDireccion_imagen() {
        return direccion_imagen;
    }

    public void setDireccion_imagen(String direccion_imagen) {
        this.direccion_imagen = direccion_imagen;
    }
}