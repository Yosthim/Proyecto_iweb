package com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.BeansGenerales.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDao extends BaseDao {

    public Personas obtenerPersona(int personaId) {

        Personas persona = null;

        String sql = "select * from personas p\n" +
                "left join roles r ON (p.id_roles = r.idroles)\n" +
                "left join imagenes i on (p.id_perfil = i.idImagenes)\n" +
                "where p.idPersona = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personaId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    persona = new Personas();
                    fetchPersonaData(persona, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return persona;
    }


    private void fetchPersonaData(Personas persona, ResultSet rs) throws SQLException {

        persona.setIdPersona(rs.getInt(1));
        persona.setCorreo(rs.getString(2));
        persona.setContrasenia(rs.getString(3));
        persona.setNombre(rs.getString(4));
        persona.setApellido(rs.getString(5));
        persona.setFechaDeNacimiento(rs.getDate(6));
        persona.setDni(rs.getInt(7));
        persona.setGenero(rs.getString(8));
        persona.setCategoriaJuegoPreferida(rs.getString(9));
        persona.setFechaRegistro(rs.getDate(10));
        persona.setEstado(rs.getString(11));

        Roles rol = new Roles();
        rol.setIdRoles(rs.getString(12));
        rol.setRol(rs.getString(16));
        persona.setRol(rol);

        Imagen imagen = new Imagen();
        imagen.setIdImagenes(rs.getInt(13));
        imagen.setTipo(rs.getString(19));
        imagen.setDireccionArchivo(rs.getString(20));
        persona.setImagen(imagen);

        persona.setHashcontrasenia(rs.getString(14));
    }

    public Personas validarUsuarioPassword(String username, String password){

        Personas persona = null;

        String sql = "Select * from personas where correo = ? and hashcontrasenia = SHA2(?,256)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setString(1,username);
                pstmt.setString(2,password);

                try (ResultSet rs = pstmt.executeQuery();){
                    if(rs.next()){
                        int personaId = rs.getInt(1);
                        persona = this.obtenerPersona(personaId);
                    }
                }

        }catch (SQLException ex){
            ex.printStackTrace();
        }


        return persona;
    }
}
