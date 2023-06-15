package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ProductosVendidos;

import java.sql.*;
import java.util.ArrayList;

public class ProductosVendidosDao {
    public ArrayList<ProductosVendidos> listarJuegosMasVendidos(){
        ArrayList<ProductosVendidos> listaMasVendidos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT id_juego, COUNT(*) AS total_ventas, direccion_archivo, nombreJuegos, precio\n" +
                "FROM juegoscompradosreservados jc\n" +
                "inner join juegos j on j.idJuegos = jc.id_juego\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "WHERE jc.estadoCompraJuego= 'comprado'\n" +
                "GROUP BY jc.id_juego, i.direccion_archivo, j.nombreJuegos, j.precio\n" +
                "ORDER BY total_ventas desc\n" +
                "LIMIT 4;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                ProductosVendidos loMasVendido = new ProductosVendidos();
                loMasVendido.setId_juego(resultSet.getInt(1));
                loMasVendido.setVentas(resultSet.getInt(2));
                loMasVendido.setDireccion_archivo(resultSet.getString(3));
                loMasVendido.setNombreJuego(resultSet.getString(4));
                loMasVendido.setPrecio(resultSet.getInt(5));

                listaMasVendidos.add(loMasVendido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMasVendidos;

    }

    public ArrayList<ProductosVendidos> listarJuegosMenosVendidos(){
        ArrayList<ProductosVendidos> listaMenosVendidos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT id_juego, COUNT(*) AS total_ventas, direccion_archivo, nombreJuegos, precio\n" +
                "FROM juegoscompradosreservados jc\n" +
                "inner join juegos j on j.idJuegos = jc.id_juego\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "WHERE jc.estadoCompraJuego= 'comprado'\n" +
                "GROUP BY jc.id_juego, i.direccion_archivo, j.nombreJuegos, j.precio\n" +
                "ORDER BY total_ventas ASC\n" +
                "LIMIT 4;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                ProductosVendidos lomenosVendido = new ProductosVendidos();
                lomenosVendido.setId_juego(resultSet.getInt(1));
                lomenosVendido.setVentas(resultSet.getInt(2));
                lomenosVendido.setDireccion_archivo(resultSet.getString(3));
                lomenosVendido.setNombreJuego(resultSet.getString(4));
                lomenosVendido.setPrecio(resultSet.getInt(5));

                listaMenosVendidos.add(lomenosVendido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMenosVendidos;

    }
}
