package jdbc.exec7;

import jdbc.BD.Database;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Main7 {
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
        String sql = "SELECT * FROM profesor ORDER BY apellidos ASC, fechaAlta DESC";
        ResultSet rs = database.select(sql);
        System.out.println("Profesores:");
        while (rs.next()) {
            System.out.printf("[%s] %s %s empezó a trabajar en el IES Saladillo el %s\n", rs.getString(1),
                    rs.getString(2), rs.getString(3), rs.getTimestamp(4).toString());
        }
        rs.close();
        System.out.println("Valdivieso el travieso jejeje");
    }
}
