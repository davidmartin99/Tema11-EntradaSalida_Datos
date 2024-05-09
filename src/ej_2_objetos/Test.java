package ej_2_objetos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase principal de Alumnos
 * @author david
 */
public class Test {
         public static void main(String[] args) {
                  // Creo el ArrayList con el tipo de dato objeto Alumnos
                  ArrayList<Alumnos> alumnoDAM = new ArrayList<Alumnos>();

                  // Instanciar el fichero donde se encuentran los datos (importamos clase Scanner y File)
                  // File fichero = new File("alumno2.txt") si lo ponemos en la carpeta del proyecto 
                  File fichero = new File(".\\src\\ej_2_objetos\\alumno2.txt");  //Si lo ponemos dentro de alumnos11 
                  // Inicializar el Scanner 
                  Scanner s = null;

                  // Para manejarlo con Excepciones try-catch 
                  try {
                           System.out.println("Leyendo contenido del fichero... ");
                           // Busca el fichero
                           s = new Scanner(fichero);

                           // Obtener los datos
                           // .hasNextLine() devuelve los datos si es true, si es false deja de repetirse
                           while (s.hasNextLine()) {  // Con s.hasNextLine nos indica si tenemos datos 
                                    String registro = s.nextLine();   //Variable auxiliar llamada registro, obtiene una fila de un alumno
                                    
                                    // Cortamos el String para dividirlo en 3 segun el delimitador, en este caso ::
                                    // Para ello usamos .split("delimitador")
                                    String [] cortarString = registro.split("::");
                                    
                                    // Objeto clase alumnos con sus atributos
                                    Alumnos alumno = new Alumnos();
                                    // Se transforma el numero de int a String
                                    alumno.setNumero(Integer.parseInt(cortarString[0]));  
                                    alumno.setNombre(cortarString[1]);
                                    alumno.setApellido(cortarString[2]);
                                    
                                    // Añadir el alumno a la lista
                                    alumnoDAM.add(alumno);
                                    
                           }//Fin while

                  } catch (FileNotFoundException e0) { //Excepcion si no encuentra el fichero
                           e0.printStackTrace(); // Muestra por pantalla la Excepcion
                  } catch (Exception e1) { // Excepcion genérica
                           System.out.println("Error excepcional");
                           e1.printStackTrace();
                  } finally {
                           try{
                                    if(s!=null)
                                    s.close();
                           }catch(Exception e2){
                                   System.out.println("Error excepcional");
                                    e2.printStackTrace();
                           }//Fin 2º try-catch
                  }//Fin try-catch

         
                  // Tamaño del ArrayList
                  System.out.println("Alumnos 1º Curso DAM: " + alumnoDAM.size());

                  // Iterator para recorrer alumnos
                  Iterator<Alumnos> itAlumnos = alumnoDAM.iterator();
                  System.out.println("NUMERO"+"- NOMBRE  "+"APELLIDO");
                  System.out.println("------------------------------------------------------------");
                  while (itAlumnos.hasNext()) {
                           Alumnos alumnos2 = itAlumnos.next();
                           System.out.println("  "+alumnos2.getNumero()+"- "+alumnos2.getNombre()+" "+alumnos2.getApellido());
                  }//Fin while

         }//Fin main
         
}//Fin class
