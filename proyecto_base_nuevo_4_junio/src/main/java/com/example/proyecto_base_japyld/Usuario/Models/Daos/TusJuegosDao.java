package com.example.proyecto_base_japyld.Usuario.Models.Daos;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.JuegoComRes;
import com.example.proyecto_base_japyld.Usuario.Models.Beans.PaginaprincipalJuegosBean;

import java.sql.*;
import java.util.ArrayList;

public class TusJuegosDao {

    public ArrayList<JuegoComRes> listarTusJuegos(int idUsuario) {
        ArrayList<JuegoComRes> listaJuegos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT jr.id_usuario, jr.id_juego, j.nombreJuegos, jr.estadoCompraJuego, i.direccion_archivo, j.id_categoria FROM juegoscompradosreservados jr left join juegos j on jr.id_juego = j.idJuegos\n" +
                "left join imagenes i on j.id_imagen = i.idImagenes\n" +
                "where jr.id_usuario = "+idUsuario+"\n" +
                "order by jr.id_juego";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegoComRes juego = new JuegoComRes();
                juego.setIdJuego(resultSet.getInt(2));
                juego.setNombre(resultSet.getString(3));
                juego.setEstado(resultSet.getString(4));
                juego.setDireccionImagen(resultSet.getString(5));
                juego.setIdCategoria(resultSet.getString(6));

                listaJuegos.add(juego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }
}
