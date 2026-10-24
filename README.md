# Práctica 11 - Nómina mensual en Java

Programación II - Segundo semestre 2026

Aplicación de consola para registrar empleados fijos y por hora y calcular la nómina de un mes.

## Cómo ejecutar

Desde la carpeta del proyecto:

```
javac -encoding UTF-8 -d bin src/modelo/*.java src/aplicacion/Main.java
java -cp bin aplicacion.Main
```

## Estructura

- `src/modelo/Empleado.java` - clase abstracta
- `src/modelo/EmpleadoFijo.java`
- `src/modelo/EmpleadoPorHora.java`
- `src/aplicacion/Main.java` - menús, captura de datos y reporte
- `EXPLICACION.txt` - explicación de los conceptos usados
