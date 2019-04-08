package jdbc.exec4;

import jdbc.BD.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@SuppressWarnings("SameParameterValue")
public class Main4 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        try {
            executeScriptNewCurse(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }

    private static void executeScriptNewCurse(Database database) throws SQLException {
        addTeacher(database, "DAS", "Daniel", "Ayala Soriano", Timestamp.valueOf(LocalDateTime.now()));
        addSubject(database, "OACE", "Operaciones auxiliares para la configuración y la explotación", 7, 245);
        addSubject(database, "MONTSC", "Montaje y mantenimiento de sistemas y componentes informáticos", 9, 315);
        addCurse(database, "FPB", "1A", "DAS");
        addReparto(database, "FPB", "1A", "OACE", "DAS");
        addReparto(database, "FPB", "1A", "MONTSC", "MGD");
    }

    private static void addReparto(Database database, String codoe, String cod_curso, String cod_asig, String cod_prof) throws SQLException {
        String sql = "INSERT INTO reparto VALUES (?, ?, ?, ?)";

        PreparedStatement insert = database.getConnection().prepareStatement(sql);
        insert.setString(1, codoe);
        insert.setString(2, cod_curso);
        insert.setString(3, cod_asig);
        insert.setString(4, cod_prof);
        insert.executeUpdate();
        System.out.println("Nuevo reparto añadido");
        insert.close();
    }

    private static void addCurse(Database database, String codoe, String cod_curso, String cod_tutor) throws SQLException {
        String sql = "INSERT INTO curso VALUES (?, ?, ?)";

        PreparedStatement insert = database.getConnection().prepareStatement(sql);
        insert.setString(1, codoe);
        insert.setString(2, cod_curso);
        insert.setString(3, cod_tutor);
        insert.executeUpdate();
        System.out.println("Curso añadido");
        insert.close();
    }

    private static void addSubject(Database database, String cod_asig, String nombre, int horas_semanales, int horas_totales) throws SQLException {
        String sql = "INSERT INTO asignatura VALUES (?, ?, ?, ?)";

        PreparedStatement insert = database.getConnection().prepareStatement(sql);
        insert.setString(1, cod_asig);
        insert.setString(2, nombre);
        insert.setInt(3, horas_semanales);
        insert.setInt(4, horas_totales);
        insert.executeUpdate();
        System.out.println("Asignatura " + nombre + " añadida");
        insert.close();
    }

    private static void addTeacher(Database database, String cod_prof, String nombre, String apellidos, Timestamp fecha_alta) throws SQLException {
        String sql = "INSERT INTO profesor VALUES (?, ?, ?, ?)";

        PreparedStatement insert = database.getConnection().prepareStatement(sql);
        insert.setString(1, cod_prof);
        insert.setString(2, nombre);
        insert.setString(3, apellidos);
        insert.setTimestamp(4, fecha_alta);
        insert.executeUpdate();
        System.out.println("Profesor " + nombre + " " + apellidos + " añadido");
        insert.close();
    }
}
