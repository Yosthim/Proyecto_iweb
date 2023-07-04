package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ;

import java.math.BigDecimal;

public class BarrasInicioAdmin {
    private int idJuego;
    private int countJuegos;
    private String Nombre;
    private BigDecimal precioJuego;

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getCountJuegos() {
        return countJuegos;
    }

    public void setCountJuegos(int countJuegos) {
        this.countJuegos = countJuegos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public BigDecimal getPrecioJuego() {
        return precioJuego;
    }

    public void setPrecioJuego(BigDecimal precioJuego) {
        this.precioJuego = precioJuego;
    }
}
