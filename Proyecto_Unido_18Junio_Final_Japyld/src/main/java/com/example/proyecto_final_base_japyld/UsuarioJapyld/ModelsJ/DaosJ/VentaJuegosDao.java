package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.InfoVentaDto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class VentaJuegosDao extends BaseDao {

    public ArrayList<VentaJuegosGeneral> listarOfertas(int idUsuario) {
        ArrayList<VentaJuegosGeneral> listaOfertas = new ArrayList<>();
        String sql = "SELECT v.idVenta, v.estadoVenta, v.razonRechazo, v.disponibilidad, v.precio_admin, v.nombre_nuevo, j.nombreJuegos, j.id_imagen, c.nombre, v.visibilidad\n" +
                "FROM ventajuegosgeneral v LEFT JOIN juegos j ON j.idJuegos = v.id_juego LEFT JOIN categorias c \n" +
                "ON c.idCategorias = j.id_categoria OR c.idCategorias = v.idCategoria\n" +
                "WHERE v.id_usuario = " + idUsuario;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                VentaJuegosGeneral oferta = new VentaJuegosGeneral();
                oferta.setIdVenta(resultSet.getInt(1));
                oferta.setEstadoVenta(resultSet.getString(2));
                //Verificando estado de Venta para no setear nulos y resulte en errores
                if (oferta.getEstadoVenta().equals("No Aceptado")) {
                    oferta.setPrecioAdmi(resultSet.getBigDecimal("precio_admin"));
                } else if (oferta.getEstadoVenta().equals("Rechazado")) {
                    oferta.setRazonRechazo(resultSet.getString(3));
                }
                //Verificando dispnibilidad para saber que columna del select setear
                oferta.setDisponibilidad(resultSet.getString("disponibilidad"));
                Juegos juego = new Juegos();
                if (oferta.getDisponibilidad().equals("Nuevo")) {
                    juego.setNombreJuegos(resultSet.getString("nombre_nuevo"));
                }else {
                    juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                    juego.setIdImagen(resultSet.getInt("id_imagen"));
                }
                Categoria categoria = new Categoria();
                categoria.setNombre(resultSet.getString("nombre"));
                juego.setCategoria(categoria);
                oferta.setJuego(juego);
                //Seteando la visibilidad que servirá para la vista
                oferta.setVisibilidad(resultSet.getString("visibilidad"));

                listaOfertas.add(oferta);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaOfertas;
    }

    public ArrayList<Juegos> listarNombreJuegos() {
        ArrayList<Juegos> listaJuegos = new ArrayList<>();
        String sql = "SELECT idJuegos, nombreJuegos FROM juegos";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                Juegos juego = new Juegos();
                juego.setIdJuegos(resultSet.getInt(1));
                juego.setNombreJuegos(resultSet.getString(2));

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

    public boolean registrarOferta(VentaJuegosGeneral ofertaJuego, String ofertType) {
        if (ofertType.equals("nuevo")) {
            String sql = "INSERT INTO ventajuegosgeneral (estadoVenta, fechaPublicacion, precio_usuario, disponibilidad, " +
                        "id_usuario, id_administrador, id_consola, descripcion_nueva, nombre_nuevo, imagen, cantidad, idCategoria, " +
                        "visibilidad) VALUES (?, CURDATE(),?,?,?,?,?,?,?,?,?,?,?)";

            try(Connection connection = this.getConnection();
                PreparedStatement psmt = connection.prepareStatement(sql)){

                psmt.setString(1, "Pendiente");
                psmt.setBigDecimal(2, ofertaJuego.getPrecioUsuario());
                psmt.setString(3, "Nuevo");
                psmt.setInt(4, ofertaJuego.getUsuario().getIdPersona());
                psmt.setInt(5, ofertaJuego.getAdministrador().getIdPersona());
                psmt.setString(6, ofertaJuego.getConsola().getIdConsola());
                psmt.setString(7, ofertaJuego.getDescripcionNueva());
                psmt.setString(8, ofertaJuego.getNombreNuevo());
                psmt.setBinaryStream(9, ofertaJuego.getImagenNueva());
                psmt.setInt(10, ofertaJuego.getCantidad());
                psmt.setString(11, ofertaJuego.getCategoria().getIdCategorias());
                psmt.setString(12, "Habilitado");

                psmt.executeUpdate();

                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (ofertType.equals("existente")) {
            String sql = "INSERT INTO ventajuegosgeneral (estadoVenta, fechaPublicacion, precio_usuario, disponibilidad, " +
                    "id_usuario, id_administrador, id_consola, id_juego, cantidad, visibilidad) " +
                    "VALUES (?, CURDATE(),?,?,?,?,?,?,?,?)";

            try(Connection connection = this.getConnection();
                PreparedStatement psmt = connection.prepareStatement(sql)){

                psmt.setString(1, "Pendiente");
                psmt.setBigDecimal(2, ofertaJuego.getPrecioUsuario());
                psmt.setString(3, "Existente");
                psmt.setInt(4, ofertaJuego.getUsuario().getIdPersona());
                psmt.setInt(5, ofertaJuego.getAdministrador().getIdPersona());
                psmt.setString(6, ofertaJuego.getConsola().getIdConsola());
                psmt.setInt(7, ofertaJuego.getJuego().getIdJuegos());
                psmt.setInt(8, ofertaJuego.getCantidad());
                psmt.setString(9, "Habilitado");

                psmt.executeUpdate();

                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public int getRandomIdAdmin() {
        ArrayList<Integer> idAdmins = new ArrayList<>();
        String sql = "SELECT idPersona FROM personas WHERE id_roles = 'ADM' AND estado = 'Activo'";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                idAdmins.add(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        double adminAleatorio = Math.random()*idAdmins.size();
        int index = (int) adminAleatorio;

        return idAdmins.get(index);
    }

    public boolean changeOfferPrice(int idVenta, BigDecimal precioNuevo) {

        String sql = "UPDATE ventajuegosgeneral SET precio_usuario = ?, estadoVenta = 'Pendiente' WHERE idVenta = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            psmt.setBigDecimal(1, precioNuevo);
            psmt.setInt(2, idVenta);

            psmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean retireOffer(int idVenta) {

        String sql = "UPDATE ventajuegosgeneral SET estadoVenta = 'Retirado' WHERE idVenta = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            psmt.setInt(1, idVenta);

            psmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOffer(int idVenta) {

        String sql = "UPDATE ventajuegosgeneral SET visibilidad = 'Deshabilitado' WHERE idventa = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)){

            psmt.setInt(1, idVenta);

            psmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public InfoVentaDto getOfferInfo(int idVenta) {
        InfoVentaDto information = new InfoVentaDto();
        String sql = "SELECT idVenta, concat(nombre,' ',apellido), id_administrador, nombre_nuevo, nombreJuegos FROM ventajuegosgeneral v\n" +
                "LEFT JOIN personas p ON v.id_usuario = p.idPersona\n" +
                "LEFT JOIN juegos j ON v.id_juego = j.idJuegos\n" +
                "WHERE v.idVenta = " + idVenta;
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                information.setOfferId(rs.getInt(1));
                information.setUserName(rs.getString(2));
                information.setIdAdmin(rs.getInt(3));
                if (rs.getString(4) != null) {
                    information.setGameName(rs.getString(4));
                }else {
                    information.setGameName(rs.getString(5));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return information;
    }

    public boolean validateIfNameExist(String nombre) {
        ArrayList<Juegos> juegosDisponibles = this.listarNombreJuegos();
        for (Juegos juego : juegosDisponibles) {
            if (juego.getNombreJuegos().equals(nombre)) {
                return false;
            }
        }
        return true;
    }
}
