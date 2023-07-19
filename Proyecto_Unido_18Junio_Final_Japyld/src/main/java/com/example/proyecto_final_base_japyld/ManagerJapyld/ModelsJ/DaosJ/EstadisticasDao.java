package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Comentarios;
import com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager;

import java.sql.*;
import java.util.ArrayList;

public class EstadisticasDao extends BaseDao {

    public int VentaPorMes(String mes){

        String sql = "SELECT \n" +
                "    id_juego, \n" +
                "    direccion_archivo, \n" +
                "    nombreJuegos, \n" +
                "    precio,\n" +
                "    MONTHNAME(fechaCompraJuego) AS mes_compra,\n" +
                "    COUNT(jc.id_juego) AS ventas,\n" +
                "    precio * COUNT(*) AS precioPeli,\n" +
                "    total.total_venta\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON j.idJuegos = jc.id_juego\n" +
                "LEFT JOIN imagenes i ON i.idImagenes = j.id_imagen\n" +
                "CROSS JOIN (\n" +
                "    SELECT SUM(precio * ventaCount) AS total_venta \n" +
                "    FROM (\n" +
                "        SELECT precio, COUNT(*) AS ventaCount\n" +
                "        FROM juegoscompradosreservados jcr\n" +
                "        INNER JOIN juegos j ON jcr.id_juego = j.idJuegos\n" +
                "        INNER JOIN personas p ON jcr.id_usuario = p.idPersona\n" +
                "        WHERE p.id_roles = \"USR\" AND MONTHNAME(jcr.fechaCompraJuego) = ?\n" +
                "        GROUP BY id_juego\n" +
                "    ) AS ventasTotales\n" +
                ") AS total\n" +
                "WHERE MONTHNAME(fechaCompraJuego) = ?\n" +
                "GROUP BY id_juego, direccion_archivo, nombreJuegos, precio, mes_compra, total.total_venta\n" +
                "LIMIT 1;";

        int ventaMes = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {
            ptsmtJuego.setString(1, mes);
            ptsmtJuego.setString(2, mes);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    ventaMes = rs.getInt(8);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ventaMes;
    }

    public int CompraPorMes(String mes){

        String sql = "SELECT SUM(precio_final) AS suma_precio_final\n" +
                "FROM (\n" +
                "  SELECT IF(precio_admin IS NULL, precio_usuario, precio_admin) AS precio_final\n" +
                "  FROM ventajuegosgeneral\n" +
                "  WHERE MONTHNAME(fechaPublicacion) = ? AND estadoVenta = \"Aceptado\"\n" +
                ") AS subconsulta;";

        int compraMes = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {
            ptsmtJuego.setString(1, mes);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    compraMes = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compraMes;
    }


    public int UsuariosPorMes(String mes){

        String sql = "SELECT COUNT(idPersona) AS usuarios\n" +
                "FROM personas\n" +
                "WHERE id_roles = \"USR\" and MONTHNAME(fechaRegistro) = ?" +
                "GROUP BY id_roles;";

        int usuariosMes = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {
            ptsmtJuego.setString(1, mes);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    usuariosMes = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuariosMes;
    }


}
