package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto;

import java.sql.*;
import java.util.ArrayList;

public class PaginaPrincipalDao {

    public ArrayList<PaginaPrincipalDto> listarJuegos(){
        ArrayList<PaginaPrincipalDto> listaJuegos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select idJuegos,nombreJuegos,precio,direccion_archivo\n" +
                "                from juegos j\n" +
                "                inner join imagenes i on j.id_imagen = i.idImagenes\n" +
                "                where j.estadoJuego = \"Activo\" or j.estadoJuego = \"Oferta\";";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                PaginaPrincipalDto juegosPrincipal = new PaginaPrincipalDto();
                juegosPrincipal.setIdJuegos(resultSet.getInt(1));
                juegosPrincipal.setNombreJuegos(resultSet.getString(2));
                juegosPrincipal.setPrecio(resultSet.getInt(3));
                juegosPrincipal.setDireccion_imagen(resultSet.getString(4));

                listaJuegos.add(juegosPrincipal);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }
}
