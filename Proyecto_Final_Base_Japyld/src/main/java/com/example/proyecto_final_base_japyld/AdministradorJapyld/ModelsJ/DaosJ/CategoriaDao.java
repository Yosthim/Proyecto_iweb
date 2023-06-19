package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Categoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDao extends BaseDao {

    public ArrayList<Categoria> listaCategoria (){

        ArrayList<Categoria> categorias= new ArrayList<>();

        String sql1 = "SELECT * FROM categorias";


        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){

                Categoria categoria = new Categoria();
                categoria.setIdCategorias(resultSet.getString(1));
                categoria.setNombre(resultSet.getString(2));

                categorias.add(categoria);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categorias;
    }

}
