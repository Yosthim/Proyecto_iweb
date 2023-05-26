import java.util.ArrayList;

public class Superviviente extends Ente {

    private ArrayList<Objeto> inventario;
    private int kills = 0;
    private int sanidad;
    private int valentia;
    private boolean trauma = false;


    public void declararVictoria(){
        //Completar
        super.declararVictoria();//Frase si no esta traumado
        //verificación de trauma
        if (trauma) {
            System.out.println("Pero igual este mundo no tiene esperanza");//Frase extra si está traumado
        }
    }

    public double getPoder(){
        //completar
        double poderArmas = 0;
        int cantArmas = 0;//Para contar las armas que tiene en su inventario
        for (Objeto arma : inventario) {
            if (arma instanceof Arma) {
                cantArmas++;
                if (cantArmas <= 2) {//Se eligen las 2 primeras armas
                    poderArmas += ((Arma) arma).getPoder();
                }
            }
        }
        double poder = this.getFuerza() + poderArmas;
        //Frases que saldrán en consola
        if (poder == this.getFuerza()) {//Cuando el supervivente no tiene arma en su inventario
            System.out.println(this.getNombre() + " no tiene arma!! -> Ataca a puño limpio");
        }else {//El superviviente tiene arma en su inventario
            Objeto arma1 = null;
            Objeto arma2 = null;
            int cant = 0;
            for (Objeto obj : this.getInventario()) {
                if (obj instanceof Arma){
                    cant++;
                }
                if (cant == 1) {
                    arma1 = obj;
                } else if (cant ==2) {
                    arma2 = obj;
                }
            }
            if (arma2 != null) {//Cuando tiene 2 armas o más
                System.out.println(this.getNombre() + " ataca con " + arma1.getNombre() + " y " + arma2.getNombre());
            }else {//Cuando tiene una sola arma
                System.out.println(this.getNombre() + " ataca con " + arma1.getNombre());
            }
        }
        //Retorna el poder calculado
        return poder;
    }

    public void curarse(){
       //Completar
        int cantObj = 0;//Cuenta de objetos curativos
        for (Objeto obj : inventario) {
            if (obj instanceof ObjetoCurativo) {
                cantObj++;
                if (cantObj == 1) {//Usa uno de sus objetos curativos
                    int vidaRes = ((ObjetoCurativo) obj).getVidaRestaurada();
                    this.setVida(this.getVida() + vidaRes);
                    System.out.println(this.getNombre() + " se curó " + vidaRes + " usando " + obj.getNombre());
                    inventario.remove(obj);//Pierde el objeto curativo de su inventario
                }
            }
        }
    }

    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Objeto> inventario) {
        this.inventario = inventario;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getSanidad() {
        return sanidad;
    }

    public void setSanidad(int sanidad) {
        this.sanidad = sanidad;
    }

    public int getValentia() {
        return valentia;
    }

    public void setValentia(int valentia) {
        this.valentia = valentia;
    }

    public boolean isTrauma() {
        return trauma;
    }

    public void setTrauma(boolean trauma) {
        this.trauma = trauma;
    }
}
