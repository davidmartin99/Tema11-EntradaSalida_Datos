package alumnos11;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *
 * @author david
 */
public class TestAlumnos {
         public static void main(String[] args) {
                  // Creo el ArrayList con el tipo de dato objeto Alumnos
                  // Tambíen podría ser <String> en vez de <Alumno> ya que el objeto solo tiene un atributo String
                  ArrayList<String> alumnos = new ArrayList<String>();
                  
                  // Instanciar el fichero donde se encuentran los datos (importamos clase Scanner y File)
                  // File fichero = new File("alumnos11.txt") si lo ponemos en la carpeta del proyecto 

                  File fichero = new File(".\\src\\alumnos11\\alumnos11.txt");  //Si lo ponemos dentro de alumnos11 
                  // Inicializar el Scanner 
                  Scanner s = null;
                  
                  // Para manejarlo con Excepciones try-catch 
                  try{
                           System.out.println("Leyendo contenido del fichero... ");
                           // Busca el fichero
                           s = new Scanner(fichero);
                           
                           // Obtener los datos
                           while(s.hasNextLine()){  // Con s.hasNextLine nos indica si tenemos datos 
                                    String linea = s.nextLine();
                                    alumnos.add(linea);
                           }//Fin while
                           
                  }catch(FileNotFoundException e0){ //Excepcion si no encuentra el fichero
                           e0.printStackTrace(); // Muestra por pantalla la Excepcion
                  }catch(Exception e1){
                           e1.printStackTrace();
                  }finally{
                           s.close();
                  }//Fin try-catch
                  
                  // Tamaño del ArrayList
                  System.out.println("Alumnos 1º Curso DAM: "+alumnos.size());
                  
                  // Iterator para recorrer alumnos
                  Iterator<String> itAlumnos = alumnos.iterator();
                  while (itAlumnos.hasNext()){
                           String alumnosDAM = itAlumnos.next(); 
                           System.out.println(alumnosDAM);
                  }//Fin while
                  
         }//Fin main

}//Fin class
