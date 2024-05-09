package ej_2_objetos;

/**
 * Clase objeto Alumnos
 * @author david
 */

public class Alumnos {
         //Atributos
         private String nombre;
         private String apellido;
         private int numero;

         //Constructor
         public Alumnos(String nombre, String apellido, int numero) {
                  this.nombre = nombre;
                  this.apellido = apellido;
                  this.numero = numero;
         }
        
         public Alumnos() {
         }

         //GET y SET
         public String getNombre() {
                  return nombre;
         }
         public String getApellido() {
                  return apellido;
         }
         public int getNumero() {
                  return numero;
         }

         public void setNombre(String nombre) {
                  this.nombre = nombre;
         }
         public void setApellido(String apellido) {
                  this.apellido = apellido;
         }
         public void setNumero(int numero) {
                  this.numero = numero;
         }
         

         //Método @Override toString
         @Override
         public String toString() {
                  StringBuilder sb = new StringBuilder();
                  sb.append("Número: ");
                  sb.append(numero);
                  sb.append("\nNombre: ");
                  sb.append(nombre);
                  sb.append("Apellido: ");
                  sb.append(apellido);

                  //Devolvemos el valor
                  return sb.toString();
         }//Fin toString
         
}//Fin class
