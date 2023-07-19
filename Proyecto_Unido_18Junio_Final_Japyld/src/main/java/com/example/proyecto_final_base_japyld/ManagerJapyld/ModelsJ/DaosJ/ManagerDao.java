package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Comentarios;
import com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDao extends BaseDao {

    public ArrayList<JuegosManager> resumenComentarios(){

        ArrayList <JuegosManager> ultimosJuegosComentados = new ArrayList<>();

        String sql = "SELECT j.idJuegos, j.nombreJuegos, j.id_imagen, i.direccion_archivo, MAX(c.fecha_comentario) AS fecha_comentario\n" +
                "FROM juegos j\n" +
                "JOIN comentarios c ON j.idJuegos = c.Juegos_idJuegos\n" +
                "JOIN categorias ca ON j.id_categoria = ca.idCategorias\n" +
                "JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                "GROUP BY j.idJuegos\n" +
                "ORDER BY fecha_comentario DESC\n" +
                "LIMIT 3;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager comentarioJuegoReciente = new JuegosManager();
                comentarioJuegoReciente.setIdJuegos(resultSet.getInt(1));
                comentarioJuegoReciente.setNombreJuegos(resultSet.getString(2));
                comentarioJuegoReciente.setDireccion_imagen(resultSet.getString(4));
                ultimosJuegosComentados.add(comentarioJuegoReciente);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ultimosJuegosComentados;
    }

    public ArrayList<JuegosManager> comentarioPorJuego(int idjuego){

        ArrayList<JuegosManager> comentarioPorJuego = new ArrayList<>();

        String sql = "SELECT j.idJuegos, j.nombreJuegos, p.nombre, p.apellido, c.fecha_comentario, c.comentario FROM juegos j, comentarios c, personas p\n" +
                "WHERE j.idJuegos = ? and p.idPersona = c.Persona_idPersona and c.Juegos_idJuegos = j.idJuegos\n" +
                "ORDER BY c.fecha_comentario DESC\n" +
                "LIMIT 3;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {

            ptsmtJuego.setInt(1,idjuego);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    JuegosManager comentarioJuego = new JuegosManager();
                    Personas personas = new Personas();
                    Comentarios comentarios = new Comentarios();
                    comentarioJuego.setIdJuegos(rs.getInt(1));
                    comentarioJuego.setNombreJuegos(rs.getString(2));
                    personas.setNombre(rs.getString(3));
                    personas.setApellido(rs.getString(4));
                    comentarios.setFecha_comentario1(rs.getTimestamp(5));
                    comentarios.setComentario(rs.getString(6));
                    comentarioJuego.setPersonas(personas);
                    comentarioJuego.setComentarios(comentarios);
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

        String sql = "SELECT * FROM juegos j, categorias c, imagenes i\n" +
                "WHERE j.id_categoria = c.idCategorias and (j.estadoJuego = \"Activo\" or j.estadoJuego = \"Oferta\") and j.id_imagen = i.idImagenes\n" +
                "ORDER BY idJuegos DESC\n" +
                "LIMIT 4;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager nuevoJuego = new JuegosManager();
                nuevoJuego.setIdJuegos(resultSet.getInt(1));
                nuevoJuego.setNombreJuegos(resultSet.getString(2));
                nuevoJuego.setPrecio(resultSet.getInt(4));
                nuevoJuego.setCategoria(resultSet.getString(10));
                nuevoJuego.setDireccion_imagen(resultSet.getString(14));
                nuevoIngreso.add(nuevoJuego);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevoIngreso;

    }


    public ArrayList<Objetivos> Objetivos(){

        ArrayList<Objetivos> objetivos= new ArrayList<>();

        String sql = "SELECT * FROM objetivosmanager\n" +
                "ORDER BY fecha desc\n" +
                "LIMIT 1;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Objetivos objetivos1 = new Objetivos();

                objetivos1.setIdObjetivos(resultSet.getInt(1));
                objetivos1.setVentasPorMesJuego(resultSet.getInt(2));
                objetivos1.setGastosPorMesJuego(resultSet.getInt(3));
                objetivos1.setUsuarioPorMes(resultSet.getInt(4));

                objetivos.add(objetivos1);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return objetivos;
    }

    public void actualizarObjetivos(Objetivos objetivos) {
        String sql = "INSERT INTO objetivosmanager (ventasPorMesJuego,gastosPorMesJuego,usariosPorMes,id_manager,fecha) VALUES (?,?,?,10,?);";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, objetivos.getVentasPorMesJuego());
            pstmt.setInt(2, objetivos.getGastosPorMesJuego());
            pstmt.setInt(3, objetivos.getUsuarioPorMes());
            pstmt.setTimestamp(4, objetivos.getFecha());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Objetivos> listarObjetivosPasados(){

        ArrayList <Objetivos> recordObjetivos = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM objetivosmanager\n" +
                "WHERE id_objetivo NOT IN (SELECT MAX(id_objetivo)\n" +
                "FROM objetivosmanager) ORDER BY fecha DESC;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                Objetivos objetivos = new Objetivos();
                objetivos.setIdObjetivos(resultSet.getInt(1));
                objetivos.setVentasPorMesJuego(resultSet.getInt(2));
                objetivos.setGastosPorMesJuego(resultSet.getInt(3));
                objetivos.setUsuarioPorMes(resultSet.getInt(4));
                objetivos.setFecha(resultSet.getTimestamp(6));
                recordObjetivos.add(objetivos);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return recordObjetivos;
    }


    public ArrayList<Objetivos> ObjetivosPorMes(String mes){

        ArrayList<Objetivos> objetivos= new ArrayList<>();

        String sql = "SELECT * FROM objetivosmanager\n" +
                "WHERE MONTHNAME(fecha) = ?;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtObjetivo = connection.prepareStatement(sql)) {

            ptsmtObjetivo.setString(1,mes);
            try (ResultSet rs = ptsmtObjetivo.executeQuery()){
                while(rs.next()){
                    Objetivos objetivosMes = new Objetivos();

                    objetivosMes.setIdObjetivos(rs.getInt(1));
                    objetivosMes.setVentasPorMesJuego(rs.getInt(2));
                    objetivosMes.setGastosPorMesJuego(rs.getInt(3));
                    objetivosMes.setUsuarioPorMes(rs.getInt(4));
                    objetivosMes.setFecha(rs.getTimestamp(6));
                    objetivos.add(objetivosMes);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objetivos;
    }
}
