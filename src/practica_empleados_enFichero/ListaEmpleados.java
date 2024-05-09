package practica_empleados_enFichero;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class ListaEmpleados {

          public static void main(String[] args) {
                    MenuEmpleados.inicializarMenu();
          }//Fin main

          /**
          * Agrega un nuevo empleado al ArrayList de empleados de la empresa con
          * los datos introducidor por el usuario
          *
          * @param formatter El objeto DateTimeFormatter utilizado para
          * formatear las fechas de nacimiento y de ingreso del empleado.
          * @throws IllegalArgumentException Si alguno de los datos proporcionados por el usuario no es válido.
          * @throws InputMismatchException Si el salario proporcionado por el usuario no es un número.
          * @throws DateTimeParseException Si la fecha proporcionada por el usuario no está en el formato correcto (dd-MM-yyyy).
          */
          public static void agregarEmpleado(DateTimeFormatter formatter) {
                  Scanner scanner = new Scanner(System.in);
                  ArrayList<Empleados> empleadosEmpresa = cargarEmpleadosDesdeArchivo("Empleados.txt"); // Cargar empleados desde el archivo

                  // Nombre
                   String nombreNuevo;
                   do {
                            try {
                                     System.out.println("Ingrese el nombre del nuevo empleado:");
                                     nombreNuevo = scanner.nextLine();
                                     if (!nombreNuevo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                              throw new Exception("Nombre no válido. Debe contener solo letras y espacios.");
                                     }
                            } catch (Exception e) {
                                     System.err.println("ERROR: " + e.getMessage());
                                     nombreNuevo = ""; // Reiniciar el valor del nombre
                            }//Fin try-catch
                   } while (nombreNuevo.isEmpty());

                   // Apellido 
                   String apellidoNuevo;
                   do {
                            try {
                                     System.out.println("Ingrese el apellido del empleado:");
                                     apellidoNuevo = scanner.nextLine();
                                     if (!apellidoNuevo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                              throw new Exception("Apellido no válido. Debe contener solo letras y espacios.");
                                     }
                            } catch (Exception e) {
                                     System.err.println("ERROR: " + e.getMessage());
                                     apellidoNuevo = ""; // Reiniciar el valor del apellido
                            }//Fin try-catch
                   } while (apellidoNuevo.isEmpty());

                  // Fecha de Nacimiento
                  LocalDate fechaNacimientoNuevo = LocalDate.now();
                  boolean fechaNacimientoValida = false;
                  do {
                           try {
                                    System.out.println("Ingrese la fecha de nacimiento del empleado (dd-MM-yyyy):");
                                    String fechaNacimientoStr = scanner.nextLine();
                                    fechaNacimientoNuevo = LocalDate.parse(fechaNacimientoStr, formatter);
                                    if (fechaNacimientoNuevo.isAfter(LocalDate.now())) {
                                             System.err.println("ERROR: La fecha de nacimiento no puede ser en el futuro.");
                                    } else {
                                             fechaNacimientoValida = true;
                                    }
                           } catch (Exception e) {
                                    System.err.println("ERROR: Formato de fecha incorrecto. Intente nuevamente.");
                           }//Fin try-catch
                  } while (!fechaNacimientoValida);

                  // Fecha de Ingreso
                  LocalDate fechaIngresoNuevo = LocalDate.now();
                  boolean fechaIngresoValida = false;
                  do {
                           try {
                                    System.out.println("Ingrese la fecha de ingreso del empleado (dd-MM-yyyy):");
                                    String fechaIngresoStr = scanner.nextLine();
                                    fechaIngresoNuevo = LocalDate.parse(fechaIngresoStr, formatter);
                                    if (fechaIngresoNuevo.isAfter(LocalDate.now())) {
                                             System.err.println("ERROR: La fecha de ingreso no puede ser en el futuro.");
                                    } else if (fechaIngresoNuevo.isBefore(fechaNacimientoNuevo)) {
                                             System.err.println("ERROR: La fecha de ingreso no puede ser anterior a la fecha de nacimiento.");
                                    } else {
                                             fechaIngresoValida = true;
                                    }
                           } catch (Exception e) {
                                    System.err.println("ERROR: Formato de fecha incorrecto. Intente nuevamente.");
                           }//Fin try-catch
                  } while (!fechaIngresoValida);

                  // Puesto
                   String puestoNuevo;
                   do {
                            try {
                                     System.out.println("Ingrese el puesto del empleado:");
                                     puestoNuevo = scanner.nextLine();
                                     if (puestoNuevo.isEmpty()) {
                                              throw new Exception("El puesto no puede estar vacío.");
                                     }
                                     // Verificar si el puesto contiene números
                                     if (puestoNuevo.matches(".*\\d.*")) {
                                              throw new Exception("El puesto no puede contener números.");
                                     }
                            } catch (Exception e) {
                                     System.err.println("ERROR: " + e.getMessage());
                                     puestoNuevo = ""; // Reiniciar el valor del puesto
                            }//Fin try-catch
                   } while (puestoNuevo.isEmpty() || puestoNuevo.matches(".*\\d.*"));

                   // Salario
                  double salarioNuevo = 0;
                  do {
                           try {
                                    System.out.println("Ingrese el salario del empleado:");
                                    salarioNuevo = scanner.nextDouble();
                                    if (salarioNuevo <= 0) {
                                             System.err.println("ERROR: El salario debe ser mayor que cero.");
                                    }
                           } catch (Exception e) {
                                    System.err.println("ERROR: El salario debe ser un número válido.");
                                    scanner.nextLine(); // Limpiar el buffer del scanner
                           }//Fin try-catch
                  } while (salarioNuevo <= 0);

                  // Limpiar el buffer del scanner
                  scanner.nextLine();

                  // Una vez que se han validado todos los datos, se crea un nuevo empleado y se agrega a la lista.
                  Empleados nuevoEmpleado = new Empleados(nombreNuevo, apellidoNuevo, fechaNacimientoNuevo, fechaIngresoNuevo, puestoNuevo, salarioNuevo);
                  empleadosEmpresa.add(nuevoEmpleado);

                  // Se ordena la lista de empleados
                  Collections.sort(empleadosEmpresa, Comparator.comparing(Empleados::getApellidos).thenComparing(Empleados::getNombre));
                  guardarEmpleadosEnArchivo(empleadosEmpresa, "Empleados.txt");
                  System.out.println("Empleado añadido correctamente.");

                  scanner.close(); // Cerramos el scanner
         }//Fin agregarEmpleado


          
           /**
          * Elimina un empleado del ArrayList de empleados de la empresa según el nombre y apellido
          * Guarda el empleado eliminado en un archivo llamado "EmpleadosAntiguos.txt"
          *
          * @throws IllegalArgumentException Si el nombre o apellido proporcionado por el usuario no contiene caracteres válidos.
          */
          public static void eliminarEmpleado() {
                    boolean empleadoEncontrado = false;
                    Scanner scanner = new Scanner(System.in);
                    ArrayList<Empleados> empleadosEmpresa = cargarEmpleadosDesdeArchivo("Empleados.txt"); // Cargar empleados desde el archivo
                    do {
                              try {
                                        System.out.println("Ingrese el nombre del empleado a borrar:");
                                        String nombreBorrar = scanner.nextLine();
                                        if (!nombreBorrar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  throw new IllegalArgumentException("El nombre solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        System.out.println("Ingrese el apellido del empleado a borrar:");
                                        String apellidoBorrar = scanner.nextLine();
                                        if (!apellidoBorrar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                                  throw new IllegalArgumentException("El apellido solo puede contener letras y/o espacios.");
                                        }//Fin if

                                        Empleados empleadoEliminado = null;

                                        // Recorremos el ArrayList 
                                        for (int i = 0; i < empleadosEmpresa.size(); i++) {
                                                  Empleados empleado = empleadosEmpresa.get(i);
                                                  if (empleado.getNombre().equalsIgnoreCase(nombreBorrar) && empleado.getApellidos().equalsIgnoreCase(apellidoBorrar)) {
                                                           // Lo elimina de empleadosEmpresa
                                                            empleadoEliminado = empleadosEmpresa.remove(i); 
                                                            empleadoEncontrado = true;
                                                            // Modifica el archivo "Empleados.txt", sin el empleado borrado
                                                            guardarEmpleadosEnArchivo(empleadosEmpresa, "Empleados.txt");
                                                            // Agrega al archivo "EmpleadosAntiguos.txt" el empleado eliminado
                                                            guardarEmpleadoAntiguo(empleadoEliminado, "EmpleadosAntiguos.txt");
                                                            System.out.println("Se ha borrado el empleado.");
                                                            break;
                                                  }//Fin if
                                        }//Fin for

                                        if (!empleadoEncontrado) {
                                                  System.out.println("No se encontró el empleado.");
                                        }//Fin if
                              } catch (IllegalArgumentException e) {
                                       System.out.println("ERROR: " + e.getMessage());
                              } catch (Exception e1) {
                                       System.out.println("ERROR: " + e1.getMessage());
                              }//Fin try-catch
                    } while (!empleadoEncontrado);
          }//Fin eliminarEmpleado

          
          /**
         * Busca un empleado en la lista de empleados de la empresa según el nombre y apellido. 
          *
         * @throws IllegalArgumentException Si el nombre o apellido proporcionado por el usuario no contiene caracteres válidos.
          */
          public static void buscarEmpleado() {
                    boolean empleadoEncontrado = false;
                    Scanner scanner = new Scanner(System.in);
                    ArrayList<Empleados> empleadosEmpresa = cargarEmpleadosDesdeArchivo("Empleados.txt"); // Cargar empleados desde el archivo
                    try {
                              System.out.println("Ingrese el nombre del empleado a buscar:");
                              String nombreBuscar = scanner.nextLine();
                              if (!nombreBuscar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                        throw new IllegalArgumentException("El nombre solo puede contener letras y/o espacios.");
                              }//Fin if

                              System.out.println("Ingrese el apellido del empleado a buscar:");
                              String apellidoBuscar = scanner.nextLine();
                              if (!apellidoBuscar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                                        throw new IllegalArgumentException("El apellido solo puede contener letras y/o espacios.");
                              }//Fin if

                              // Un ArrayList que guarde los empleados encontrados 
                              ArrayList<Empleados> empleadosEncontrados = new ArrayList<>();

                              for (Empleados empleado : empleadosEmpresa) {
                                        if (empleado.getNombre().equalsIgnoreCase(nombreBuscar) && empleado.getApellidos().equalsIgnoreCase(apellidoBuscar)) {
                                                  empleadosEncontrados.add(empleado);
                                                  empleadoEncontrado = true;
                                        }//Fin if
                              }//Fin for

                              // Si los encuentra, imprime el ArrayList empleadosEncontrados
                              if (empleadoEncontrado) {
                                        System.out.println("Empleados encontrados:");
                                        for (Empleados empleado : empleadosEncontrados) {
                                                  System.out.println(empleado.toString());
                                        }//fin for
                              } else {
                                        System.out.println("No se encontró ningún empleado...");
                              }//Fin if-else
                    } catch (IllegalArgumentException e) {
                             System.out.println("ERROR: " + e.getMessage());
                    } catch (Exception e1) {
                              System.out.println("ERROR: " + e1.getMessage());
                    }//Fin try-catch
          }//Fin buscarEmpleado


          /**
          * Ordena el ArrayList de empleados de la empresa según la opción elegida  por el usuario. 
          * El usuario puede elegir ordenar por: antigüedad, salario o apellido.
          *
          * @throws IllegalArgumentException Si el usuario introduce una opción no válida.
          */
          public static void ordenarEmpleados() {
                    boolean salir = false;
                    Scanner scanner = new Scanner(System.in);
                    ArrayList<Empleados> empleadosEmpresa = cargarEmpleadosDesdeArchivo("Empleados.txt"); // Cargar empleados desde el archivo
                    do {
                              try {
                                        System.out.println("Seleccione la forma de ordenamiento:");
                                        System.out.println("a) Por antigüedad");
                                        System.out.println("b) Por salario");
                                        System.out.println("c) Por apellido");
                                        // Pasamos a minúsculas la opción siempre
                                        String ordenOpcion = scanner.nextLine().toLowerCase();

                                        // Declaramos el Comparator 
                                        Comparator<Empleados> comparador = null;

                                        // Elegimos el tipo de Comparator que va a ser
                                        switch (ordenOpcion) {
                                                  case "a":
                                                            comparador = Comparator.comparing(Empleados::getFechaIngreso);
                                                            break;
                                                  case "b":
                                                            comparador = Comparator.comparingDouble(Empleados::getSalario);
                                                            break;
                                                  case "c":
                                                            comparador = Comparator.comparing(Empleados::getApellidos).thenComparing(Empleados::getNombre);
                                                            break;
                                                  default:
                                                            throw new IllegalArgumentException("Opción no válida. Introduzca 'a', 'b' o 'c'.");
                                        }//Fin switch

                                        // Mostramos los empleados ordenados con un índice 1-, 2-, 3-, etc.
                                        if (comparador != null) {
                                                  Collections.sort(empleadosEmpresa, comparador);
                                                  System.out.println("Empleados ordenados:");
                                                  int i = 1;
                                                  for (Empleados empleado : empleadosEmpresa) {
                                                            System.out.println(i + "- " + empleado);
                                                            i++; //Aumentamos i en 1 cada vez 
                                                  }//Fin for
                                                  salir = true;
                                        }//Fin if
                              } catch (IllegalArgumentException e) {
                                       System.out.println("ERROR: " + e.getMessage());
                              } catch (Exception e1) {
                                       System.out.println("ERROR: " + e1.getMessage());
                              }//fin try-catch
                    } while (!salir);
          }//Fin ordenarEmpleados

          
          /**
          * Calcula el gasto total en salarios de todos los empleados de la empresa.
          *
          * @return la suma total de todos los salarios.
          */
          public static double gastoTotalSalarios() {
                    ArrayList<Empleados> empleadosEmpresa = cargarEmpleadosDesdeArchivo("Empleados.txt"); // Cargar empleados desde el archivo
                    double sumaSalarios = 0;
                    // Un for que recorra el ArrayList y vaya sumando los salarios
                    for (Empleados empEmpresa : empleadosEmpresa) {
                              sumaSalarios += empEmpresa.getSalario();
                    }//Fin for
                    return sumaSalarios;
          }//Fin gastoTotalSalarios

          
          /**
          * Guarda la información de los empleados de la empresa en un archivo.
          * Cada línea del archivo contendrá los datos de un empleado en el
          * formato
          * "nombre::apellidos::fechaNacimiento::fechaIngreso::puesto::salario".
          *
          * @param empleadosEmpresa el ArrayList de objetos Empleados que se desea
          * guardar en el archivo.
          * @param rutaArchivo La ruta del archivo en el cual se guardarán los
          * datos de los empleados.
          */
          public static void guardarEmpleadosEnArchivo(ArrayList<Empleados> empleadosEmpresa, String rutaArchivo) {
                    try (PrintWriter writer = new PrintWriter(new FileWriter(".\\src\\practica_empleados_enFichero\\Empleados.txt"))) {
                             // Se recorre el ArrayList de empleados y se escribe cada empleado en una línea del archivo
                              for (Empleados empleado : empleadosEmpresa) {
                                        writer.println(empleado.getNombre() + "::" + empleado.getApellidos() + "::" + empleado.getFechaNacimiento() + "::" + empleado.getFechaIngreso() + "::" + empleado.getPuesto() + "::" + empleado.getSalario());
                              }//Fin for
                    } catch (IOException e) {
                              System.err.println("ERROR: No se pudo guardar el archivo. Detalles: " + e.getMessage());
                    }//Fin try-catch
          }//Fin guardarEmpleadosEnArchivo

          
          /**
          * Guarda la información de un empleado antiguo en un archivo separado.
          * La línea del archivo contendrá los datos del empleado en el formato
          * "nombre::apellidos::fechaNacimiento::fechaIngreso::fechaSalida".
          *
          * @param empleado el ArrayList de objetos Empleados que se desea guardar como
          * empleado antiguo.
          * @param rutaArchivo La ruta del archivo en el cual se guardarán los
          * datos del empleado antiguo.
          */
          public static void guardarEmpleadoAntiguo(Empleados empleado, String rutaArchivo) {
                  try (PrintWriter writer = new PrintWriter(new FileWriter(".\\src\\practica_empleados_enFichero\\EmpleadosAntiguos.txt", true))) {
                           // Se escribe el empleado antiguo en una línea del archivo
                           writer.println(empleado.getNombre() + "::" + empleado.getApellidos() + "::" + empleado.getFechaNacimiento() + "::" + empleado.getFechaIngreso() + "::" + LocalDate.now());
                  } catch (IOException e) {
                           System.err.println("ERROR: No se pudo guardar el archivo de empleados antiguos. Detalles: " + e.getMessage());
                  }//Fin try-catch
         }//Fin guardarEmpleadoAntiguo

          
          /**
          * Carga la información de los empleados desde el archivo 
          * y devuelve un ArrayList de objetos de tipo Empleados.
          *
          * @param rutaArchivo La ruta del archivo desde el cual se cargarán los
          * datos de los empleados.
          * @return Un ArrayList de objetos Empleados que contiene la información
          * de los empleados cargada desde el archivo.
          * @throws RuntimeException Si ocurre un error al intentar acceder o leer el archivo.
          */
         public static ArrayList<Empleados> cargarEmpleadosDesdeArchivo(String rutaArchivo) {
                  ArrayList<Empleados> empleados = new ArrayList<>();
                  try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\practica_empleados_enFichero\\Empleados.txt"))) {
                           // Creamos un Iterator que va a recorrer línea a línea el ArrayList 
                           //  .reader.lines() obtiene un flujo de líneas del archivo a través del BufferedReader.
                           Iterator<String> lista = reader.lines().iterator();
                           // .hasNext() comprueba que hay más líneas para leer en el archivo, es decir que la siguiente línea no es null
                           while (lista.hasNext()) {
                                    String linea = lista.next(); // .next() va saltando línea a línea
                                    String[] partes = linea.split("::"); // Declaramos el delimitador con .split()
                                    String nombre = partes[0];
                                    String apellidos = partes[1];
                                    LocalDate fechaNacimiento = LocalDate.parse(partes[2]);
                                    LocalDate fechaIngreso = LocalDate.parse(partes[3]);
                                    String puesto = partes[4];
                                    double salario = Double.parseDouble(partes[5]);
                                    // Añadimos el empleado leído del fichero al Objeto Empleados
                                    empleados.add(new Empleados(nombre, apellidos, fechaNacimiento, fechaIngreso, puesto, salario));
                           }//Fin while Iterator
                  } catch (FileNotFoundException e1) {
                           System.err.println("ERROR: No se puede encontrar el archivo" + rutaArchivo);
                           e1.printStackTrace();
                           System.exit(0);
                  } catch (IOException e) {
                           System.err.println("ERROR: No se pudo leer el archivo. Detalles: " + e.getMessage());
                           e.printStackTrace();
                           System.exit(0);
                  }//Fin try-catch
                  return empleados;
         }//Fin cargarEmpleadosDesdeArchivo
          
         
}//Fin class
