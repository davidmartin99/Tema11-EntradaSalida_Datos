package fichero_practica_empleados_Panel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Clase principal donde se encuentra inicializado el menú y sus métodos
 * 
 * @version 1.0
 * @author david
 */
public class ListaEmpleados {
          
          public static void main(String[] args) {
                    MenuEmpleados.inicializarMenu();
          }//Fin main
          
          /**
           * Método para ir agregando nuevos empleados
           * 
           * @param empleadosEmpresa
           * @param formatter
           * @param teclado 
           */
          public static void agregarEmpleado(ArrayList<Empleados> empleadosEmpresa, DateTimeFormatter formatter ) {
                    // boolean para comprobar que los datos introducidos sean válidos
                    boolean datosCorrectos = false;
                    do {
                              try {
                                        String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nombre del nuevo empleado:");
                                        // Comprobar que el nombre son letras o espacios en blanco
                                        if (!nombreNuevo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El nombre solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        // Igual para el apellido
                                        String apellidoNuevo = JOptionPane.showInputDialog("Ingrese el apellido del empleado:");
                                        if (!apellidoNuevo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El apellido solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del empleado (dd-MM-yyyy):");
                                        LocalDate fechaNacimientoNuevo = LocalDate.parse(fechaNacimientoStr, formatter);

                                        String fechaIngresoStr = JOptionPane.showInputDialog("Ingrese la fecha de ingreso del empleado (dd-MM-yyyy):");
                                        LocalDate fechaIngresoNuevo = LocalDate.parse(fechaIngresoStr, formatter);

                                        // Comprobar que la fecha de ingreso es posterior a la de nacimiento
                                        if (fechaNacimientoNuevo.isAfter(LocalDate.now()) || fechaIngresoNuevo.isAfter(LocalDate.now()) || fechaNacimientoNuevo.isAfter(fechaIngresoNuevo)) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("Fecha no válida. La fecha de nacimiento no puede ser mayor que la de ingreso y ambas fechas deben estar en el pasado.");
                                        }//Fin if

                                        String puestoNuevo = JOptionPane.showInputDialog("Ingrese el puesto del empleado:");
                                        double salarioNuevo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario del empleado:"));


                                        // Comprobamos que el salario no sea negativo
                                        if (salarioNuevo <= 0) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El salario debe ser un valor positivo.");
                                        }//Fin if

                                        // Creamos un nuevo empleado con los datos introducidos
                                        Empleados nuevoEmpleado = new Empleados(nombreNuevo, apellidoNuevo, fechaNacimientoNuevo, fechaIngresoNuevo, puestoNuevo, salarioNuevo);

                                        // Lo añadimos al ArrayList
                                        empleadosEmpresa.add(nuevoEmpleado);
                                        JOptionPane.showMessageDialog(null, "Empleado añadido correctamente.");
                                        datosCorrectos = true;
                                        //Fin try
                              } catch (InputMismatchException e1) {
                                        JOptionPane.showMessageDialog(null, "ERROR: el salario debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (DateTimeParseException e2) {
                                        JOptionPane.showMessageDialog(null, "ERROR: Fecha no válida, ingrese la fecha en el formato correcto (dd-MM-yyyy)", "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (IllegalArgumentException e3) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (Exception e4) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e4.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              }//Fin try-catch
                              
                    } while (!datosCorrectos);
          }//Fin agregarEmpleado

          
          /**
           * Método para eliminar un empleado
           * @param empleadosEmpresa
           * @param teclado 
           */
          public static void eliminarEmpleado(ArrayList<Empleados> empleadosEmpresa ) {
                    // boolean para comprobar que los datos introducidos sean válidos
                    boolean empleadoEncontrado = false;
                    do {
                              try {
                                        String nombreBorrar = JOptionPane.showInputDialog("Ingrese el nombre del empleado a borrar:");
                                        // Comprobar que el nombre son letras o espacios en blanco
                                        if (!nombreBorrar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El nombre solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        String apellidoBorrar = JOptionPane.showInputDialog("Ingrese el apellido del empleado a borrar:");
                                        // Comprobar que el apellido son letras o espacios en blanco
                                        if (!apellidoBorrar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El apellido solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        // Recorremos el ArrayList buscando los datos (nombre y apellido)
                                        for (int i = 0; i < empleadosEmpresa.size(); i++) {
                                                  Empleados empleado = empleadosEmpresa.get(i);

                                                  // Si lo encuentra 
                                                  if (empleado.getNombre().equalsIgnoreCase(nombreBorrar) && empleado.getApellidos().equalsIgnoreCase(apellidoBorrar)) {
                                                            empleadosEmpresa.remove(i);
                                                            empleadoEncontrado = true;
                                                            JOptionPane.showMessageDialog(null, "Se ha borrado el empleado.");
                                                            break;
                                                  }//Fin if
                                        }//Fin for

                                        // Si no lo encuentra
                                        if (!empleadoEncontrado) {
                                                  JOptionPane.showMessageDialog(null, "No se encontró el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                                        }//Fin if
                              } catch (InputMismatchException e1) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (IllegalArgumentException e2) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e2.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (Exception e3) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e3.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              }//Fin try-catch
                    } while (!empleadoEncontrado);
          }//Fin eliminarEmpleado

          
          /**
           * Método para encontrar a un empleado 
           * @param empleadosEmpresa
           * @param teclado 
           */
          public static void buscarEmpleado(ArrayList<Empleados> empleadosEmpresa ) {
                    // boolean para comprobar que los datos introducidos sean válidos
                    boolean empleadoEncontrado = false;
                    do {
                              try {
                                        String nombreBuscar = JOptionPane.showInputDialog("Ingrese el nombre del empleado a buscar:");
                                        // Comprobar que el nombre son letras o espacios en blanco
                                        if (!nombreBuscar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El nombre solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        String apellidoBuscar = JOptionPane.showInputDialog("Ingrese el apellido del empleado a buscar:");
                                        // Comprobar que el apellido son letras o espacios en blanco
                                        if (!apellidoBuscar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  // Creamos una excepcion
                                                  throw new IllegalArgumentException("El apellido solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        for (Empleados empleado : empleadosEmpresa) {
                                                  // Si encuentra al empleado, imprime sus datos
                                                  if (empleado.getNombre().equalsIgnoreCase(nombreBuscar) && empleado.getApellidos().equalsIgnoreCase(apellidoBuscar)) {
                                                            JOptionPane.showMessageDialog(null, "Empleado encontrado:\n" + empleado.toString());
                                                            empleadoEncontrado = true;
                                                            break;
                                                  }//Fin if
                                        }//Fin for

                                        // Si no lo encuentra
                                        if (!empleadoEncontrado) {
                                                  JOptionPane.showMessageDialog(null, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                                        }//Fin if
                              } catch (InputMismatchException e1) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (IllegalArgumentException e2) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e2.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (Exception e3) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e3.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              }//Fin try-catch
                    } while (!empleadoEncontrado);
          }//Fin buscarEmpleado
          

          /**
           * Método para ordenar los empleados
           *
           * @param empleadosEmpresa
           * @param teclado
           */
          public static void ordenarEmpleados(ArrayList<Empleados> empleadosEmpresa) {
                    boolean salir = false;
                    do {                              
                              try{
                                        // Mostramos las opciones al usuario
                                        String ordenOpcion = JOptionPane.showInputDialog(
                                                null,
                                                "Seleccione la forma de ordenamiento:\n"
                                                + "a) Por antigüedad\n"
                                                + "b) Por salario\n"
                                                + "c) Por apellido",
                                                "Ordenar Empleados",
                                                JOptionPane.PLAIN_MESSAGE);
                                        // Pasamos a minúscula directamente 
                                        
                                        if (ordenOpcion == null) {
                                                  salir = true; // Si el usuario cierra la ventana del diálogo, salimos del bucle.
                                                  continue;
                                        }//Fin if
                                        
                                        ordenOpcion.toLowerCase();

                                        // Cuando son objetos, el Collections.sort() no funciona
                                        // Hay que ponerlo con un nuevo Comparator
                                        Comparator<Empleados> comparador = null;

                                        switch (ordenOpcion) {
                                                  case "a":
                                                            comparador = Comparator.comparing(Empleados::getFechaIngreso);
                                                            break;
                                                  case "b":
                                                            comparador = Comparator.comparingDouble(Empleados::getSalario);
                                                            break;
                                                  case "c":
                                                            comparador = Comparator.comparing(Empleados::getApellidos);
                                                            break;
                                                  default:
                                                            System.err.println("Opción no válida. Introduzca 'a', 'b' o 'c'.");
                                                            break;
                                        }//Fin switch

                                        if (comparador != null) {
                                                  Collections.sort(empleadosEmpresa, comparador);
                                                  StringBuilder listaOrdenada = new StringBuilder();
                                                  listaOrdenada.append("Empleados ordenados:\n");
                                                  
                                                  // Usamos un Iterator para recorrer la lista de empleados ordenados
                                                  Iterator<Empleados> iteratorEmpleados = empleadosEmpresa.iterator();
                                                  int i = 1;
                                                  for (Empleados empleado : empleadosEmpresa) {
                                                            listaOrdenada.append(i).append(". ").append(empleado).append("\n");
                                                            i++;
                                                  }//Fin for
                                                  JOptionPane.showMessageDialog(null, listaOrdenada.toString(), "Empleados Ordenados", JOptionPane.INFORMATION_MESSAGE);

                                                  salir = true; // Ya se ha seleccionado una opcion buena, salimos del do-while
                                        }//Fin if
                                        
                              } catch (IllegalArgumentException e1) {  //Excepcion del default
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                              } catch (Exception e2) {
                                        JOptionPane.showMessageDialog(null, "ERROR: " + e2.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                              }//Fin try-catch
                    } while (!salir);
          }//Fin ordenarEmpleados
          
        
          /**
           * Método para calcular el gasto total de los empleados 
           * @param empleadosEmpresa
           * @return sumaSalarios
           */
          public static double gastoTotalSalarios (ArrayList<Empleados> empleadosEmpresa) {
                    // Sumamos todos los salarios
                    double sumaSalarios = 0;
                    // For para ir recorriendo los salarios de los empresarios y sumarlos
                    for (Empleados empEmpresa : empleadosEmpresa) {
                              sumaSalarios += empEmpresa.getSalario();
                    }//Fin for
                    // Devolvemos la suma total de los salarios de los empleados
                    return sumaSalarios;
          }//Fin gastoTotalSalarios
          
}//Fin class
