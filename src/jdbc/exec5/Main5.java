package jdbc.exec5;

import jdbc.BD.Database;

public class Main5 {
    public static void main(String[] args) {
        Database database = new Database(Database.DB_HORARIO);

        String update = "UPDATE asignatura SET horasSemanales = horasSemanales * 1.1, horasTotales = horasTotales * 1.1 ";
        String sub_select = "WHERE codAsig IN (SELECT codAsig FROM reparto WHERE codOE like 'FPB' AND codAsig like 'M%') ";
        String sql = update.concat(sub_select);

        database.updateDatabase(sql);
        database.closeConnection();
    }


}
