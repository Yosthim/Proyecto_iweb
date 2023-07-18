package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.CompraDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ConsolasDetallesDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.MasDetallesDto;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class CompraDao extends BaseDao{
    public ArrayList<CompraDto> listarDetallesCompra(int idjuego,String consola){
        ArrayList<CompraDto> listaDetallesCompra = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT j.nombreJuegos, i.direccion_archivo, c.nombre, jc.id_consola, jc.stock_consola, j.precio, COALESCE(d.precio_nuevo, 0) AS precio_nuevo, j.idJuegos\n" +
                "FROM juegos j\n" +
                "LEFT join categorias c on j.id_categoria = c.idCategorias\n" +
                "LEFT join imagenes i on j.id_imagen = i.idImagenes\n" +
                "LEFT join juegos_por_consolas jc on j.idJuegos = jc.id_juego\n" +
                "LEFT join descuentos d on j.idJuegos = d.id_juego\n" +
                "WHERE j.idJuegos = ?  and jc.id_consola = ?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtCompra = connection.prepareStatement(sql))
        {
            ptsmtCompra.setInt(1,idjuego);
            ptsmtCompra.setString(2,consola);
            try (ResultSet rs = ptsmtCompra.executeQuery()){
                while(rs.next()){
                    CompraDto juegoCompra = new CompraDto();

                    juegoCompra.setNombreJuego(rs.getString(1));
                    juegoCompra.setDireccion_imagen(rs.getString(2));
                    juegoCompra.setNombreCategoria(rs.getString(3));
                    juegoCompra.setIdConsola(rs.getString(4));
                    juegoCompra.setStock_consola(rs.getInt(5));
                    juegoCompra.setPrecio(rs.getInt(6));
                    juegoCompra.setPrecio_nuevo(rs.getInt(7));
                    juegoCompra.setIdJuego(rs.getInt(8));

                    listaDetallesCompra.add(juegoCompra);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaDetallesCompra;

    }

    public void guardarJuegoComprado(JuegosCompradosReservados juegoCompradoReservado){

        String sql = "INSERT INTO juegoscompradosreservados(fechaCompraJuego,estadoCompraJuego,id_usuario,id_administrador,id_juego,id_consola,precio_compra,direccion_compra) values(?,?,?,?,?,?,?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

             long FechaCompra = juegoCompradoReservado.getFechaCompraJuego().getTime();
             java.sql.Date sqlDateFechaCompra = new java.sql.Date(FechaCompra);

             pstmt.setDate(1,sqlDateFechaCompra);
             pstmt.setString(2,juegoCompradoReservado.getEstadoCompraJuego());
             pstmt.setInt(3,juegoCompradoReservado.getUsuario().getIdPersona());
             pstmt.setInt(4,juegoCompradoReservado.getAdministrador().getIdPersona());
             pstmt.setInt(5,juegoCompradoReservado.getJuego().getIdJuegos());
             pstmt.setString(6,juegoCompradoReservado.getConsola().getIdConsola());
             pstmt.setInt(7,juegoCompradoReservado.getPrecio_compra());
             pstmt.setString(8,juegoCompradoReservado.getDireccion_compra());

             pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Juegos obtenerJuego(int id){

        Juegos juegos = new Juegos();

        String sql = "SELECT * from juegos\n" +
                "where idJuegos = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){

                    juegos.setIdJuegos(resultSet.getInt(1));
                    juegos.setNombreJuegos(resultSet.getString(2));
                    juegos.setStock(resultSet.getInt(3));
                    juegos.setPrecio(resultSet.getBigDecimal(4));
                    juegos.setEstaoJuego(resultSet.getString(5));
                    juegos.setDescripcion(resultSet.getString(6));

                    Imagen imagen = new Imagen();
                    imagen.setIdImagenes(resultSet.getInt(7));
                    juegos.setImagen(imagen);

                    Categoria categoria = new Categoria();
                    categoria.setIdCategorias(resultSet.getString(8));
                    juegos.setCategoria(categoria);

                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos;
    }

    public void actualizarStockJuegos(Juegos juego,int id){

        String sql = "UPDATE juegos \n" +
                "SET stock =? \n" +
                "WHERE idJuegos = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            int stock = juego.getStock();
            int stockActualizado = stock - 1;

            preparedStatement.setInt(1,stockActualizado);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public JuegosXConsola obtenerJuegoXConsola(String idConsola, int id){

        JuegosXConsola juego = new JuegosXConsola();

        String sql = "SELECT * from juegos_por_consolas\n" +
                "where id_juego = ? and id_consola = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,idConsola);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){

                    Juegos juego1 = new Juegos();
                    juego1.setIdJuegos(resultSet.getInt(1));
                    juego.setJuegoXConsola(juego1);

                    Consola consola = new Consola();
                    consola.setIdConsola(resultSet.getString(2));
                    juego.setConsolaDeJuego(consola);

                    juego.setStockXConsola(resultSet.getInt(3));

                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juego;
    }

    public void actualizarStockJuegosConsola(JuegosXConsola juegoxconsola,String idConsola, int id){

        String sql = "UPDATE juegos_por_consolas\n" +
                "SET stock_consola = ? \n" +
                "WHERE id_juego = ? and id_consola = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            int stock = juegoxconsola.getStockXConsola();
            int stockActualizado = stock - 1;

            preparedStatement.setInt(1,stockActualizado);
            preparedStatement.setInt(2,id);
            preparedStatement.setString(3,idConsola);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }



}