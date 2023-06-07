package com.example.proyecto_base_japyld.Usuario.Models.Daos;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.PaginaprincipalJuegosBean;

import java.sql.*;
import java.util.ArrayList;

public class PaginaprincipalJuegosDao {

    public ArrayList<PaginaprincipalJuegosBean> listarJuegos(){
        ArrayList<PaginaprincipalJuegosBean> listaJuegos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select nombreJuegos, precio from\n" +
                "juegos;";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                PaginaprincipalJuegosBean juegosPrincipal = new PaginaprincipalJuegosBean();
                juegosPrincipal.setNombreJuegos(resultSet.getString(1));
                juegosPrincipal.setPrecio(resultSet.getInt(2));

                listaJuegos.add(juegosPrincipal);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }
}
