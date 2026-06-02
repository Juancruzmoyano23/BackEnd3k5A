package ar.edu.utnfc.backend.model;

import java.time.LocalDate;

public class EmpleadoContratado
        extends Empleado {

    private LocalDate fechaContratacion;

    public EmpleadoContratado(
            int legajo,
            String nombre,
            double montoBase,
            Categoria categoria,
            LocalDate fechaContratacion) {

        super(
                legajo,
                nombre,
                montoBase,
                categoria
        );

        this.fechaContratacion =
                fechaContratacion;
    }

    public double calcularIncremento() {

        return switch (
                categoria.getNombre()
        ) {

            case "A" -> 0.10;
            case "B" -> 0.05;
            default -> 0.02;
        };
    }

    @Override
    public double calcularSueldo() {

        return montoBase
                * categoria.getCoeficiente()
                * (1 + calcularIncremento());
    }
}
