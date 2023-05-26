import java.util.ArrayList;
import java.util.Scanner;
//Yosthim Francisco Enciso Aparco - 20201497

public class Main {
    public static void main(String[] args) {

        System.out.print("Bienvenido a ZombieWeb, donde la mayor pesadilla esta por llegar");
        System.out.print("En la primera etapa vas a configurar tu partida y en la segunda los zombies van a ir a por ti ¡buahaha!!");
        boolean finInicio = false;
        boolean finjuego = false;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Objeto> objetos = new ArrayList<>();
        ArrayList<Ente> entes = new ArrayList<>();
        AppController controller = new AppController();



        while (!finInicio) {
            System.out.println("Elige la opción que desees: ");
            System.out.println("1. Añadir objetos a la lista");
            System.out.println("2. Añadir un superviviente");
            System.out.println("3. Añadir un zombie");
            System.out.println("5. Empezar el juego");

            String opcion = scanner.nextLine();

            switch (opcion){
                case "1" -> controller.agregarObjeto(objetos);
                case "2" -> controller.agregarSuperviviente(entes,objetos);
                case "3" -> controller.agregarZombie(entes);
                case "5" -> {
                    int res = controller.verificarListaPura(entes);
                    switch (res){
                        case 0:
                            System.out.println("No se tienen elementos en la lista");
                            break;
                        case 1:
                            System.out.println("No se tienen supervivientes");
                            break;
                        case 2:
                            System.out.println("No se tienen zombies");
                            break;
                        case 3:
                            finInicio = true;
                            break;
                    }

                }
            }
        }

        while (!finjuego)
        {
            //Se obtienen el superviviente y zombie que pelearan
            Superviviente supervivienteActivo = controller.obtenerSupervivienteMasValiente(entes);

            Zombie zombieActivo = controller.obtenerZombieMasDebil(entes);


            //Prueba realizada para corroborar que se eligan los entes correctos
            /*System.out.println(supervivienteActivo.getNombre());
            System.out.println(zombieActivo.getNombre());
            finjuego = true;*/

            //Validaciones
            if (supervivienteActivo == null) {//Ganan los zombies
                System.out.println("No queda ningún superviviente vivo. Los zombies arrasaron con el mundo. GAME OVER!");
                finjuego = true;
            } else if (zombieActivo == null) {//Ganan los supervivientes
                System.out.println("No queda ningún zombie, el mundo se ha salvado de esta crisis; pero aún hay mucho que trabajar :)");
                finjuego = true;
            } else {//Continua la batalla
                System.out.println("Juego iniciado");
                System.out.println(supervivienteActivo.getNombre() + " ha decidido pelear");//Superviviente elegido
                System.out.println("Se realizara el combate contra el zombie " + zombieActivo.getNombre());//Zombie elegido
                //Inicio de la batalla
                boolean terminoBatalla = false;
                p: while (!terminoBatalla) {//La batalla se repite hasta que uno muera
                    //El más rápido ataca primero
                    if (supervivienteActivo.getVelocidad() > zombieActivo.getVelocidad()) {//Superviviente ataca primero
                        double poderSup = supervivienteActivo.getPoder();
                        controller.atacarZombie(poderSup, zombieActivo);
                        if (zombieActivo.getVida() > 0) {//El zombie sigue con vida, se defiende
                            double poderZom = zombieActivo.getFuerza();
                            controller.defenderseDeZombie(poderZom, supervivienteActivo);
                            if (supervivienteActivo.getVida() <= 0) {//El superviviente murió :(
                                zombieActivo.declararVictoria();
                                entes.remove(supervivienteActivo);//Se elimina al superviviente de la lista
                                String nomSup = supervivienteActivo.getNombre();
                                int kills = supervivienteActivo.getKills();
                                System.out.println("Superviviente " + nomSup + " ha muerto, habiendo aniquilado " + kills + " zombies");
                                System.out.println("Zombie " + zombieActivo.getNombre() + " aun le queda " + zombieActivo.getVida() + " puntos de vida");
                                terminoBatalla = true;//la batalla termina
                            }
                        }else {//El zombie fue vencido
                            supervivienteActivo.declararVictoria();
                            System.out.println("Zombie " + zombieActivo.getNombre() + " ha perecido, victoria para los supervivientes");
                            entes.remove(zombieActivo);//Se elimina el zombie de la lista
                            int kills = supervivienteActivo.getKills() + 1;
                            supervivienteActivo.setKills(kills);//Aumentamos las kills del superviviente en uno
                            //El superviviente se cura con uno de sus objetos
                            supervivienteActivo.curarse();
                            System.out.println("Superviviente " + supervivienteActivo.getNombre() + " aun le queda " + supervivienteActivo.getVida() + " puntos de vida");
                            terminoBatalla = true;//Termina la batalla
                        }
                    }else {//Zombie ataca primero
                        double poderZom = zombieActivo.getFuerza();
                        controller.defenderseDeZombie(poderZom, supervivienteActivo);
                        if (supervivienteActivo.getVida() <= 0) {//El superviviente murió :(
                            zombieActivo.declararVictoria();
                            entes.remove(supervivienteActivo);//Se elimina al superviviente de la lista
                            String nomSup = supervivienteActivo.getNombre();
                            int kills = supervivienteActivo.getKills();
                            System.out.println("Superviviente " + nomSup + " ha muerto, habiendo aniquilado " + kills + " zombies");
                            System.out.println("Zombie " + zombieActivo.getNombre() + " aun le queda " + zombieActivo.getVida() + " puntos de vida");
                            terminoBatalla = true;//Termina la batalla
                        }else {//Superviviente sigue con vida, se defiende y ataca al zombie
                            double poderSup = supervivienteActivo.getPoder();
                            controller.atacarZombie(poderSup, zombieActivo);
                            if (zombieActivo.getVida() <= 0) {//Zombie fue vencido
                                supervivienteActivo.declararVictoria();
                                //Se elimina el zombie de la lista
                                System.out.println("Zombie " + zombieActivo.getNombre() + " ha perecido, victoria para los supervivientes");
                                entes.remove(zombieActivo);
                                int kills = supervivienteActivo.getKills() + 1;
                                supervivienteActivo.setKills(kills);//Aumentamos las kills del superviviente en uno
                                //El superviviente se cura con uno de sus objetos
                                supervivienteActivo.curarse();
                                System.out.println("Superviviente " + supervivienteActivo.getNombre() + " aun le queda " + supervivienteActivo.getVida() + " puntos de vida");
                                terminoBatalla = true;//termina la batalla
                            }
                        }
                    }
                }
            }
        }
    }

}