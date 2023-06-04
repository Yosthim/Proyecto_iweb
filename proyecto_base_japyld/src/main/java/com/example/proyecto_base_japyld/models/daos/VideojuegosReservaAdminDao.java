package com.example.proyecto_base_japyld.models.daos;
import com.example.proyecto_base_japyld.models.beans.Juegos;
import java.sql.*;
import java.util.ArrayList;

public class VideojuegosReservaAdminDao {

    public ArrayList<Juegos> listarJuegosReservadosMas10(){
        ArrayList<Juegos> lista = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juegosReservas1 = new Juegos();
                juegosReservas1.setNombre(resultSet.getString(1));
                juegosReservas1.setUsuario(resultSet.getString(2));
                juegosReservas1.setCantDias(resultSet.getInt(3));
                lista.add(juegosReservas1);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }
    public ArrayList<Juegos> listarJuegosReservadosMas20(){
        ArrayList<Juegos> lista2 = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juegosReservas2 = new Juegos();
                juegosReservas2.setNombre(resultSet.getString(1));
                juegosReservas2.setUsuario(resultSet.getString(2));
                juegosReservas2.setCantDias(resultSet.getInt(3));

                lista2.add(juegosReservas2);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista2;
    }
    public ArrayList<Juegos> listarJuegosReservadosUltimamente(){
        ArrayList<Juegos> lista3 = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "";
        String url = "jdbc:mysql://127.0.0.1:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url, "root", "root"); Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while(resultSet.next()){
                Juegos juegosReservas3 = new Juegos();
                juegosReservas3.setNombre(resultSet.getString(1));
                juegosReservas3.setUsuario(resultSet.getString(2));
                juegosReservas3.setCantDias(resultSet.getInt(3));
                lista3.add(juegosReservas3);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista3;
    }
}
