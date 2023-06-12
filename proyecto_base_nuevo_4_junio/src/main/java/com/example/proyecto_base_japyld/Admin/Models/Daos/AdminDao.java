package com.example.proyecto_base_japyld.Admin.Models.Daos;
import com.example.proyecto_base_japyld.Admin.Models.Beans.Juegos;
import com.example.proyecto_base_japyld.Admin.controllers.BaseDao;
import com.example.proyecto_base_japyld.models.beans.*;

import java.sql.*;
import java.util.ArrayList;

public class AdminDao extends BaseDao {
    public ArrayList<JuegosCompradosReservados> primeraTabla(){

            ArrayList<JuegosCompradosReservados> ultimasCompras= new ArrayList<>();

        String sql = "SELECT * FROM juegoscompradosreservados c\n" +
                "left join personas p on c.id_usuario = p.idPersona\n" +
                "left join juegos j on c.id_juego = j.idJuegos\n" +
                "ORDER BY c.fechaCompraJuego DESC\n" +
                "LIMIT 5;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                JuegosCompradosReservados compras =new JuegosCompradosReservados();
                compras.setIdJuegosCompradosReservados(resultSet.getInt(1));
                compras.setEstadoCompraJuego(resultSet.getString(3));

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                compras.setUsuario(usuario);

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));

                compras.setJuego(juego);

                ultimasCompras.add(compras);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return ultimasCompras;
    }

    public ArrayList<VentaJuegosGeneral> segundaTabla(){

        ArrayList<VentaJuegosGeneral> ventas= new ArrayList<>();

        String sql1 = "SELECT * FROM ventajuegosgeneral v\n" +
                "left join personas p on v.id_usuario = p.idPersona\n" +
                "left join juegos j on v.id_juego = j.idJuegos\n" +
                "ORDER BY v.fechaPublicacion DESC\n" +
                "LIMIT 5;";


        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){

                VentaJuegosGeneral venta = new VentaJuegosGeneral();
                venta.setIdVenta(resultSet.getInt(1));
                venta.setEstadoVenta(resultSet.getString(2));
                venta.setPrecioUsuario(resultSet.getBigDecimal(5));

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                venta.setUsuario(usuario);

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                venta.setJuego(juego);

                ventas.add(venta);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ventas;
    }

    // pagina videojuegos

    public ArrayList<Juegos> terceraTabla(){

        ArrayList<Juegos> lista= new ArrayList<>();


        String sql1 = "select * from juegos";


        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){

                Juegos juegos = new Juegos();

                juegos.setIdJuegos(resultSet.getInt(1));
                juegos.setNombreJuegos(resultSet.getString(2));

                lista.add(juegos);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    public ArrayList<Juegos> cuartaTabla(){

        ArrayList<Juegos> juegos2= new ArrayList<>();


        String sql1 = "select * from juegos";

        try(Connection connection = this.getConnection();
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

        ArrayList<Juegos> juegos= new ArrayList<>();

        String sql = "SELECT * FROM juegos j\n" +
                "left join categorias c on j.id_categoria = c.idCategorias;";
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Juegos juego =new Juegos();
                juego.setIdJuegos(resultSet.getInt(1));
                juego.setNombreJuegos((resultSet.getString(2)));
                juego.setPrecio(resultSet.getDouble(4));
                juego.setStock(resultSet.getInt(3));

                Categoria categoria = new Categoria();
                categoria.setIdCategorias(resultSet.getString("c.idCategorias"));
                categoria.setNombre((resultSet.getString("nombre")));

                juego.setCategoria(categoria);

                juegos.add(juego);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos;
    }

    // PAGINA DE OFERTAS GENERALES

    public ArrayList<VentaJuegosGeneral> sextaTabla(){

        ArrayList<VentaJuegosGeneral> ventas = new ArrayList<>();

        String sql = "SELECT * FROM ventajuegosgeneral c\n" +
                "left join personas p on c.id_usuario = p.idPersona\n" +
                "left join juegos j on c.id_juego = j.idJuegos\n" +
                "WHERE c.estadoVenta = 'Aceptado'\n" +
                "ORDER BY c.fechaPublicacion DESC\n" +
                "LIMIT 5";
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                VentaJuegosGeneral venta = new VentaJuegosGeneral();
                venta.setIdVenta(resultSet.getInt(1));

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                juego.setStock(resultSet.getInt("stock"));
                venta.setJuego(juego);

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                venta.setUsuario(usuario);

                ventas.add(venta);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ventas;
    }

    public ArrayList<VentaJuegosGeneral> setimaTabla(){

        ArrayList<VentaJuegosGeneral> nuevosJuegos = new ArrayList<>();

        // CAMBIA QUERY A DISPONIBILIDAD IGUAL A NUEVO
        String sql = "SELECT * FROM ventajuegosgeneral c\n" +
                "left join personas p on c.id_usuario = p.idPersona\n" +
                "left join juegos j on c.id_juego = j.idJuegos\n" +
                "WHERE c.estadoVenta = 'Aceptado' and disponibilidad = 'Presente' \n" +
                "ORDER BY c.fechaPublicacion DESC\n" +
                "LIMIT 5;";
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                VentaJuegosGeneral venta = new VentaJuegosGeneral();
                venta.setIdVenta(resultSet.getInt(1));

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                venta.setJuego(juego);

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                venta.setUsuario(usuario);

                nuevosJuegos.add(venta);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevosJuegos;
    }

    public ArrayList<VentaJuegosGeneral> octavaTabla(){

        ArrayList<VentaJuegosGeneral> nuevosOfertas = new ArrayList<>();

        // CAMBIA QUERY en ESTADO DE VENTA NO LEIDO
        String sql = "SELECT * FROM ventajuegosgeneral c\n" +
                "left join personas p on c.id_usuario = p.idPersona\n" +
                "left join juegos j on c.id_juego = j.idJuegos\n" +
                "WHERE c.estadoVenta = 'Aceptado' \n" +
                "ORDER BY c.fechaPublicacion DESC;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                VentaJuegosGeneral venta = new VentaJuegosGeneral();
                venta.setIdVenta(resultSet.getInt(1));
                venta.setPrecioUsuario(resultSet.getBigDecimal(5));

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                venta.setJuego(juego);

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                venta.setUsuario(usuario);

                nuevosOfertas.add(venta);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevosOfertas;
    }

}
