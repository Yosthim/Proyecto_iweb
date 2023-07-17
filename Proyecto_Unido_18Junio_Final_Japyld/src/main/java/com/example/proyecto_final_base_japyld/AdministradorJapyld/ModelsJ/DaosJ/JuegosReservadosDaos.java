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

        String sql = "Select j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
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
                    J10.setNombre(resultSet.getString(1));
                    J10.setUsuario(resultSet.getString(2));
                    J10.setDias(resultSet.getInt(3));
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
        String sql1 ="Select j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
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
                    J10.setNombre(resultSet.getString(1));
                    J10.setUsuario(resultSet.getString(2));
                    J10.setDias(resultSet.getInt(3));
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
        String sql1 ="Select j.nombreJuegos, p.nombre, timestampdiff(day,r.fechaCompraJuego, current_date()) as 'dias'\n" +
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
                    J10.setNombre(resultSet.getString(1));
                    J10.setUsuario(resultSet.getString(2));
                    J10.setDias(resultSet.getInt(3));
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
                    juegosCompradosReservados = new JuegosCompradosReservados();
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
        return juegosCompradosReservados;
    }

}
