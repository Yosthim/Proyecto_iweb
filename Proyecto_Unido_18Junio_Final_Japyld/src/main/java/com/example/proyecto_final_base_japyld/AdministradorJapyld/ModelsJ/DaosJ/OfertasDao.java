package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfertasDao extends BaseDao {

    // Obtiene la venta

    public VentaJuegosGeneral obtenerJuego(int id_venta){
        VentaJuegosGeneral ventaJuegosGeneral = null;
        Juegos juegos = null;
        Imagen imagen = null;
        Personas usaurio = null;
        Consola consola = null;
        Categoria categoria =null;

        try(Connection connection = this.getConnection();
            PreparedStatement psmt =connection.prepareStatement("SELECT * FROM ventajuegosgeneral v\n" +
                    "left join juegos j on v.id_juego = j.idJuegos\n" +
                    "left join categorias ca on j.id_categoria = ca.idCategorias\n" +
                    "left join imagenes i on j.id_imagen = i.idImagenes\n" +
                    "left join personas p on v.id_usuario = p.idPersona\n" +
                    "left join personas a on v.id_administrador = a.idPersona\n" +
                    "left join consolas c on v.id_consola = c.idConsolas\n" +
                    "WHERE  v.idVenta =? ;")){

            psmt.setInt(1,id_venta);

            try(ResultSet rs = psmt.executeQuery()){
                if(rs.next()){
                    ventaJuegosGeneral = new VentaJuegosGeneral();
                    ventaJuegosGeneral.setIdVenta(rs.getInt(1));
                    ventaJuegosGeneral.setPrecioUsuario(rs.getBigDecimal(5));
                    ventaJuegosGeneral.setCantidad(rs.getInt(15));

                    juegos = new Juegos();
                    juegos.setIdJuegos(rs.getInt(10));
                    juegos.setNombreJuegos(rs.getString(19));
                    juegos.setDescripcion(rs.getString(23));
                    juegos.setStock(rs.getInt(20));

                    imagen = new Imagen();
                    imagen.setIdImagenes(rs.getInt(28));
                    imagen.setDireccionArchivo(rs.getString(31));
                    juegos.setImagen(imagen);

                    categoria = new Categoria();
                    categoria.setIdCategorias(rs.getString(26));
                    categoria.setNombre(rs.getString(27));
                    juegos.setCategoria(categoria);

                    ventaJuegosGeneral.setJuego(juegos);

                    usaurio = new Personas();
                    usaurio.setIdPersona(rs.getInt(8));

                    ventaJuegosGeneral.setUsuario(usaurio);

                    consola= new Consola();
                    consola.setIdConsola(rs.getString(11));
                    consola.setNombre(rs.getString("c.nombre"));

                    ventaJuegosGeneral.setConsola(consola);

                }
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }


        return ventaJuegosGeneral;
    }

    // Rechazar un juego

    public void editarVenta(VentaJuegosGeneral ventaJuegosGeneral){

        String sql = "UPDATE ventajuegosgeneral  \n" +
                "SET estadoVenta = 'Rechazado',\n" +
                "razonRechazo = ?" +
                "where idVenta = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,ventaJuegosGeneral.getRazonRechazo());
            preparedStatement.setInt(2,ventaJuegosGeneral.getIdVenta());
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    // Enviar una contraoeferta

    public void editarVentaC(VentaJuegosGeneral ventaJuegosGeneral){

        String sql = "UPDATE ventajuegosgeneral  \n" +
                "SET estadoVenta = 'Contraoferta',\n" +
                "razonRechazo = ?,\n" +
                "precio_admin = ?\n" +
                "where idVenta = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,ventaJuegosGeneral.getRazonRechazo());
            preparedStatement.setBigDecimal(2,ventaJuegosGeneral.getPrecioAdmi());
            preparedStatement.setInt(3,ventaJuegosGeneral.getIdVenta());
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    // Acepta una compra de un juego  que ya esta en la base de datos

    public void borrar(VentaJuegosGeneral ventaJuegosGeneral){

        String sql = "UPDATE ventajuegosgeneral SET estadoVenta = 'Aceptado' WHERE idVenta = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, ventaJuegosGeneral.getIdVenta());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Se actualiza el stock del juego

    public void actualizarStock(VentaJuegosGeneral ventaJuegosGeneral){

        String sql = "UPDATE juegos SET stock = ?" +
                " WHERE idJuegos = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int nuevo =ventaJuegosGeneral.getJuego().getStock()+ventaJuegosGeneral.getCantidad();
            System.out.println(nuevo);
            pstmt.setInt(1,nuevo);
            pstmt.setInt(2, ventaJuegosGeneral.getJuego().getIdJuegos());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
