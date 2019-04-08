package jdbc.exec11;

import jdbc.BD.Database;
import entidades.Asignatura;
import entidades.Curso;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main11 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);

        try {
            Asignatura asignatura = JDBCUtils.getAsignatura(database);
            Curso curso = JDBCUtils.getCurso(database);
            String sql = "SELECT tr.* FROM horario h INNER JOIN tramohorario tr ON h.codTramo = tr.codTramo" +
                    " WHERE h.codAsig LIKE ? AND h.codOe LIKE ? AND h.codCurso LIKE ?";

            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ps.setString(1, asignatura.getCodAsig());
            ps.setString(2, curso.getCodOe());
            ps.setString(3, curso.getCodCurso());

            ResultSet rs = ps.executeQuery();

            System.out.printf("%s del curso %s %s se imparte los: \n", asignatura.getNombreAsig(), curso.getCodCurso(), curso.getCodOe());
            while (rs.next()) {
                System.out.printf("%s de %s a %s\n", rs.getString(4), rs.getString(2), rs.getString(3));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }


}
