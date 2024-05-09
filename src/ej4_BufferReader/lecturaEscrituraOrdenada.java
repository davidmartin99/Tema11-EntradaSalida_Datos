package ej4_BufferReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

/**
 * Lee un fichero de datos y lo muestra por pantalla Pregunta al usuario que
 * dato quiere escribir y lo añade al final del fichero
 *
 * Ahora la lista estara ordenada directamente
 *
 * @author david
 */
public class lecturaEscrituraOrdenada {

         public static void main(String[] args) {
                  // Nombre del archivo
                  String fichero = ".\\src\\ej4_BufferReader\\prueba.txt";

                  // Mostramos el contenido actual del archivo
                  System.out.println("Contenido actual del archivo:");
                  mostrarContenidoArchivo(fichero);

                  // Pedimos el dato que quiera añadir
                  Scanner sc = new Scanner(System.in);
                  System.out.println("\nIntroduzca el dato: ");
                  String dato = sc.nextLine();

                  // Agregamos el dato al archivo 
                  anadirDatoAlArchivo(fichero, dato);

                  // Mostramos el contenido Actualizado del archivo
                  System.out.println("\nContenido actualizado correctamente: ");
                  mostrarContenidoArchivo(fichero);

                  // Ordenar la lista 
                  
                  sc.close();

         }//Fin main

         // Método para mostrar el contenido de un archivo
         public static void mostrarContenidoArchivo(String fichero) {
                  try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                           String linea;
                           while ((linea = br.readLine()) != null) {
                                    System.out.println(linea);
                           }
                  } catch (Exception e) {

                  }
         }//Fin mostrarContenidoArchivo 

         // Método para añadir un dato al final de un archivo
         public static void anadirDatoAlArchivo(String nombreArchivo, String dato) {
                  try (FileWriter fichero = new FileWriter(nombreArchivo, true); // true para modo de adición
                           PrintWriter pw = new PrintWriter(fichero)) {
                           pw.println(dato);
                           pw.flush();
                  } catch (Exception e) {
                           e.printStackTrace();
                  }
         }//Fin anadirDatoAlArchivo

}//Fin class
