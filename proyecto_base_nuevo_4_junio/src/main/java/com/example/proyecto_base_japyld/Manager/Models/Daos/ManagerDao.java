package com.example.proyecto_base_japyld.Manager.Models.Daos;

import com.example.proyecto_base_japyld.Admin.controllers.BaseDao;
import com.example.proyecto_base_japyld.models.beans.Objetivos;
import com.example.proyecto_base_japyld.models.beans.Personas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerDao extends BaseDao {

    public ArrayList<Objetivos> Objetivos(){

        ArrayList<Objetivos> objetivos= new ArrayList<>();

        String sql = "SELECT * FROM objetivosmanager o\n" +
                "left join personas p on o.id_manager = p.idPersona\n" +
                "WHERE o.id_manager = (SELECT idPersona FROM japyld.personas\n" +
                "\tWHERE id_roles ='MNG');";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Objetivos objetivos1 = new Objetivos();

                objetivos1.setIdObjetivos(resultSet.getInt(1));
                objetivos1.setVentasPorMesJuego(resultSet.getBigDecimal(2));
                objetivos1.setGastosPorMesJuego(resultSet.getBigDecimal(3));
                objetivos1.setUsuarioPorMes(resultSet.getInt(4));

                Personas manager = new Personas();
                manager.setIdPersona(resultSet.getInt("p.idPersona"));

                objetivos.add(objetivos1);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return objetivos;
    }

}
