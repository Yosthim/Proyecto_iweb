package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;

import java.io.InputStream;

public class ImagenPerfilDto {

    private int idPersona;
    private int idImagen;
    private InputStream imagen;

    private String tipo;

    private String direccion_archivo;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion_archivo() {
        return direccion_archivo;
    }

    public void setDireccion_archivo(String direccion_archivo) {
        this.direccion_archivo = direccion_archivo;
    }
}
