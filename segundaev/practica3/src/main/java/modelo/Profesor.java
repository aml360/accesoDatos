package modelo;

public class Profesor {
    
    private Integer codigo;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fechaNacimiento;

    public Profesor() {
    }

    public Profesor(int codigo, String nombre, String apellidos, String sexo, String fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" - Profesor: ").append(codigo).append("\n   Nombre: ").append(nombre)
                .append(" ").append(apellidos).append("\n   Género: ").append(sexo).append("\n   Fecha nacimiento: ")
                .append(fechaNacimiento);
        return sb.toString();
    }
}
