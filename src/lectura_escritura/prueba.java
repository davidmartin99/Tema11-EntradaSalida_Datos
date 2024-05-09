package lectura_escritura;

import java.io.File;

/**
 *
 * @author david
 */
public class prueba {
         
         public static void main(String[] args) {
                  
                  File fichero = new File(".\\src\\lectura_escritura\\prueba.txt");
                  
                  if(fichero.exists()){
                           System.out.println("NOMBRE DEL FICHERO: "+fichero.getName());
                           System.out.println("RUTA: "+fichero.getPath());
                           System.out.println("RUTA ABSOLUTA: "+fichero.getAbsolutePath());
                           System.out.println("SE PUEDE LEER: "+fichero.canRead());
                           System.out.println("SE PUEDE ESCRIBIR: "+fichero.canWrite());
                           System.out.println("Tamaño del fichero: "+fichero.length());
                           System.out.println("");
                  }
                  
                  //Borrar entero fichero
                  fichero.delete();
                   
         }//Fin main
         
}//Fin class
