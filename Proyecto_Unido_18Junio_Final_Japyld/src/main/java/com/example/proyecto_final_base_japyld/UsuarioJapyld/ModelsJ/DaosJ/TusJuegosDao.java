package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.*;
import java.util.ArrayList;

public class TusJuegosDao extends BaseDao {

    public ArrayList<JuegosCompradosReservados> listarJuegos(int idUsuario) {
        ArrayList<JuegosCompradosReservados> listaJuegos = new ArrayList<>();

        String sql = "SELECT jr.id_juego, j.nombreJuegos, jr.estadoCompraJuego, j.id_imagen,jr.id_consola,jr.idJuegosCompradosReservados FROM juegoscompradosreservados jr left join juegos j on jr.id_juego = j.idJuegos\n" +
                "                left join imagenes i on j.id_imagen = i.idImagenes\n" +
                "                where jr.id_usuario = "+idUsuario+"\n" +
                "                order by jr.estadoCompraJuego desc;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {

                JuegosCompradosReservados juegoComp = new JuegosCompradosReservados();
                Juegos juego = new Juegos();

                juego.setIdJuegos(resultSet.getInt(1));
                juego.setNombreJuegos(resultSet.getString(2));
                juegoComp.setEstadoCompraJuego(resultSet.getString(3));
                juego.setIdImagen(resultSet.getInt(4));
                juegoComp.setJuego(juego);

                Consola consolas = new Consola();
                consolas.setIdConsola(resultSet.getString(5));
                juegoComp.setConsola(consolas);
                juegoComp.setIdJuegosCompradosReservados(resultSet.getInt(6));
                listaJuegos.add(juegoComp);

            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }

    public ArrayList<Juegos> listarJuegosRecomendados(String categoria) {

        ArrayList<Juegos> juegosRecomendados = new ArrayList<>();
        String sql = "SELECT idJuegos, nombreJuegos, id_imagen, nombre FROM juegos j INNER JOIN categorias c on j.id_categoria = c.idCategorias\n" +
                "WHERE c.nombre = '" + categoria + "'";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                Juegos juego = new Juegos();
                juego.setIdJuegos(rs.getInt(1));
                juego.setNombreJuegos(rs.getString(2));
                juego.setIdImagen(rs.getInt(3));
                Categoria category = new Categoria();
                category.setNombre(rs.getString(4));
                juego.setCategoria(category);

                juegosRecomendados.add(juego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return juegosRecomendados;
    }

    public String categoriaMasComprada(int idUsuario) {

        String categoria = null;
        String sql = "SELECT count(j.id_categoria) as `Cuenta`, c.nombre FROM juegoscompradosreservados jc\n" +
                "LEFT JOIN juegos j ON jc.id_juego = j.idJuegos LEFT JOIN categorias c ON c.idCategorias = j.id_categoria\n" +
                "WHERE jc.estadoCompraJuego = 'Comprado' AND jc.id_usuario = " + idUsuario + "\n" +
                "GROUP BY j.id_categoria\n" +
                "ORDER BY `Cuenta`";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //Obtenemos solo el primer resultado de la consulta porque es la categoria más comprada
            if (rs.next()) {
                categoria = rs.getString(2);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categoria;
    }

    public String categoriaPreferida(int idUsuario) {

        String categoria = null;
        String sql = "SELECT categoriaJuegoPreferida FROM personas WHERE idPersona = " + idUsuario;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                categoria = rs.getString(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categoria;
    }

    public void añadirRating(int idjcr, int rating){

        String sql = "UPDATE juegoscompradosreservados\n" +
                     "SET rating = ? \n" +
                     "WHERE idJuegosCompradosReservados = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,rating);
            preparedStatement.setInt(2,idjcr);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
