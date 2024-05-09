package alumnos11;

/**
 * Clase Objeto Alumno
 * @author david
 */
public class Alumno {
         //Atributos
         private String nombreCompleto;
         
         //Constructor
         public Alumno(String nombre) {
                  this.nombreCompleto = nombreCompleto;
         }

         public Alumno() {
         }
         
         //GET y SET
         public String getNombreCompleto() {
                  return nombreCompleto;
         }
         
         public void setNombreCompleto(String nombreCompleto) {
                  this.nombreCompleto = nombreCompleto;
         }
         
         //Método @Override toString
         @Override
         public String toString(){
                  StringBuilder sb = new StringBuilder();
                  sb.append("\nNombre Completo: ");
                  sb.append(nombreCompleto);
                
                  //Devolvemos el valor
                  return sb.toString();
         }//Fin toString
         
}//Fin class
