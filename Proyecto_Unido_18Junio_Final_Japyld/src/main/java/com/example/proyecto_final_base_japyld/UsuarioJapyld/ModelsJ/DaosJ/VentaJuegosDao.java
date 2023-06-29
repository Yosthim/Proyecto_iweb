package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Categoria;
import com.example.proyecto_final_base_japyld.BeansGenerales.Consola;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral;

import java.sql.*;
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

    public void registrarOferta(VentaJuegosGeneral ofertaJuego, String ofertType) {
        if (ofertType.equals("nuevo")) {
            String sql = "INSERT INTO ventajuegosgeneral (estadoVenta, fechaPublicacion, precio_usuario, disponibilidad, " +
                        "id_usuario, id_administrador, id_consola, descripcion_nueva, nombre_nuevo, imagen, cantidad, idCategoria) " +
                        "VALUES (?, CURDATE(),?,?,?,?,?,?,?,?,?,?)";

            try(Connection connection = this.getConnection();
                PreparedStatement psmt = connection.prepareStatement(sql)){

                psmt.setString(1, "Pendiente");
                psmt.setBigDecimal(2, ofertaJuego.getPrecioUsuario());
                psmt.setString(3, "Habilitado");
                psmt.setInt(4, ofertaJuego.getUsuario().getIdPersona());
                psmt.setInt(5, ofertaJuego.getAdministrador().getIdPersona());
                psmt.setString(6, ofertaJuego.getConsola().getIdConsola());
                psmt.setString(7, ofertaJuego.getDescripcionNueva());
                psmt.setString(8, ofertaJuego.getNombreNuevo());
                psmt.setBinaryStream(9, ofertaJuego.getImagenNueva());
                psmt.setInt(10, ofertaJuego.getCantidad());
                psmt.setString(11, ofertaJuego.getCategoria().getIdCategorias());

                psmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
