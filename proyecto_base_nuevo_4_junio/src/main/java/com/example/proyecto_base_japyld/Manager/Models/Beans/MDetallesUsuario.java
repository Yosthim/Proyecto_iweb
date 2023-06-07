package com.example.proyecto_base_japyld.Manager.Models.Beans;

import java.math.BigDecimal;
import java.sql.Date;

public class MDetallesUsuario {
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaDeNacimiento;
    private int dni;
    private String genero;
    private String categoriaJuegoPreferida;
    private Date fechaRegistro;
    private String estado;

    private String nombreJuegos;
    private BigDecimal precio_admin;

    private BigDecimal precio;

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

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCategoriaJuegoPreferida() {
        return categoriaJuegoPreferida;
    }

    public void setCategoriaJuegoPreferida(String categoriaJuegoPreferida) {
        this.categoriaJuegoPreferida = categoriaJuegoPreferida;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreJuegos() {
        return nombreJuegos;
    }

    public void setNombreJuegos(String nombreJuegos) {
        this.nombreJuegos = nombreJuegos;
    }

    public BigDecimal getPrecio_admin() {
        return precio_admin;
    }

    public void setPrecio_admin(BigDecimal precio_admin) {
        this.precio_admin = precio_admin;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
