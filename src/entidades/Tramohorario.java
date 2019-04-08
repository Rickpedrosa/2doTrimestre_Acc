package entidades;

import java.sql.Time;

public class Tramohorario {
    private String codTramo;
    private Time hora_inicio, hora_fin;
    private String dia;

    public Tramohorario(String codTramo, Time hora_inicio, Time hora_fin, String dia) {
        this.codTramo = codTramo;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.dia = dia;
    }

    public String getCodTramo() {
        return codTramo;
    }

    public void setCodTramo(String codTramo) {
        this.codTramo = codTramo;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
