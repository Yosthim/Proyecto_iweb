package com.example.proyecto_base_japyld.Admin.Models.Daos;



import com.example.proyecto_base_japyld.Admin.Models.Beans.JuegosEBean;

import java.sql.*;
import java.util.ArrayList;

public class JuegosEditarDao {

    public ArrayList<JuegosEBean> listarJuegoEditar(int idjuego) {
        ArrayList<JuegosEBean> listaJuegoEditar = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select idJuegos, nombreJuegos, descripcion,stock, precio, nombre as \"Categorías\", direccion_archivo\n" +
                "from juegos j\n" +
                "inner join categorias g on g.idCategorias = j.id_categoria\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "where j.idJuegos = ?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/

             PreparedStatement ptsmtJuegoEditar = connection.prepareStatement(sql)) {

            ptsmtJuegoEditar.setInt(1, idjuego);
            try (ResultSet rs = ptsmtJuegoEditar.executeQuery()) {
                while (rs.next()) {

                    JuegosEBean juegoEditar = new JuegosEBean();

                    juegoEditar.setId_juego(rs.getInt(1));
                    juegoEditar.setJuegoNombre(rs.getString(2));
                    juegoEditar.setDescripcion(rs.getString(3));
                    juegoEditar.setPrecio(rs.getInt(5));
                    juegoEditar.setCategoria(rs.getString(6));
                    juegoEditar.setDireccion_imagen(rs.getString(7));

                    listaJuegoEditar.add(juegoEditar);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegoEditar;

    }
}