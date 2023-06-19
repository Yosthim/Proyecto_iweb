package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDao extends BaseDao {

    public ArrayList<JuegosManager> resumenComentarios(){

        ArrayList <JuegosManager> ultimosJuegosComentados = new ArrayList<>();

        String sql = "SELECT j.idJuegos, j.nombreJuegos FROM juegos j, comentarios c\n" +
                "WHERE j.idJuegos = c.Juegos_idJuegos\n" +
                "GROUP BY j.idJuegos, j.nombreJuegos\n" +
                "ORDER BY MAX(c.fecha_comentario);";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager comentarioJuegoReciente = new JuegosManager();
                comentarioJuegoReciente.setIdJuegos(resultSet.getInt(1));
                comentarioJuegoReciente.setNombreJuegos(resultSet.getString(2));
                ultimosJuegosComentados.add(comentarioJuegoReciente);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ultimosJuegosComentados;
    }

    public ArrayList<JuegosManager> comentarioPorJuego(int idjuego){

        ArrayList<JuegosManager> comentarioPorJuego = new ArrayList<>();

        String sql = "SELECT j.idJuegos, c.comentario FROM juegos j, comentarios c\n" +
                "WHERE j.idJuegos = c.Juegos_idJuegos and j.idJuegos = ?\n" +
                "ORDER BY c.ratingComentario DESC;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {

            ptsmtJuego.setInt(1,idjuego);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    JuegosManager comentarioJuego = new JuegosManager();
                    comentarioJuego.setIdJuegos(rs.getInt(1));
                    comentarioJuego.setComentarios(rs.getString(2));
                    comentarioPorJuego.add(comentarioJuego);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comentarioPorJuego;
    }

    public ArrayList<JuegosManager> catologoReciente(){

        ArrayList<JuegosManager> nuevoIngreso = new ArrayList<>();

        String sql = "SELECT j.idJuegos, j.nombreJuegos, c.nombre, j.precio, i.direccion_archivo  FROM juegos j, ventajuegosgeneral vjg, categorias c, imagenes i\n" +
                "WHERE j.idJuegos = vjg.id_juego and j.id_categoria = c.idCategorias and vjg.estadoVenta = 'Aceptado' and j.id_imagen = i.idImagenes\n" +
                "ORDER BY vjg.fechaPublicacion DESC\n" +
                "LIMIT 4;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager nuevoJuego = new JuegosManager();
                nuevoJuego.setIdJuegos(resultSet.getInt(1));
                nuevoJuego.setNombreJuegos(resultSet.getString(2));
                nuevoJuego.setCategoria(resultSet.getString(3));
                nuevoJuego.setPrecio(resultSet.getInt(4));
                nuevoJuego.setDireccion_imagen(resultSet.getString(5));
                nuevoIngreso.add(nuevoJuego);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevoIngreso;

    }


    public ArrayList<Objetivos> Objetivos(){

        ArrayList<Objetivos> objetivos= new ArrayList<>();

        String sql = "SELECT * FROM objetivosmanager o\n" +
                "left join personas p on o.id_manager = p.idPersona\n" +
                "WHERE o.id_manager = (SELECT idPersona FROM japyld.personas\n" +
                "\tWHERE id_roles ='MNG');";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Objetivos objetivos1 = new Objetivos();

                objetivos1.setIdObjetivos(resultSet.getInt(1));
                objetivos1.setVentasPorMesJuego(resultSet.getInt(2));
                objetivos1.setGastosPorMesJuego(resultSet.getInt(3));
                objetivos1.setUsuarioPorMes(resultSet.getInt(4));

                Personas manager = new Personas();
                manager.setIdPersona(resultSet.getInt("p.idPersona"));

                objetivos.add(objetivos1);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return objetivos;
    }

    public void actualizarObjetivos(Objetivos objetivos) {

        String sql = "UPDATE objetivosmanager SET ventasPorMesJuego = ?,gastosPorMesJuego = ?,usariosPorMes = ? WHERE id_objetivo = 1;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, objetivos.getVentasPorMesJuego());
            pstmt.setInt(2, objetivos.getGastosPorMesJuego());
            pstmt.setInt(3, objetivos.getUsuarioPorMes());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
