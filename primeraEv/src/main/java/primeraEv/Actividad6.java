package primeraEv;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Actividad6 {
  static final Path ruta = Paths.get(System.getProperty("user.dir"), "ficheros", "enteros.dat");

  public static void main(String[] args) {
    Scanner ky = new Scanner(System.in);
    int posicion, num;
    long ln;
    try (RandomAccessFile archivo = new RandomAccessFile(ruta.toString(), "rw");) {

      ln = archivo.length();
      // Los int ocupan 4 bytes
      ln = ln / 4;
      System.out.println("El archivo tiene " + ln + " numeros");
      do {
        System.out.print("Introduce una posicion mayor que 1 y menor que " + ln + ": ");
        posicion = ky.nextInt();
      } while (posicion < 1 || posicion > ln);
      posicion--;
      archivo.seek((long) posicion * 4);

      System.out.println("Valor: " + archivo.readInt());
      System.out.print("Introduzca un nuevo valor: ");
      num = ky.nextInt();

      archivo.seek((long) posicion * 4);
      archivo.writeInt(num);
    } catch (InputMismatchException err) {
      System.out.println("No has introducido un int");
    } catch (IOException err) {
      err.printStackTrace();
    }
    ky.close();
  }
}
