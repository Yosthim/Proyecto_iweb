public class Arma extends Objeto{

    private double poder;
    private double valentia = 0;

    public Arma(String nombre, String descripcion, double poder) {//constructor usando super
        super(nombre, descripcion);
        this.poder = poder;
    }

    public Arma(){

    }

    public double getPoder() {
        return poder;
    }

    public void setPoder(double poder) {
        this.poder = poder;
    }

    public double getValentia() {
        return valentia;
    }

    public void setValentia(double valentia) {
        this.valentia = valentia;
    }
}
