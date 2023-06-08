package com.example.proyecto_base_japyld.Admin.Models.Daos;

import com.example.proyecto_base_japyld.Admin.Models.beans.Juegos;

import java.sql.*;
import java.util.ArrayList;

public class AdminDao {
    public ArrayList<Juegos> primeraTabla(){

        ArrayList<Juegos> juegos= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "select * from juegos";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){
                Juegos juegos1 =new Juegos();

                juegos1.setIdJuegos(resultSet.getInt(1));
                juegos.add(juegos1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return juegos;
    }

    public ArrayList<Juegos> segundaTabla(){

        ArrayList<Juegos> juegos2= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 = "select * from juegos";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){
                Juegos juegos1 =new Juegos();

                juegos1.setNombreJuegos(resultSet.getString(2));
                juegos2.add(juegos1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos2;
    }

    public ArrayList<Juegos> terceraTabla(){

        ArrayList<Juegos> juegos2= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 = "select * from juegos";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){
                Juegos juegos1 =new Juegos();

                juegos1.setNombreJuegos(resultSet.getString(2));
                juegos2.add(juegos1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos2;
    }

    public ArrayList<Juegos> cuartaTabla(){

        ArrayList<Juegos> juegos2= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 = "select * from juegos";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){
                Juegos juegos1 =new Juegos();

                juegos1.setNombreJuegos(resultSet.getString(2));
                juegos2.add(juegos1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos2;
    }

    public ArrayList<Juegos> quintaTabla(){

        ArrayList<Juegos> juegos2= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "select * from juegos";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){
                Juegos juegos1 =new Juegos();

                juegos1.setNombreJuegos(resultSet.getString(2));
                juegos2.add(juegos1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos2;
    }

}
