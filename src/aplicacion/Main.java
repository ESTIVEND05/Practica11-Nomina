package aplicacion;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import modelo.Empleado;
import modelo.EmpleadoFijo;
import modelo.EmpleadoPorHora;

public class Main {

    // Atributos de la clase Main (todos private).
    // Arreglo simple con los 12 meses, la cantidad no cambia.
    private static String[] meses = {
        "Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    // ArrayList para los empleados, crece cada vez que se agrega uno.
    // Aqui se guardan fijos y por hora juntos (polimorfismo).
    private static ArrayList<Empleado> empleados = new ArrayList<>();

    private static Scanner teclado = new Scanner(System.in);

    private static int numeroMes;

    public static void main(String[] args) {
        // Para que los montos salgan con punto decimal (4300.00) y no con coma
        Locale.setDefault(Locale.US);

        System.out.println("CÁLCULO DE NÓMINA");
        numeroMes = leerEntero("Ingrese el mes a trabajar (1-12): ", 1, 12);

        int opcion = 0;
        while (opcion != 3) {
            limpiarPantalla();
            System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1].toUpperCase());
            System.out.println("1 - Ingreso de nómina");
            System.out.println("2 - Impresión de nómina");
            System.out.println("3 - Salir");
            opcion = leerEntero("Seleccione una opción: ", 1, 3);

            switch (opcion) {
                case 1:
                    ingresoNomina();
                    break;
                case 2:
                    imprimirNomina();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa. ¡Hasta luego!");
                    break;
            }
        }
    }

    // Opcion 1 del menu principal
    private static void ingresoNomina() {
        int otro = 1;
        while (otro == 1) {
            limpiarPantalla();
            System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1].toUpperCase());
            System.out.println("INGRESO DE EMPLEADO A NÓMINA");
            System.out.println("Trabajadores actuales = " + empleados.size());
            System.out.println("1 - Empleado fijo");
            System.out.println("2 - Empleado por hora");
            int tipo = leerEntero("Seleccione el tipo de empleado: ", 1, 2);

            limpiarPantalla();
            // Aqui si se puede usar if para saber que objeto crear
            Empleado nuevo;
            if (tipo == 1) {
                nuevo = capturarEmpleadoFijo();
            } else {
                nuevo = capturarEmpleadoPorHora();
            }

            // Se agrega hasta que todos los datos ya fueron validados
            empleados.add(nuevo);
            System.out.printf("Pago de %s = Q %.2f%n", nuevo.getNombre(), nuevo.calcularPago());
            System.out.println();
            System.out.println("Empleado agregado correctamente.");
            System.out.println("Trabajadores actuales = " + empleados.size());
            System.out.println("¿Desea ingresar otro empleado?");
            System.out.println("1 - Sí, ingresar otro empleado");
            System.out.println("2 - Volver al menú principal");
            otro = leerEntero("Seleccione una opción: ", 1, 2);
        }
    }

    // Devuelve un objeto EmpleadoFijo con los datos que escribe el usuario
    private static Empleado capturarEmpleadoFijo() {
        System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1].toUpperCase());
        System.out.println("INGRESO DE EMPLEADO FIJO");
        String dpi = leerDpi();
        String nombre = leerNombre();
        double sueldo = leerNumero("Sueldo mensual (Q): ");
        double bono = leerNumero("Bonificación de este mes (Q): ");
        // dpi, nombre, sueldo y bono son los ARGUMENTOS que se mandan al constructor
        return new EmpleadoFijo(dpi, nombre, sueldo, bono);
    }

    private static Empleado capturarEmpleadoPorHora() {
        System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1].toUpperCase());
        System.out.println("INGRESO DE EMPLEADO POR HORA");
        String dpi = leerDpi();
        String nombre = leerNombre();
        double pagoHora = leerNumero("Pago por hora (Q): ");
        double horas = leerNumero("Horas trabajadas este mes: ");
        return new EmpleadoPorHora(dpi, nombre, pagoHora, horas);
    }

    // Opcion 2 del menu principal
    private static void imprimirNomina() {
        limpiarPantalla();
        System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1].toUpperCase());

        if (empleados.size() == 0) {
            System.out.println("No hay empleados registrados en la nómina de este mes");
        } else {
            System.out.printf("%-15s%-20s%-10s%12s%n", "DPI", "Nombre", "Tipo", "Pago (Q)");
            // Recorrido del ArrayList. Cada objeto sabe calcular su pago (polimorfismo),
            // por eso no hace falta preguntar con if de que tipo es.
            for (Empleado e : empleados) {
                System.out.printf("%-15s%-20s%-10s%12.2f%n",
                        e.getDpi(), e.getNombre(), e.obtenerTipo(), e.calcularPago());
            }
        }

        System.out.println("Total de trabajadores: " + empleados.size());
        System.out.printf("Total de nómina: Q %.2f%n", calcularTotal());
        System.out.println("Presione Enter para volver al menú principal...");
        teclado.nextLine();
    }

    // Metodo que devuelve un resultado: suma lo que se le paga a cada empleado
    private static double calcularTotal() {
        double total = 0; // variable local, solo existe dentro de este metodo
        for (Empleado e : empleados) {
            total = total + e.calcularPago();
        }
        return total;
    }

    // ----------- Metodos para leer y validar datos -----------

    // Lee un numero entero que este entre min y max
    private static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            try {
                int numero = Integer.parseInt(texto);
                if (numero >= min && numero <= max) {
                    return numero;
                }
                System.out.println("Opción inválida. Debe ser un número entre " + min + " y " + max + ".");
            } catch (NumberFormatException ex) {
                System.out.println("Error: debe ingresar un número entero.");
            }
        }
    }

    // Lee un numero decimal mayor o igual a 0
    private static double leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            try {
                double numero = Double.parseDouble(texto);
                if (Double.isNaN(numero) || Double.isInfinite(numero)) {
                    System.out.println("Error: el valor no es válido.");
                } else if (numero < 0) {
                    System.out.println("Error: no se permiten valores negativos.");
                } else {
                    return numero;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error: debe ingresar un valor numérico.");
            }
        }
    }

    // El DPI se guarda como String y debe tener 13 digitos
    private static String leerDpi() {
        while (true) {
            System.out.print("DPI: ");
            String dpi = teclado.nextLine().trim();
            if (dpi.matches("\\d{13}")) {
                return dpi;
            }
            System.out.println("Error: el DPI debe tener exactamente 13 dígitos.");
        }
    }

    private static String leerNombre() {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine().trim();
            if (!nombre.isEmpty()) {
                return nombre;
            }
            System.out.println("Error: el nombre no puede estar vacío.");
        }
    }

    // Como la consola del IDE no siempre se puede borrar, se imprimen lineas en blanco
    private static void limpiarPantalla() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
