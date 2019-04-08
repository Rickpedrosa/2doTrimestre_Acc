package pruebas;

import com.mysql.jdbc.TimeUtil;
import entidades.Curso;
import entidades.Profesor;
import entidades.Tramohorario;
import jdbc.BD.Database;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main_Collec {
    public static void main(String[] args) {
//        System.out.printf("%-20s%-20s%-20s\n","0000011","Carlos","Mauricio");
//        System.out.printf("%-20s%-20s%-20s\n","0001442","Andrea","Margarita");
//        System.out.printf("%-20s%-20s%-20s\n","0344127","Marco","Antonio");
//        System.out.printf("%-20s%-20s%-20s\n","9532114","Juan","Fernando");
        //System.out.println(LocalDate.now().getDayOfWeek());

        //1.Pasar visit a Timestamp, pasar time de dias a segundos, sumarlo, y convertilo a fecha
        //para después volver a pasarlo a String
        String sDate = "05-04-2019";
//        int time = 5 /*(DAYS)*/;
//        try {
//            Date visitDate = new SimpleDateFormat("dd-MM-yyyy").parse(visit);
//            System.out.println("START -> " + visitDate);
//            long visitDateToMilisecs = visitDate.getTime();
//            System.out.printf("%s en segundos es -> %s\n", visit, visitDateToMilisecs);
//            System.out.println("END -> " + new Date(visitDateToMilisecs));
//            long timeToMiliSecs = (long) (time * 86400000);
//            System.out.println("TRUE END time + visit -> " + new Date(timeToMiliSecs + visitDateToMilisecs));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        int[] values = new int[3];
        values[0] = Integer.parseInt(sDate.substring(0, sDate.indexOf("-"))); //day
        values[1] = Integer.parseInt(sDate.substring(sDate.indexOf("-") + 1, sDate.lastIndexOf("-"))); //month
        values[2] = Integer.parseInt(sDate.substring(sDate.lastIndexOf("-") + 1)); //year
        System.out.printf("%d-%d-%d", values[2], values[1], values[0]);
    }
}
