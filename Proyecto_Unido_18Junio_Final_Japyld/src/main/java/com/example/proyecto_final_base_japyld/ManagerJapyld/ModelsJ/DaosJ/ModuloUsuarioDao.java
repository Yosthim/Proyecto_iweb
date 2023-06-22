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

        String sql = "SELECT p.idPersona, p.nombre, p.apellido, p.correo, count(jcr.id_usuario) as \"numJuegos\",  p.fechaRegistro, p.idPersona, i.direccion_archivo FROM personas p \n" +
                "LEFT JOIN juegoscompradosreservados jcr ON p.idPersona = jcr.id_usuario\n" +
                "LEFT JOIN imagenes i ON  p.id_perfil= i.idImagenes WHERE p.id_roles = \"USR\" and p.estado = 'Activo'\n" +
                "GROUP BY idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloUsuario usuariosModulo = new ModuloUsuario();
                usuariosModulo.setId(resultSet.getInt(1));
                usuariosModulo.setNombre(resultSet.getString(2));
                usuariosModulo.setApellido(resultSet.getString(3));
                usuariosModulo.setCorreo(resultSet.getString(4));
                usuariosModulo.setNumJuegos(resultSet.getInt(5));
                usuariosModulo.setFechaRegistro(resultSet.getDate(6));
                usuariosModulo.setId(resultSet.getInt(7));
                usuariosModulo.setDireccionImagen(resultSet.getString(8));

                lista.add(usuariosModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }

    public ArrayList<ModuloUsuario> listarUsuariosBaneados(){

        ArrayList <ModuloUsuario> listaBaneados = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.idPersona, p.nombre, p.apellido, p.correo, count(jcr.id_usuario) as \"numJuegos\",  p.fechaRegistro, p.idPersona, i.direccion_archivo FROM personas p \n" +
                "LEFT JOIN juegoscompradosreservados jcr ON p.idPersona = jcr.id_usuario\n" +
                "LEFT JOIN imagenes i ON  p.id_perfil= i.idImagenes WHERE p.id_roles = \"USR\" and p.estado = 'Baneado'\n" +
                "GROUP BY idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloUsuario usuariosModulo = new ModuloUsuario();
                usuariosModulo.setId(resultSet.getInt(1));
                usuariosModulo.setNombre(resultSet.getString(2));
                usuariosModulo.setApellido(resultSet.getString(3));
                usuariosModulo.setCorreo(resultSet.getString(4));
                usuariosModulo.setNumJuegos(resultSet.getInt(5));
                usuariosModulo.setFechaRegistro(resultSet.getDate(6));
                usuariosModulo.setId(resultSet.getInt(7));
                usuariosModulo.setDireccionImagen(resultSet.getString(8));

                listaBaneados.add(usuariosModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return listaBaneados;
    }


}
