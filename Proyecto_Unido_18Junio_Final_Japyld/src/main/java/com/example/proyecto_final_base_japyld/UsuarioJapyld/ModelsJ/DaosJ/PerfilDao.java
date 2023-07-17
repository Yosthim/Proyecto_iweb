package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ;
import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto;

import java.sql.*;
import java.util.ArrayList;

public class PerfilDao extends BaseDao {

    public ArrayList<Imagen> listarImagenesPerfil(){
        ArrayList<Imagen> listaImagenPerfil = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from imagenes\n" +
                "where tipo = \"predeterminado\";";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                Imagen imagenperfil = new Imagen();
                imagenperfil.setIdImagenes(resultSet.getInt(1));
                imagenperfil.setTipo(resultSet.getString(3));
                imagenperfil.setDireccionArchivo(resultSet.getString(4));

                listaImagenPerfil.add(imagenperfil);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaImagenPerfil;
    }


    public void actualizarFotoPerfil(int idPerfil, int idUsuario){

        String sql = "UPDATE personas\n" +
                "SET id_perfil = ? \n" +
                "WHERE idPersona = ?;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,idPerfil);
            preparedStatement.setInt(2,idUsuario);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ImagenPerfilDto> listarFotoPerfil(){
        ArrayList<ImagenPerfilDto> listaFotoPerfil = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select p.idPersona, i.idImagenes, i.imagen,i.tipo,i.direccion_archivo from imagenes i\n" +
                "inner join personas p ON i.idImagenes = p.id_perfil\n" +
                "where tipo = \"predeterminado\";";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                ImagenPerfilDto perfilImagen = new ImagenPerfilDto();
                perfilImagen.setIdPersona(resultSet.getInt(1));
                perfilImagen.setIdImagen(resultSet.getInt(2));
                perfilImagen.setImagen(resultSet.getAsciiStream(3));
                perfilImagen.setTipo(resultSet.getString(4));
                perfilImagen.setDireccion_archivo(resultSet.getString(5));

                listaFotoPerfil.add(perfilImagen);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaFotoPerfil;
    }
}
