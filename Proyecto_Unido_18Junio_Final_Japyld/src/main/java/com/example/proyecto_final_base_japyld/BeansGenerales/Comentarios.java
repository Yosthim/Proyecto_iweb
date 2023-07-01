package com.example.proyecto_final_base_japyld.BeansGenerales;

public class Comentarios {

    private int idComentario;

    private String comentario;

    private int ratingComentario;

    private String fecha_comentario;
    private Juegos juegoComentario;

    private Personas personaComentario;

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getRatingComentario() {
        return ratingComentario;
    }

    public void setRatingComentario(int ratingComentario) {
        this.ratingComentario = ratingComentario;
    }

    public Juegos getJuegoComentario() {
        return juegoComentario;
    }

    public void setJuegoComentario(Juegos juegoComentario) {
        this.juegoComentario = juegoComentario;
    }

    public Personas getPersonaComentario() {
        return personaComentario;
    }

    public void setPersonaComentario(Personas personaComentario) {
        this.personaComentario = personaComentario;
    }

    public String getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(String fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }
}
