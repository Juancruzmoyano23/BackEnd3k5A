package ar.edu.utnfc.backend.app;

import ar.edu.utnfc.backend.menu.ApplicationContext;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Actions {

    @SuppressWarnings("unchecked")
    private List<Persona> getPersonas(ApplicationContext ctx) {
        return (List<Persona>) ctx.get("personas");
    }

    public void listar(ApplicationContext ctx) {

        List<Persona> personas = getPersonas(ctx);

        System.out.println("\nCantidad total: " + personas.size());

        personas.stream()
                .limit(20)
                .forEach(System.out::println);

        if (personas.size() > 20) {
            System.out.println("\nSe muestran solo las primeras 20.");
        }
    }

    public void buscar(ApplicationContext ctx) {

        Scanner sc = ctx.get("in", Scanner.class);

        System.out.print("Ingrese texto a buscar: ");

        String texto = sc.nextLine().toLowerCase();

        List<Persona> resultado =
                getPersonas(ctx)
                        .stream()
                        .filter(p ->
                                p.getNombre().toLowerCase().contains(texto)
                                        ||
                                        p.getApellido().toLowerCase().contains(texto)
                        )
                        .toList();

        System.out.println("\nCoincidencias encontradas: " + resultado.size());

        resultado.stream()
                .limit(20)
                .forEach(System.out::println);
    }

    public void topEdades(ApplicationContext ctx) {

        Scanner sc = ctx.get("in", Scanner.class);

        try {

            System.out.print("Ingrese N: ");

            int n = Integer.parseInt(sc.nextLine());

            getPersonas(ctx)
                    .stream()
                    .sorted(
                            Comparator
                                    .comparing(Persona::getEdad)
                                    .reversed()
                    )
                    .limit(n)
                    .forEach(System.out::println);

        } catch (NumberFormatException e) {

            System.out.println("Debe ingresar un número válido.");
        }
    }

    public void conteoPorCiudad(ApplicationContext ctx) {

        Map<String, Long> conteo =
                getPersonas(ctx)
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Persona::getCiudad,
                                        Collectors.counting()
                                )
                        );

        conteo.entrySet()
                .stream()
                .sorted(
                        Map.Entry
                                .<String, Long>comparingByValue()
                                .reversed()
                )
                .forEach(entry ->
                        System.out.printf(
                                "%-15s -> %d%n",
                                entry.getKey(),
                                entry.getValue()
                        )
                );
    }

    public void estadisticasEdad(ApplicationContext ctx) {

        IntSummaryStatistics stats =
                getPersonas(ctx)
                        .stream()
                        .collect(
                                Collectors.summarizingInt(
                                        Persona::getEdad
                                )
                        );

        System.out.printf(
                """
                
                Count: %d
                Min: %d
                Avg: %.2f
                Max: %d
                
                """,
                stats.getCount(),
                stats.getMin(),
                stats.getAverage(),
                stats.getMax()
        );
    }
}