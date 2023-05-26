public class ObjetoCurativo extends Objeto{

    private int vidaRestaurada;

    public ObjetoCurativo(String nombre, String descripcion, int vidaRestaurada) {//constructor usando super
        super(nombre, descripcion);
        this.vidaRestaurada = vidaRestaurada;
    }

    public int getVidaRestaurada() {
        return vidaRestaurada;
    }

    public void setVidaRestaurada(int vidaRestaurada) {
        this.vidaRestaurada = vidaRestaurada;
    }
}
