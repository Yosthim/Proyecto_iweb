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
        String sql = "select idJuegos,nombreJuegos,precio,i.direccion_archivo,i.idImagenes, COALESCE(d.precio_nuevo, 0) AS precio_nuevo\n" +
                "from juegos j \n" +
                "LEFT JOIN descuentos d ON j.idJuegos = d.id_juego\n" +
                "inner join imagenes i on j.id_imagen = i.idImagenes\n" +
                "inner join categorias c on j.id_categoria = c.idCategorias\n" +
                "where (j.estadoJuego = 'Activo' OR j.estadoJuego = 'Oferta') and id_categoria = ?";

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
                    categoriasjuego.setIdImagen(rs.getInt(5));
                    categoriasjuego.setPrecio_nuevo(rs.getInt(6));


                    listaJuegosXcategoria.add(categoriasjuego);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaJuegosXcategoria;

    }
}
