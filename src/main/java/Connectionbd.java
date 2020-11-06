import java.sql.*;

public class Connectionbd {
    private static String DB_CONNECTION = "jdbc:mysql://localhost/BBDD_PSP_1";
    private static String USER_NAME = "DAM2020_PSP";
    private static String USER_PASSWORD= "DAM2020_PSP";

    public void leerEmpleados() {
        try {
            long start = System.currentTimeMillis();

            int sumaIngresos = 0;
            Connection connection = DriverManager.getConnection(DB_CONNECTION, USER_NAME, USER_PASSWORD);
            Statement consulta = connection.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT * FROM EMPLEADOS;");
            while(registro.next()){
                System.out.println("ID: " + registro.getInt("ID") + " | Email: " + registro.getString("EMAIL") + " | Ingresos: " + registro.getInt("INGRESOS"));
                sumaIngresos += registro.getInt("INGRESOS");
            }
            System.out.println("Ingresos totales: " + sumaIngresos);
            connection.close();

            //TIEMPO
            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("Ha tardado " + time + " milisegundos");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int totalRegistros() {
        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION, USER_NAME, USER_PASSWORD);
            Statement consulta = connection.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT count(*) FROM EMPLEADOS;");
            int  countEmpleados = 0;
            if (registro.next()){
                countEmpleados = registro.getInt("count(*)");
            }
            connection.close();
            return countEmpleados;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
