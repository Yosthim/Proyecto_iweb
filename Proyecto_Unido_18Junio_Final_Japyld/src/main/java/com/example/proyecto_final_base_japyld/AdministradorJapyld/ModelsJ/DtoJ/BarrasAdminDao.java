package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.BarrasInicioAdmin;
import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BarrasAdminDao  extends BaseDao {
    public ArrayList<BarrasInicioAdmin> Barras(){

        ArrayList<BarrasInicioAdmin> listaBarras= new ArrayList<>();

        String sql1 = "SELECT jr.id_juego, COUNT(jr.id_juego), j.nombreJuegos, j.precio*count(j.precio)\n" +
                "FROM juegoscompradosreservados jr\n" +
                "INNER JOIN juegos j ON j.idJuegos = jr.id_juego\n" +
                "WHERE jr.estadoCompraJuego = 'Comprado' AND jr.fechaCompraJuego >= DATE_SUB(NOW(), INTERVAL 1 MONTH)\n" +
                "GROUP BY jr.id_juego, j.nombreJuegos, j.idJuegos\n" +
                "limit 4;";


        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){

                BarrasInicioAdmin graficoBarras = new BarrasInicioAdmin();
                graficoBarras.setIdJuego(resultSet.getInt(1));
                graficoBarras.setCountJuegos(resultSet.getInt(2));
                graficoBarras.setNombre(resultSet.getString(3));
                graficoBarras.setPrecioJuego(resultSet.getBigDecimal(4));
                listaBarras.add(graficoBarras);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaBarras;
    }
}
