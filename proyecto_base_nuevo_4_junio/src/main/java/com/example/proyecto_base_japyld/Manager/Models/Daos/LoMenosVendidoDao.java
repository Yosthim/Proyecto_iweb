package com.example.proyecto_base_japyld.Manager.Models.Daos;

import com.example.proyecto_base_japyld.Manager.Models.Beans.LoMasVendidoBean;

import java.sql.*;
import java.util.ArrayList;

public class LoMenosVendidoDao {

    public ArrayList<LoMasVendidoBean> listarJuegosMenosVendidos(){
        ArrayList<LoMasVendidoBean> listaMenosVendidos = new ArrayList<>();

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
                "LIMIT 5;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                LoMasVendidoBean lomenosVendido = new LoMasVendidoBean();
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