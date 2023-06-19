package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleAdmin;

import java.sql.*;
import java.util.ArrayList;

public class DetalleAdminDao {
    public DetalleAdmin detallesAdmin(int n){

        DetalleAdmin perfilAdmin= null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT idPersona, nombre, apellido, correo, fechaDeNacimiento, dni, genero, categoriaJuegoPreferida, fechaRegistro, estado FROM personas\n" +
                "                WHERE id_roles = \"ADM" +
                "\" and idPersona=?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, n);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    perfilAdmin = new DetalleAdmin();

                    perfilAdmin.setId(resultSet.getInt(1));
                    perfilAdmin.setNombre(resultSet.getString(2)); // Establece el valor del nombre ingresado en el método
                    perfilAdmin.setApellido(resultSet.getString(3));
                    perfilAdmin.setCorreo(resultSet.getString(4));
                    perfilAdmin.setFechaDeNacimiento(resultSet.getDate(5));
                    perfilAdmin.setDni(resultSet.getInt(6));
                    perfilAdmin.setGenero(resultSet.getString(7));
                    perfilAdmin.setCategoriaJuegoPreferida(resultSet.getString(8));
                    perfilAdmin.setFechaRegistro(resultSet.getDate(9));
                    perfilAdmin.setEstado(resultSet.getString(10));
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return perfilAdmin;
    }

    public ArrayList<DetalleAdmin> listarJuegosPropuestos(int jp){

        ArrayList <DetalleAdmin> listaJuegosAceptados = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.idPersona,j.nombreJuegos, j.precio FROM juegoscompradosreservados jcr\n" +
                "                left join personas p on jcr.id_administrador = p.idPersona\n" +
                "                left join juegos_por_consolas jpc on jcr.id_consola = jpc.id_consola\n" +
                "                left join juegos j on jpc.id_juego = j.idJuegos\n" +
                "\t\t\t    where jcr.estadoCompraJuego = \"Comprado\" and p.idPersona=?";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, jp);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    DetalleAdmin juegoPropuestos = new DetalleAdmin();

                    juegoPropuestos.setId(resultSet.getInt(1));
                    juegoPropuestos.setNombreJuegos(resultSet.getString(2)); // Establece el valor del nombre ingresado en el método
                    juegoPropuestos.setPrecioCompra(resultSet.getBigDecimal(3));
                    juegoPropuestos.setPrecioVenta(resultSet.getBigDecimal(3));

                    listaJuegosAceptados.add(juegoPropuestos);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaJuegosAceptados;
    }
}
