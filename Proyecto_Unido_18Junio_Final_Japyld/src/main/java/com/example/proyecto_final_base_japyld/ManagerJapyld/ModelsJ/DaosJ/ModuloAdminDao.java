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
                "                    p.nombre,\n" +
                "                    p.apellido,\n" +
                "                    p.correo,\n" +
                "                    COUNT(jcr.estadoCompraJuego = 'Comprado') AS Comprados,\n" +
                "                    COUNT(jcr.estadoCompraJuego = 'Reservado') AS Por_entregar,\n" +
                "                   p.idPersona,\n" +
                "                    (SELECT SUM(j.precio) FROM juegoscompradosreservados jcr\n" +
                "                    LEFT JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola\n" +
                "                    LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "                    WHERE jcr.estadoCompraJuego = 'Comprado' AND jcr.id_administrador = p.idPersona) AS suma_precios\n" +
                "                FROM personas p\n" +
                "                LEFT JOIN juegoscompradosreservados jcr ON p.idPersona = jcr.id_administrador\n" +
                "                LEFT JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola\n" +
                "                LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "                WHERE id_roles = 'ADM' and estado ='Activo'\n" +
                "                GROUP BY p.idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloAdmin adminModulo = new ModuloAdmin();
                adminModulo.setNombre(resultSet.getString(1));
                adminModulo.setApellido(resultSet.getString(2));
                adminModulo.setCorreo(resultSet.getString(3));
                adminModulo.setJuegoComprados(resultSet.getInt(4));
                adminModulo.setJuegoPorEntregar(resultSet.getInt(5));
                adminModulo.setId(resultSet.getInt(6));
                adminModulo.setDineroGastoTotal(resultSet.getBigDecimal(7));

                lista.add(adminModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void crearAdmin(ModuloAdmin admin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql2 = "INSERT INTO imagenes (idImagenes, tipo, direccion_archivo) VALUES (?, ?,?)";
        String sql3 = "INSERT INTO personas (idPersona,correo, contrasenia, nombre, apellido, fechaDeNacimiento,fechaRegistro, estado, categoriaJuegoPreferida ,dni, genero, id_roles, id_perfil) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement smt2 = connection.prepareStatement(sql2);
             PreparedStatement smt3 = connection.prepareStatement(sql3)) {

            // Insertar datos en la tabla de imágenes

            smt2.setInt(1,admin.getIdImagen());
            smt2.setString(2, admin.getTipo());
            smt2.setString(3, admin.getImagen());
            smt2.executeUpdate();

            // Insertar datos en la tabla de personas
            smt3.setInt(1,admin.getId());
            smt3.setString(2, admin.getCorreo());
            smt3.setString(3, admin.getContrasenia());
            smt3.setString(4, admin.getNombre());
            smt3.setString(5, admin.getApellido());
            smt3.setDate(6, admin.getFechaDeNacimiento());
            smt3.setDate(7,admin.getFechaRegistro());
            smt3.setString(8, admin.getEstado());
            smt3.setString(9,admin.getCategoriaJuegoPreferida());
            smt3.setInt(10, admin.getDni());
            smt3.setString(11, admin.getGenero());
            smt3.setString(12, admin.getId_rol());
            smt3.setInt(13, admin.getIdImagen());
            smt3.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int contarImagenes(){
        int total=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT COUNT(*) AS total_imagenes FROM imagenes;";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ModuloAdmin p1 = new ModuloAdmin();
                p1.setIdImagen(rs.getInt(1));
                total = p1.getIdImagen();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return total+1;

    }

    public int contarPersonas(){
        int total=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT COUNT(*) FROM personas;";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ModuloAdmin p1 = new ModuloAdmin();
                p1.setId(rs.getInt(1));
                total = p1.getId();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return total+1;

    }


    public ArrayList<ModuloAdmin> listarAdminInactivos(){

        ArrayList <ModuloAdmin> listaAdminInactivos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "SELECT\n" +
                "                    p.nombre,\n" +
                "                    p.apellido,\n" +
                "                    p.correo,\n" +
                "                    COUNT(jcr.estadoCompraJuego = 'Comprado') AS Comprados,\n" +
                "                    COUNT(jcr.estadoCompraJuego = 'Reservado') AS Por_entregar,\n" +
                "                   p.idPersona,\n" +
                "                    (SELECT SUM(j.precio) FROM juegoscompradosreservados jcr\n" +
                "                    LEFT JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola\n" +
                "                    LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "                    WHERE jcr.estadoCompraJuego = 'Comprado' AND jcr.id_administrador = p.idPersona) AS suma_precios\n" +
                "                FROM personas p\n" +
                "                LEFT JOIN juegoscompradosreservados jcr ON p.idPersona = jcr.id_administrador\n" +
                "                LEFT JOIN juegos_por_consolas jpc ON jcr.id_consola = jpc.id_consola\n" +
                "                LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "                WHERE id_roles = 'ADM' and estado ='Despedido'\n" +
                "                GROUP BY p.idPersona;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloAdmin adminModulo = new ModuloAdmin();
                adminModulo.setNombre(resultSet.getString(1));
                adminModulo.setApellido(resultSet.getString(2));
                adminModulo.setCorreo(resultSet.getString(3));
                adminModulo.setJuegoComprados(resultSet.getInt(4));
                adminModulo.setJuegoPorEntregar(resultSet.getInt(5));
                adminModulo.setId(resultSet.getInt(6));
                adminModulo.setDineroGastoTotal(resultSet.getBigDecimal(7));

                listaAdminInactivos.add(adminModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return listaAdminInactivos;
    }
}
