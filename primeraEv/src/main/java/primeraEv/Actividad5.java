package primeraEv;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Actividad5 {
  static Scanner sc = new Scanner(System.in);
  static final Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", "double.dat");

  public static void main(String[] args) {
    Double numero = 0.0;
    System.out.print("Introduce un numero: ");
    try {
      numero = sc.nextDouble();
    } catch (Exception e) {
      System.out.println("Ha introducido un dato erroneo, recargue el programa.");
    }
    try (RandomAccessFile fichero = new RandomAccessFile(ruta.toString(), "rw");) {

      fichero.seek(fichero.length());
      fichero.writeDouble(numero);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}