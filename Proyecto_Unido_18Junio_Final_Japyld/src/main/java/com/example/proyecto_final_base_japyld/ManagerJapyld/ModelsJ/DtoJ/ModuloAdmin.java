package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ;

import java.math.BigDecimal;
import java.sql.Date;

public class ModuloAdmin {
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

    private int idImagen;
    private String imagen;
    private String tipo;
    private String id_rol;

    private int juegoPorEntregar;
    private int juegoComprados;

    private BigDecimal DineroGastoTotal;
    private BigDecimal DineroCompraTotal;
    private BigDecimal DineroTotal;

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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getId_rol() {
        return id_rol;
    }

    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public BigDecimal getDineroCompraTotal() {
        return DineroCompraTotal;
    }

    public void setDineroCompraTotal(BigDecimal dineroCompraTotal) {
        DineroCompraTotal = dineroCompraTotal;
    }

    public BigDecimal getDineroTotal() {
        return DineroTotal;
    }

    public void setDineroTotal(BigDecimal dineroTotal) {
        DineroTotal = dineroTotal;
    }
}
