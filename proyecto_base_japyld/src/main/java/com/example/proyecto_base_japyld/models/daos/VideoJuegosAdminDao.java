package com.example.proyecto_base_japyld.models.daos;
import com.example.proyecto_base_japyld.models.beans.Juegos;
import java.sql.*;
import java.util.ArrayList;

public class VideoJuegosAdminDao {
    public ArrayList<Juegos> listarJuegos(){
        ArrayList<Juegos> lista = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT juegos.nombreJuegos, juegos.precio, categorias.nombre, juegos.stock from juegos, categorias where juegos.id_categoria = categorias.idCategorias";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juego = new Juegos();
                juego.setNombre(resultSet.getString(1));
                juego.setPrecio(resultSet.getDouble(2));
                juego.setNombreCategoria(resultSet.getString(3));
                juego.setStock(resultSet.getInt(4));
                lista.add(juego);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }
    public ArrayList<Juegos> listarJuegosPopularesMes(){
        ArrayList<Juegos> lista2 = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT COUNT(nombreJuegos) as 'Juegos_comprados_por_título', rating, direccion_archivo, nombreJuegos, estadoCompraJuego, idJuegos, nombre\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON jc.id_juego = j.idJuegos\n" +
                "LEFT JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                "LEFT JOIN categorias c on j.id_categoria = c.idCategorias\n" +
                "WHERE estadoCompraJuego = 'Aceptado'\n" +
                "GROUP BY nombreJuegos, rating, direccion_archivo, estadoCompraJuego, idJuegos\n" +
                "ORDER BY count(nombreJuegos) desc\n" +
                "limit 5;";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juego2 = new Juegos();
                juego2.setNombre(resultSet.getString(4));
                juego2.setCantidadVentasJuegos(resultSet.getInt(1));
                lista2.add(juego2);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista2;
    }
    public ArrayList<Juegos> listarCategoriasPopularesMes(){
        ArrayList<Juegos> lista3 = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT COUNT(nombreJuegos) as 'Juegos_comprados_por_título', rating, direccion_archivo, nombreJuegos, estadoCompraJuego, idJuegos, nombre\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON jc.id_juego = j.idJuegos\n" +
                "LEFT JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                "LEFT JOIN categorias c on j.id_categoria = c.idCategorias\n" +
                "WHERE estadoCompraJuego = 'Aceptado'\n" +
                "GROUP BY nombreJuegos, rating, direccion_archivo, estadoCompraJuego, idJuegos\n" +
                "ORDER BY count(nombreJuegos) desc\n" +
                "limit 5;";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juego3 = new Juegos();
                juego3.setNombreCategoria(resultSet.getString(7));
                juego3.setCantidadVentasCategorias(resultSet.getInt(1));
                lista3.add(juego3);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista3;
    }
}
