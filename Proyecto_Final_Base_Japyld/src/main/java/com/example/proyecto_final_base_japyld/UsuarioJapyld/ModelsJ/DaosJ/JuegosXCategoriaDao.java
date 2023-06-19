package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;


import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.MasDetallesDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto;


import java.sql.*;
import java.util.ArrayList;
public class JuegosXCategoriaDao {

    public ArrayList<PaginaPrincipalDto> listarJuegosXcategoria(String idcategoria){

        ArrayList<PaginaPrincipalDto> listaJuegosXcategoria = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "select idJuegos,nombreJuegos,precio,direccion_archivo \n" +
                "from juegos j \n" +
                "inner join imagenes i on j.id_imagen = i.idImagenes\n" +
                "inner join categorias c on j.id_categoria = c.idCategorias\n" +
                "where j.estadoJuego = \"Activo\" and id_categoria = ?;";

        String url = "jdbc:mysql://localhost:3306/japyld";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
                /*Usaremos prepared Statement*/
             PreparedStatement ptsmtCategorias = connection.prepareStatement(sql))
        {
            ptsmtCategorias.setString(1,idcategoria);
            try (ResultSet rs = ptsmtCategorias.executeQuery()){

                while(rs.next()){

                    PaginaPrincipalDto categoriasjuego = new PaginaPrincipalDto();

                    categoriasjuego.setIdJuegos(rs.getInt(1));
                    categoriasjuego.setNombreJuegos(rs.getString(2));
                    categoriasjuego.setPrecio(rs.getInt(3));
                    categoriasjuego.setDireccion_imagen(rs.getString(4));

                    listaJuegosXcategoria.add(categoriasjuego);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegosXcategoria;

    }
}
