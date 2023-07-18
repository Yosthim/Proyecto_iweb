package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ConsolasDetallesDto;

import java.sql.*;
import java.util.ArrayList;

public class JuegosManagerDao extends BaseDao {
    public ArrayList<JuegosManager> listarJuegosMasVendidos(){

        ArrayList <JuegosManager> listaMasVendidos = new ArrayList<>();

        String sql = "SELECT  j.idJuegos, j.nombreJuegos, j.precio, j.stock, j.descripcion, c.nombre, j.id_imagen, i.direccion_archivo\n" +
                "FROM personas p, juegoscompradosreservados jcr, juegos j, categorias c, imagenes i\n" +
                "where  p.idPersona = jcr.id_usuario and jcr.estadoCompraJuego = 'Comprado' and jcr.id_juego = j.idJuegos and j.id_categoria = c.idCategorias and i.idImagenes = j.id_imagen\n" +
                "GROUP BY id_juego\n" +
                "ORDER BY count(j.nombreJuegos) DESC\n" +
                "LIMIT 5;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager juegosMasVendidos = new JuegosManager();
                juegosMasVendidos.setIdJuegos(resultSet.getInt(1));
                juegosMasVendidos.setNombreJuegos(resultSet.getString(2));
                juegosMasVendidos.setPrecio(resultSet.getInt(3));
                juegosMasVendidos.setId_imagen(resultSet.getInt(7));
                juegosMasVendidos.setDireccion_imagen(resultSet.getString(8));
                listaMasVendidos.add(juegosMasVendidos);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaMasVendidos;
    }


    public ArrayList<JuegosManager> listarJuegosMenosVendidos(){

        ArrayList <JuegosManager> listaMenosVendidos = new ArrayList<>();

        String sql = "SELECT  j.idJuegos, j.nombreJuegos, j.precio, j.stock, j.descripcion, c.nombre, j.id_imagen, i.direccion_archivo\n" +
                "FROM personas p, juegoscompradosreservados jcr, juegos j, categorias c, imagenes i\n" +
                "where  p.idPersona = jcr.id_usuario and jcr.estadoCompraJuego = 'Comprado' and jcr.id_juego = j.idJuegos and j.id_categoria = c.idCategorias and i.idImagenes = j.id_imagen\n" +
                "GROUP BY id_juego\n" +
                "ORDER BY count(j.nombreJuegos) ASC\n" +
                "LIMIT 5;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager juegosMenosVendidos = new JuegosManager();
                juegosMenosVendidos.setIdJuegos(resultSet.getInt(1));
                juegosMenosVendidos.setNombreJuegos(resultSet.getString(2));
                juegosMenosVendidos.setPrecio(resultSet.getInt(3));
                juegosMenosVendidos.setId_imagen(resultSet.getInt(7));
                juegosMenosVendidos.setDireccion_imagen(resultSet.getString(8));
                listaMenosVendidos.add(juegosMenosVendidos);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaMenosVendidos;
    }

    public ArrayList<JuegosManager> listarMasDetallesJuego(String idjuego){

        ArrayList<JuegosManager> listaMasDetallesJuego = new ArrayList<>();

        String sql = "select idJuegos, nombreJuegos, descripcion,stock, precio, nombre as \"Categorías\", direccion_archivo\n" +
                "from juegos j\n" +
                "inner join categorias g on g.idCategorias = j.id_categoria\n" +
                "left join imagenes i on i.idImagenes = j.id_imagen\n" +
                "where j.nombreJuegos = ?;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {

            ptsmtJuego.setString(1,idjuego);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    JuegosManager juegodetalles = new JuegosManager();
                    juegodetalles.setIdJuegos(rs.getInt(1));
                    juegodetalles.setNombreJuegos(rs.getString(2));
                    juegodetalles.setDescripcion_juego(rs.getString(3));
                    juegodetalles.setStock(rs.getInt(4));
                    juegodetalles.setPrecio(rs.getInt(5));
                    juegodetalles.setCategoria(rs.getString(6));
                    juegodetalles.setDireccion_imagen(rs.getString(7));

                    listaMasDetallesJuego.add(juegodetalles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMasDetallesJuego;
    }

    public ArrayList<JuegosManager> listarTodosJuegos(){

        ArrayList<JuegosManager> listaTodosJuegos = new ArrayList<>();

        String sql = "SELECT idJuegos, nombreJuegos, precio,direccion_archivo, COALESCE(d.precio_nuevo, 0) AS precio_nuevo\n" +
                    "FROM juegos j\n" +
                    "LEFT JOIN descuentos d ON j.idJuegos = d.id_juego\n" +
                    "INNER JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                    "WHERE j.estadoJuego = \"Activo\" OR j.estadoJuego = \"Oferta\";";

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager listaJuegos = new JuegosManager();
                listaJuegos.setIdJuegos(resultSet.getInt(1));
                listaJuegos.setNombreJuegos(resultSet.getString(2));
                listaJuegos.setPrecio(resultSet.getInt(3));
                listaJuegos.setDireccion_imagen(resultSet.getString(4));
                listaJuegos.setPrecio_nuevo(resultSet.getInt(5));
                listaTodosJuegos.add(listaJuegos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaTodosJuegos;
    }


    public void borrarJuego(int juegoID) {

        String sql = "UPDATE juegos SET estadoJuego = 'Eliminado' WHERE idJuegos = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, juegoID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ConsolasDetallesDto> listarConsolasPorJuego(int idjuego){
        ArrayList<ConsolasDetallesDto> listaConsolaPorJuego = new ArrayList<>();

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


        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtConsola = connection.prepareStatement(sql)) {

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

}
