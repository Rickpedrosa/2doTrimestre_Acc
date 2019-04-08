package jdbc.exec14;

import entidades.Curso;
import entidades.Tramohorario;
import jdbc.BD.Database;
import utils.JDBCUtils;
import entidades.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main14 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        try {
            Profesor profesor = JDBCUtils.getProfesor(database);
            Tramohorario tramo = JDBCUtils.getTramo(database, "LUNES", 6);

            String select = "SELECT rep.*";
            String from_join1 = "FROM reparto rep INNER JOIN horario hor ON " +
                    "(rep.codOe = hor.codOE) AND (rep.codCurso = hor.codCurso) AND (rep.codAsig = hor.codAsig) ";
            String where = "WHERE rep.codProf LIKE '" + profesor.getCodProf() + "' AND hor.codTramo LIKE '" + tramo.getCodTramo() + "'";
            String sql = select.concat(from_join1).concat(where);

            Curso curso = null;
            String codAsig = "";
            String asig = "";

            ResultSet rs_tramo = database.select(sql);
            if (rs_tramo.next()) {
                curso = new Curso(rs_tramo.getString(1), rs_tramo.getString(2));
                codAsig = rs_tramo.getString(3);
            }
            rs_tramo.close();

            ResultSet rs_asig = database.select("SELECT nombre FROM asignatura WHERE codAsig LIKE '" + codAsig + "'");
            if (rs_asig.next()) {
                asig = rs_asig.getString(1);
            }
            rs_asig.close();

            //noinspection ConstantConditions
            System.out.printf("%s %s está los %s (de %s a %s) impartiendo %s para el curso %s %s\n",
                    profesor.getNombre(),
                    profesor.getApellidos(),
                    tramo.getDia(),
                    tramo.getHora_inicio().toString(),
                    tramo.getHora_fin().toString(),
                    asig,
                    curso.getCodCurso(),
                    curso.getCodOe());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();

    }
}
