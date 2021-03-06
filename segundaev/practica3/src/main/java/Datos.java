
import java.util.Arrays;
import java.util.List;

import modelo.Asignatura;
import modelo.Centro;
import modelo.Profesor;

public class Datos {
    private Datos() {}

    public static final Profesor PROF_ITA_JOSE = new Profesor(0, "Josejuanzo", "Gorallez", "M", "19-3-2993");
    public static final Profesor PROF_ITA_MARI = new Profesor(1, "Marianosa", "Capote", "F", "38-7-1990");
    public static final Profesor PROF_ITA_ACHO = new Profesor(2, "Achoroque", "Gonzalsio", "M", "5-21-1746");
    public static final Profesor PROF_MON_ELNI = new Profesor(3, "Elni", "Casio", "M", "22-5-1998");
    public static final Profesor PROF_MON_ROLE = new Profesor(4, "Rolevilla", "Hinasia", "F", "4-12-3821");
    public static final Profesor PROF_MON_HIGU = new Profesor(5, "Higuchumbu", "Zoforeos", "?", "38-4-1997");
    public static final Profesor DIR_ITACA = new Profesor(6, "José Antonio", "Guallar", "M", "348-231");
    public static final Profesor DIR_MONTE = new Profesor(7, "Nosequé Señor", "Notas", "M", "9-23-1234");
    public static final Centro IES_ITACA = new Centro(Centro.Codigo.COD_ITACA, "IES Itaca", DIR_ITACA, "Avda Estudiantes 1", "Zaragoza", "Zaragoza", Arrays.asList(PROF_ITA_JOSE, PROF_ITA_MARI, PROF_ITA_ACHO));
    public static final Centro COLE_MONT = new Centro(Centro.Codigo.COD_MONTE, "Colegio Montessori", DIR_MONTE, "Calle Lagasca 25", "Zaragoza", "Zaragoza", Arrays.asList(PROF_MON_ELNI, PROF_MON_ROLE, PROF_MON_HIGU));
    public static final Asignatura ASIG_PM = new Asignatura(0, "Programación Multimedia", Arrays.asList(PROF_ITA_ACHO, PROF_MON_HIGU));
    public static final Asignatura ASIG_DI = new Asignatura(1, "Desarrollo de Interfaces", Arrays.asList(PROF_ITA_JOSE, PROF_MON_ROLE));
    public static final Asignatura ASIG_AD = new Asignatura(2, "Acceso a Datos", Arrays.asList(PROF_ITA_ACHO, PROF_ITA_MARI, PROF_MON_ELNI));
    public static final Asignatura ASIG_PSP = new Asignatura(3, "Programación de Servicios y Procesos", Arrays.asList(PROF_ITA_JOSE, PROF_MON_ELNI));
    public static final Asignatura ASIG_EIE = new Asignatura(4, "Empresa e Iniciativa Emprendedora", Arrays.asList(PROF_ITA_ACHO, PROF_ITA_JOSE, PROF_ITA_MARI, PROF_MON_ELNI, PROF_MON_HIGU));
    public static final Asignatura ASIG_SGE = new Asignatura(5, "Sistemas de Gestión Empresarial", Arrays.asList(PROF_MON_ROLE, PROF_ITA_MARI, PROF_MON_ROLE));
    public static final Asignatura ASIG_BBDD = new Asignatura(6, "Bases de Datos", Arrays.asList(PROF_ITA_JOSE, PROF_MON_HIGU));
    public static final Asignatura ASIG_EEDD = new Asignatura(7, "Entornos de Desarrollo", Arrays.asList(PROF_ITA_MARI, PROF_ITA_ACHO, PROF_MON_HIGU, PROF_MON_ELNI));

    public static List<Centro> getCentros() {
        return Arrays.asList(
            IES_ITACA,
            COLE_MONT
        );
    }

    public static List<Profesor> getProfesores() {
        return Arrays.asList(
            PROF_ITA_JOSE,
            PROF_ITA_MARI,
            PROF_ITA_ACHO,
            PROF_MON_ELNI,
            PROF_MON_ROLE,
            PROF_MON_HIGU,
            DIR_ITACA,
            DIR_MONTE
        );
    }

    public static List<Asignatura> getAsignaturas() {
        return Arrays.asList(
            ASIG_PM,
            ASIG_DI,
            ASIG_AD,
            ASIG_PSP,
            ASIG_EIE,
            ASIG_SGE,
            ASIG_BBDD,
            ASIG_EEDD
        );
    }
}
