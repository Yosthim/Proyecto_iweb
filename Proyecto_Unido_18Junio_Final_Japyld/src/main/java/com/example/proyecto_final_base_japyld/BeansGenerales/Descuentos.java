package com.example.proyecto_final_base_japyld.BeansGenerales;

import java.util.Date;

public class Descuentos {

    private int idDescuentos;

    private int precioDescuentoNuevo;

    private Date fechaPublicacion;

    private int duracionDescuento;

    private Juegos juegoDescuento;

    private Personas administradorDescuento;


    public int getIdDescuentos() {
        return idDescuentos;
    }

    public void setIdDescuentos(int idDescuentos) {
        this.idDescuentos = idDescuentos;
    }

    public int getPrecioDescuentoNuevo() {
        return precioDescuentoNuevo;
    }

    public void setPrecioDescuentoNuevo(int precioDescuentoNuevo) {
        this.precioDescuentoNuevo = precioDescuentoNuevo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getDuracionDescuento() {
        return duracionDescuento;
    }

    public void setDuracionDescuento(int duracionDescuento) {
        this.duracionDescuento = duracionDescuento;
    }

    public Juegos getJuegoDescuento() {
        return juegoDescuento;
    }

    public void setJuegoDescuento(Juegos juegoDescuento) {
        this.juegoDescuento = juegoDescuento;
    }

    public Personas getAdministradorDescuento() {
        return administradorDescuento;
    }

    public void setAdministradorDescuento(Personas administradorDescuento) {
        this.administradorDescuento = administradorDescuento;
    }
}
