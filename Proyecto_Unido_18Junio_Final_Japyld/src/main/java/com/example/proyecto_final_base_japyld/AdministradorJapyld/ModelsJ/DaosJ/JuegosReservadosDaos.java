package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.JuegosReservadosDias;
import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;

import java.sql.*;
import java.util.ArrayList;

public class JuegosReservadosDaos extends BaseDao {
    public ArrayList<JuegosReservadosDias> tabla30Dias(int id){

        ArrayList<JuegosReservadosDias> JR30D= new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "Select r.idJuegosCompradosReservados ,j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
                "                from juegoscompradosreservados r \n" +
                "                left join juegos j on  r.id_juego =j.idJuegos\n" +
                "                left join personas p on r.id_usuario =p.idPersona\n" +
                "                where timestampdiff(day,r.fechaCompraJuego, current_date())>10 and r.estadoCompraJuego = 'Reservado' and r.id_administrador = ?;";
        String url = "jdbc:mysql://localhost:3306/japyld";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){

                    JuegosReservadosDias J10 = new JuegosReservadosDias();
                    J10.setId_venta(resultSet.getInt(1));
                    J10.setNombre(resultSet.getString(2));
                    J10.setUsuario(resultSet.getString(3));
                    J10.setDias(resultSet.getInt(4));
                    JR30D.add(J10);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return JR30D;
    }
    public ArrayList<JuegosReservadosDias> tabla20Dias(int id){

        ArrayList<JuegosReservadosDias> JR20D = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 ="Select r.idJuegosCompradosReservados ,j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
                "                from juegoscompradosreservados r \n" +
                "                left join juegos j on  r.id_juego =j.idJuegos\n" +
                "                left join personas p on r.id_usuario =p.idPersona\n" +
                "                where timestampdiff(day,r.fechaCompraJuego, current_date()) between 3 and 10 and r.estadoCompraJuego = 'Reservado' and r.id_administrador = ?;";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql1)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    JuegosReservadosDias J10 = new JuegosReservadosDias();
                    J10.setId_venta(resultSet.getInt(1));
                    J10.setNombre(resultSet.getString(2));
                    J10.setUsuario(resultSet.getString(3));
                    J10.setDias(resultSet.getInt(4));
                    JR20D.add(J10);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return JR20D;
    }

    public ArrayList<JuegosReservadosDias> tabla10Dias(int id){

        ArrayList<JuegosReservadosDias> JR10D = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql1 ="Select r.idJuegosCompradosReservados ,j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
                "                from juegoscompradosreservados r \n" +
                "                left join juegos j on  r.id_juego =j.idJuegos\n" +
                "                left join personas p on r.id_usuario =p.idPersona\n" +
                "                where timestampdiff(day,r.fechaCompraJuego, current_date())<3  and r.estadoCompraJuego = 'Reservado' and r.id_administrador = ?;";
        String url = "jdbc:mysql://localhost:3306/japyld";


        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql1)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    JuegosReservadosDias J10 = new JuegosReservadosDias();
                    J10.setId_venta(resultSet.getInt(1));
                    J10.setNombre(resultSet.getString(2));
                    J10.setUsuario(resultSet.getString(3));
                    J10.setDias(resultSet.getInt(4));
                    JR10D.add(J10);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return JR10D;
    }

    public JuegosCompradosReservados obtenerReserva(int id_venta){

        JuegosCompradosReservados juegosCompradosReservados = null;

        Juegos juegos = null;
        Imagen imagen = null;
        Personas usaurio = null;
        Consola consola = null;
        Categoria categoria =null;

        try(Connection connection = this.getConnection();
            PreparedStatement psmt =connection.prepareStatement("SELECT * FROM juegoscompradosreservados r\n" +
                    "                    left join juegos j on r.id_juego = j.idJuegos\n" +
                    "                    left join categorias ca on j.id_categoria = ca.idCategorias\n" +
                    "                    left join imagenes i on j.id_imagen = i.idImagenes\n" +
                    "                    left join personas p on r.id_usuario = p.idPersona\n" +
                    "                    left join personas a on r.id_administrador = a.idPersona\n" +
                    "                    left join consolas c on r.id_consola = c.idConsolas\n" +
                    "                    WHERE  r.idJuegosCompradosReservados =?;")){

            psmt.setInt(1,id_venta);

            try(ResultSet rs = psmt.executeQuery()){
                if(rs.next()){
                    juegosCompradosReservados = new JuegosCompradosReservados();

                    juegosCompradosReservados.setIdJuegosCompradosReservados(rs.getInt(1));
                    juegosCompradosReservados.setPrecio(rs.getBigDecimal(9));
                    juegosCompradosReservados.setDireccion_compra(rs.getString(10));
                    juegosCompradosReservados.setFechaCompraJuego(rs.getDate(2));

                    juegos = new Juegos();
                    juegos.setIdJuegos(rs.getInt(11));
                    juegos.setNombreJuegos(rs.getString(12));
                    juegos.setDescripcion(rs.getString(16));
                    juegos.setStock(rs.getInt(13));

                    imagen = new Imagen();
                    imagen.setIdImagenes(rs.getInt(21));
                    // como pasar la imagen a la base de datos
                    imagen.setImagem(rs.getAsciiStream(22));
                    juegos.setImagen(imagen);

                    categoria = new Categoria();
                    categoria.setIdCategorias(rs.getString(19));
                    categoria.setNombre(rs.getString(20));
                    juegos.setCategoria(categoria);

                    juegosCompradosReservados.setJuego(juegos);


                    usaurio = new Personas();
                    usaurio.setIdPersona(rs.getInt(25));
                    usaurio.setCorreo(rs.getString(26));

                    juegosCompradosReservados.setUsuario(usaurio);


                    consola= new Consola();
                    consola.setIdConsola(rs.getString(51));
                    consola.setNombre(rs.getString(52));
                    juegosCompradosReservados.setConsola(consola);

                }
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegosCompradosReservados;
    }

    public void cambiarEstado(JuegosCompradosReservados juegosCompradosReservados){

        String sql = "UPDATE juegoscompradosreservados SET estadoCompraJuego = 'Comprado' WHERE idJuegosCompradosReservados = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, juegosCompradosReservados.getIdJuegosCompradosReservados());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
