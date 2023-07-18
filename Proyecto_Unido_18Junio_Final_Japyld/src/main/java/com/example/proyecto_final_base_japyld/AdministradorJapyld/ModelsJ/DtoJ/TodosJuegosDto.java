package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;

public class TodosJuegosDto {
    private int idJuegos;
    private String nombreJuegos;

    private int precio;
    private Imagen imagen;

    private int stock;

    private String estado_juego;
    private int precio_nuevo;

    public int getPrecio_nuevo() {
        return precio_nuevo;
    }

    public void setPrecio_nuevo(int precio_nuevo) {
        this.precio_nuevo = precio_nuevo;
    }


    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

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

    public String getEstado_juego() {
        return estado_juego;
    }

    public void setEstado_juego(String estado_juego) {
        this.estado_juego = estado_juego;
    }
}
