package jdbc.exec16;

import jdbc.BD.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main16 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        String sql = "SELECT asig.*, count(rep.codCurso), count(rep.codOe) " +
                "FROM asignatura asig INNER JOIN reparto rep ON asig.codAsig = rep.codAsig " +
                "WHERE asig.horasSemanales >= 3 " +
                "GROUP BY asig.codAsig " +
                "ORDER BY asig.horasSemanales DESC";

        try (ResultSet rs = database.select(sql)) {
            System.out.println("Información de asignaturas con más de 3 horas semanales:");
            while (rs.next()) {
                System.out.printf("[%s] %s:\n", rs.getString(1), rs.getString(2));
                System.out.printf("Número de horas a la semana: %-10s\n", rs.getString(3));
                System.out.printf("Número de cursos: %-10s\n", rs.getString(5));
                System.out.printf("Número de ofertas educativas: %-10s\n", rs.getString(6));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.closeConnection();
    }
}
