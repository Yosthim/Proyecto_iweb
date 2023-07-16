package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleAdmin;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;

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

        String sql = "SELECT idPersona, nombre, apellido, correo, fechaDeNacimiento, dni, genero, categoriaJuegoPreferida, fechaRegistro, estado, contrasenia FROM personas\n" +
                "                                WHERE id_roles = \"ADM\" and idPersona = ?;";

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
                    perfilAdmin.setContrasenia(resultSet.getString(11));
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return perfilAdmin;
    }

    public ArrayList<DetalleAdmin> listarJuegosPropuestosVendidos(int jp){

        ArrayList <DetalleAdmin> listaJuegosComprados = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.idPersona,p.nombre,j.nombreJuegos, jcr.precio_compra FROM juegoscompradosreservados jcr\n" +
                "                                left join personas p on jcr.id_administrador = p.idPersona\n" +
                "                                left join juegos_por_consolas jpc on jcr.id_consola = jpc.id_consola and jcr.id_juego=jpc.id_juego\n" +
                "                                left join juegos j on jpc.id_juego = j.idJuegos\n" +
                "                    where jcr.estadoCompraJuego = \"Comprado\" and p.idPersona=?";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, jp);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    DetalleAdmin juegoPropuestos = new DetalleAdmin();

                    juegoPropuestos.setId(resultSet.getInt(1));
                    juegoPropuestos.setNombreJuegos(resultSet.getString(3)); // Establece el valor del nombre ingresado en el método
                    juegoPropuestos.setPrecioVenta(resultSet.getBigDecimal(4));

                    listaJuegosComprados.add(juegoPropuestos);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaJuegosComprados;
    }

    public ArrayList<DetalleAdmin> listarJuegosPropuestosComprados(int jp){

        ArrayList <DetalleAdmin> listaJuegosAceptados = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT p.idPersona,p.nombre,j.nombreJuegos, vjg.precio_usuario FROM ventajuegosgeneral vjg\n" +
                "                                left join personas p on vjg.id_administrador = p.idPersona\n" +
                "                                left join juegos_por_consolas jpc on vjg.id_consola = jpc.id_consola and vjg.id_juego=jpc.id_juego\n" +
                "                                left join juegos j on jpc.id_juego = j.idJuegos\n" +
                "                    where vjg.estadoVenta = \"Aceptado\" and p.idPersona=?";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, jp);

            try (ResultSet resultSet = smt.executeQuery()) {
                while (resultSet.next()) {
                    DetalleAdmin juegoPropuestos = new DetalleAdmin();

                    juegoPropuestos.setId(resultSet.getInt(1));
                    juegoPropuestos.setNombreJuegos(resultSet.getString(3)); // Establece el valor del nombre ingresado en el método
                    juegoPropuestos.setPrecioCompra(resultSet.getBigDecimal(4));

                    listaJuegosAceptados.add(juegoPropuestos);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaJuegosAceptados;
    }

    public void editarAdmin(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "update personas set estado = \"Despedido\" where idPersona = ?";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root");
            PreparedStatement smt = connection.prepareStatement(sql))
        {

            smt.setInt(1,id);
            smt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenerAdminMenosEntregas(){

       int id=0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT\n" +
                "    p.idPersona,\n" +
                "    p.nombre,\n" +
                "    p.apellido,\n" +
                "    p.correo,\n" +
                "    jcr_contador.contador AS contador_juegos\n" +
                "FROM\n" +
                "    personas p\n" +
                "LEFT JOIN (\n" +
                "    SELECT\n" +
                "        id_administrador,\n" +
                "        COUNT(*) AS contador\n" +
                "    FROM\n" +
                "        juegoscompradosreservados\n" +
                "    WHERE\n" +
                "        estadoCompraJuego IN ('Reservado')\n" +
                "    GROUP BY\n" +
                "        id_administrador\n" +
                ") AS jcr_contador ON p.idPersona = jcr_contador.id_administrador\n" +
                "WHERE\n" +
                "    p.id_roles = 'ADM' AND p.estado = 'Activo'\n" +
                "ORDER BY\n" +
                "    contador_juegos ASC\n" +
                "LIMIT 1;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return id;
    }

    public void cambiarEntregas(int idDespedido, int idMenorEntregas){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "UPDATE juegoscompradosreservados\n" +
                "SET id_administrador = ?\n" +
                "WHERE id_administrador = ? and estadoCompraJuego='Reservado';";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root")) {
            connection.setAutoCommit(false);

            try (PreparedStatement smt = connection.prepareStatement(sql)) {
                smt.setInt(1, idMenorEntregas);
                smt.setInt(2, idDespedido);
                smt.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
