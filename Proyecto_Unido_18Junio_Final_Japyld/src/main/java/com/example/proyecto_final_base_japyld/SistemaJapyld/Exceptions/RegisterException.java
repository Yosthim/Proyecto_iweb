package com.example.proyecto_final_base_japyld.SistemaJapyld.Exceptions;

public class RegisterException extends Exception{
    public String mensajeError(int tipo) {
        String msg1 = "Los datos ingresados son incorrectos o se ingresó el nombre de un juego existente en el sistema";
        String msg2 = "Los datos ingresados son incorrectos";
        if (tipo == 1) {
            return msg1;
        }else {
            return msg2;
        }
    }
}
