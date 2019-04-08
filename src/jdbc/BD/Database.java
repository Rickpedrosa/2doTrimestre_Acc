package jdbc.BD;

import java.sql.*;

public class Database {

    private Connection connection;
    private Statement statement;

    public static final String DB_HORARIO = "horario";
    public static final String DB_FUTHEAD = "futhead";

    public Database(String db_name) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db_name + "?allowMultiQueries=true",
                    "root", "");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connected to " + db_name);
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection to database closed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDatabase(String updateQuery) {
        //For insert, update, delete...
        try {
            System.out.println(updateQuery);
            int affected = statement.executeUpdate(updateQuery);
            System.out.println("Query executed successfully -> " + affected + " rows affected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String query) throws SQLException {
        return statement.executeQuery(query);
    }

}
