package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;

import java.sql.*;
import java.util.ArrayList;

public class ModuloAdminDao {
    public ArrayList<ModuloAdmin> listarAdmin(){

        ArrayList <ModuloAdmin> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT\n" +
                "    p.nombre,\n" +
                "    p.apellido,\n" +
                "    COUNT(jcr.estadoCompraJuego = 'Comprado') AS Comprados,\n" +
                "    COUNT(jcr.estadoCompraJuego = 'Reservado') AS Por_entregar,\n" +
                "    p.idPersona,\n" +
                "    (SELECT SUM(j.precio) FROM juegoscompradosreservados jcr\n" +
                "    LEFT JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola\n" +
                "    LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "    WHERE jcr.estadoCompraJuego = 'Comprado' AND jcr.id_administrador = p.idPersona) AS suma_precios\n" +
                "FROM personas p\n" +
                "LEFT JOIN juegoscompradosreservados jcr ON p.idPersona = jcr.id_administrador\n" +
                "LEFT JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola\n" +
                "LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "WHERE id_roles = 'ADM'\n" +
                "GROUP BY p.idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloAdmin adminModulo = new ModuloAdmin();
                adminModulo.setNombre(resultSet.getString(1));
                adminModulo.setApellido(resultSet.getString(2));
                adminModulo.setJuegoComprados(resultSet.getInt(3));
                adminModulo.setJuegoPorEntregar(resultSet.getInt(4));
                adminModulo.setId(resultSet.getInt(5));
                adminModulo.setDineroGastoTotal(resultSet.getBigDecimal(6));

                lista.add(adminModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }
}
