package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ;

public class CompraDto {

    private int IdJuego;
    private String nombreJuego;

    private String direccion_imagen;

    private String nombreCategoria;

    private String idConsola;

    private int stock_consola;

    private int precio;

    private int precio_nuevo;

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public String getDireccion_imagen() {
        return direccion_imagen;
    }

    public void setDireccion_imagen(String direccion_imagen) {
        this.direccion_imagen = direccion_imagen;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getIdConsola() {
        return idConsola;
    }

    public void setIdConsola(String idConsola) {
        this.idConsola = idConsola;
    }

    public int getStock_consola() {
        return stock_consola;
    }

    public void setStock_consola(int stock_consola) {
        this.stock_consola = stock_consola;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPrecio_nuevo() {
        return precio_nuevo;
    }

    public void setPrecio_nuevo(int precio_nuevo) {
        this.precio_nuevo = precio_nuevo;
    }

    public int getIdJuego() {
        return IdJuego;
    }

    public void setIdJuego(int idJuego) {
        IdJuego = idJuego;
    }
}
