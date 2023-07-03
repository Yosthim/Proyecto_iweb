package com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImageDao extends BaseDao {
    public byte[] getGameImage(int idImg) {

        byte[] content = null;
        String sql = "SELECT imagen FROM imagenes WHERE idImagenes = " + idImg;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            if (resultSet.next()) {
                content = resultSet.getBytes("imagen");
            }else {
                content = new byte[]{(byte) 1};//Cuando la imagen es nula
            }

        } catch (SQLException e) {
            e.printStackTrace();
            content = new byte[]{(byte) 0};
        }
        return content;
    }

    public byte[] getOfertimage(int idVenta) {
        byte[] content = null;
        String sql = "SELECT imagen FROM ventajuegosgeneral WHERE idVenta = " + idVenta;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            if (resultSet.next()) {
                content = resultSet.getBytes("imagen");
            }else {
                content = new byte[]{(byte) 1};
            }

        } catch (SQLException e) {
            e.printStackTrace();
            content = new byte[]{(byte) 0};
        }
        return content;
    }
}
