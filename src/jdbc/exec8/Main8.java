package jdbc.exec8;

import jdbc.BD.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main8 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        try {
            listTeachers(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }

    private static void listTeachers(Database database) throws SQLException {
        String sql = "SELECT p.*, c.codOe, c.codCurso FROM profesor p LEFT JOIN curso c ON c.codTutor = p.codProf ";
        ResultSet rs = database.select(sql);
        System.out.println("Profesores:");
        while (rs.next()) {
            if (rs.getString(5) == null) {
                System.out.printf("[%s] %s %s empezó a trabajar en el IES Saladillo el %s ", rs.getString(1),
                        rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4).toString().substring(0, 10));
                System.out.println("y no es tutor(a) de ningún curso");
            } else {
                System.out.printf("[%s] %s %s empezó a trabajar en el IES Saladillo el %s ", rs.getString(1),
                        rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4).toString().substring(0, 10));
                System.out.printf("y es tutor(a) de %s %s\n", rs.getString(6), rs.getString(5));
            }

        }
        rs.close();
    }
}
