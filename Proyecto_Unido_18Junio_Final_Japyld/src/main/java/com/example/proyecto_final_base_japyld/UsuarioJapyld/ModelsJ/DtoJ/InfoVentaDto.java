package com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;

public class InfoVentaDto {

    private String userName;
    private int idAdmin;
    private int offerId;
    private String gameName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
