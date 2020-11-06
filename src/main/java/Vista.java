import java.util.ArrayList;
import java.util.Scanner;

public class Vista {

    public void startLecturaConcurrente(){
        Connectionbd conbd = new Connectionbd();

        long start = System.currentTimeMillis();

        int totalIngresos = 0;
        int hilos = 5;
        int registros = conbd.totalRegistros();
        int rango = registros / hilos;
        int leerStart = 0;
        int leerMax = rango;

        int diferencia = registros - (rango * hilos);

        ArrayList<LecturaConcurrente> arrayLectura = new ArrayList<>();
        for (int i = 0; i < hilos ; i++){
            if(diferencia > 0) {
                leerMax +=1;
                diferencia--;
            }
            arrayLectura.add(new LecturaConcurrente(leerStart, leerMax));
            leerStart = leerMax;
            leerMax += rango;
        }

        for (int i = 0; i < arrayLectura.size() ; i++){
            try {
                arrayLectura.get(i).start();
                arrayLectura.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < arrayLectura.size(); i++) {
            totalIngresos += arrayLectura.get(i).getIngresosThread();
        }
        System.out.println("Total ingresos: " +  totalIngresos);

        //TIEMPO
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("Ha tardado " + time + " segundos");
    }

    public int pedirNumero(){
        Scanner sc = new Scanner(System.in);
        String strNumero = sc.nextLine();
        try {
            int num = Integer.parseInt(strNumero);
            return num;
        } catch (NumberFormatException e){
            return -1;
        }
    }
}
