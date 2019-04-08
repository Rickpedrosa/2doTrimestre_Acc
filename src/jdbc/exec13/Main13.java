package jdbc.exec13;

import jdbc.BD.Database;
import entidades.Curso;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main13 {
    public static void main(String[] args) {
        try {
            Database database = new Database(Database.DB_HORARIO);

            Curso curso = JDBCUtils.getCurso(database);
            String sql = "SELECT trm.codTramo, trm.dia, asig.nombre, asig.codAsig FROM horario hor " +
                    "INNER JOIN tramohorario trm ON hor.codTramo = trm.codTramo" +
                    " INNER JOIN asignatura asig ON asig.codAsig = hor.codAsig WHERE " +
                    "hor.codCurso LIKE '" + curso.getCodCurso() + "' AND hor.codOe LIKE '" + curso.getCodOe() + "' ORDER BY trm.dia, trm.horaInicio";
            String[] lunes = new String[6];
            String[] martes = new String[6];
            String[] miercoles = new String[6];
            String[] jueves = new String[6];
            String[] viernes = new String[6];

            ResultSet rs = database.select(sql);

            String asignatura;
            Pattern pattern = Pattern.compile("(@[12]?(.*?))");
            Matcher matcher;
            boolean desdoble;
            boolean allow = true;

            while (rs.next()) {
                asignatura = rs.getString(4);
                matcher = pattern.matcher(asignatura);

                desdoble = matcher.find();
                if (desdoble) {
                    asignatura = rs.getString(4).replace(matcher.group(1), "").concat("*");
                }

                if (allow) {
                    switch (rs.getString(2)) {
                        case "LUNES":
                            lunes[Integer.valueOf(rs.getString(1).substring(1)) - 1] = asignatura;
                            break;
                        case "MARTES":
                            martes[Integer.valueOf(rs.getString(1).substring(1)) - 1] = asignatura;
                            break;
                        case "MIERCOLES":
                            miercoles[Integer.valueOf(rs.getString(1).substring(1)) - 1] = asignatura;
                            break;
                        case "JUEVES":
                            jueves[Integer.valueOf(rs.getString(1).substring(1)) - 1] = asignatura;
                            break;
                        case "VIERNES":
                            viernes[Integer.valueOf(rs.getString(1).substring(1)) - 1] = asignatura;
                            break;
                    }
                }
                //noinspection RedundantIfStatement
                if (desdoble) {
                    allow = false;
                } else {
                    allow = true;
                }
            }
            rs.close();

            String[] horas = new String[6];
            horas[0] = "08:15:00 - 09:15:00";
            horas[1] = "09:15:00 - 10:15:00";
            horas[2] = "10:15:00 - 11:15:00";
            horas[3] = "11:45:00 - 12:45:00";
            horas[4] = "12:45:00 - 13:45:00";
            horas[5] = "13:45:00 - 14:45:00";
            String separador = "";
            for (int i = 0; i < horas[0].length(); i++) {
                separador = separador.concat(" ");
            }

            System.out.println("Horario de " + curso.getCodCurso() + " " + curso.getCodOe() + "\n");
            System.out.printf("%s %-15s%-15s%-15s%-15s%-15s\n", separador, "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES");
            for (int i = 0; i < lunes.length; i++) {
                System.out.printf("%s %-15s%-15s%-15s%-15s%-15s\n", horas[i], lunes[i], martes[i], miercoles[i], jueves[i], viernes[i]);
            }

            System.out.println();
            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
