package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ;

public class PaginaPrincipalDto {
    private int idJuegos;
    private String nombreJuegos;

    private int precio;

    private String direccion_imagen;

    private int precio_nuevo;

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

    public String getDireccion_imagen() {
        return direccion_imagen;
    }

    public void setDireccion_imagen(String direccion_imagen) {
        this.direccion_imagen = direccion_imagen;
    }

    public int getIdJuegos() {
        return idJuegos;
    }

    public void setIdJuegos(int idJuegos) {
        this.idJuegos = idJuegos;
    }

    public int getPrecio_nuevo() {
        return precio_nuevo;
    }

    public void setPrecio_nuevo(int precio_nuevo) {
        this.precio_nuevo = precio_nuevo;
    }
}
