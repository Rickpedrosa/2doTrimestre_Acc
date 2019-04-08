package entidades;

public class Asignatura {
    private String codAsig;
    private String nombreAsig;

    public Asignatura(String codAsig, String nombreAsig) {
        this.codAsig = codAsig;
        this.nombreAsig = nombreAsig;
    }

    public String getCodAsig() {
        return codAsig;
    }

    public void setCodAsig(String codAsig) {
        this.codAsig = codAsig;
    }

    public String getNombreAsig() {
        return nombreAsig;
    }

    public void setNombreAsig(String nombreAsig) {
        this.nombreAsig = nombreAsig;
    }
}
