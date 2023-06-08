package com.example.proyecto_base_japyld.Manager.Models.Beans;

import java.sql.Date;

public class MUsuarioModulo {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int numJuegos;
    private Date fechaRegistro;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNumJuegos() {
        return numJuegos;
    }

    public void setNumJuegos(int numJuegos) {
        this.numJuegos = numJuegos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
