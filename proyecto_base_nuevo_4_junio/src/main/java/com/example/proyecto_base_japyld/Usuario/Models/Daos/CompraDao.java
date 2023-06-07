package com.example.proyecto_base_japyld.Usuario.Models.Daos;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.MasDetallesBean;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class CompraDao {
    public ArrayList<MasDetallesBean> listarDetallesCompra(int idjuego){
        ArrayList<MasDetallesBean> listaDetallesCompra = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "select idJuegos, nombreJuegos, precio, nombre as \"Categorías\", direccion_archivo\n" +
                "from juegos j\n" +
                "inner join categorias g on g.idCategorias = j.id_categoria\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "where j.idJuegos = ?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtCompra = connection.prepareStatement(sql))
        {
            ptsmtCompra.setInt(1,idjuego);
            try (ResultSet rs = ptsmtCompra.executeQuery()){
                while(rs.next()){
                    MasDetallesBean juegoCompra = new MasDetallesBean();

                    juegoCompra.setIdJuegos(rs.getInt(1));
                    juegoCompra.setNombreJuegos(rs.getString(2));
                    juegoCompra.setPrecio(rs.getInt(3));
                    juegoCompra.setCategoria(rs.getString(4));
                    juegoCompra.setDireccion_imagen(rs.getString(5));

                    listaDetallesCompra.add(juegoCompra);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaDetallesCompra;

    }
}

