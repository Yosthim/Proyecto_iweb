package com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CorreoAdminDao {
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
            mensaje.setText(contenido + "\n" + "Gracias por su preferencia\n"
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
}
