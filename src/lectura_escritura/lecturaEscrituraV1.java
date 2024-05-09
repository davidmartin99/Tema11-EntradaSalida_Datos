
package lectura_escritura;

import java.io.*;
import java.util.Scanner;
/**
 * Lee un fichero de datos y lo muestra por pantalla
 * pregunta al usuario que dato quiere escribir
 * y lo añade al final del fichero en una nueva linea.
 * 
 * @author alumno
 */
public class lecturaEscrituraV1 {
    public static void main(String[] args) {
        // Ruta del fichero
        String rutaFichero = ".\\src\\lectura_escritura\\prueba.txt";
        
        // Inicializo variables  para leer y escribir en el archivo respectivamente.
        BufferedReader br = null;
        PrintWriter pw = null;

        // Primero, leemos y mostramos el contenido del fichero
        /*
         * Lectura del contenido del fichero: Se utiliza un bloque try-catch-finally para leer el contenido del archivo línea por línea utilizando un BufferedReader. 
         * Cada línea se imprime en la consola. En el bloque finally, se cierra el BufferedReader para liberar recursos.
         */
        System.out.println("\nContenido del fichero:");
        File fichero = new File(rutaFichero);
        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Luego, preguntamos al usuario qué dato quiere escribir
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIntroduce un nuevo dato:");
        String dato = sc.nextLine();
        sc.close();

        // Finalmente, escribimos el dato al final del fichero
        /*
         * Se utiliza un bloque try-catch-finally para escribir el dato ingresado por el usuario al final del archivo utilizando un PrintWriter. 
         * En el bloque finally, se cierra el PrintWriter.
        */
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(rutaFichero, true))); // Sin true se sobreescribe el fichero cada vez que lo ejecuto.
            pw.println(dato);
            System.out.println("\nSe ha añadido el dato al fichero.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        // Mostramos el contenido actualizado del fichero usando un BufferedReader
        System.out.println("\nContenido actualizado del fichero:");
        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}