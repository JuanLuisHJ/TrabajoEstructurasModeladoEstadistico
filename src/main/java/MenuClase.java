import Clases.Norma;
import Clases.Clase;
import Comparadores.ComparadorNombreClase;
import Comparadores.ComparadorNombreNorma;

import java.io.*;
import java.util.*;

public class MenuClase{
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void MenuClase() throws IOException {
        while (true) {
            System.out.println("1. Ver Clase.");
            System.out.println("2. Crear Clase.");
            System.out.println("3. Editar Clase.");
            System.out.println("4. Eliminar Clase.");
            System.out.println("0. Cancelar");
            String opcionA = input.readLine();
            if (opcionA.equals("1")) {
                if (Main.clases.isEmpty()) {
                    System.out.println("No hay clases guardadas en la base de datos. ");
                } else {
                    for (Clase clase : Main.clases) {
                        System.out.println(clase);
                    }
                }

            } else if (opcionA.equals("2")) {
                System.out.println("Para crear una clase, por favor ingrese en orden los datos solicitados:");
                System.out.println("Ingrese el nombre de la clase: ");
                String nombre = input.readLine();
                if (nombre.equals("")) {
                    System.out.println("No ingreso el nombre.");
                    return;
                }
                for (Clase clase : Main.clases) {
                    if (clase.Nombre.equalsIgnoreCase(nombre)) {
                        System.out.println("El nombre ingresado ya se encuentra registrado para otra clase.");
                        return;
                    }
                }
                System.out.println("Ingrese el nombre de la norma asociada a la clase: ");
                String nombrenorm = input.readLine();
                if (nombrenorm.equals("")) {
                    System.out.println("No ingreso el nombre de la norma.");
                    return;
                }
                for (Norma norma1 : Main.normas) {
                    if (!norma1.Nombre.equalsIgnoreCase(nombrenorm)) {
                        System.out.println("No hay normas con ese nombre guardadas en la base de datos. ");
                        return;
                    }
                }
                System.out.println("Ingrese el voltaje de la prueba: ");
                String voltaje = input.readLine();
                double voltajer = Double.parseDouble(voltaje);
                System.out.println("Ingrese la corriente de la prueba: ");
                String corriente = input.readLine();
                double corrienter = Double.parseDouble(corriente);
                System.out.println("Ingrese el voltaje máximo de la prueba: ");
                String voltajem = input.readLine();
                double voltajemr = Double.parseDouble(voltajem);
                System.out.println("Ingrese la corriente máxima de la prueba: ");
                String corrientem = input.readLine();
                double corrientemr = Double.parseDouble(corrientem);
                System.out.println("Ingrese el tiempo de la prueba: ");
                String tiempo = input.readLine();
                double tiempor = Double.parseDouble(tiempo);
                Clase clasenueva = new Clase(nombre, nombrenorm, voltajer, corrienter, voltajemr, corrientemr, tiempor);
                Main.clases.add(clasenueva);
                Collections.sort(Main.normas, new ComparadorNombreNorma());
                int indicenorma = Collections.binarySearch(Main.normas, new Norma(nombrenorm, null), new ComparadorNombreNorma());
                Norma normadelaclase = Main.normas.get(indicenorma);
                normadelaclase.Clases.add(nombre);
                System.out.println("Operación realizada con éxito");

            } else if (opcionA.equals("3")) {
                if (Main.clases.isEmpty()) {
                    System.out.println("No hay clases guardadas en la base de datos. ");
                    return;
                } else {
                    while (true) {
                        System.out.println("Menu de edición de clases: ");
                        System.out.println("1. Editar por nombre. ");
                        System.out.println("0. Salir. ");
                        String opcion = input.readLine();
                        if (opcion.equals("1")) {
                            System.out.println("Ingrese el nombre de la clase: ");
                            String nombre = input.readLine();
                            if (nombre.equals("")) {
                                System.out.println("No ingreso el nombre. ");
                                return;
                            }
                            for (Clase clase : Main.clases) {
                                if (!clase.Nombre.equalsIgnoreCase(nombre)) {
                                    System.out.println("El nombre ingresado no se encuentra asignado a ninguna clase. ");
                                    return;
                                }
                            }
                            Main.clases.sort(new ComparadorNombreClase());
                            int indiceclasedit = Collections.binarySearch(Main.clases, new Clase(nombre, null, 0, 0, 0, 0, 0), new ComparadorNombreClase());
                            Clase clasedit = Main.clases.get(indiceclasedit);
                            System.out.println("El nombre de la clase seleccionada es: " + clasedit.Nombre + ".");
                            System.out.println("¿Desea modificar el nombre? (Y/N)");
                            String opcionnom = input.readLine();
                            if (opcionnom.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese el nuevo nombre: ");
                                String nuevonom = input.readLine();
                                if (nuevonom.equals("")) {
                                    System.out.println("No ingreso el nombre. ");
                                    return;
                                }
                                for (Clase clase : Main.clases) {
                                    if (clase.Nombre.equalsIgnoreCase(nuevonom)) {
                                        System.out.println("El nombre ingresado ya se encuentra asignado a otra clase. ");
                                        return;
                                    }
                                }
                                clasedit.Nombre = nuevonom;
                                System.out.println("Operación realizada con éxito. ");
                            }
                            System.out.println("El nombre de la norma asociada a la clase es: " + clasedit.Norma + ".");
                            System.out.println("¿Desea modificar el nombre de la norma asociada? (Y/N)");
                            String opcionnombrenorm = input.readLine();
                            if (opcionnombrenorm.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese el nuevo nombre de la norma asociada: ");
                                String nuevonombrenorm = input.readLine();
                                if (nuevonombrenorm.equals("")) {
                                    System.out.println("No ingreso el nombre. ");
                                    return;
                                }
                                for (Norma norma : Main.normas) {
                                    if (!norma.Nombre.equalsIgnoreCase(nuevonombrenorm)) {
                                        System.out.println("La norma ingresada no se encuentra guardada en la base de datos. ");
                                        return;
                                    }
                                }
                                clasedit.Norma = nuevonombrenorm;
                                System.out.println("Operación realizada con éxito. ");
                            }
                            System.out.println("El voltaje nominal de prueba de la clase seleccionada es: " + clasedit.VoltajePrueba + ".");
                            System.out.println("¿Desea modificar el voltaje nominal de prueba de la clase? (Y/N)");
                            String opcionvol = input.readLine();
                            if (opcionvol.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese el nuevo voltaje nominal de prueba: ");
                                String nuevovol = input.readLine();
                                if (nuevovol.equals("")) {
                                    System.out.println("No ingreso el voltaje nominal de prueba. ");
                                    return;
                                }
                                clasedit.VoltajePrueba = Double.parseDouble(nuevovol);
                                System.out.println("Operación realizada con éxito. ");
                            }
                            System.out.println("La corriente nominal de prueba de la clase seleccionada es: " + clasedit.CorrientePrueba + ".");
                            System.out.println("¿Desea modificar la corriente nominal de prueba de la clase? (Y/N)");
                            String opcioncorr = input.readLine();
                            if (opcioncorr.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese la nueva corriente nominal de prueba: ");
                                String nuevocorr = input.readLine();
                                if (nuevocorr.equals("")) {
                                    System.out.println("No ingreso la corriente nominal de prueba. ");
                                    return;
                                }
                                clasedit.CorrientePrueba = Double.parseDouble(nuevocorr);
                                System.out.println("Operación realizada con éxito. ");
                            }
                            System.out.println("El voltaje máximo de prueba de la clase seleccionada es: " + clasedit.VoltajeMaximo + ".");
                            System.out.println("¿Desea modificar el voltaje máximo de prueba de la clase? (Y/N)");
                            String opcionvolm = input.readLine();
                            if (opcionvolm.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese el nuevo voltaje máximo de prueba: ");
                                String nuevovolm = input.readLine();
                                if (nuevovolm.equals("")) {
                                    System.out.println("No ingreso el voltaje máximo de prueba. ");
                                    return;
                                }
                                clasedit.VoltajeMaximo = Double.parseDouble(nuevovolm);
                                System.out.println("Operación realizada con éxito. ");
                            }
                            System.out.println("La corriente máxima de prueba de la clase seleccionada es: " + clasedit.CorrienteMaxima + ".");
                            System.out.println("¿Desea modificar la corriente máxima de prueba de la clase? (Y/N)");
                            String opcioncorrm = input.readLine();
                            if (opcioncorrm.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese la nueva corriente máxima de prueba: ");
                                String nuevocorrm = input.readLine();
                                if (nuevocorrm.equals("")) {
                                    System.out.println("No ingreso la corriente máxima de prueba. ");
                                    return;
                                }
                                clasedit.CorrienteMaxima = Double.parseDouble(nuevocorrm);
                                System.out.println("Operación realizada con éxito. ");
                            }
                            System.out.println("El tiempo de prueba de la clase seleccionada es: " + clasedit.Tiempo + ".");
                            System.out.println("¿Desea modificar el tiempo de prueba de la clase? (Y/N)");
                            String opciontiem = input.readLine();
                            if (opciontiem.equalsIgnoreCase("Y")) {
                                System.out.println("Ingrese el nuevo tiempo de prueba: ");
                                String nuevotiem = input.readLine();
                                if (nuevotiem.equals("")) {
                                    System.out.println("No ingreso el tiempo de prueba. ");
                                    return;
                                }
                                clasedit.Tiempo = Double.parseDouble(nuevotiem);
                                System.out.println("Operación realizada con éxito. ");
                            }

                        } else if (opcion.equals("0")) {
                            break;

                        } else {
                            System.out.println("Los datos ingresados no son validos");
                        }
                    }
                }

            } else if (opcionA.equals("4")) {
                while (true) {
                    System.out.println("Menu de borrado de clases: ");
                    System.out.println("1. Borrar por nombre. ");
                    System.out.println("0. Salir. ");
                    String opcion = input.readLine();
                    if (opcion.equals("1")) {
                        System.out.println("Ingrese el nombre de la clase: ");
                        String nombre = input.readLine();
                        if (nombre.equals("")) {
                            System.out.println("No ingreso el nombre. ");
                            return;
                        }
                        for (Clase clase : Main.clases) {
                            if (!clase.Nombre.equalsIgnoreCase(nombre)) {
                                System.out.println("El nombre ingresado no se encuentra asignado a ninguna clase. ");
                                return;
                            }
                        }
                        Main.clases.sort(new ComparadorNombreClase());
                        int indiceclaseborrado = Collections.binarySearch(Main.clases, new Clase(nombre, null, 0, 0, 0, 0, 0), new ComparadorNombreClase());
                        Main.dispositivos.remove(indiceclaseborrado);
                        System.out.println("Operación realizada con éxito. ");

                    } else if (opcion.equals("0")) {
                        break;

                    } else {
                        System.out.println("Los datos ingresados no son validos");
                    }
                }

            } else if (opcionA.equals("0")) {
                break;

            } else {
                System.out.println("La opción ingresada no es válida");
            }
        }
    }
}