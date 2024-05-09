package lectura_escritura;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class lecturaEscrituraOrdenadaBufferedWriter {

         public static void main(String[] args) {
                  // Ruta del fichero
                  String rutaFichero = ".\\src\\lectura_escritura\\prueba.txt";

                  // ArrayList para almacenar el contenido del fichero
                  ArrayList<String> lista = new ArrayList<>();

                  // Primero, leemos y mostramos el contenido del fichero
                  System.out.println("\nContenido del fichero:");
                  File fichero = new File(rutaFichero);
                  try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                           String linea;
                           while ((linea = br.readLine()) != null) {
                                    System.out.println(linea);
                                    lista.add(linea);
                           }
                  } catch (IOException e) {
                           e.printStackTrace();
                  }

                  // Mostramos el contenido del ArrayList por pantalla
                  System.out.println("\nContenido del ArrayList:");
                  for (String linea : lista) {
                           System.out.println(linea);
                  }

                  // Pedir datos
                  Scanner sc = new Scanner(System.in);
                  System.out.println("\nIntroduce un nuevo dato:");
                  String dato = sc.nextLine();
                  sc.close();

                  // Añadir Dato
                  lista.add(dato);

                  // Ordenar la lista en orden descendente
                  Collections.sort(lista, Collections.reverseOrder());

                  // Imprimir ArrayList con Iterator
                  Iterator<String> imprimir = lista.iterator();
                  System.out.println("\nArrayList ordenado de forma descendente:");
                  while (imprimir.hasNext()) {
                           System.out.println(imprimir.next());
                  }

                  // Finalmente, escribimos el dato al final del fichero sin sobreescribir
                  FileWriter fw = null;

                  try {
                           fw = new FileWriter(rutaFichero, true);
                           BufferedWriter bw = new BufferedWriter(fw); // Corregido: Utilizar bw en lugar de bw2

                           // Iterator<String> imprimir = lista.iterator(); // No necesitas declarar imprimir de nuevo
                           while (imprimir.hasNext()) {
                                    bw.write(imprimir.next()); // Escribir cada elemento en el BufferedWriter
                                    bw.newLine(); // Agregar una nueva línea después de cada elemento
                           }

                           bw.write(dato); // Escribir el nuevo dato al final del archivo
                           bw.newLine(); // Agregar una nueva línea después del nuevo dato

                           bw.flush(); // Limpiar el BufferedWriter
                           System.out.println("\nSe ha añadido el dato al fichero.");
                  } catch (IOException e) {
                           e.printStackTrace();
                  } finally { // Asegurarse de cerrar el BufferedWriter y el FileWriter
                           try {
                                    if (null != fw) {
                                             fw.close();
                                    }
                           } catch (Exception e2) {
                                    e2.printStackTrace();
                           }
                  }

                  // Mostramos el contenido actualizado del fichero
                  System.out.println("\nContenido actualizado del fichero:");
                  try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                           String linea;
                           while ((linea = br.readLine()) != null) {
                                    System.out.println(linea);
                           }
                  } catch (IOException e) {
                           e.printStackTrace();
                  }

         }
}
