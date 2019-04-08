package utils;

import entidades.Asignatura;
import entidades.Curso;
import entidades.Profesor;
import entidades.Tramohorario;
import jdbc.BD.Database;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class JDBCUtils {

    private static Scanner teclado = new Scanner(System.in);

    public static void closeTeclado() {
        teclado.close();
    }

    public static Map<Integer, String> getAllJDBCTypeNames() throws IllegalAccessException {
        Map<Integer, String> result = new HashMap<>();
        for (Field field : Types.class.getFields()) {
            result.put((Integer) field.get(null), field.getName());
        }
        return result;
    }

    public static String getRowsNumber(Database database, String table) throws SQLException {
        long counter = 0;
        ResultSet rs = database.select("SELECT count(*) FROM " + table);
        if (rs.next()) {
            counter = rs.getInt(1);
        }
        rs.close();
        return "La tabla " + table + " tiene " + counter + " registros";
    }

    public static Asignatura getAsignatura(Database database) throws SQLException {
        Asignatura value = null;
        int count = 0;
        List<Asignatura> list = new ArrayList<>();

        ResultSet rs = database.select("SELECT * FROM asignatura");
        while (rs.next()) {
            System.out.printf("%d.-[%s] %s\n", count, rs.getString(1), rs.getString(2));
            list.add(new Asignatura(rs.getString(1), rs.getString(2)));
            count++;
        }
        System.out.println();
        rs.close();

        System.out.print("Escribe el índice de la asignatura que quieres obtener -> ");
        int choice = new Scanner(System.in).nextInt();

        for (int i = 0; i < list.size(); i++) {
            if (choice == i) {
                value = list.get(i);
                break;
            }
        }
        return value;
    }

    public static Curso getCurso(Database database) throws SQLException {
        Curso curso = null;
        int count = 0;
        List<Curso> list = new ArrayList<>();

        ResultSet rs = database.select("SELECT * FROM curso");
        while (rs.next()) {
            System.out.printf("%d.-[%s] %s\n", count, rs.getString(1), rs.getString(2));
            list.add(new Curso(rs.getString(1), rs.getString(2)));
            count++;
        }
        System.out.println();
        rs.close();

        System.out.print("Escribe el índice del curso que quieres obtener -> ");
        int choice = new Scanner(System.in).nextInt();

        for (int i = 0; i < list.size(); i++) {
            if (choice == i) {
                curso = list.get(i);
                break;
            }
        }
        return curso;
    }

    public static Profesor getProfesor(Database database) throws SQLException {
        Profesor profesor = null;
        int count = 0;
        List<Profesor> list = new ArrayList<>();

        ResultSet rs = database.select("SELECT * FROM profesor");
        while (rs.next()) {
            System.out.printf("%d.-[%s] %s %s\n", count, rs.getString(1), rs.getString(2), rs.getString(3));
            list.add(new Profesor(rs.getString(1), rs.getString(2), rs.getString(3)));
            count++;
        }
        System.out.println();
        rs.close();

        System.out.print("Escribe el índice del profesor que quieres obtener -> ");
        int choice = new Scanner(System.in).nextInt();

        for (int i = 0; i < list.size(); i++) {
            if (choice == i) {
                profesor = list.get(i);
                break;
            }
        }
        return profesor;
    }

    public static Tramohorario getTramo(Database database, String dia, int hora) throws SQLException {
        Tramohorario tramo = null;

        String[] dias = new String[5];
        dias[0] = "LUNES";
        dias[1] = "MARTES";
        dias[2] = "MIERCOLES";
        dias[3] = "JUEVES";
        dias[4] = "VIERNES";

        int index = 0;

        for (int i = 0; i < dias.length; i++) {
            if (dia.toUpperCase().equalsIgnoreCase(dias[i])) {
                index = i;
            }
        }

        String codTramo = String.valueOf(dias[index].substring(0, 1)).concat(String.valueOf(hora));

        ResultSet rs = database.select("SELECT * FROM tramohorario WHERE codTramo LIKE '" + codTramo + "'");
        if (rs.first()) {
            tramo = new Tramohorario(rs.getString(1), rs.getTime(2), rs.getTime(3), rs.getString(4));
        }

        return tramo;
    }

}
