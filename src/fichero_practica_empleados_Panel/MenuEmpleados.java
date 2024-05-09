package fichero_practica_empleados_Panel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Menú dónde muestra las opciones a elegir y ejecuta los métodos elegidos
 * 
 * @version 1.0
 * @author david
 */
public class MenuEmpleados {
          public static void inicializarMenu() {
                    // Creo el ArrayList con el tipo de dato objeto Alumnos
                    ArrayList<Empleados> empleadosEmpresa = new ArrayList<Empleados>();

                    // Formateo la fecha para que el patron sea día-mes-año.
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    // Añado los empleados
                    Empleados empleado1 = new Empleados("Juan", "Torres", LocalDate.parse("01-01-1960", formatter), LocalDate.parse("24-05-1980", formatter), "Jefe", 60000);
                    empleadosEmpresa.add(empleado1); //Lo añadimos al ArrayList

                    Empleados empleado2 = new Empleados("Sara", "Gonzalez", LocalDate.parse("02-05-1980", formatter), LocalDate.parse("03-06-1999", formatter), "Secretaria", 25000);
                    empleadosEmpresa.add(empleado2); //Lo añadimos al ArrayList

                    Empleados empleado3 = new Empleados("Elena", "Sanchez", LocalDate.parse("03-09-1990", formatter), LocalDate.parse("02-11-2010", formatter), "TecnicoFP", 30000);
                    empleadosEmpresa.add(empleado3); //Lo añadimos al ArrayList

                    Empleados empleado4 = new Empleados("Pepe", "Uriel", LocalDate.parse("04-10-1991", formatter), LocalDate.parse("01-10-2015", formatter), "Administrativo", 24000);
                    empleadosEmpresa.add(empleado4); //Lo añadimos al ArrayList

                    Empleados empleado5 = new Empleados("David", "Martin", LocalDate.parse("17-04-1999", formatter), LocalDate.parse("04-03-2025", formatter), "Programador", 50000);
                    empleadosEmpresa.add(empleado5); //Lo añadimos al ArrayList

                    // Menú
                    boolean salir = false;

                    do {
                              String opcionStr = JOptionPane.showInputDialog(
                                      null,
                                      "----- Menú -----\n"
                                      + "1. Añadir empleado\n"
                                      + "2. Eliminar empleado\n"
                                      + "3. Buscar empleado\n"
                                      + "4. Imprimir empleados ordenados por: \n"
                                      + "5. Calcular el gasto total de los empleados.\n"
                                      + "6. Salir\n"
                                      + "Ingrese el número de la opción deseada:",
                                      "Menú",
                                      JOptionPane.PLAIN_MESSAGE);

                              if (opcionStr == null) {
                                        salir = true; // Si el usuario cierra la ventana del diálogo, salimos del bucle.
                                        continue;
                              }

                              try {
                                        int opcion = Integer.parseInt(opcionStr);

                                        switch (opcion) {
                                                  case 1:
                                                            ListaEmpleados.agregarEmpleado(empleadosEmpresa, formatter);
                                                            break;
                                                  case 2:
                                                            ListaEmpleados.eliminarEmpleado(empleadosEmpresa);
                                                            break;
                                                  case 3:
                                                            ListaEmpleados.buscarEmpleado(empleadosEmpresa);
                                                            break;
                                                  case 4:
                                                            ListaEmpleados.ordenarEmpleados(empleadosEmpresa);
                                                            break;
                                                  case 5:
                                                            JOptionPane.showMessageDialog(null, "El gasto total de los empleados es: " + ListaEmpleados.gastoTotalSalarios(empleadosEmpresa), "Gasto Total", JOptionPane.INFORMATION_MESSAGE);
                                                            break;
                                                  case 6:
                                                            salir = true;
                                                            JOptionPane.showMessageDialog(null, "¡Hasta luego!", "Salida", JOptionPane.INFORMATION_MESSAGE);
                                                            break;
                                                  default:
                                                            throw new IllegalArgumentException("Opción no válida. Debe ser una opción disponible (1 - 6).");
                                        }

                              } catch (NumberFormatException e1) {
                                        JOptionPane.showMessageDialog(null, "Debe introducir un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (IllegalArgumentException e2) {  //Excepcion del default
                                        JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (Exception e3) {
                                        JOptionPane.showMessageDialog(null, e3.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              }

                    } while (!salir);

          }//Fin main

}//Fin class
