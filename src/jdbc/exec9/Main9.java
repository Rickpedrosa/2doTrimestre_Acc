package jdbc.exec9;

import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

public class Main9 {
    public static void main(String[] args) {
        try {
            Database database = new Database(Database.DB_HORARIO);
            String sql = "SELECT p.*, c.codOe, c.codCurso FROM profesor p LEFT JOIN curso c ON c.codTutor = p.codProf ";
            ResultSet rs = database.select(sql);
            ResultSetMetaData meta = rs.getMetaData();
            Map<Integer, String> jdbcMapping = JDBCUtils.getAllJDBCTypeNames();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("Columna %s(%s) | Nullable: %s | Máx. ancho = %d\n",
                        meta.getColumnName(i), jdbcMapping.get(meta.getColumnType(i)),
                        (nullOrWhat(meta.isNullable(i)) ? "Sí" : "No"), meta.getColumnDisplaySize(i));
            }
            rs.close();

            database.closeConnection();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static boolean nullOrWhat(int n) {
        return n == 1;
    }
}
