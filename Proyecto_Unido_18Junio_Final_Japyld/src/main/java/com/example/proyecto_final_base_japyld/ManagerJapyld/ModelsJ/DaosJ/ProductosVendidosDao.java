package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ProductosVendidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosVendidosDao extends BaseDao {
    public ArrayList<ProductosVendidos> listarJuegosMasVendidos(String mes) throws SQLException {
        ArrayList<ProductosVendidos> listaMasVendidos = new ArrayList<>();

        String sql = "SELECT id_juego, direccion_archivo, nombreJuegos, precio, \n" +
                "       MONTHNAME(fechaCompraJuego) AS mes_compra, COUNT(*) AS ventas\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON j.idJuegos = jc.id_juego\n" +
                "LEFT JOIN imagenes i ON i.idImagenes = j.id_imagen\n" +
                "WHERE jc.estadoCompraJuego = 'Comprado' AND MONTHNAME(fechaCompraJuego) = ?\n" +
                "GROUP BY id_juego, direccion_archivo, nombreJuegos, precio, mes_compra\n" +
                "ORDER BY ventas DESC\n" +
                "LIMIT 1;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtConsola = connection.prepareStatement(sql)) {

            ptsmtConsola.setString(1, mes);

            try (ResultSet rs = ptsmtConsola.executeQuery()) {

                while (rs.next()) {
                    ProductosVendidos loMasVendido = new ProductosVendidos();
                    loMasVendido.setId_juego(rs.getInt(1));
                    loMasVendido.setDireccion_archivo(rs.getString(2));
                    loMasVendido.setNombreJuego(rs.getString(3));
                    loMasVendido.setPrecio(rs.getInt(4));
                    loMasVendido.setMes(rs.getString(5));
                    loMasVendido.setVentas(rs.getInt(6));
                    listaMasVendidos.add(loMasVendido);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return listaMasVendidos;

        }
    }

    public ArrayList<ProductosVendidos> listarJuegosMenosVendidos(String mes) throws SQLException {
        ArrayList<ProductosVendidos> listaMenosVendidos = new ArrayList<>();

        String sql = "SELECT id_juego, direccion_archivo, nombreJuegos, precio, \n" +
                "       MONTHNAME(fechaCompraJuego) AS mes_compra, COUNT(*) AS ventas\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON j.idJuegos = jc.id_juego\n" +
                "LEFT JOIN imagenes i ON i.idImagenes = j.id_imagen\n" +
                "WHERE jc.estadoCompraJuego = 'Comprado' AND MONTHNAME(fechaCompraJuego) = ?\n" +
                "GROUP BY id_juego, direccion_archivo, nombreJuegos, precio, mes_compra\n" +
                "ORDER BY ventas ASC\n" +
                "LIMIT 1;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtConsola = connection.prepareStatement(sql)) {

            ptsmtConsola.setString(1, mes);

            try (ResultSet rs = ptsmtConsola.executeQuery()) {

                while (rs.next()) {
                    ProductosVendidos lomenosVendido = new ProductosVendidos();
                    lomenosVendido.setId_juego(rs.getInt(1));
                    lomenosVendido.setDireccion_archivo(rs.getString(2));
                    lomenosVendido.setNombreJuego(rs.getString(3));
                    lomenosVendido.setPrecio(rs.getInt(4));
                    lomenosVendido.setMes(rs.getString(5));
                    lomenosVendido.setVentas(rs.getInt(6));
                    listaMenosVendidos.add(lomenosVendido);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return listaMenosVendidos;
        }
    }
}
