
package tema11.lectura_escritura;

import java.io.*;

/**
 *
 * @author alumno
 */
public class escribir {
    public static void main(String[] args){
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try{
            fichero = new FileWriter(".\\src\\tema11\\lectura_escritura\\prueba.txt"); // Creamos el fichero
            pw = new PrintWriter(fichero);
            
            for (int i = 0; i < 10; i++) {
                pw.println("linea " + (i+1)); //De esta forma imprimimos en el fichero
                
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }finally{ // Haya excepción o no asegurarse de que se cierra el archivo.
            try{
                if(null != fichero)
                    fichero.close();
            } catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
