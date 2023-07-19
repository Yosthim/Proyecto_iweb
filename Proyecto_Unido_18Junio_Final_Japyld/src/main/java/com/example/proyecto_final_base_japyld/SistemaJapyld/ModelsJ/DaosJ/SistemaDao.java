package com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SistemaDao extends BaseDao {
    //Dao para obtener información general de las tablas en la base de datos
    public String obtenerNombreJuego(int idJuego) {

        String nombreJuego = null;
        String sql = "SELECT idJuegos, nombreJuegos FROM juegos WHERE idJuegos = " + idJuego;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                nombreJuego = (rs.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nombreJuego;
    }

    public String obtenerNombreConsola(String idConsola) {
        String nombreConsola = null;
        String sql = "SELECT * FROM consolas WHERE idConsolas = '" + idConsola + "'";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                nombreConsola = rs.getString(2);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nombreConsola;
    }
}
