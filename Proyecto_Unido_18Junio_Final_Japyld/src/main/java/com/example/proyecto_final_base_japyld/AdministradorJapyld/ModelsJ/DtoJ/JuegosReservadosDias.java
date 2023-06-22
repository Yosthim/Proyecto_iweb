package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ;


public class JuegosReservadosDias {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    private String nombre;
    private String usuario;
    private int dias;
}
