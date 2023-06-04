package com.example.proyecto_base_japyld.models.daos;
import com.example.proyecto_base_japyld.models.beans.Juegos;
import java.sql.*;
import java.util.ArrayList;

public class VideojuegosOfertaAdminDao {

    public ArrayList<Juegos> listarJuegosAceptados(){
        ArrayList<Juegos> lista = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT nombreJuegos, stock , estadoVenta, idJuegos, nombre\n" +
                "                FROM ventajuegosgeneral v\n" +
                "                INNER JOIN juegos j ON v.id_juego = j.idJuegos\n" +
                "                LEFT JOIN personas p ON v.id_usuario = p.idPersona\n" +
                "                LEFT JOIN roles r ON p.id_roles = r.idroles\n" +
                "                WHERE estadoVenta = 'Aceptado' and idroles = '1'\n" +
                "                ORDER BY estadoVenta desc\n" +
                "                limit 7;";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juegosOfertas = new Juegos();
                juegosOfertas.setNombre(resultSet.getString(1));
                juegosOfertas.setUsuario(resultSet.getString(5));
                juegosOfertas.setStock(resultSet.getInt(2));
                lista.add(juegosOfertas);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }
    public ArrayList<Juegos> listarJuegosNuevos(){
        ArrayList<Juegos> lista2 = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "SELECT nombreJuegos, nombre\n" +
                "                FROM ventajuegosgeneral v\n" +
                "                INNER JOIN juegos j ON v.id_juego = j.idJuegos\n" +
                "                LEFT JOIN personas p ON v.id_usuario = p.idPersona\n" +
                "                LEFT JOIN roles r ON p.id_roles = r.idroles\n" +
                "                WHERE  tiempoUso = '0' and idroles = '1'\n" +
                "                ORDER BY estadoVenta desc\n" +
                "                limit 7;";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juegosOfertas2 = new Juegos();
                juegosOfertas2.setNombre(resultSet.getString(1));
                juegosOfertas2.setUsuario(String.valueOf(resultSet.getInt(2)));
                lista2.add(juegosOfertas2);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista2;
    }
    public ArrayList<Juegos> listarNuevasOfertas(){
        ArrayList<Juegos> lista3 = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "select nombreJuegos,nombre, precio_nuevo\n" +
                "from descuentos d \n" +
                "inner join juegos j on d.id_juego = j.idJuegos\n" +
                "inner join personas p on d.id_administrador = p.idPersona";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juegosOfertas3 = new Juegos();
                juegosOfertas3.setNombre(resultSet.getString(1));
                juegosOfertas3.setUsuario(resultSet.getString(2));
                juegosOfertas3.setPrecio(resultSet.getDouble(3));
                lista3.add(juegosOfertas3);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista3;
    }


}
