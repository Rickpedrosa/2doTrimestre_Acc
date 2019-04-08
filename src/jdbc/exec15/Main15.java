package jdbc.exec15;

import entidades.Profesor;
import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main15 {
    public static void main(String[] args) {
        try {
            Database database = new Database(Database.DB_HORARIO);
            Profesor profesor = JDBCUtils.getProfesor(database);

            String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            String select = "SELECT rep.*, asig.nombre\n";
            String from_join1 = "FROM reparto rep INNER JOIN horario hor ON " +
                    "(rep.codOe = hor.codOE) AND (rep.codCurso = hor.codCurso) AND (rep.codAsig = hor.codAsig) \n";
            String from_join2 = "INNER JOIN tramohorario trm ON trm.codTramo = hor.codTramo \n";
            String from_join3 = "INNER JOIN asignatura asig ON rep.codAsig = asig.codAsig \n";
            String where = "WHERE rep.codProf LIKE '" + profesor.getCodProf() + "'" +
                    " AND '" + hora + "' BETWEEN trm.horaInicio AND trm.horaFin AND" +
                    " trm.dia LIKE '" + dayOfWeekESP() + "'\n";
            String sql = select.concat(from_join1).concat(from_join2).concat(from_join3).concat(where);

            System.out.println(sql);
            ResultSet rs = database.select(sql);
            System.out.println("Hora actual -> " + hora);
            if (rs.next()) {
                System.out.printf("%s %s está dando %s con %s %s\n",
                        profesor.getNombre(),
                        profesor.getApellidos(),
                        rs.getString(5),
                        rs.getString(2),
                        rs.getString(1));
            } else {
                System.out.printf("%s %s no está dando clase a las %s\n",
                        profesor.getNombre(),
                        profesor.getApellidos(),
                        hora);
            }

            rs.close();
            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String dayOfWeekESP() {
        switch (LocalDate.now().getDayOfWeek()) {
            case MONDAY:
                return "LUNES";
            case TUESDAY:
                return "MARTES";
            case WEDNESDAY:
                return "MIERCOLES";
            case THURSDAY:
                return "JUEVES";
            case FRIDAY:
                return "VIERNES";
            default:
                return null;
        }
    }
}
