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
                "                p.idPersona,\n" +
                "                    p.nombre,\n" +
                "                    p.apellido,\n" +
                "                    p.correo,\n" +
                "                    jcr_contador.contador AS contador_juegos,\n" +
                "                    vg_contador.contador AS contador_ventas,\n" +
                "                    vg_suma_precios.suma_precios_ventas,\n" +
                "                    jcr_suma_precios.suma_precios_compras\n" +
                "                FROM\n" +
                "                    personas p\n" +
                "                LEFT JOIN (\n" +
                "                    SELECT\n" +
                "                        id_administrador,\n" +
                "                        COUNT(*) AS contador\n" +
                "                    FROM\n" +
                "                        juegoscompradosreservados jcr\n" +
                "                    WHERE\n" +
                "                        jcr.estadoCompraJuego IN ('Reservado')\n" +
                "                    GROUP BY\n" +
                "                        id_administrador\n" +
                "                ) jcr_contador ON p.idPersona = jcr_contador.id_administrador\n" +
                "                LEFT JOIN (\n" +
                "                    SELECT\n" +
                "                        id_administrador,\n" +
                "                        COUNT(*) AS contador\n" +
                "                    FROM\n" +
                "                        ventajuegosgeneral vg\n" +
                "                    WHERE\n" +
                "                        vg.estadoVenta = 'Aceptado'\n" +
                "                    GROUP BY\n" +
                "                        id_administrador\n" +
                "                ) vg_contador ON p.idPersona = vg_contador.id_administrador\n" +
                "                LEFT JOIN (\n" +
                "                    SELECT\n" +
                "                        id_administrador,\n" +
                "                        SUM(vg.precio_usuario) AS suma_precios_ventas\n" +
                "                    FROM\n" +
                "                        ventajuegosgeneral vg\n" +
                "                    LEFT JOIN juegos_por_consolas jpc ON vg.id_juego = jpc.id_juego AND vg.id_consola = jpc.id_consola\n" +
                "                    LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "                    WHERE\n" +
                "                        vg.estadoVenta = 'Aceptado'\n" +
                "                    GROUP BY\n" +
                "                        id_administrador\n" +
                "                ) vg_suma_precios ON p.idPersona = vg_suma_precios.id_administrador\n" +
                "                LEFT JOIN (\n" +
                "                    SELECT\n" +
                "                        id_administrador,\n" +
                "                        SUM(jcr.precio_compra) AS suma_precios_compras\n" +
                "                    FROM\n" +
                "                        juegoscompradosreservados jcr\n" +
                "                    LEFT JOIN juegos_por_consolas jpc ON jcr.id_juego = jpc.id_juego AND jcr.id_consola = jpc.id_consola\n" +
                "                    LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "                    WHERE\n" +
                "                        jcr.estadoCompraJuego = 'Comprado'\n" +
                "                    GROUP BY\n" +
                "                        id_administrador\n" +
                "                ) jcr_suma_precios ON p.idPersona = jcr_suma_precios.id_administrador\n" +
                "                WHERE\n" +
                "                    p.id_roles = 'ADM' AND p.estado = 'Activo'";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloAdmin adminModulo = new ModuloAdmin();
                adminModulo.setNombre(resultSet.getString(2));
                adminModulo.setApellido(resultSet.getString(3));
                adminModulo.setCorreo(resultSet.getString(4));
                adminModulo.setJuegoComprados(resultSet.getInt(6));
                adminModulo.setJuegoPorEntregar(resultSet.getInt(5));
                adminModulo.setId(resultSet.getInt(1));
                adminModulo.setDineroGastoTotal(resultSet.getBigDecimal(7));
                adminModulo.setDineroCompraTotal(resultSet.getBigDecimal(8));

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


            smt2.setInt(1,admin.getIdImagen());
            smt2.setString(2, admin.getTipo());
            smt2.setString(3, admin.getImagen());
            smt2.executeUpdate();

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
                "\tp.idPersona,\n" +
                "    p.nombre,\n" +
                "    p.apellido,\n" +
                "    p.correo,\n" +
                "    jcr_contador.contador AS contador_juegos,\n" +
                "    vg_contador.contador AS contador_ventas,\n" +
                "    vg_suma_precios.suma_precios_ventas\n" +
                "FROM\n" +
                "    personas p\n" +
                "LEFT JOIN (\n" +
                "    SELECT\n" +
                "        id_administrador,\n" +
                "        COUNT(*) AS contador\n" +
                "    FROM\n" +
                "        juegoscompradosreservados\n" +
                "    WHERE\n" +
                "        estadoCompraJuego IN ('Comprado')\n" +
                "    GROUP BY\n" +
                "        id_administrador\n" +
                ") jcr_contador ON p.idPersona = jcr_contador.id_administrador\n" +
                "LEFT JOIN (\n" +
                "    SELECT\n" +
                "        id_administrador,\n" +
                "        COUNT(*) AS contador\n" +
                "    FROM\n" +
                "        ventajuegosgeneral vg\n" +
                "    WHERE\n" +
                "        vg.estadoVenta = 'Aceptado'\n" +
                "    GROUP BY\n" +
                "        id_administrador\n" +
                ") vg_contador ON p.idPersona = vg_contador.id_administrador\n" +
                "LEFT JOIN (\n" +
                "    SELECT\n" +
                "        id_administrador,\n" +
                "        SUM(vg.precio_usuario) AS suma_precios_ventas\n" +
                "    FROM\n" +
                "        ventajuegosgeneral vg\n" +
                "    LEFT JOIN juegos_por_consolas jpc ON vg.id_juego = jpc.id_juego AND vg.id_consola = jpc.id_consola\n" +
                "    LEFT JOIN juegos j ON jpc.id_juego = j.idJuegos\n" +
                "    WHERE\n" +
                "        vg.estadoVenta = 'Aceptado'\n" +
                "    GROUP BY\n" +
                "        id_administrador\n" +
                ") vg_suma_precios ON p.idPersona = vg_suma_precios.id_administrador\n" +
                "WHERE\n" +
                "    p.id_roles = 'ADM' AND p.estado = 'Despedido';";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                ModuloAdmin adminModulo = new ModuloAdmin();
                adminModulo.setNombre(resultSet.getString(2));
                adminModulo.setApellido(resultSet.getString(3));
                adminModulo.setCorreo(resultSet.getString(4));
                adminModulo.setJuegoComprados(resultSet.getInt(6));
                adminModulo.setJuegoPorEntregar(resultSet.getInt(5));
                adminModulo.setId(resultSet.getInt(1));
                adminModulo.setDineroGastoTotal(resultSet.getBigDecimal(7));

                listaAdminInactivos.add(adminModulo);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return listaAdminInactivos;
    }
}
