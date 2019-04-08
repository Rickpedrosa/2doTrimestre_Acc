package jdbc.exec18;

import entidades.Asignatura;
import entidades.Curso;
import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Main18 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        try {
            Curso curso = JDBCUtils.getCurso(database);
            Asignatura asignatura = JDBCUtils.getAsignatura(database);
            String sql = "{call getHorasSemanales(?, ?, ?, ?)}";

            CallableStatement callableStatement = database.getConnection().prepareCall(sql);
            callableStatement.setString(1, curso.getCodOe());
            callableStatement.setString(2, curso.getCodCurso());
            callableStatement.setString(3, asignatura.getCodAsig());
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.executeUpdate();

            System.out.println(callableStatement.getString(1));

            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }
}
