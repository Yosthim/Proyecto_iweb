package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.PorCategoriaJuego;

import java.sql.*;
import java.util.ArrayList;

public class PorCategoriaJuegoDao extends BaseDao {
    public ArrayList<PorCategoriaJuego> listarJuegosPorCategoria() {

        ArrayList<PorCategoriaJuego> listaCategoriaJuego = new ArrayList<>();

        String sql = "SELECT c.nombre, count(p.categoriaJuegoPreferida)\n" +
                "FROM categorias c, personas p\n" +
                "WHERE c.nombre = p.categoriaJuegoPreferida\n" +
                "GROUP BY p.categoriaJuegoPreferida\n" +
                "ORDER BY count(p.categoriaJuegoPreferida) DESC;";

        try (Connection connection = this.getConnection();
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
