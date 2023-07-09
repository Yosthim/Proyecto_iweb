package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Consola;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.JuegosXConsola;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.CompraDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ConsolasDetallesDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.MasDetallesDto;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class CompraDao {
    public ArrayList<CompraDto> listarDetallesCompra(int idjuego,String consola){
        ArrayList<CompraDto> listaDetallesCompra = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT j.nombreJuegos, i.direccion_archivo, c.nombre, jc.id_consola, jc.stock_consola, j.precio, COALESCE(d.precio_nuevo, 0) AS precio_nuevo\n" +
                "FROM juegos j\n" +
                "LEFT join categorias c on j.id_categoria = c.idCategorias\n" +
                "LEFT join imagenes i on j.id_imagen = i.idImagenes\n" +
                "LEFT join juegos_por_consolas jc on j.idJuegos = jc.id_juego\n" +
                "LEFT join descuentos d on j.idJuegos = d.id_juego\n" +
                "WHERE j.idJuegos = ?  and jc.id_consola = ?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtCompra = connection.prepareStatement(sql))
        {
            ptsmtCompra.setInt(1,idjuego);
            ptsmtCompra.setString(2,consola);
            try (ResultSet rs = ptsmtCompra.executeQuery()){
                while(rs.next()){
                    CompraDto juegoCompra = new CompraDto();

                    juegoCompra.setNombreJuego(rs.getString(1));
                    juegoCompra.setDireccion_imagen(rs.getString(2));
                    juegoCompra.setNombreCategoria(rs.getString(3));
                    juegoCompra.setIdConsola(rs.getString(4));
                    juegoCompra.setStock_consola(rs.getInt(5));
                    juegoCompra.setPrecio(rs.getInt(6));
                    juegoCompra.setPrecio_nuevo(rs.getInt(7));

                    listaDetallesCompra.add(juegoCompra);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaDetallesCompra;

    }
}