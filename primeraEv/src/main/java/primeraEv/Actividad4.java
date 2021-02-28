package primeraEv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Actividad4 {

  static final Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", "Personas.ddr");
  static Scanner ky = new Scanner(System.in);
  private static String errorKeyMsg = "Ha introducido una tecla erronea";

  public static void main(String[] args) {

    boolean exit = false;
    while (!exit) {

      try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta.toFile(), true));
          DataInputStream dis = new DataInputStream(new FileInputStream(ruta.toFile()))) {

        System.out.println("1.- Introducir persona");
        System.out.println("2.- Mostrar fichero");
        System.out.println("3.- Exit");
        System.out.print("Elija una opcion: ");

        int tecla = 0;
        tecla = ky.reset().nextInt();
        // Borrar \n
        ky.nextLine();
        switch (tecla) {
          case 1: {
            introduceDatos(dos);
            break;
          }
          case 2: {
            muestraDatos(dis);
            break;
          }
          case 3: {
            exit = true;
            break;
          }
          default:
            throw new IllegalArgumentException("Valor erroneo: " + tecla);
        }
      } catch (Exception e) {
        System.out.println("");
        System.out.println(errorKeyMsg);
        System.out.println("");
      }
    }

  }

  public static void introduceDatos(DataOutputStream dos) {
    String n, a, ec;
    System.out.println("");
    System.out.print("Introduzca el nombre: ");
    n = ky.nextLine();
    System.out.print("Introduzca el apellido: ");
    a = ky.nextLine();
    System.out.print("Introduzca su estado civil: ");
    ec = ky.nextLine();
    System.out.println();
    try {
      dos.writeUTF(n);
      dos.writeUTF(a);
      dos.writeUTF(ec);
      dos.writeUTF("\n");
    } catch (IOException e) {
      System.out.println("");
      System.out.println(errorKeyMsg);
      System.out.println("");
    }
  }

  public static void muestraDatos(DataInputStream dis) {
    try {
      System.out.println(
          "\n La persona se llama " + dis.readUTF() + " " + dis.readUTF() + " y su estado civil es " + dis.readUTF());
      System.out.println();
    } catch (Exception e) {
      System.out.println("");
      System.out.println("No se han podido mostrar los datos, fichero no creado");
      System.out.println("");
    }
  }
}
