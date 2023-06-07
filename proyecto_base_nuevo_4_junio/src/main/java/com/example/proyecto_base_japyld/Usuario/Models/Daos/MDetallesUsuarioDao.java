package com.example.proyecto_base_japyld.Usuario.Models.Daos;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.MDetallesUsuario;

import java.sql.*;
import java.util.ArrayList;

public class MDetallesUsuarioDao {

    public MDetallesUsuario detallesUsuario(String n){

        MDetallesUsuario perfilUsuario= null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT nombre, apellido, correo, fechaDeNacimiento, dni, genero, categoriaJuegoPreferida, fechaRegistro, estado FROM personas\n" +
                "WHERE id_roles = 1 and nombre = ?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setString(1, n);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    perfilUsuario = new MDetallesUsuario();

                    perfilUsuario.setNombre(resultSet.getString(1)); // Establece el valor del nombre ingresado en el método
                    perfilUsuario.setApellido(resultSet.getString(2));
                    perfilUsuario.setCorreo(resultSet.getString(3));
                    perfilUsuario.setFechaDeNacimiento(resultSet.getDate(4));
                    perfilUsuario.setDni(resultSet.getInt(5));
                    perfilUsuario.setGenero(resultSet.getString(6));
                    perfilUsuario.setCategoriaJuegoPreferida(resultSet.getString(7));
                    perfilUsuario.setFechaRegistro(resultSet.getDate(8));
                    perfilUsuario.setEstado(resultSet.getString(9));

                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return perfilUsuario;
    }


    public ArrayList<MDetallesUsuario> listarJuegosVendidos(){

        ArrayList <MDetallesUsuario> listaJuegosVendidos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT  j.nombreJuegos, vjg.precio_admin\n" +
                "FROM personas p, ventajuegosgeneral vjg, juegos j\n" +
                "where p.idPersona = 1  and p.idPersona = vjg.id_usuario and vjg.estadoVenta = 'Aceptado' and vjg.id_juego = j.idJuegos;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                MDetallesUsuario perfilUsuarioVentas = new MDetallesUsuario();

                perfilUsuarioVentas.setNombreJuegos(resultSet.getString(1));
                perfilUsuarioVentas.setPrecio_admin(resultSet.getBigDecimal(2));

                listaJuegosVendidos.add(perfilUsuarioVentas);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return listaJuegosVendidos;
    }


    public ArrayList<MDetallesUsuario> listarJuegosComprados(){

        ArrayList <MDetallesUsuario> listaJuegosComprados = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT  j.nombreJuegos, j.precio\n" +
                "FROM personas p, juegoscompradosreservados jcr, juegos j\n" +
                "where p.idPersona = 1  and p.idPersona = jcr.id_usuario and jcr.estadoCompraJuego = 'Aceptado' and jcr.id_juego = j.idJuegos;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                MDetallesUsuario perfilUsuarioCompras = new MDetallesUsuario();

                perfilUsuarioCompras.setNombreJuegos(resultSet.getString(1));
                perfilUsuarioCompras.setPrecio(resultSet.getBigDecimal(2));

                listaJuegosComprados.add(perfilUsuarioCompras);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return listaJuegosComprados;
    }
}
