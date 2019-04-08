package jdbc.exec10;

import jdbc.BD.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main10 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        try {
            listCurses(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }

    private static void listCurses(Database database) throws SQLException {
        String sql = "SELECT c.codCurso, of.nombre, p.nombre, p.apellidos FROM curso c INNER JOIN ofertaeducativa of ON c.codOE = of.codOE " +
                "INNER JOIN profesor p ON p.codProf = c.codTutor";
        ResultSet data_cursos = database.select(sql);
        while (data_cursos.next()) {
            System.out.printf("[%s] -> %s tutorado por %s %s\n", data_cursos.getString(1),
                    data_cursos.getString(2),
                    data_cursos.getString(3),
                    data_cursos.getString(4));
        }
        data_cursos.close();
    }
}
