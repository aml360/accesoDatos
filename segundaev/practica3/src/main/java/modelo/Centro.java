package modelo;

import java.util.List;

public class Centro {
    
    private Integer codigo;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;

    private Profesor director;
    private List<Profesor> profesoresDelCentro;

    public Centro() {}
    
    public Centro(int codigo, String nombre, Profesor director, String direccion, String localidad, String provincia,List<Profesor> profesoresDelCentro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.director = director;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.profesoresDelCentro = profesoresDelCentro;
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

    public Profesor getDirector() {
        return director;
    }

    public void setDirector(Profesor director) {
        this.director = director;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }    

    public List<Profesor> getProfesoresDelCentro() {
        return profesoresDelCentro;
    }

    public void setProfesoresDelCentro(List<Profesor> profesoresDelCentro) {
        this.profesoresDelCentro = profesoresDelCentro;
    }

    public static class Codigo {
        private Codigo() {}
        public static final int COD_ITACA = 0,
                                COD_MONTE = 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ===== Centro ").append(codigo).append(" =====\n").append(" - Nombre: ").append(nombre)
        .append("\n - Direccion: ").append(direccion).append(", ").append(localidad).append(", (").append(provincia).append(")")
        .append("\n - Director: ").append(director.getNombre()).append(" ").append(director.getApellidos()).append("\n - Profesores: ");
        for (Profesor profesor : profesoresDelCentro) {
            sb.append("\n   - ").append(profesor.getNombre()).append(" ").append(profesor.getApellidos());
        }
        return sb.toString();
    }
}
