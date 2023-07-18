package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.*;
import java.util.ArrayList;

public class AgregarDao extends BaseDao {
    // lista de categorias
    public ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategorias(resultSet.getString(1));
                categoria.setNombre(resultSet.getString(2));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorias;
    }
    // lista de consolas
    public ArrayList<Consola> listarConsolas() {
        ArrayList<Consola> consolas = new ArrayList<>();
        String sql = "SELECT * FROM consolas";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Consola consola = new Consola();
                consola.setIdConsola(resultSet.getString(1));
                consola.setNombre(resultSet.getString(2));

                consolas.add(consola);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return consolas;
    }
    // agrergar un juego
    public void registrarJuego(Juegos juegos) {

        agregar_imagen(juegos.getImagen());
        int id=id_imagen();

        String sql = "INSERT INTO juegos (nombreJuegos, stock, precio, estadoJuego,descripcion,id_imagen,id_categoria) " +
                    " VALUES (?,?,?,'Activo',?,?,?)";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            String firstLetter = juegos.getNombreJuegos().substring(0, 1).toUpperCase();
            String restOfSentence = juegos.getNombreJuegos().substring(1);
            String nombre  = firstLetter + restOfSentence;

            psmt.setString(1,nombre);
            psmt.setInt(2,juegos.getStock());
            psmt.setBigDecimal(3,juegos.getPrecio());

            String firstLetter1 = juegos.getDescripcion().substring(0, 1).toUpperCase();
            String restOfSentence1 = juegos.getDescripcion().substring(1);
            String descripcion  = firstLetter1 + restOfSentence1;

            psmt.setString(4,descripcion);
            psmt.setInt(5,id);
            psmt.setString(6,juegos.getCategoria().getIdCategorias());

            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    // se agrega imagen
    public void agregar_imagen(Imagen imagen){

        String sql = "INSERT INTO imagenes (imagen, tipo)" +
                " VALUES (?,'juego')";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            psmt.setBinaryStream(1,imagen.getImagem());
            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // se obtiene el id de la imagen requerido para agregar un juego
    public int id_imagen(){

        int id=0;

        String sql = "select count(*) from imagenes;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {

                id= resultSet.getInt(1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }

    public void registrarJuegoXCategoria(JuegosXConsola juegosXConsola) {

        int id=id_juego();

        String sql = "INSERT INTO juegos_por_consolas (id_juego, id_consola,stock_consola)" +
                " VALUES (?,?,?)";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            psmt.setInt(1,id);
            psmt.setString(2,juegosXConsola.getConsolaDeJuego().getIdConsola());
            psmt.setInt(3,juegosXConsola.getStockXConsola());
            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public int id_juego(){

        int id=0;

        String sql = "select count(*) from juegos;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {

                id= resultSet.getInt(1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }

    // se actualiza una imagen en blob

    public void actaualizarImagen(Juegos juegos) {


        String sql = "UPDATE imagenes SET imagen = ?\n" +
                "WHERE idImagenes =?; ";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            psmt.setBinaryStream(1,juegos.getImagen().getImagem());
            psmt.setInt(2,idImagen(juegos));

            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int idImagen(Juegos juegos){

        int id=0;

        String sql = "select  id_imagen\n" +
                "from  juegos\n" +
                "WHERE idJuegos = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,juegos.getIdJuegos());

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    id = resultSet.getInt(1);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }

}
