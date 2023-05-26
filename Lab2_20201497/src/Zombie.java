public class Zombie extends Ente {
    //Completar (cuando se tenga que completar)

    @Override
    public void setFraseVictoria(String fraseVictoria) {
        //Cambiar frase por raw de acuerdo al tamaño
        String fraseNueva = "";//sin nada inicialmente
        //Variable generadora, se usara para forma la nueva frase
        int gen = (fraseVictoria.length() / 3) + 1;//Entre 3 porque "Raw" tiene 3 caracteres
        //Generación de nueva frase de victoria
        for (int i = 0; i < gen; i++) {
            fraseNueva += "Raw";
        }
        //Extraemos parte de la cadena para que tenga la misma longitud que la frase ingresada
        String newFraseVictoria = fraseNueva.substring(0, fraseVictoria.length());
        super.setFraseVictoria(newFraseVictoria);//Seteo de frase de victoria usando el set del padre
    }
}
