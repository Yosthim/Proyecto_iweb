package com.example.proyecto_base_japyld.Manager.Models.Daos;

import com.example.proyecto_base_japyld.Manager.Models.Beans.Juegos;

import java.sql.*;
import java.util.ArrayList;

public class JuegosDao {

    public ArrayList<Juegos> listarJuegosMasVendidos(){
        ArrayList <Juegos> listaMasVendidos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT  j.idJuegos, j.nombreJuegos, j.precio, j.stock, j.descripcion, c.nombre, j.id_imagen, i.direccion_archivo\n" +
                "FROM personas p, juegoscompradosreservados jcr, juegos j, categorias c, imagenes i\n" +
                "where  p.idPersona = jcr.id_usuario and jcr.estadoCompraJuego = 'Comprado' and jcr.id_juego = j.idJuegos and j.id_categoria = c.idCategorias and i.idImagenes = j.id_imagen\n" +
                "GROUP BY id_juego\n" +
                "ORDER BY count(j.nombreJuegos) DESC\n" +
                "LIMIT 5;";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                Juegos juegosMasVendidos = new Juegos();
                juegosMasVendidos.setIdJuegos(resultSet.getInt(1));
                juegosMasVendidos.setNombreJuegos(resultSet.getString(2));
                juegosMasVendidos.setPrecio(resultSet.getInt(3));
                juegosMasVendidos.setId_imagen(resultSet.getInt(7));
                juegosMasVendidos.setDireccion_imagen(resultSet.getString(8));
                listaMasVendidos.add(juegosMasVendidos);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaMasVendidos;
    }

    public ArrayList<Juegos> listarJuegosMenosVendidos(){
        ArrayList <Juegos> listaMenosVendidos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT  j.idJuegos, j.nombreJuegos, j.precio, j.stock, j.descripcion, c.nombre, j.id_imagen, i.direccion_archivo\n" +
                "FROM personas p, juegoscompradosreservados jcr, juegos j, categorias c, imagenes i\n" +
                "where  p.idPersona = jcr.id_usuario and jcr.estadoCompraJuego = 'Comprado' and jcr.id_juego = j.idJuegos and j.id_categoria = c.idCategorias and i.idImagenes = j.id_imagen\n" +
                "GROUP BY id_juego\n" +
                "ORDER BY count(j.nombreJuegos) ASC\n" +
                "LIMIT 5;";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                Juegos juegosMenosVendidos = new Juegos();
                juegosMenosVendidos.setIdJuegos(resultSet.getInt(1));
                juegosMenosVendidos.setNombreJuegos(resultSet.getString(2));
                juegosMenosVendidos.setPrecio(resultSet.getInt(3));
                juegosMenosVendidos.setId_imagen(resultSet.getInt(7));
                juegosMenosVendidos.setDireccion_imagen(resultSet.getString(8));
                listaMenosVendidos.add(juegosMenosVendidos);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaMenosVendidos;
    }

    public ArrayList<Juegos> listarMasDetallesJuego(String idjuego){
        ArrayList<Juegos> listaMasDetallesJuego = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "select idJuegos, nombreJuegos, descripcion,stock, precio, nombre as \"Categorías\", direccion_archivo\n" +
                "from juegos j\n" +
                "inner join categorias g on g.idCategorias = j.id_categoria\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "where j.nombreJuegos = ?;";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql))
        {
            ptsmtJuego.setString(1,idjuego);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    Juegos juegodetalles = new Juegos();
                    juegodetalles.setIdJuegos(rs.getInt(1));
                    juegodetalles.setNombreJuegos(rs.getString(2));
                    juegodetalles.setDescripcion_juego(rs.getString(3));
                    juegodetalles.setStock(rs.getInt(4));
                    juegodetalles.setPrecio(rs.getInt(5));
                    juegodetalles.setCategoria(rs.getString(6));
                    juegodetalles.setDireccion_imagen(rs.getString(7));

                    listaMasDetallesJuego.add(juegodetalles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMasDetallesJuego;
    }

    public ArrayList<Juegos> listarTodosJuegos(){
        ArrayList<Juegos> listaTodosJuegos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT idJuegos,nombreJuegos,precio,direccion_archivo\n" +
                "FROM juegos j \n" +
                "INNER JOIN imagenes i ON j.id_imagen = i.idImagenes;";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                Juegos listaJuegos = new Juegos();
                listaJuegos.setIdJuegos(resultSet.getInt(1));
                listaJuegos.setNombreJuegos(resultSet.getString(2));
                listaJuegos.setPrecio(resultSet.getInt(3));
                listaJuegos.setDireccion_imagen(resultSet.getString(4));

                listaTodosJuegos.add(listaJuegos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaTodosJuegos;
    }


    public void borrarJuego(int juegoID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/japyld";
        String sql = "DELETE FROM juegos WHERE idJuegos = ?;";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, juegoID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
