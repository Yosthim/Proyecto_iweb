package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.PorConsolaJuego;

import java.sql.*;
import java.util.ArrayList;

public class PorConsolaJuegoDao extends BaseDao {
    public ArrayList<PorConsolaJuego> listarJuegosPorConsola() {

        ArrayList<PorConsolaJuego> listaConsolaJuego = new ArrayList<>();

        String sql = "SELECT jcr.id_consola, count(jpc.id_consola)\n" +
                "FROM juegoscompradosreservados jcr, juegos_por_consolas jpc\n" +
                "WHERE jcr.id_consola = jpc.id_consola and jcr.estadoCompraJuego = 'Comprado'\n" +
                "GROUP BY jcr.id_consola\n" +
                "ORDER BY count(jpc.id_consola) DESC;";

        try (Connection connection = this.getConnection();
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
