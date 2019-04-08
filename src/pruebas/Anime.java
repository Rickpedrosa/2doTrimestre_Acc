package pruebas;

public class Anime {
    public static void main(String[] args) {
        float days = 20;
        float caps = 49;
        float capsPerDay = (caps / days);

        float restAux = 0;
        float capsPerDayAux = 0;
        float daysAux = days;
        float totalCapsAux = caps;

        if (caps > days) {
            if ((int) capsPerDay != 0) {
                System.out.println("The anime series last for " + caps + " caps");
                System.out.println("You want to see it in " + days + " days");
                System.out.println("SCHEDULE:\n");

                for (int i = 0; i < days; i++) {
                    System.out.print("Day " + (i + 1) + " you must see ");
                    if (i == 0) { //día 1
                        System.out.println((int) capsPerDay + " caps"); //capítulos del primer día
                        restAux = capsPerDay - ((int) capsPerDay); //parte decimal que pasa al día siguiente
                        totalCapsAux = totalCapsAux - (int) capsPerDay; //se resta X parte entera capítulo del total
                    } else if (i != (daysAux - 1)) { //días entre el primero y el último
                        capsPerDayAux = capsPerDay + restAux;
                        System.out.println((int) capsPerDayAux + " caps"); //capítulos del día (parte entera de capsPerDayAux)
                        restAux = capsPerDayAux - ((int) capsPerDayAux); //parte decimal que pasa al día siguiente
                        totalCapsAux = totalCapsAux - ((int) capsPerDayAux); //se resta la parte entera del total de caps
                    } else {
                        System.out.println((int) totalCapsAux + " caps"); //el último día no se hace operación, se le asigna los
                        //capítulos que quedan y ya está
                    }
                    capsPerDayAux = 0;
                }
            }
        }


    }
}
