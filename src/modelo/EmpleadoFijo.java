package modelo;

// Herencia: EmpleadoFijo es un Empleado
public class EmpleadoFijo extends Empleado {

    private double sueldoMensual;
    private double bonificacion;

    public EmpleadoFijo(String dpi, String nombre, double sueldoMensual, double bonificacion) {
        super(dpi, nombre); // el DPI y nombre los guarda la clase padre
        this.sueldoMensual = sueldoMensual;
        this.bonificacion = bonificacion;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    // Pago del mes = sueldo mensual + bonificacion
    @Override
    public double calcularPago() {
        return sueldoMensual + bonificacion;
    }

    @Override
    public String obtenerTipo() {
        return "Fijo";
    }
}
