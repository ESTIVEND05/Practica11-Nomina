package modelo;

// Clase abstracta: tiene lo que comparten todos los empleados (DPI y nombre).
// No se puede hacer new Empleado(...), solo se usan sus hijas.
public abstract class Empleado {

    // Atributos privados (encapsulamiento). Se pueden usar solo dentro de esta clase,
    // desde afuera se consultan con los getters.
    private String dpi;
    private String nombre;

    // Constructor con parametros. "dpi" y "nombre" son los PARAMETROS,
    // por eso uso this para diferenciar el atributo del parametro.
    public Empleado(String dpi, String nombre) {
        this.dpi = dpi;
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    // Metodo abstracto: cada hija decide como se calcula su pago
    public abstract double calcularPago();

    // Cada hija devuelve su tipo para mostrarlo en el reporte
    public abstract String obtenerTipo();
}
