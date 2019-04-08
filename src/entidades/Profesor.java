package entidades;

public class Profesor {
    private String codProf;
    private String nombre;
    private String apellidos;

    public Profesor(String codProf, String nombre, String apellidos) {
        this.codProf = codProf;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getCodProf() {
        return codProf;
    }

    public void setCodProf(String codProf) {
        this.codProf = codProf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
