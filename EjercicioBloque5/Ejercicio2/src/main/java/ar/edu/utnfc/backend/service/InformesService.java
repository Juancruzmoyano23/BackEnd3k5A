package ar.edu.utnfc.backend.service;

import ar.edu.utnfc.backend.model.Empleado;
import ar.edu.utnfc.backend.model.EmpleadoContratado;
import ar.edu.utnfc.backend.model.EmpleadoPermanente;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InformesService {

    private List<Empleado> empleados;

    public InformesService(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void informarMayorYMenor() {

        System.out.println("\n===== MAYOR Y MENOR SUELDO POR TIPO Y CATEGORIA =====");

        var grupos = empleados.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getClass().getSimpleName()
                                + " - "
                                + e.getCategoria().getNombre()
                ));

        grupos.forEach((grupo, lista) -> {

            Empleado mayor = lista.stream()
                    .max(Comparator.comparing(Empleado::calcularSueldo))
                    .orElse(null);

            Empleado menor = lista.stream()
                    .min(Comparator.comparing(Empleado::calcularSueldo))
                    .orElse(null);

            System.out.println("\n" + grupo);

            System.out.println("Mayor:");
            System.out.println(mayor);

            System.out.println("Menor:");
            System.out.println(menor);
        });
    }

    public void totalSueldosPorTipo() {

        System.out.println("\n===== TOTAL SUELDOS POR TIPO =====");

        Map<String, Double> resultado =
                empleados.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.getClass().getSimpleName(),
                                Collectors.summingDouble(
                                        Empleado::calcularSueldo
                                )
                        ));

        resultado.forEach(
                (tipo, total) ->
                        System.out.printf(
                                "%s -> %.2f%n",
                                tipo,
                                total
                        )
        );
    }

    public void porcentajeContratados() {

        System.out.println("\n===== PORCENTAJE CONTRATADOS =====");

        double totalGeneral =
                empleados.stream()
                        .mapToDouble(
                                Empleado::calcularSueldo
                        )
                        .sum();

        double totalContratados =
                empleados.stream()
                        .filter(
                                e ->
                                        e.getClass()
                                                .getSimpleName()
                                                .equals("EmpleadoContratado")
                        )
                        .mapToDouble(
                                Empleado::calcularSueldo
                        )
                        .sum();

        double porcentaje =
                (totalContratados * 100)
                        / totalGeneral;

        System.out.printf(
                "Porcentaje: %.2f%%%n",
                porcentaje
        );
    }

    public void antiguedadPromedio() {

        System.out.println("\n===== ANTIGUEDAD PROMEDIO =====");

        double promedio =
                empleados.stream()
                        .filter(
                                e ->
                                        e instanceof EmpleadoPermanente
                        )
                        .map(
                                e ->
                                        (EmpleadoPermanente) e
                        )
                        .mapToInt(
                                EmpleadoPermanente::calcularAntiguedad
                        )
                        .average()
                        .orElse(0);

        System.out.printf(
                "Promedio: %.2f años%n",
                promedio
        );
    }
}