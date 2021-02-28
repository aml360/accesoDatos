package primeraEv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Actividad3 {
  static List<Integer> numeros = new ArrayList<>();
  static final Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", "sumandos.txt");

  public static void main(String[] args) {

    File archivo = ruta.toFile();
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));) {

      int suma = 0;
      for (int i = 1; i < 21; i++) {
        numeros.add(i);
        bw.write(i + "\n");
        System.out.println(i);
        suma += i;
      }
      System.out.println("La suma de estos numeros es: " + suma);

    } catch (Exception e) {
      System.out.println("Error: ");
      e.printStackTrace();
    }
  }
}