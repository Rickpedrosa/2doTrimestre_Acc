package jdbc.exec2;

import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Nombre de la tabla -> ");
        String tname = teclado.next();
        teclado.close();
        try {
            metaDataFromTable(tname);
        } catch (SQLSyntaxErrorException ex) {
            System.out.println("La tabla " + tname + " no existe en la base de datos");
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void metaDataFromTable(String table_name) throws SQLException, IllegalAccessException {
        Database database = new Database(Database.DB_HORARIO);
        DatabaseMetaData metaData = database.getConnection().getMetaData();
        //info de columnas de tabla, primary keys, foreign keys, foreign keys usadas por primary key

        ResultSet columns_info = metaData.getColumns(null, null, table_name, null);
        ResultSet primarys = metaData.getPrimaryKeys(null, null, table_name);
        ResultSet foreign = metaData.getImportedKeys(null, null, table_name);
        //ResultSet foreign_usedby_prim = metaData.getExportedKeys(null, null, table_name);
        Map<Integer, String> jdbcMapping = JDBCUtils.getAllJDBCTypeNames();

        System.out.println("Información de las columnas de la tabla [" + table_name + "]:");
        while (columns_info.next()) {
            System.out.println("Nombre de columna: " + columns_info.getString("COLUMN_NAME"));
            System.out.println("Posición de columna: " + columns_info.getString("ORDINAL_POSITION"));
            System.out.println("Datatype de columna: " + jdbcMapping.get(columns_info.getInt("DATA_TYPE")));
            System.out.println("Tamaño de columna: " + columns_info.getString("COLUMN_SIZE"));
            System.out.println("Nulabilidad de columna: " + columns_info.getString("IS_NULLABLE"));
            System.out.println("*********************************************");
        }
        columns_info.close();

        System.out.println("\n");

        System.out.println("Información de claves primarias de la tabla [" + table_name + "]:");
        while (primarys.next()) {
            System.out.printf("El campo %s es clave primaria de la tabla %s \n", primarys.getString("COLUMN_NAME"),
                    primarys.getString("TABLE_NAME"));
            System.out.println("PK_NAME: " + primarys.getString("PK_NAME"));
            System.out.println("*********************************************");
        }
        primarys.close();

        System.out.println("\n");

        if (foreign.first()) {
            System.out.println("Información de claves ajenas de la tabla [" + table_name + "]:");
            while (foreign.next()) {
                System.out.printf("El campo %s es clave ajena de %s, proveniente de la tabla %s (%s)\n", foreign.getString("FKCOLUMN_NAME"),
                        foreign.getString("FKTABLE_NAME"), foreign.getString("PKTABLE_NAME"), foreign.getString("PKCOLUMN_NAME"));
                System.out.println("*********************************************");
            }
        } else {
            System.out.println("La tabla [" + table_name + "]" + " no tiene claves ajenas");
        }
        foreign.close();


        database.closeConnection();
    }
}
