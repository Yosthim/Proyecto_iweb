package com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.InfoVentaDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CorreoDao extends BaseDao {

    public void correo(String correoDestino , String asunto, String contenido){
        String correo = "japyld.6@gmail.com";
        String contra = "ulctzppylyslvagd";


        Properties p ;
        p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.user",correo);
        p.setProperty("mail.smtp.auth", "true");
        Session s = Session.getDefaultInstance(p);
        try{
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correo));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);
            mensaje.setText(contenido + "\n\nGracias por su preferencia\n"
                    +"Atentamente,\n" +
                    "Japyld\n");
            Transport mTransport = s.getTransport("smtp");
            mTransport.connect(correo, contra);
            mTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            mTransport.close();

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    public String getEmail(int idPersona) {
        String sql = "SELECT correo FROM personas WHERE idPersona = " + idPersona;
        String emailDirection = null;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                emailDirection = rs.getString(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emailDirection;
    }

    public void sendChangeOfferEmail(InfoVentaDto require) {
        String asunto = "Cambio de oferta";
        String contenido =
                "Le informamos lo siguiente:\n\n" +
                "El usuario " + require.getUserName() + " modificó el precio de su oferta en el juego " + require.getGameName() + ".\n" +
                "La información del precio actualizado la puede ver en el registro de ofertas:\n" +
                "   N° de oferta = " + require.getOfferId();

        //Se envia el correo
        this.correo(this.getEmail(require.getIdAdmin()), asunto, contenido);
    }

    public void sendNewOfferEmail(Personas usuario, int idAdmin) {
        String asunto = "Nueva oferta";
        String contenido =
                "Una nueva oferta fue publicada a su cargo por parte del usuario " + usuario.getNombre() + " " + usuario.getApellido() + ".\n" +
                "Los detalles de la oferta lo puede observar en su página de ofertas.\n\nGracias por su atención,\n\nJapyld Solutions";
        //Se envia el correo
        this.correo(this.getEmail(idAdmin), asunto, contenido);
    }

}
