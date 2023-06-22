package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Categoria;
import com.example.proyecto_final_base_japyld.BeansGenerales.Consola;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentaJuegosDao extends BaseDao {

    public ArrayList<Juegos> listarNombreJuegos() {
        ArrayList<Juegos> listaJuegos = new ArrayList<>();
        String sql = "SELECT nombreJuegos FROM juegos";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                Juegos juego = new Juegos();
                juego.setNombreJuegos(resultSet.getString(1));

                listaJuegos.add(juego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }

    public ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategorias(resultSet.getString(1));
                categoria.setNombre(resultSet.getString(2));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorias;
    }

    public ArrayList<Consola> listarConsolas() {
        ArrayList<Consola> consolas = new ArrayList<>();
        String sql = "SELECT * FROM consolas";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Consola consola = new Consola();
                consola.setIdConsola(resultSet.getString(1));
                consola.setNombre(resultSet.getString(2));

                consolas.add(consola);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return consolas;
    }

}
