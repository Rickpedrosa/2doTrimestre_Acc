package jdbc.exec1;

import jdbc.BD.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

//Realiza un programa que ejecute el script de la jdbc.BD Horario.
//Batch -> 2383 mls
//ExecuteUpdate -> 2922mls

public class Main1 {
    public static void main(String[] args) {
        //crearHorarioExecuteUpdateVersion();
    }

    @SuppressWarnings("unused")
    private static void createHorarioBatchVersion() {
        long start_time = System.currentTimeMillis();
        Database database = new Database(Database.DB_HORARIO);
        try {
            Statement batch = database.getConnection().createStatement();

            BufferedReader bufferedReader = new BufferedReader(new FileReader("files/2018-2019 script_horario.sql"));
            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();

            while (line != null) {
                if (!line.contains(";") && !line.isEmpty()) {
                    stringBuilder.append(line.concat("\n"));
                } else if (line.contains(";")) {
                    stringBuilder.append(line.replace(";", "").concat("\n"));
                    batch.addBatch(stringBuilder.toString());
                    System.out.println(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            batch.executeBatch();
            System.out.println("Base de datos creada");
            database.closeConnection();
            long stop_time = System.currentTimeMillis();
            System.out.println("Tiempo de ejecución: " + (stop_time - start_time) + "mls");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private static void crearHorarioExecuteUpdateVersion() {
        long start_time = System.currentTimeMillis();
        Database database = new Database(Database.DB_HORARIO);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("files/2018-2019 script_horario.sql"));
            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();

            while (line != null) {
                if (!line.contains(";") && !line.isEmpty()) {
                    stringBuilder.append(line.concat("\n"));
                } else if (line.contains(";")) {
                    stringBuilder.append(line.concat("\n"));
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            System.out.println(stringBuilder.toString());
            database.updateDatabase(stringBuilder.toString());
            System.out.println("Base de datos creada");
            database.closeConnection();
            long stop_time = System.currentTimeMillis();
            System.out.println("Tiempo de ejecución: " + (stop_time - start_time) + "mls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
