package primeraEv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Actividad1 {

  public static final Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", "act1.txt");
  public static final File archivo = ruta.toFile();
  private static Scanner ky = new Scanner(System.in);

  public static void main(String[] args) {
    boolean exit = false;
    while (!exit) {
      try {
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
            crear();
            break;
          }

          case 2: {
            mostrar();
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
        e.printStackTrace();
        System.out.println("Ha introducido una tecla erronea");
        System.out.println("");
      }
    }
    ky.close();
  }

  /**
   * Metodo que crea el fichero dentro de la carpeta ficheros
   */
  private static void crear() {

    PrintWriter bw;
    try {
      bw = new PrintWriter(new FileWriter(archivo, true));
    } catch (IOException e) {
      System.out.println("No se ha podido crear el filewriter");
      return;
    }

    String n, a, ec;
    System.out.println("");
    System.out.print("Introduzca el nombre: ");
    n = ky.reset().nextLine();
    System.out.print("Introduzca el apellido: ");
    a = ky.nextLine();
    System.out.print("Introduzca su estado civil: ");
    ec = ky.nextLine();
    System.out.println("");
    bw.write("\n La persona se llama " + n + " " + a + " y su estado civil es " + ec);
    bw.close();
  }

  /**
   * Muestra el archivo previamente creado, imprime por pantalla si no lo está
   * 
   */
  private static void mostrar() {
    if (!archivo.exists()) {
      System.out.println("Fichero no encontrado (vacio)");
      return;
    }
    try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
      String linea;
      while ((linea = br.readLine()) != null) {
        System.out.println(linea);
      }
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
