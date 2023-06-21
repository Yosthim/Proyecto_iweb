package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TusJuegosDao extends BaseDao {

    public ArrayList<JuegosCompradosReservados> listarJuegos(int idUsuario) {
        ArrayList<JuegosCompradosReservados> listaJuegos = new ArrayList<>();

        String sql = "SELECT jr.id_juego, j.nombreJuegos, jr.estadoCompraJuego, i.direccion_archivo FROM juegoscompradosreservados jr left join juegos j on jr.id_juego = j.idJuegos\n" +
                "left join imagenes i on j.id_imagen = i.idImagenes\n" +
                "where jr.id_usuario = "+idUsuario+"\n" +
                "order by jr.id_juego";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {

                JuegosCompradosReservados juegoComp = new JuegosCompradosReservados();
                Juegos juego = new Juegos();
                Imagen img = new Imagen();
                juego.setIdJuegos(resultSet.getInt(1));
                juego.setNombreJuegos(resultSet.getString(2));
                juegoComp.setEstadoCompraJuego(resultSet.getString(3));
                img.setDireccionArchivo(resultSet.getString(4));
                juego.setImagen(img);
                juegoComp.setJuego(juego);

                listaJuegos.add(juegoComp);

            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }

}
