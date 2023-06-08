package com.example.proyecto_base_japyld.Manager.Models.Daos;

import com.example.proyecto_base_japyld.Manager.Models.Beans.LoMasVendidoBean;

import java.sql.*;
import java.util.ArrayList;

public class LoMasVendidoDao {

    public ArrayList<LoMasVendidoBean> listarJuegosMasVendidos(){
        ArrayList<LoMasVendidoBean> listaMasVendidos = new ArrayList<>();

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
                "LIMIT 5;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                LoMasVendidoBean loMasVendido = new LoMasVendidoBean();
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

}
