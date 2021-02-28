package primeraEv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actividad2 {
  static final List<Integer> numeros = new ArrayList<>();
  static Scanner ky = new Scanner(System.in);

  public static void main(String[] args) {
    boolean exit = false;

    while (!exit) {
      try {

        System.out.println("1.- Crear fichero");
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
            System.out.println("Introduzca el nombre del archivo a leer: ");
            String nF = ky.next();
            mostrar(nF);
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
      }
    }
    ky.close();
  }

  private static void crear() {

    System.out.print("Introduzca el nombre del fichero: ");
    String ficheroName = ky.nextLine();
    Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", ficheroName);
    File archivo = ruta.toFile();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));) {
      int num = 1;
      numeros.add(num);
      bw.write(num + " ");
      for (int i = 0; i < 99; i++) {
        num += 2;
        numeros.add(num);
        bw.write(num + " ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Esta funcion muestra el archivo indicado por el usuario
   * 
   * @param fichero El nombre del fichero (extension por defecto .txt)
   * @throws FileNotFoundException
   */

  private static void mostrar(String ficheroName) {
    Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", ficheroName);
    try (BufferedReader br = new BufferedReader(new FileReader(ruta.toFile()));) {
      System.out.println(br.readLine());
    } catch (Exception e) {
      System.out.println("Error de lectura, puede que no exista el archivo");
    }
    System.out.println();
  }

}
