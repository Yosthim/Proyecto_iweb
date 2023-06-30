package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto;

import java.sql.*;
import java.util.ArrayList;

public class PaginaPrincipalDao extends BaseDao {

    public ArrayList<PaginaPrincipalDto> listarJuegos(){
        ArrayList<PaginaPrincipalDto> listaJuegos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select idJuegos,nombreJuegos,precio,direccion_archivo\n" +
                "                from juegos j\n" +
                "                inner join imagenes i on j.id_imagen = i.idImagenes\n" +
                "                where j.estadoJuego = \"Activo\" or j.estadoJuego = \"Oferta\";";
        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                PaginaPrincipalDto juegosPrincipal = new PaginaPrincipalDto();
                juegosPrincipal.setIdJuegos(resultSet.getInt(1));
                juegosPrincipal.setNombreJuegos(resultSet.getString(2));
                juegosPrincipal.setPrecio(resultSet.getInt(3));
                juegosPrincipal.setDireccion_imagen(resultSet.getString(4));

                listaJuegos.add(juegosPrincipal);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegos;
    }

    public ArrayList<PaginaPrincipalDto> buscarJuegoPorNombre(String name) {

        ArrayList<PaginaPrincipalDto> listaJuegos = new ArrayList<>();

        String sql = "SELECT idJuegos, nombreJuegos, precio, direccion_archivo\n" +
                "FROM juegos j\n" +
                "INNER JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                "WHERE (j.estadoJuego = 'Activo' OR j.estadoJuego = 'Oferta') AND (j.nombreJuegos LIKE ?);";


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            name = '%'+ name +'%';
            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    PaginaPrincipalDto juego = new PaginaPrincipalDto();
                    juego.setIdJuegos(rs.getInt(1));
                    juego.setNombreJuegos(rs.getString(2));
                    juego.setPrecio(rs.getInt(3));
                    juego.setDireccion_imagen(rs.getString(4));

                    listaJuegos.add(juego);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaJuegos;
    }




}
