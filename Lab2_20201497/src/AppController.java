import java.util.ArrayList;
import java.util.Scanner;

public class AppController {

    Scanner sc = new Scanner(System.in);
    public void agregarObjeto(ArrayList<Objeto> listaObj)
    {
        //Completar
        //Pedidos y opciones
        System.out.println("Indique el nombre del objeto");
        String nombreObj = sc.nextLine();
        System.out.println("Indique el uso del objeto");
        String useObj = sc.nextLine();
        System.out.println("Elija el tipo de objeto");
        System.out.println("1. arma");
        System.out.println("2. objeto curativo");
        System.out.println("3. otro");
        System.out.print("Opción: ");
        String opcion = sc.nextLine();

        switch(opcion) {
            case "1":
                while(true) {
                    System.out.println("Indique el poder del arma");
                    String pw = sc.nextLine();
                    double power = Double.parseDouble(pw);
                    if (power > 0 & power < 1000) {
                        Arma arma = new Arma(nombreObj, useObj, power);//Se crea nuevo objeto de la Clase Arma usando constructor
                        listaObj.add(arma);//se añade el arma a la lista de objetos
                        break;
                    } else {
                        System.out.println("El poder debe ser un valor entre 0 y 1000!");
                    }
                }
                break;
            case "2":
                System.out.println("Indique la vida que restaura el objeto");
                String vr = sc.nextLine();
                int vidaRes = Integer.parseInt(vr);
                ObjetoCurativo objCur = new ObjetoCurativo(nombreObj, useObj, vidaRes);//Se crea nuevo objeto de la Clase ObjetoCurativo usando constructor
                listaObj.add(objCur);//Se añade el objeto curativo a la lista de objetos
                break;
            case "3":
                Objeto object = new Objeto(nombreObj, useObj);
                listaObj.add(object);//Se añade a la lista de objetos
                break;
        }
    }

    public void agregarZombie(ArrayList<Ente> listaEntes)
    {
        //Completar
        Zombie zombie = new Zombie();//Se crea un objeto de la clase Zombie
        System.out.println("Indique el nombre del zombie");
        String nombreZom = sc.nextLine();
        zombie.setNombre(nombreZom);//Se setea el nombre

        System.out.println("Indique la fuerza del zombie");
        String fc = sc.nextLine();
        double force = Double.parseDouble(fc);
        zombie.setFuerza(force);//Se setea la fuerza

        System.out.println("Indique la defensa del zombie");
        String df = sc.nextLine();
        double defense = Double.parseDouble(df);
        zombie.setDefensa(defense);//Seteo de la defensa

        System.out.println("Indique la velocidad del zombie");
        String vc = sc.nextLine();
        double velocity = Double.parseDouble(vc);
        zombie.setVelocidad(velocity);//seteo de la velocidad

        System.out.println("Indique la vida del zombie");
        String life = sc.nextLine();
        double lifePoints = Double.parseDouble(life);
        zombie.setVida(lifePoints);//seteo de la vida

        System.out.println("Indique una frase de victoria que diría: ");
        String victory = sc.nextLine();
        zombie.setFraseVictoria(victory);//seteo de la frase de victoria

        //Añadir zombie a lista
        listaEntes.add(zombie);//Se añade a la lista de entes
        //mensaje de confirmacion
        System.out.println("Zombie añadido con nombre: " + nombreZom);

    }

    public void agregarSuperviviente(ArrayList<Ente> listaEntes,ArrayList<Objeto> objetos)
    {
        //Completar
        Superviviente survivor = new Superviviente();//Se crea nuevo objeto de la clase Superviviente
        System.out.println("Indique el nombre del superviviente");
        String nombreSup = sc.nextLine();
        survivor.setNombre(nombreSup);//seteo del nombre

        System.out.println("Indique la fuerza del superviviente");
        String fc = sc.nextLine();
        double force = Double.parseDouble(fc);
        survivor.setFuerza(force);//seteo de la fuerza

        System.out.println("Indique la defensa del superviviente");
        String df = sc.nextLine();
        double defense = Double.parseDouble(df);
        survivor.setDefensa(defense);//seteo de la defensa

        System.out.println("Indique la velocidad del superviviente");
        String vc = sc.nextLine();
        double velocity = Double.parseDouble(vc);
        survivor.setVelocidad(velocity);//seteo de la velocidad

        System.out.println("Indique la vida del superviviente");
        String life = sc.nextLine();
        double lifePoints = Double.parseDouble(life);
        survivor.setVida(lifePoints);//seteo de la vida

        System.out.println("Indique la valentía del superviviente");
        String br = sc.nextLine();
        int brave = Integer.parseInt(br);
        survivor.setValentia(brave);//seteo de la valentia

        System.out.println("Indique una frase de victoria que diría: ");
        String victory = sc.nextLine();
        survivor.setFraseVictoria(victory);//seteo de la frase de victoria

        System.out.println("Indique la cantidad de objetos que tendrá");
        String num = sc.nextLine();
        int numObj = Integer.parseInt(num);
        ArrayList<Objeto> inventario = new ArrayList<>();
        //Se añaden numobj objetos aleatoriamente al iventario
        for(int i = 0; i<numObj; i++) {
            double objAleatorio = Math.random()*objetos.size();
            int index = (int) objAleatorio;
            System.out.println(objetos.get(index).getNombre() + " añadido");
            inventario.add(objetos.get(index));
        }
        survivor.setInventario(inventario);//seteo del inventario

        //Cálculo de sanidad
        int sanidad = (int) ((255 - force) - defense);
        survivor.setSanidad(sanidad);//seteo de la sanidad

        //Agregar superviviente a la lista
        listaEntes.add(survivor);

    }


