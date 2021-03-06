
import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import modelo.Asignatura;
import modelo.Centro;
import modelo.Profesor;

public final class Main {
    private static String urlDB = "src/main/resources/base_de_datos.txt";
    private static ODB db;

    private Main() {
    }

    public static void main(String[] args) {
        db = ODBFactory.open(urlDB);
        try {

            int orden = 0;
            do {
                imprimirMenu();
                orden = pedirOrden();
                acatar(orden);
            } while (orden != 0);

        } finally {
            if (db != null)
                db.close();
        }
    }

    private static void imprimirMenu() {
        System.out.println("\nÓrdenes válidas:\n================\n"
                + "0 -> Salir\n1 -> Poblar base de datos\n2 -> Listar todos los centros\n"
                + "3 -> Listar todos los profesores\n4 -> Listar todos los profesores de un centro\n"
                + "5 -> Listar todos los profesores de un centro cuya fecha de nacimiento sea anterior a 1993\n"
                + "6 -> Listar los profesores con sexo masculino que impartan la asignatura de \"Acceso a datos\"\n"
                + "7 -> Comprobar que un profesor ya existe\n");
    }

    private static void acatar(int orden) {
        switch (orden) {
        case 0:
            return;
        case 1:
            poblarBaseDeDatos();
            break;
        case 2:
            listarTodosLosCentros();
            break;
        case 3:
            listarTodosLosProfesores();
            break;
        case 4:
            listarTodosLosProfesDeUnCentro();
            break;
        case 5:
            listarProfesoresDeUnCentroNacidosAntesDe1993();
            break;
        case 6:
            listarProfesoresDeSexoMasculinoQueImpartenAD();
            break;
        case 7:
            comprobarQueUnProfesorYaExiste();
            break;
        default:
            break;
        }
    }

    private static void comprobarQueUnProfesorYaExiste() {
        int codigo = pedirEntero("Códido del profesor", false);
        Objects<Profesor> profes = db.getObjects(new CriteriaQuery(Profesor.class, Where.equal("codigo", codigo)));
        if (profes.size() == 1) {
            for (Profesor profesor : profes) {
                System.out.println(profesor);
            }
        } else if (profes.size() > 1) {
            System.out.println("Hay profesores con clave duplicada: " + codigo);
        } else {
            System.out.println("No existe ningún profesor con esa clave");
        }
    }

    private static void listarProfesoresDeSexoMasculinoQueImpartenAD() {
        Asignatura AD = (Asignatura) db
                .getObjects(new CriteriaQuery(Asignatura.class,
                        Where.and().add(Where.isNotNull("codigo")).add(Where.equal("nombre", "Acceso a Datos"))))
                .getFirst();

        Objects<Profesor> profes = db
                .getObjects(new CriteriaQuery(Profesor.class, Where.and().add(Where.equal("sexo", "M"))));
        for (Profesor profesor : profes) {
            if (AD.getProfesoresQueLaImparten().contains(profesor)) {
                System.out.println(profesor);
            }
        }
    }

    private static void listarProfesoresDeUnCentroNacidosAntesDe1993() {
        String s = pedirString("Nombre del centro", false);
        Objects<Centro> centro = db.getObjects(new CriteriaQuery(Centro.class, Where.equal("nombre", s)));

        if (centro.isEmpty()) {
            System.out.println("No hay ningún centro con ese nombre");
            return;
        }
        Centro c = centro.getFirst();
        for (Profesor p : c.getProfesoresDelCentro()) {
            try {
                String[] splitted = p.getFechaNacimiento().split("-");
                if (Integer.parseInt(splitted[splitted.length - 1]) < 1993) {
                    System.out.println("\n" + p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void listarTodosLosProfesDeUnCentro() {
        String s = pedirString("Nombre del centro", false);
        Objects<Centro> centro = db.getObjects(new CriteriaQuery(Centro.class, Where.equal("nombre", s)));

        if (centro.isEmpty()) {
            System.out.println("No hay ningún centro con ese nombre");
            return;
        }
        Centro c = centro.getFirst();
        for (Profesor p : c.getProfesoresDelCentro()) {
            System.out.println("\n" + p);
        }
    }

    private static void listarTodosLosProfesores() {
        Objects<Profesor> profes = db.getObjects(Profesor.class);

        profes.forEach(o -> System.out.println("\n" + o));
    }

    private static void listarTodosLosCentros() {
        Objects<Centro> centros = db.getObjects(new CriteriaQuery(Centro.class, Where.isNotNull("codigo")));
        centros.forEach(o -> System.out.println("\n" + o));
    }

    private static void poblarBaseDeDatos() {
        // priomero elimina la movida
        db.close();
        new File(urlDB).delete();
        // despues la crea otra vez y la rellena
        db = ODBFactory.open(urlDB);
        for (Asignatura a : Datos.getAsignaturas()) {
            db.store(a);
        }
        for (Centro c : Datos.getCentros()) {
            db.store(c);
        }
        for (Profesor p : Datos.getProfesores()) {
            db.store(p);
        }
    }

    // Devuelve un entero entre 0 y 7
    private static int pedirOrden() {
        boolean input_ok = false;
        int orden = 0;
        do {
            try {
                orden = pedirEntero("orden", false);
                if (orden < 0 || orden > 7) {
                    System.out.println("Orden no válida.");
                } else {
                    input_ok = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número del 0 al 7.");
            }
        } while (!input_ok);

        return orden;
    }

    /**
     * Devuelve un string que puede ser null o no según el parámetro booleano.
     */
    private static String pedirString(String msj, boolean puedeEstarVacio) {
        String s;
        boolean error = false;
        do {
            if (error)
                System.out.println("El string no puede estar vacío.");
            System.out.print(msj + " (string): ");
            s = System.console().readLine();
        } while (puedeEstarVacio ? false : (error = StringUtils.isBlank(s)));
        return StringUtils.isBlank(s) ? null : s.trim();
    }

    /**
     * Pide un entero que puede ser null o no según el parámetro booleano.
     */
    private static Integer pedirEntero(String msj, boolean puedeSerNull) {
        while (true) {
            System.out.print(msj + " (entero): ");
            int entero;
            try {
                String s = System.console().readLine();
                if (puedeSerNull && StringUtils.isBlank(s)) {
                    return null;
                } else {
                    entero = Integer.parseInt(s);
                    return entero;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un entero.");
            }
        }
    }
}
