package entidades;

public class Curso {
    private String codOe;
    private String codCurso;

    public Curso(String codOe, String codAsig) {
        this.codOe = codOe;
        this.codCurso = codAsig;
    }

    public String getCodOe() {
        return codOe;
    }

    public void setCodOe(String codOe) {
        this.codOe = codOe;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }
}
