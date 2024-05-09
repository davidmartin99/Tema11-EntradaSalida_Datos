package ej3_escritura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Crea un archivo
 * Escritura de datos 
 * @author david
 */

public class prueba1 {
         
         public static void main(String[] args) throws IOException {
                  Scanner sc = new Scanner(System.in);
                  // Creamos un archivo desde aquí
                  File fichero1 = new File(".\\src\\ej3_escritura\\prueba1.txt");
                  
                  // Comprueba que existe con .exists()
                  if(!fichero1.exists()){
                           System.out.println("El archivo no existe!!!");
                           System.out.println("¿Quiere crear un nuevo fichero de texto?\nPulse Y para crear");
                           String respuesta =sc.nextLine(); // Leo la respuesta del usuario
                           // Si la respuesta es "Y" o "y"
                           if(respuesta.equalsIgnoreCase("Y")) {
                                    System.out.println("Nombre del nuevo fichero: ");
                                    String nombreFichero = sc.nextLine(); // el usuario le da un nombre al nuevo fichero
                                    File ficheroUser = new File(".\\src\\ej3_escritura\\"+nombreFichero); // Ruta del fichero con el nombre dado por el usuario 
                                    
                                    // Habría que meterlo en un try-catch
                                    try{
                                             ficheroUser.createNewFile();
                                             ficheroUser.getAbsoluteFile();
                                    }catch(Exception e1){
                                             System.err.println("Error no se ha podido crear el fichero");
                                             e1.printStackTrace();
                                    }//Fin try-catch
                                   
                                    System.out.println("El archivo se ha creado correctamente");
                           }else{
                                    System.out.println("Cerrando programa...");
                           }//Fin 2ºif-else
                           
                  }else{ // Si existe el fichero
                           try{
                                    // Usaremos bufferedReader y bufferedWriter
                                    BufferedReader br = new BufferedReader(new FileReader(".\\src\\ej3_escritura\\prueba1.txt"));
                                    BufferedWriter bw = new BufferedWriter(new FileWriter(".\\src\\ej3_escritura\\prueba1.txt"));
                                    
                                    // Escribir en el fichero con .write
                                    bw.write("Linea 1");
                                    
                                    // Si el usuario lo introduce: 
                                    System.out.println("Escriba su Linea 1:");
                                    String escritura = sc.nextLine();
                                    bw.write(escritura);
                                    
                                    // Una nueva linea
                                    bw.newLine();
                                    bw.write("Linea 2");
                                    System.out.println("Escriba su Linea 2:");
                                    escritura = sc.nextLine();
                                    bw.write(escritura);
                                    
                                    bw.flush();

                           }catch(Exception e1){
                                    
                           }//Fin try-catch
                           
                  }//Fin 1º if-else
                  
         }//Fin main
         
}//Fin class
