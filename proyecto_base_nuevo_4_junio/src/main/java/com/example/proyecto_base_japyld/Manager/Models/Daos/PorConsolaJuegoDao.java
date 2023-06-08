package com.example.proyecto_base_japyld.Manager.Models.Daos;

import com.example.proyecto_base_japyld.Manager.Models.Beans.PorCategoriaJuego;
import com.example.proyecto_base_japyld.Manager.Models.Beans.PorConsolaJuego;

import java.sql.*;
import java.util.ArrayList;

public class PorConsolaJuegoDao {

    public ArrayList<PorConsolaJuego> listarJuegosPorConsola() {

        ArrayList<PorConsolaJuego> listaConsolaJuego = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT jcr.id_consola, count(jpc.id_consola)\n" +
                "FROM juegoscompradosreservados jcr, juegos_por_consolas jpc\n" +
                "WHERE jcr.id_consola = jpc.id_consola and jcr.estadoCompraJuego = 'Comprado'\n" +
                "GROUP BY jcr.id_consola\n" +
                "ORDER BY count(jpc.id_consola) DESC;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while (resultSet.next()) {
                PorConsolaJuego porConsolaJuego = new PorConsolaJuego();
                porConsolaJuego.setTipoConsola(resultSet.getString(1));
                porConsolaJuego.setNum_usuarios(resultSet.getInt(2));

                listaConsolaJuego.add(porConsolaJuego);}
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsolaJuego;
    }
}


