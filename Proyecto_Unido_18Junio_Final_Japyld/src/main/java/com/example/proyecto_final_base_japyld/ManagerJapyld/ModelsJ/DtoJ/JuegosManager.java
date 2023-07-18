package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Comentarios;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;

public class JuegosManager {
    private int idJuegos;
    private String nombreJuegos;
    private int precio;
    private int stock;
    private String descripcion_juego;
    private String categoria;
    private int id_imagen;
    private String direccion_imagen;
    private int venta_total;
    private int compra_total;
    private int ingreso_mensual;
    private int cant_usuarios;
    private int precio_nuevo;
    private Personas personas;
    private Comentarios comentarios;
    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getVenta_total() {
        return venta_total;
    }

    public void setVenta_total(int venta_total) {
        this.venta_total = venta_total;
    }

    public int getCompra_total() {
        return compra_total;
    }

    public void setCompra_total(int compra_total) {
        this.compra_total = compra_total;
    }

    public int getIngreso_mensual() {
        return ingreso_mensual;
    }

    public void setIngreso_mensual(int ingreso_mensual) {
        this.ingreso_mensual = ingreso_mensual;
    }

    public int getCant_usuarios() {
        return cant_usuarios;
    }

    public void setCant_usuarios(int cant_usuarios) {
        this.cant_usuarios = cant_usuarios;
    }

    public Comentarios getComentarios() {
        return comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
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

    public int getPrecio_nuevo() {
        return precio_nuevo;
    }

    public void setPrecio_nuevo(int precio_nuevo) {
        this.precio_nuevo = precio_nuevo;
    }
}
