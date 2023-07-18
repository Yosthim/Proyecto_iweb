package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.PorCategoriaJuego;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PorCategoriaJuegoDao extends BaseDao {
    public ArrayList<PorCategoriaJuego> listarJuegosPorCategoria() {

        ArrayList<PorCategoriaJuego> listaCategoriaJuego = new ArrayList<>();

        String sql = "SELECT c.nombre, COUNT(j.id_categoria) AS cantidad_juegos\n" +
                "FROM juegoscompradosreservados jcr\n" +
                "INNER JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola AND jcr.id_juego = jpc.id_juego\n" +
                "INNER JOIN juegos j ON jcr.id_juego = j.idJuegos\n" +
                "INNER JOIN personas p ON jcr.id_usuario = p.idPersona\n" +
                "INNER JOIN categorias c ON j.id_categoria = c.idCategorias\n" +
                "WHERE jcr.estadoCompraJuego = 'Comprado' AND p.id_roles = \"USR\"\n" +
                "GROUP BY c.nombre\n" +
                "ORDER BY COUNT(j.id_categoria) DESC;";

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
