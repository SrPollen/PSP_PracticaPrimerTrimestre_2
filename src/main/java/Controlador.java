
public class Controlador {
    public static void main(String[] args) {
        Connectionbd conbd = new Connectionbd();
        Vista vista = new Vista();
        int opcion;
        do{
            System.out.println("1. Leer tabla de forma secuencial");
            System.out.println("2. Leer tabla de forma concurrente");
            System.out.println("0. Salir");

            opcion = vista.pedirNumero();
            switch (opcion){
                case 1:
                    conbd.leerEmpleados();
                    break;
                case 2:
                    vista.startLecturaConcurrente();
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }while(opcion != 0);
    }
}
