package ar.edu.utnfc.backend.model;

import java.time.LocalDate;
import java.time.Period;

public class EmpleadoPermanente
        extends Empleado {

    private LocalDate fechaIngreso;

    public EmpleadoPermanente(
            int legajo,
            String nombre,
            double montoBase,
            Categoria categoria,
            LocalDate fechaIngreso) {

        super(
                legajo,
                nombre,
                montoBase,
                categoria
        );

        this.fechaIngreso = fechaIngreso;
    }

    public int calcularAntiguedad() {

        return Period.between(
                fechaIngreso,
                LocalDate.now()
        ).getYears();
    }

    @Override
    public double calcularSueldo() {

        return montoBase
                * categoria.getCoeficiente()
                * (1 + 0.02 * calcularAntiguedad());
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
}
