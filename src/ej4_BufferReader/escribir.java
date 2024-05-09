package ej4_BufferReader;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Crear un archivo llamado prueba.txt 
 * con linea 1, linea 2, etc..
 * @author david
 */
public class escribir {
         
         public static void main(String[] args) {
                  FileWriter fichero = null;
                  PrintWriter pw = null;
                  
                  try{
                           fichero = new FileWriter(".\\src\\ej4_BufferReader\\prueba.txt"); // Se crea en el proyecto
                           pw = new PrintWriter(fichero);
                           
                           // Imprimirlo
                           for (int i = 0; i < 10; i++) {
                                    pw.println("linea "+i);
                           }//Fin for
                  }catch(Exception e){
                           e.printStackTrace();
                  }finally { // Siempre, para que se cierre el archivo
                           try{
                                    if(null != fichero) 
                                             fichero.close();
                           }catch(Exception e2){
                                    e2.printStackTrace();
                           }//Fin 2º try-catch
                  }//Fin 1º try-catch
                  
         }//Fin main
         
}//Fin class
