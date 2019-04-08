package jdbc.exec12;

import jdbc.BD.Database;
import utils.JDBCUtils;
import entidades.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Main12 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);

        try {
            Profesor profesor = JDBCUtils.getProfesor(database);
            String sql = "SELECT asig.codAsig, asig.nombre FROM asignatura asig INNER JOIN reparto r ON r.codAsig = asig.codAsig INNER JOIN " +
                    "profesor p ON p.codProf = r.codProf WHERE p.codProf LIKE '" + profesor.getCodProf() + "'";

            ResultSet rs = database.select(sql);
            System.out.printf("[%s] %s %s imparte las siguientes asignaturas:\n",
                    profesor.getCodProf(), profesor.getNombre(), profesor.getApellidos());
            while (rs.next()) {
                System.out.printf("[%s] %s\n", rs.getString(1), rs.getString(2));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.closeConnection();
    }
}
