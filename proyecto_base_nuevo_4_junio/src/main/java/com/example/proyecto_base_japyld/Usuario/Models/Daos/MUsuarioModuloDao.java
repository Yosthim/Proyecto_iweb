package com.example.proyecto_base_japyld.Usuario.Models.Daos;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.MUsuarioModulo;

import java.sql.*;
import java.util.ArrayList;

public class MUsuarioModuloDao {
    public ArrayList<MUsuarioModulo> listarUsuarios(){

        ArrayList <MUsuarioModulo> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.nombre, p.apellido, p.correo, count(jcr.id_usuario) as \"numJuegos\",  p.fechaRegistro\n" +
                "FROM personas p LEFT JOIN juegoscompradosreservados jcr \n" +
                "ON p.idPersona = jcr.id_usuario\n" +
                "WHERE p.id_roles = 1\n" +
                "GROUP BY idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                MUsuarioModulo usuariosModulo = new MUsuarioModulo();
                usuariosModulo.setNombre(resultSet.getString(1));
                usuariosModulo.setApellido(resultSet.getString(2));
                usuariosModulo.setCorreo(resultSet.getString(3));
                usuariosModulo.setNumJuegos(resultSet.getInt(4));
                usuariosModulo.setFechaRegistro(resultSet.getDate(5));

                lista.add(usuariosModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }
}
