import java.sql.*;

public class LecturaConcurrente extends Thread{
    private static String DB_CONNECTION = "jdbc:mysql://localhost/BBDD_PSP_1";
    private static String USER_NAME = "DAM2020_PSP";
    private static String USER_PASSWORD= "DAM2020_PSP";

    private int leerStart;
    private int leerMax;
    private int ingresosThread;

    public LecturaConcurrente(int leerStart, int leerMax){
        this.leerStart = leerStart;
        this.leerMax = leerMax;
    }

    @Override
    public void run() {
        super.run();
        leerEmpleados(this.leerStart, this.leerMax);
    }

    private synchronized void leerEmpleados(int leerStart, int leerMax){
        try {
            int sumaIngresos = 0;
            Connection connection = DriverManager.getConnection(DB_CONNECTION, USER_NAME, USER_PASSWORD);
            Statement consulta = connection.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT * FROM EMPLEADOS WHERE ID BETWEEN " + (leerStart+1) + " AND " + leerMax + ";");
            //System.out.println("Thread: " + Thread.currentThread().getName() + " start " + leerStart + " max " + leerMax);
            while(registro.next()){
               System.out.println("ID: " + registro.getInt("ID") + " | Email: " + registro.getString("EMAIL") + " | Ingresos: " + registro.getInt("INGRESOS"));
               sumaIngresos += registro.getInt("INGRESOS");
            }
            this.ingresosThread = sumaIngresos;
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getIngresosThread() {
        return ingresosThread;
    }
}
