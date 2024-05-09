package fichero_practica_empleados_Panel;

import java.time.LocalDate;

/**
 * Clase empleados que contiene Nombre, Apellidos, FechaNacimiento, FechaIngreso, Puesto y Salario.
 * 
 * @author david
 */
public class Empleados {
          // Atributos
          private String nombre;
          private String apellidos;
          private LocalDate fechaNacimiento;
          private LocalDate fechaIngreso;
          private String puesto;
          private double salario;
          
          // Constructor
          public Empleados(){
                    
          }

          public Empleados(String nombre, String apellidos, LocalDate fechaNacimiento, LocalDate fechaIngreso, String puesto, double salario) {
                    this.nombre = nombre;
                    this.apellidos = apellidos;
                    this.fechaNacimiento = fechaNacimiento;
                    this.fechaIngreso = fechaIngreso;
                    this.puesto = puesto;
                    this.salario = salario;
          }//Fin Constructor
          
          // Métodos GET y SET
          public String getNombre() {
                    return nombre;
          }
          public String getApellidos() {
                    return apellidos;
          }
          public LocalDate getFechaNacimiento() {
                    return fechaNacimiento;
          }
          public LocalDate getFechaIngreso() {
                    return fechaIngreso;
          }
          public String getPuesto() {
                    return puesto;
          }
          public double getSalario() {
                    return salario;
          }

          public void setNombre(String nombre) {
                    this.nombre = nombre;
          }
          public void setApellidos(String apellidos) {
                    this.apellidos = apellidos;
          }
          public void setFechaNacimiento(LocalDate fechaNacimiento) {
                    this.fechaNacimiento = fechaNacimiento;
          }
          public void setFechaIngreso(LocalDate fechaIngreso) {
                    this.fechaIngreso = fechaIngreso;
          }
          public void setPuesto(String puesto) {
                    this.puesto = puesto;
          }
          public void setSalario(double salario) {
                    this.salario = salario;
          }
          
          // Método toString
          @Override
          public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append("\nNombre: ");
                    sb.append(nombre);
                    sb.append("\nApellidos: ");
                    sb.append(apellidos);
                    sb.append("\nFecha de Nacimiento: ");
                    sb.append(fechaNacimiento);
                    sb.append("\nFecha de Ingreso: ");
                    sb.append(fechaIngreso);
                    sb.append("\nPuesto: ");
                    sb.append(puesto);
                    sb.append("\nSalario: ");
                    sb.append(salario);
                    
                    //Devolvemos el valor
                    return sb.toString();
          }//Fin toString
          
}//Fin class
