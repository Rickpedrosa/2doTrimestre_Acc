package jdbc.exec3;

import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class Main3 {
    public static void main(String[] args) {
//        try {
//            Database database = new Database(Database.DB_HORARIO);
//            insertOEV3(database, "FPB", "FP Básica Informática y comunicaciones",
//                    "La formación profesional básica de informática y...");
//            database.closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void insertOEV1(Database database, String oe, String nombre, String dscrp) throws SQLException {
        System.out.println(JDBCUtils.getRowsNumber(database, "ofertaeducativa"));
        ResultSet insert = database.select("SELECT * FROM ofertaeducativa");

        insert.moveToInsertRow();
        insert.updateString(1, oe);
        insert.updateString(2, nombre);
        insert.updateString(3, dscrp);
        insert.updateString(4, null);
        insert.updateString(5, null);
        insert.insertRow();

        System.out.println("Inserción realizada");
        insert.close();
        System.out.println(JDBCUtils.getRowsNumber(database, "ofertaeducativa"));
    }

    @SuppressWarnings("SameParameterValue")
    private static void insertOEV2(Database database, String oe, String nombre, String dscrp) throws SQLException {
        System.out.println(JDBCUtils.getRowsNumber(database, "ofertaeducativa"));

        String sql = "INSERT INTO ofertaeducativa VALUES ('" + oe + "', '" + nombre + "', '" + dscrp + "', null, null)";
        database.updateDatabase(sql);

        System.out.println(JDBCUtils.getRowsNumber(database, "ofertaeducativa"));
    }

    @SuppressWarnings("SameParameterValue")
    private static void insertOEV3(Database database, String oe, String nombre, String dscrp) throws SQLException {
        System.out.println(JDBCUtils.getRowsNumber(database, "ofertaeducativa"));

        String sql = "INSERT INTO ofertaeducativa VALUES (?, ?, ?, null, null)";
        PreparedStatement insert = database.getConnection().prepareStatement(sql);
        insert.setString(1, oe);
        insert.setString(2, nombre);
        insert.setString(3, dscrp);
        insert.executeUpdate();

        System.out.println("Inserción realizada");
        insert.close();

        System.out.println(JDBCUtils.getRowsNumber(database, "ofertaeducativa"));
    }
}