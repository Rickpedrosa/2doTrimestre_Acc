package jdbc.exec6;

import jdbc.BD.Database;

public class Main6 {
    public static void main(String[] args) {
        //Primero OE -> se va curso con él.
        //Borrar tutor. -> lo demás en cascada.

        Database database = new Database(Database.DB_HORARIO);
        String sql = "DELETE FROM ofertaeducativa WHERE codOE like 'FPB'";
        database.updateDatabase(sql);
        database.closeConnection();
    }
}
