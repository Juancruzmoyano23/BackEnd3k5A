package ar.edu.utnfc.backend.EjercicioCalculadora;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjemploStream {
    public static void main(String[] args) {

        List<String> lista = List.of("Hola", "Mundo", "Juan", "Pedro", "Matias", "Tomas");
        // s.forEach((String str ) -> {System.out.println(str);});
        lista.stream().filter(st -> !st.startsWith("H"))
        .map(st -> st.toLowerCase())
        .distinct()
        .collect(Collectors.toMap(s -> s, s -> s.length()));




        Suma suma = new Suma();
        Resta resta = new Resta();
        Multiplicacion multiplicacion = new Multiplicacion();
        Division division = new Division();

        double a = 10;
        double b = 5;

        System.out.println("Suma: " + suma.operar(a, b));
        System.out.println("Resta: " + resta.operar(a, b));
        System.out.println("Multiplicación: " + multiplicacion.multiplicacion(a, b));
        System.out.println("División: " + division.operar(a, b));
    }
}
