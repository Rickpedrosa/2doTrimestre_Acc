package jdbc.exec17;

import entidades.Curso;
import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Main17 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);
        try {
            Curso curso = JDBCUtils.getCurso(database);
            String sql = "{? = call getTutor(?, ?)}";

            CallableStatement callableStatement = database.getConnection().prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2, curso.getCodOe());
            callableStatement.setString(3, curso.getCodCurso());
            callableStatement.executeUpdate();

            System.out.printf("%s es tutor(a) de %s %s\n", callableStatement.getString(1),
                    curso.getCodOe(), curso.getCodCurso());

            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }
}
