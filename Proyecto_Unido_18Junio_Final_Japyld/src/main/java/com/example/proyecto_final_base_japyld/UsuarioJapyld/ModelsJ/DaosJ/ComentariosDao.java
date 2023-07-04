package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.*;
import java.util.ArrayList;

public class ComentariosDao extends BaseDao {

    public ArrayList<Comentarios> listarComentarios(int idjuego) {
        ArrayList<Comentarios> listaComentarios = new ArrayList<>();

        String sql = "SELECT idComentarios, comentario, fecha_comentario, ratingComentario, j.nombreJuegos, p.idPersona, p.nombre, p.apellido\n" +
                "FROM comentarios c\n" +
                "INNER JOIN juegos j ON c.Juegos_idJuegos = j.idJuegos\n" +
                "LEFT JOIN personas p ON c.Persona_idPersona = p.idPersona\n" +
                "WHERE j.idJuegos = ?\n" +
                "ORDER BY fecha_comentario DESC\n" +
                "limit 3;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmt = connection.prepareStatement(sql)) {

            ptsmt.setInt(1, idjuego);

            try (ResultSet rs = ptsmt.executeQuery()) {
                while (rs.next()) {
                    Comentarios comentario = new Comentarios();
                    comentario.setIdComentario(rs.getInt(1));
                    comentario.setComentario(rs.getString(2));
                    comentario.setFecha_comentario(rs.getString(3));
                    comentario.setRatingComentario(rs.getInt(4));

                    Juegos juego = new Juegos();
                    juego.setNombreJuegos(rs.getString(5));
                    comentario.setJuegoComentario(juego);

                    Personas persona = new Personas();
                    persona.setIdPersona(rs.getInt(6));
                    persona.setNombre(rs.getString(7));
                    persona.setApellido(rs.getString(8));
                    comentario.setPersonaComentario(persona);

                    listaComentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaComentarios;
    }
    public void guardarComentario(Comentarios comentario){

        String sql = "INSERT INTO comentarios(comentario,fecha_comentario,Juegos_idJuegos,Persona_idPersona) VALUES (?,?,?,?);";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

             pstmt.setString(1,comentario.getComentario());
             long Fecha_comentario = comentario.getFecha_comentario1().getTime();
             java.sql.Date sqlDateFecha_comentario = new java.sql.Date(Fecha_comentario);
             pstmt.setDate(2,sqlDateFecha_comentario);
             pstmt.setInt(3,comentario.getJuegoComentario().getIdJuegos());
             pstmt.setInt(4,comentario.getPersonaComentario().getIdPersona());

             pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


}








