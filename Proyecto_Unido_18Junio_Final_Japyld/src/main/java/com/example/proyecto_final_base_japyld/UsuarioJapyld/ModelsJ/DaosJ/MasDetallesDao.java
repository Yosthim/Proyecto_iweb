package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ConsolasDetallesDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.MasDetallesDto;

import java.sql.*;
import java.util.ArrayList;

public class MasDetallesDao extends BaseDao {

    public ArrayList<MasDetallesDto> listarMasDetallesJuego(int idjuego){
        ArrayList<MasDetallesDto> listaMasDetallesJuego = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT idJuegos, nombreJuegos, precio,direccion_archivo,i.idImagenes, COALESCE(d.precio_nuevo, 0) AS precio_nuevo,c.nombre,j.descripcion\n" +
                "                FROM juegos j\n" +
                "                LEFT JOIN descuentos d ON j.idJuegos = d.id_juego\n" +
                "                LEFT JOIN categorias c ON j.id_categoria = c.idCategorias\n" +
                "                INNER JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                "                WHERE (j.estadoJuego = 'Activo' OR j.estadoJuego = 'Oferta') and idJuegos = ?";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql))
        {
            ptsmtJuego.setInt(1,idjuego);

            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    MasDetallesDto juegodetalles = new MasDetallesDto();
                    juegodetalles.setIdJuegos(rs.getInt(1));
                    juegodetalles.setNombreJuegos(rs.getString(2));
                    juegodetalles.setPrecio(rs.getInt(3));
                    juegodetalles.setDireccion_imagen(rs.getString(4));
                    juegodetalles.setIdImagen(rs.getInt(5));
                    juegodetalles.setPrecio_nuevo(rs.getInt(6));
                    juegodetalles.setCategoria(rs.getString(7));
                    juegodetalles.setDescripcion(rs.getString(8));

                    listaMasDetallesJuego.add(juegodetalles);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMasDetallesJuego;
    }

    public ArrayList<ConsolasDetallesDto> listarConsolasPorJuego(int idjuego){
        ArrayList<ConsolasDetallesDto> listaConsolaPorJuego = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT id_juego,\n" +
                "       GROUP_CONCAT(CASE WHEN id_consola IN ('PS5') THEN id_consola END) AS consola_1,\n" +
                "       GROUP_CONCAT(CASE WHEN id_consola IN ('PS4') THEN id_consola END) AS consola_2,\n" +
                "       GROUP_CONCAT(CASE WHEN id_consola IN ('XB3') THEN id_consola END) AS consola_3,\n" +
                "       GROUP_CONCAT(CASE WHEN id_consola IN ('XBO') THEN id_consola END) AS consola_4,\n" +
                "       GROUP_CONCAT(CASE WHEN id_consola IN ('SWI') THEN id_consola END) AS consola_5,\n" +
                "       GROUP_CONCAT(CASE WHEN id_consola IN ('WIU') THEN id_consola END) AS consola_6\n" +
                "FROM (\n" +
                "    SELECT t1.id_juego, t1.id_consola, t2.nombre AS consola\n" +
                "    FROM juegos_por_consolas t1\n" +
                "    INNER JOIN consolas t2 ON t1.id_consola = t2.idConsolas\n" +
                ") AS subquery\n" +
                "WHERE id_juego = ? \n" +
                "GROUP BY id_juego\n" +
                "ORDER BY id_juego;\n";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtConsola = connection.prepareStatement(sql))
        {
            ptsmtConsola.setInt(1,idjuego);
            try (ResultSet rs = ptsmtConsola.executeQuery()){
                while(rs.next()){

                    ConsolasDetallesDto consoladetalles = new ConsolasDetallesDto();

                    Juegos juego = new Juegos();
                    juego.setIdJuegos(rs.getInt(1));

                    consoladetalles.setJuego(juego);
                    consoladetalles.setConsola1(rs.getString(2));
                    consoladetalles.setConsola2(rs.getString(3));
                    consoladetalles.setConsola3(rs.getString(4));
                    consoladetalles.setConsola4(rs.getString(5));
                    consoladetalles.setConsola5(rs.getString(6));
                    consoladetalles.setConsola6(rs.getString(7));

                    listaConsolaPorJuego.add(consoladetalles);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaConsolaPorJuego;

    }

    public ArrayList<JuegosCompradosReservados> listarRating(int idjuego){
        ArrayList<JuegosCompradosReservados> listaRating = new ArrayList<>();

        String sql = "SELECT rating\n" +
                "from juegoscompradosreservados\n" +
                "where id_juego = ?;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmt = connection.prepareStatement(sql)) {

             ptsmt.setInt(1, idjuego);

             try (ResultSet rs = ptsmt.executeQuery()) {
                 while (rs.next()) {
                     JuegosCompradosReservados rating = new JuegosCompradosReservados();
                     rating.setRating(rs.getInt(1));

                     listaRating.add(rating);
                 }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaRating;

    }

}