package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.JuegosReservadosDias;

import java.sql.*;
import java.util.ArrayList;

public class JuegosReservadosDaos {
    public ArrayList<JuegosReservadosDias> tabla30Dias(int id){

        ArrayList<JuegosReservadosDias> JR30D= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "Select j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
                "from juegoscompradosreservados r \n" +
                "left join juegos j on  r.id_juego =j.idJuegos\n" +
                "left join personas p on r.id_usuario =p.idPersona\n" +
                "where timestampdiff(day,r.fechaCompraJuego, current_date())>=30  and estadoCompraJuego like \"Reservado\" and r.id_administrador=?;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    JuegosReservadosDias J10 = new JuegosReservadosDias();
                    J10.setNombre(resultSet.getString(1));
                    J10.setUsuario(resultSet.getString(2));
                    J10.setDias(resultSet.getInt(3));
                    JR30D.add(J10);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return JR30D;
    }
    public ArrayList<JuegosReservadosDias> tabla20Dias(int id){

        ArrayList<JuegosReservadosDias> JR20D = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 = "Select j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
                "from juegoscompradosreservados r \n" +
                "left join juegos j on  r.id_juego =j.idJuegos\n" +
                "left join personas p on r.id_usuario =p.idPersona\n" +
                "where timestampdiff(day,r.fechaCompraJuego, current_date())>10 and  timestampdiff(day,r.fechaCompraJuego, current_date())<=20 and r.estadoCompraJuego like \"Reservado\" and r.id_administrador=?;";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql1)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    JuegosReservadosDias J10 = new JuegosReservadosDias();
                    J10.setNombre(resultSet.getString(1));
                    J10.setUsuario(resultSet.getString(2));
                    J10.setDias(resultSet.getInt(3));
                    JR20D.add(J10);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return JR20D;
    }

    public ArrayList<JuegosReservadosDias> tabla10Dias(int id){

        ArrayList<JuegosReservadosDias> JR10D = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 ="Select j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
                "                from juegoscompradosreservados r \n" +
                "                left join juegos j on  r.id_juego =j.idJuegos\n" +
                "                left join personas p on r.id_usuario =p.idPersona\n" +
                "                where timestampdiff(day,r.fechaCompraJuego, current_date())<=10  and estadoCompraJuego like 'Reservado' and r.id_administrador=?;";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql1)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    JuegosReservadosDias J10 = new JuegosReservadosDias();
                    J10.setNombre(resultSet.getString(1));
                    J10.setUsuario(resultSet.getString(2));
                    J10.setDias(resultSet.getInt(3));
                    JR10D.add(J10);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return JR10D;
    }
}
