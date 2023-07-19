package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto;

import java.sql.*;
import java.util.ArrayList;

public class JuegosOfertaDao {
    public ArrayList<PaginaPrincipalDto> listarJuegosOferta(){

        ArrayList<PaginaPrincipalDto> listaJuegosOferta = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "select idJuegos,nombreJuegos,d.precio_nuevo,i.idImagenes,direccion_archivo\n" +
                "                from juegos j\n" +
                "                inner join descuentos d on j.idJuegos = d.id_juego\n" +
                "                inner join imagenes i on j.id_imagen = i.idImagenes\n" +
                "                inner join categorias c on j.id_categoria = c.idCategorias\n" +
                "                \n" +
                "                where j.estadoJuego = \"Oferta\";";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                PaginaPrincipalDto juegooferta = new PaginaPrincipalDto();
                juegooferta.setIdJuegos(resultSet.getInt(1));
                juegooferta.setNombreJuegos(resultSet.getString(2));
                juegooferta.setPrecio(resultSet.getInt(3));
                juegooferta.setIdImagen(resultSet.getInt(4));
                juegooferta.setDireccion_imagen(resultSet.getString(5));

                listaJuegosOferta.add(juegooferta);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegosOferta;

    }


}
