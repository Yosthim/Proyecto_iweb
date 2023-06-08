package com.example.proyecto_base_japyld.Manager.Models.Daos;

import com.example.proyecto_base_japyld.Manager.Models.Beans.PorCategoriaJuego;

import java.sql.*;
import java.util.ArrayList;

public class PorCategoriaJuegoDao {

    public ArrayList<PorCategoriaJuego> listarJuegosPorCategoria() {

        ArrayList<PorCategoriaJuego> listaCategoriaJuego = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT c.nombre, count(p.categoriaJuegoPreferida)\n" +
                "FROM categorias c, personas p\n" +
                "WHERE c.nombre = p.categoriaJuegoPreferida\n" +
                "GROUP BY p.categoriaJuegoPreferida\n" +
                "ORDER BY count(p.categoriaJuegoPreferida) DESC;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while (resultSet.next()) {
                PorCategoriaJuego porCategoriaJuego = new PorCategoriaJuego();
                porCategoriaJuego.setNombreCategoria(resultSet.getString(1));
                porCategoriaJuego.setNum_usuarios(resultSet.getInt(2));

                listaCategoriaJuego.add(porCategoriaJuego);}
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCategoriaJuego;
    }
}


