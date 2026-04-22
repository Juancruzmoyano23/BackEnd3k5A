package ar.edu.utnfc.backend.EjercicioStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){

        Path archivo = Path.of("data/Estudiantes.csv");

        // Ejercicio 1

        try (Stream<String> linea = Files.lines(archivo)){
            List<Estudiante> estudiantes = linea
                    .skip(1)
                    .map(Estudiante::new)
                    .toList();

            estudiantes.forEach(System.out::println);

            // Ejercicio 2

            long aprobadosCordoba = estudiantes.stream()
                    .filter(e -> e.getCiudad().equals("Cordoba") && e.getNota() >= 6)
                    .count();

            System.out.println("Estudiantes de Cordoba aprobados: " + aprobadosCordoba);
            System.out.println();

            // Ejercicio 3

            String promocionados = estudiantes.stream()
                    .filter(e -> e.getNota() >= 8)
                    .map(Estudiante::getNombre)
                    .collect(Collectors.joining(", "));

            System.out.println("Promocionados: " + promocionados);
            System.out.println();

            // Ejercicio 4

            IntSummaryStatistics statsEdades = estudiantes.stream()
                    .mapToInt(Estudiante::getEdad)
                    .summaryStatistics();

            System.out.println("Edad mínima: " + statsEdades.getMin());
            System.out.println("Edad máxima: " + statsEdades.getMax());
            System.out.println("Edad promedio: " + statsEdades.getAverage());
            System.out.println();


            // Ejercicio 5

            Map<String, List<Estudiante>> porCiudad = estudiantes.stream()
                    .collect(Collectors.groupingBy(Estudiante::getCiudad));

            porCiudad.forEach((ciudad, lista) -> System.out.println(ciudad + ": " + lista));
            System.out.println();


            // mostrar las ciudades y al lado su cantidad
            porCiudad.forEach((ciudad, lista)-> System.out.println(ciudad + ":" + lista.size() + " Estudiantes"));
            System.out.println();


            // mostrar las ciudades y el promedio de notas de cada ciudad
            porCiudad.forEach((ciudad, lista) -> {
                double promedio = lista.stream()
                        .mapToInt(Estudiante::getNota)
                        .average()
                        .orElse(0.0);
                System.out.println(ciudad + ": " + "Promedio de Notas: " + promedio);
            });
            System.out.println();

            //mostrar las ciudades y los nombre de los promocionados de cada ciudad
            porCiudad.forEach((ciudad, lista)-> {
                String nombre = lista.stream()
                        .filter(e -> e.getNota() >= 8)
                        .map(Estudiante::getNombre)
                        .collect(Collectors.joining(","));
                System.out.println(ciudad + ": " + "Promocionados: " + nombre);
            });

            //Rankind de ciudades

            List<ResumenCiudad> listaCiudades = porCiudad.entrySet().stream()
                    .map(entry -> new ResumenCiudad(
                            entry.getKey(),
                            entry.getValue().size(),
                            entry.getValue().stream().mapToInt(Estudiante::getNota).average().orElse(0.0),
                            (int) entry.getValue().stream().filter(e -> e.getNota() >= 8).count()
                    ))
                    .sorted(Comparator.comparingDouble(ResumenCiudad::getPromedioNotas).reversed())
                    .toList();

            System.out.println("== RANKING DE CIUDADES POR PROMEDIO DE NOTAS == ");
            AtomicInteger contador = new AtomicInteger(1);
            listaCiudades.forEach(resumen -> {
                System.out.println(contador.getAndIncrement() + ". " + resumen.getNombre() + " - Cantidad de Estudiantes: " + resumen.getCantidadDeEstudiantes() + " - Promedio de Notas: " + resumen.getPromedioNotas() +  " - Cantidad de Promocionados: " + resumen.getCantidadPromocionados());
                System.out.println();

                // verificar si todas las ciudades tienen al menos un promocionado
                boolean tienePromocionados = listaCiudades.stream()
                        .allMatch(resumenCiudad -> resumenCiudad.getCantidadPromocionados() > 0);

                System.out.println("¿Todas las ciudades tienen al menos un promocionado? " + (tienePromocionados ? "Sí" : "No"));
            });


            // Asignacion de Becas
            InformeBeca informe = new InformeBeca().sistemaDeBecas(estudiantes);

            System.out.println("===================================================");

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