    public int verificarListaPura(ArrayList<Ente> lista){

        //Tu mismo eres
        if (lista.size() == 0) {
            return 0;//Caso donde no hay entes registrados
        }else {
            int numSup = 0;
            int numZom = 0;
            for (Ente ente : lista) {
                if (ente instanceof Superviviente) {
                    numSup++;//incrementa la cuenta de supervivientes
                } else if (ente instanceof Zombie) {
                    numZom++;//incrementa la cuenta de zombies
                }
            }
            if (numSup == 0) {
                return 1;//Caso donde no hay supervivientes en la lista
            } else if (numZom == 0) {
                return 2;//Caso donde no hay zombies en la lista
            } else {
                return 3;//Puede iniciar el juego
            }
        }

    }

    public Superviviente obtenerSupervivienteMasValiente(ArrayList<Ente> lista){
        //Tu vas por el 20
        int cantSup = 0;//Variable para contar los supervivientes
        Superviviente supElegido = null;//Variable donde se guardará el superviviente elegido en caso se encuentre
        for(int i = 0; i<lista.size(); i++) {
            Ente ente = lista.get(i);
            if (ente instanceof Superviviente) {
                cantSup++;
                if (cantSup == 1) {
                    supElegido = (Superviviente) ente;//Se elige al primer superviviente encontrado
                }else {
                    if (supElegido.getValentia() < ((Superviviente) ente).getValentia()) {
                        supElegido = (Superviviente) ente;//Si luego se encuentra un superviviente más valiente pasa a ser elegido
                    }
                }
            }
        }
        return supElegido;//retorna null en caso no se encuentren supervivientes en la lista de entes
    }

    public Zombie obtenerZombieMasDebil(ArrayList<Ente> lista){
        //Sigue asi tu puedes!!
        int cantZom = 0;//Variable para contar zombies de la lista
        Zombie zombElegido = null;//variable que guardara al zombie elegido en caso exista
        for(int i = 0; i<lista.size(); i++) {
            Ente ente = lista.get(i);
            if (ente instanceof Zombie) {
                cantZom++;
                if (cantZom == 1) {
                    zombElegido = (Zombie) ente;//Se elige al primer zombie encontrado
                }else {
                    if (zombElegido.getFuerza() > ((Zombie) ente).getFuerza()) {
                        zombElegido = (Zombie) ente;//Si luego se encuentra un zombie más débil pasa a ser elegido
                    }
                }
            }
        }
        return zombElegido;//retorna null en caso no hayan zombies en la lista de entes
    }

    public void atacarZombie(double poder, Zombie zombie)
    {
        //Ya falta poco
        //Daño que hace el superviviente
        //si la defensa es mayor o igual al poder le hará poco daño al zombie (para evitar peleas infinitas)
        double vidaActu = zombie.getVida() - Math.max(poder - zombie.getDefensa(), 5);/*Se cambió de 0 a 5 para evitar que peleen infinitamente en caso
        ambos tengan defensas superiores al poder de ataque*/
        //Mensajes en consola
        double vidaPerdida = zombie.getVida() - vidaActu;
        if (vidaPerdida == 5) {//Cuando le hizo poco daño
            System.out.println("Oh no! El ataque generó poco daño :(");
        }else {//Cuando le hace daño
            System.out.println("Ataque efectivo! " + zombie.getNombre() + " perdió " + vidaPerdida + " puntos de vida");
        }
        //Verificación de la vida perdida para comprobar si se rompe o no la defensa del zombie
        if (vidaPerdida/zombie.getVida() < 1/10) {//Rompe la defensa si la vida perdida es menor al 10% de la vida total
            System.out.println("La defensa de " + zombie.getNombre() + " se rompió!");
            double defensaNueva = (3/4)*zombie.getDefensa();//Se reduce en un 25%
            zombie.setDefensa(defensaNueva);
        }
        //Actualización de la vida del zombie
        zombie.setVida(vidaActu);
    }

    public void defenderseDeZombie(double poder, Superviviente superviviente)
    {
        //Veo un futuro 20
        System.out.println("El zombie ataca!");
        //Daño que hace el zombie, si su fuerza es menor a la defensa del superviviente le hace poco daño
        double vidaActu = superviviente.getVida() - Math.max(poder - superviviente.getDefensa(), 5);/*Se cambió a 5 para evitar que peleen infinitamente en caso
        ambos tengan defensas superiores al poder de ataque*/
        double vidaPerdida = superviviente.getVida() - vidaActu;
        //Mensajes de consola
        if (vidaPerdida == 5) {//El zombie hizo poco daño
            System.out.println("Buena defensa! El ataque generó poco daño");
        }else {//El zombie hizo daño
            System.out.println("Oh no! " + superviviente.getNombre() + " perdió " + vidaPerdida + " puntos de vida");
        }
        //Variable de sorteo
        double sorteo = Math.random();//Número aleatorio entre 0 y 1

        if (sorteo < 0.5) {//50% de probabilidad de asustar al superviviente
            int sanidadNueva = (int) (superviviente.getSanidad() - superviviente.getFuerza());
            superviviente.setSanidad(sanidadNueva);//seteo de nueva sanidad
        }
        //Validación de la sanidad para verificar que el superviviente quedó traumado o no
        if (superviviente.getSanidad() < 0) {
            System.out.println(superviviente.getNombre() + ": Ha quedado traumado");
            superviviente.setTrauma(true);
        }
        //Actualizar vida del superviviente
        superviviente.setVida(vidaActu);
    }

}
