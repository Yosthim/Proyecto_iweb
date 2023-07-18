package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ;

public class ProductosVendidos {
    private int id_juego;

    private int ventas;

    private String direccion_archivo;

    private String nombreJuego;

    private int precio;
    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public String getDireccion_archivo() {
        return direccion_archivo;
    }

    public void setDireccion_archivo(String direccion_archivo) {
        this.direccion_archivo = direccion_archivo;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
