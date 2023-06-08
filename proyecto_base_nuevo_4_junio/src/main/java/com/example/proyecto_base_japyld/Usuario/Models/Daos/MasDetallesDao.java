package com.example.proyecto_base_japyld.Usuario.Models.Daos;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.MasDetallesBean;

import java.sql.*;
import java.util.ArrayList;

public class MasDetallesDao {

    public ArrayList<MasDetallesBean> listarMasDetallesJuego(String idjuego){
       ArrayList<MasDetallesBean> listaMasDetallesJuego = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "select idJuegos, nombreJuegos, descripcion,stock, precio, nombre as \"Categorías\", direccion_archivo\n" +
                "from juegos j\n" +
                "inner join categorias g on g.idCategorias = j.id_categoria\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "where j.nombreJuegos = ?;";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql))
        {
            ptsmtJuego.setString(1,idjuego);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    MasDetallesBean juegodetalles = new MasDetallesBean();
                    juegodetalles.setIdJuegos(rs.getInt(1));
                    juegodetalles.setNombreJuegos(rs.getString(2));
                    juegodetalles.setDescripcion(rs.getString(3));
                    juegodetalles.setStock(rs.getInt(4));
                    juegodetalles.setPrecio(rs.getInt(5));
                    juegodetalles.setCategoria(rs.getString(6));
                    juegodetalles.setDireccion_imagen(rs.getString(7));

                    listaMasDetallesJuego.add(juegodetalles);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMasDetallesJuego;
    }
}
