package com.example.proyecto_final_base_japyld.BeansGenerales;

import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;

import java.util.Date;

public class JuegosCompradosReservados {
    private int idJuegosCompradosReservados;
    private Date fechaCompraJuego;
    private String estadoCompraJuego;
    private int rating;
    private  Personas usuario;

    public int getIdJuegosCompradosReservados() {
        return idJuegosCompradosReservados;
    }

    public void setIdJuegosCompradosReservados(int idJuegosCompradosReservados) {
        this.idJuegosCompradosReservados = idJuegosCompradosReservados;
    }

    public Date getFechaCompraJuego() {
        return fechaCompraJuego;
    }

    public void setFechaCompraJuego(Date fechaCompraJuego) {
        this.fechaCompraJuego = fechaCompraJuego;
    }

    public String getEstadoCompraJuego() {
        return estadoCompraJuego;
    }

    public void setEstadoCompraJuego(String estadoCompraJuego) {
        this.estadoCompraJuego = estadoCompraJuego;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Personas getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Personas administrador) {
        this.administrador = administrador;
    }

    public Juegos getJuego() {
        return juego;
    }

    public void setJuego(Juegos juego) {
        this.juego = juego;
    }

    public Consola getConsola() {
        return consola;
    }

    public void setConsola(Consola consola) {
        this.consola = consola;
    }

    private Personas administrador;
    private Juegos juego;
    private Consola consola;

    private int precio_compra;

    public int getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }
}
