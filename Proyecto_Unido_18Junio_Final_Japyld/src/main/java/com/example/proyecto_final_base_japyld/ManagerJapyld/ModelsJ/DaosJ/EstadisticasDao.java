package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Comentarios;
import com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager;

import java.sql.*;
import java.util.ArrayList;

public class EstadisticasDao extends BaseDao {

    public int VentaPorMes(String mes){

        String sql = "SELECT \n" +
                "    id_juego, \n" +
                "    direccion_archivo, \n" +
                "    nombreJuegos, \n" +
                "    precio,\n" +
                "    MONTHNAME(fechaCompraJuego) AS mes_compra,\n" +
                "    COUNT(jc.id_juego) AS ventas,\n" +
                "    precio * COUNT(*) AS precioPeli,\n" +
                "    total.total_venta\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON j.idJuegos = jc.id_juego\n" +
                "LEFT JOIN imagenes i ON i.idImagenes = j.id_imagen\n" +
                "CROSS JOIN (\n" +
                "    SELECT SUM(precio * ventaCount) AS total_venta \n" +
                "    FROM (\n" +
                "        SELECT precio, COUNT(*) AS ventaCount\n" +
                "        FROM juegoscompradosreservados jcr\n" +
                "        INNER JOIN juegos j ON jcr.id_juego = j.idJuegos\n" +
                "        INNER JOIN personas p ON jcr.id_usuario = p.idPersona\n" +
                "        WHERE jcr.estadoCompraJuego = 'Comprado' AND p.id_roles = \"USR\" AND MONTHNAME(jcr.fechaCompraJuego) = ?\n" +
                "        GROUP BY id_juego\n" +
                "    ) AS ventasTotales\n" +
                ") AS total\n" +
                "WHERE jc.estadoCompraJuego = 'Comprado' AND MONTHNAME(fechaCompraJuego) = ?\n" +
                "GROUP BY id_juego, direccion_archivo, nombreJuegos, precio, mes_compra, total.total_venta\n" +
                "LIMIT 1;";

        int ventaMes = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {
            ptsmtJuego.setString(1, mes);
            ptsmtJuego.setString(2, mes);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    ventaMes = rs.getInt(8);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ventaMes;
    }


    public ArrayList<JuegosManager> comentarioPorJuego(int idjuego){

        ArrayList<JuegosManager> comentarioPorJuego = new ArrayList<>();

        String sql = "SELECT j.idJuegos, j.nombreJuegos, p.nombre, p.apellido, c.fecha_comentario, c.comentario FROM juegos j, comentarios c, personas p\n" +
                "WHERE j.idJuegos = ? and p.idPersona = c.Persona_idPersona and c.Juegos_idJuegos = j.idJuegos\n" +
                "ORDER BY c.fecha_comentario DESC\n" +
                "LIMIT 3;";

        try (Connection connection = this.getConnection();
             PreparedStatement ptsmtJuego = connection.prepareStatement(sql)) {

            ptsmtJuego.setInt(1,idjuego);
            try (ResultSet rs = ptsmtJuego.executeQuery()){
                while(rs.next()){
                    JuegosManager comentarioJuego = new JuegosManager();
                    Personas personas = new Personas();
                    Comentarios comentarios = new Comentarios();
                    comentarioJuego.setIdJuegos(rs.getInt(1));
                    comentarioJuego.setNombreJuegos(rs.getString(2));
                    personas.setNombre(rs.getString(3));
                    personas.setApellido(rs.getString(4));
                    comentarios.setFecha_comentario1(rs.getTimestamp(5));
                    comentarios.setComentario(rs.getString(6));
                    comentarioJuego.setPersonas(personas);
                    comentarioJuego.setComentarios(comentarios);
                    comentarioPorJuego.add(comentarioJuego);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comentarioPorJuego;
    }

    public ArrayList<JuegosManager> catologoReciente(){

        ArrayList<JuegosManager> nuevoIngreso = new ArrayList<>();

        String sql = "SELECT j.idJuegos, j.nombreJuegos, c.nombre, j.precio, i.direccion_archivo, vjg.id_administrador, p.nombre, p.apellido  FROM juegos j, ventajuegosgeneral vjg, categorias c, imagenes i, personas p\n" +
                "WHERE j.idJuegos = vjg.id_juego and j.id_categoria = c.idCategorias and vjg.estadoVenta = 'Aceptado' and j.id_imagen = i.idImagenes and p.idPersona = vjg.id_administrador\n" +
                "ORDER BY vjg.fechaPublicacion DESC\n" +
                "LIMIT 4;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                JuegosManager nuevoJuego = new JuegosManager();
                nuevoJuego.setIdJuegos(resultSet.getInt(1));
                nuevoJuego.setNombreJuegos(resultSet.getString(2));
                nuevoJuego.setCategoria(resultSet.getString(3));
                nuevoJuego.setPrecio(resultSet.getInt(4));
                nuevoJuego.setDireccion_imagen(resultSet.getString(5));
                Personas personas = new Personas();
                personas.setNombre(resultSet.getString(7));
                personas.setApellido(resultSet.getString(8));
                nuevoJuego.setPersonas(personas);
                nuevoIngreso.add(nuevoJuego);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevoIngreso;

    }


    public ArrayList<Objetivos> Objetivos(){

        ArrayList<Objetivos> objetivos= new ArrayList<>();

        String sql = "SELECT * FROM objetivosmanager\n" +
                "ORDER BY fecha desc\n" +
                "LIMIT 1;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Objetivos objetivos1 = new Objetivos();

                objetivos1.setIdObjetivos(resultSet.getInt(1));
                objetivos1.setVentasPorMesJuego(resultSet.getInt(2));
                objetivos1.setGastosPorMesJuego(resultSet.getInt(3));
                objetivos1.setUsuarioPorMes(resultSet.getInt(4));

                objetivos.add(objetivos1);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return objetivos;
    }

    public void actualizarObjetivos(Objetivos objetivos) {
        String sql = "INSERT INTO objetivosmanager (ventasPorMesJuego,gastosPorMesJuego,usariosPorMes,id_manager,fecha) VALUES (?,?,?,10,?);";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, objetivos.getVentasPorMesJuego());
            pstmt.setInt(2, objetivos.getGastosPorMesJuego());
            pstmt.setInt(3, objetivos.getUsuarioPorMes());
            pstmt.setTimestamp(4, objetivos.getFecha());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Objetivos> listarObjetivosPasados(){

        ArrayList <Objetivos> recordObjetivos = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM objetivosmanager\n" +
                "WHERE id_objetivo NOT IN (SELECT MAX(id_objetivo)\n" +
                "FROM objetivosmanager) ORDER BY fecha DESC;";

        try (Connection connection = this.getConnection();
             Statement smt = connection.createStatement();
             ResultSet resultSet = smt.executeQuery(sql)) {

            while(resultSet.next()){
                Objetivos objetivos = new Objetivos();
                objetivos.setIdObjetivos(resultSet.getInt(1));
                objetivos.setVentasPorMesJuego(resultSet.getInt(2));
                objetivos.setGastosPorMesJuego(resultSet.getInt(3));
                objetivos.setUsuarioPorMes(resultSet.getInt(4));
                objetivos.setFecha(resultSet.getTimestamp(6));
                recordObjetivos.add(objetivos);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return recordObjetivos;
    }
}
