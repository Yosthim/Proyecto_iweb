package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DescuentoDao extends BaseDao {

    public Juegos obtenerJuego(int id){

        Juegos juegos = null;
        Categoria categoria = null;
        Imagen imagen = null;

        try(Connection connection = this.getConnection();
            PreparedStatement psmt =connection.prepareStatement("SELECT * FROM juegos j\n" +
                    "left join categorias ca on j.id_categoria = ca.idCategorias\n" +
                    "left join imagenes i on j.id_imagen = i.idImagenes\n" +
                    "WHERE  j.idJuegos =? ;")){

            psmt.setInt(1,id);

            try(ResultSet rs = psmt.executeQuery()){
                if(rs.next()){


                    juegos = new Juegos();
                    juegos.setIdJuegos(rs.getInt(1));
                    juegos.setNombreJuegos(rs.getString(2));
                    juegos.setStock(rs.getInt(3));
                    juegos.setPrecio(rs.getBigDecimal(4));
                    juegos.setDescripcion(rs.getString(6));

                    imagen = new Imagen();
                    imagen.setIdImagenes(rs.getInt(7));
                    imagen.setDireccionArchivo(rs.getString(14));
                    juegos.setImagen(imagen);

                    categoria = new Categoria();
                    categoria.setIdCategorias(rs.getString(8));
                    categoria.setNombre(rs.getString(10));
                    juegos.setCategoria(categoria);


                }
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return  juegos;
    }

    public void publicarDescuento(Descuentos descuentos,Personas administrador,Juegos juegos){

        String sql = "INSERT INTO descuentos (precio_nuevo,fecha_publicacion,duracion,id_juego,id_administrador)\n" +
                "VALUES (?,current_date(),?,?,?);";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,descuentos.getPrecioDescuentoNuevo());
            preparedStatement.setInt(2,descuentos.getDuracionDescuento());
            preparedStatement.setInt(3,juegos.getIdJuegos());
            preparedStatement.setInt(4,administrador.getIdPersona());
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void actualizarEstado(Juegos juegos){

        String sql = "UPDATE juegos SET estadoJuego = 'Oferta'\n" +
                "WHERE idJuegos = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1,juegos.getIdJuegos());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // ACTUALIZA EL ESTADO DEL JUEGO CUANDO YA SE ACABO EL PAZO,

    public void actualizarEstadoJuego(){

        String sql = "UPDATE juegos SET estadoJuego = 'Activo'\n" +
                "WHERE idJuegos IN (select idJuegos from(Select j.idJuegos\n" +
                "from  descuentos d\n" +
                "left join juegos j on  d.id_juego =j.idJuegos\n" +
                "where timestampdiff(day,d.fecha_publicacion, current_date())=d.duracion) as t);";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
