package com.example.proyecto_final_base_japyld.BeansGenerales;

import java.io.InputStream;

public class Imagen {
    public int getIdImagenes() {
        return idImagenes;
    }

    public void setIdImagenes(int idImagenes) {
        this.idImagenes = idImagenes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }

    private int idImagenes;

    private byte[] imagen;
    private String tipo;
    private String direccionArchivo;

    public InputStream getImagem() {
        return imagem;
    }

    public void setImagem(InputStream imagem) {
        this.imagem = imagem;
    }

    private InputStream imagem;

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
