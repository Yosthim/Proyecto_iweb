package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloUsuario;

import java.sql.*;
import java.util.ArrayList;

public class ModuloUsuarioDao {
    public ArrayList<ModuloUsuario> listarUsuarios(){

        ArrayList <ModuloUsuario> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.nombre, p.apellido, p.correo, count(jcr.id_usuario) as \"numJuegos\",  p.fechaRegistro, p.idPersona \n" +
                "FROM personas p LEFT JOIN juegoscompradosreservados jcr \n" +
                "                ON p.idPersona = jcr.id_usuario \n" +
                "                WHERE p.id_roles = \"USR\"\n" +
                "                GROUP BY idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloUsuario usuariosModulo = new ModuloUsuario();
                usuariosModulo.setNombre(resultSet.getString(1));
                usuariosModulo.setApellido(resultSet.getString(2));
                usuariosModulo.setCorreo(resultSet.getString(3));
                usuariosModulo.setNumJuegos(resultSet.getInt(4));
                usuariosModulo.setFechaRegistro(resultSet.getDate(5));
                usuariosModulo.setId(resultSet.getInt(6));

                lista.add(usuariosModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }


}
