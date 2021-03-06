package modelo;

import java.util.List;

public class Asignatura {
    private Integer codigo;
    private String nombre;
    private List<Profesor> profesoresQueLaImparten;

    public Asignatura() {}

    public Asignatura(int codigo, String nombre, List<Profesor> profesoresQueLaImparten) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesoresQueLaImparten = profesoresQueLaImparten;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Profesor> getProfesoresQueLaImparten() {
        return profesoresQueLaImparten;
    }

    public void setProfesoresQueLaImparten(List<Profesor> profesoresQueLaImparten) {
        this.profesoresQueLaImparten = profesoresQueLaImparten;
    }
}
