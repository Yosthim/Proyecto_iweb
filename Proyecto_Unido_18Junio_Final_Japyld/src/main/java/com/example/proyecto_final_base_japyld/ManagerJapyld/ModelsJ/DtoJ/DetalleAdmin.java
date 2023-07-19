package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ;

import java.math.BigDecimal;
import java.sql.Date;

public class DetalleAdmin {
    private int id;

    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaDeNacimiento;
    private int dni;
    private String contrasenia;
    private String genero;
    private String categoriaJuegoPreferida;
    private Date fechaRegistro;
    private String estado;

    private String nombreJuegos;
    private BigDecimal precioVenta;

    private BigDecimal precioCompra;
    private BigDecimal DineroTotal;

    private String direccion_imagen;
    private int idImagen;

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

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public BigDecimal getDineroTotal() {
        return DineroTotal;
    }

    public void setDineroTotal(BigDecimal dineroTotal) {
        DineroTotal = dineroTotal;
    }

    public String getDireccion_imagen() {
        return direccion_imagen;
    }

    public void setDireccion_imagen(String direccion_imagen) {
        this.direccion_imagen = direccion_imagen;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
