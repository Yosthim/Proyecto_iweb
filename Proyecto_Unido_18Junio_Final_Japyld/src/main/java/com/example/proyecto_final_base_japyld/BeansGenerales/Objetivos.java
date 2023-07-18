package com.example.proyecto_final_base_japyld.BeansGenerales;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Objetivos {
    public int getIdObjetivos() {
        return idObjetivos;
    }

    public void setIdObjetivos(int idObjetivos) {
        this.idObjetivos = idObjetivos;
    }

    public int getVentasPorMesJuego() {
        return ventasPorMesJuego;
    }

    public void setVentasPorMesJuego(int ventasPorMesJuego) {
        this.ventasPorMesJuego = ventasPorMesJuego;
    }

    public int getGastosPorMesJuego() {
        return gastosPorMesJuego;
    }

    public void setGastosPorMesJuego(int gastosPorMesJuego) {
        this.gastosPorMesJuego = gastosPorMesJuego;
    }

    public int getUsuarioPorMes() {
        return usuarioPorMes;
    }

    public void setUsuarioPorMes(int usuarioPorMes) {
        this.usuarioPorMes = usuarioPorMes;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    private int idObjetivos;
    private int ventasPorMesJuego;
    private int gastosPorMesJuego;
    private int usuarioPorMes;
    private Personas personaManager;
    private Timestamp fecha;

    public Personas getPersonaManager() {
        return personaManager;
    }

    public void setPersonaManager(Personas personaManager) {
        this.personaManager = personaManager;
    }
}
