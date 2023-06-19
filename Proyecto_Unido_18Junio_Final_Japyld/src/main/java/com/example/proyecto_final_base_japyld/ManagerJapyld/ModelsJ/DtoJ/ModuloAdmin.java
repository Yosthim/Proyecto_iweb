package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ;

import java.math.BigDecimal;

public class ModuloAdmin {
    private int id;
    private String nombre;
    private String apellido;

    private int juegoPorEntregar;
    private int juegoComprados;

    private BigDecimal DineroGastoTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getJuegoPorEntregar() {
        return juegoPorEntregar;
    }

    public void setJuegoPorEntregar(int juegoPorEntregar) {
        this.juegoPorEntregar = juegoPorEntregar;
    }

    public int getJuegoComprados() {
        return juegoComprados;
    }

    public void setJuegoComprados(int juegoComprados) {
        this.juegoComprados = juegoComprados;
    }

    public BigDecimal getDineroGastoTotal() {
        return DineroGastoTotal;
    }

    public void setDineroGastoTotal(BigDecimal dineroGastoTotal) {
        DineroGastoTotal = dineroGastoTotal;
    }
}
