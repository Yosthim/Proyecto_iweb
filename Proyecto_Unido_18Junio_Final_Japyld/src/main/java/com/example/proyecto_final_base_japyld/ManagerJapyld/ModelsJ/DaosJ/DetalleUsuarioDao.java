package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleUsuario;

import java.sql.*;
import java.util.ArrayList;

public class DetalleUsuarioDao {

    public DetalleUsuario detallesUsuario(int n){

        DetalleUsuario perfilUsuario= null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT idPersona, nombre, apellido, correo, fechaDeNacimiento, dni, genero, categoriaJuegoPreferida, fechaRegistro, estado FROM personas\n" +
                "                WHERE id_roles = \"USR\" and idPersona=?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, n);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    perfilUsuario = new DetalleUsuario();

                    perfilUsuario.setId(resultSet.getInt(1));
                    perfilUsuario.setNombre(resultSet.getString(2)); // Establece el valor del nombre ingresado en el método
                    perfilUsuario.setApellido(resultSet.getString(3));
                    perfilUsuario.setCorreo(resultSet.getString(4));
                    perfilUsuario.setFechaDeNacimiento(resultSet.getDate(5));
                    perfilUsuario.setDni(resultSet.getInt(6));
                    perfilUsuario.setGenero(resultSet.getString(7));
                    perfilUsuario.setCategoriaJuegoPreferida(resultSet.getString(8));
                    perfilUsuario.setFechaRegistro(resultSet.getDate(9));
                    perfilUsuario.setEstado(resultSet.getString(10));

                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return perfilUsuario;
    }


    public ArrayList<DetalleUsuario> listarJuegosVendidos(int jv){

        ArrayList <DetalleUsuario> listaJuegosVendidos = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.idPersona,j.nombreJuegos, vjg.precio_usuario FROM ventajuegosgeneral vjg \n" +
                "left join personas p on vjg.id_usuario = p.idPersona\n" +
                "left join juegos_por_consolas jpc on vjg.id_consola = jpc.id_consola\n" +
                "left join juegos j on jpc.id_juego = j.idJuegos\n" +
                "where vjg.estadoVenta = \"Aceptado\" and p.idPersona=?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, jv);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    DetalleUsuario juegoVendidos = new DetalleUsuario();

                    juegoVendidos.setId(resultSet.getInt(1));
                    juegoVendidos.setNombreJuegos(resultSet.getString(2)); // Establece el valor del nombre ingresado en el método
                    juegoVendidos.setPrecio_admin(resultSet.getBigDecimal(3));

                    listaJuegosVendidos.add(juegoVendidos);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaJuegosVendidos;
    }


    public ArrayList<DetalleUsuario> listarJuegosComprados(int jc){

        ArrayList <DetalleUsuario> listaJuegosComprados = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.idPersona,j.nombreJuegos, j.precio FROM juegoscompradosreservados jcr \n" +
                "left join personas p on jcr.id_usuario = p.idPersona\n" +
                "left join juegos_por_consolas jpc on jcr.id_consola = jpc.id_consola\n" +
                "left join juegos j on jpc.id_juego = j.idJuegos\n" +
                "where jcr.estadoCompraJuego = \"Comprado\" and p.idPersona=?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, jc);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    DetalleUsuario juegoComprados = new DetalleUsuario();

                    juegoComprados.setId(resultSet.getInt(1));
                    juegoComprados.setNombreJuegos(resultSet.getString(2)); // Establece el valor del nombre ingresado en el método
                    juegoComprados.setPrecio(resultSet.getBigDecimal(3));

                    listaJuegosComprados.add(juegoComprados);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaJuegosComprados;
    }
}
