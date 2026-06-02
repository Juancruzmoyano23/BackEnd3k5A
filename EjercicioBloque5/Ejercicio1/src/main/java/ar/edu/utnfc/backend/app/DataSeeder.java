package ar.edu.utnfc.backend.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataSeeder {

    private static final String[] NOMBRES = {
            "Juan","Pedro","Ana","Lucia",
            "Maria","Sofia","Martin","Pablo"
    };

    private static final String[] APELLIDOS = {
            "Perez","Gomez","Lopez",
            "Fernandez","Rodriguez"
    };

    private static final String[] CIUDADES = {
            "Cordoba",
            "Rosario",
            "Mendoza",
            "Buenos Aires",
            "Salta"
    };

    public static List<Persona> generar(int cantidad) {

        List<Persona> personas = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {

            String documento =
                    String.valueOf(
                            ThreadLocalRandom.current()
                                    .nextInt(10000000,99999999)
                    );

            String nombre =
                    NOMBRES[
                            ThreadLocalRandom.current()
                                    .nextInt(NOMBRES.length)
                    ];

            String apellido =
                    APELLIDOS[
                            ThreadLocalRandom.current()
                                    .nextInt(APELLIDOS.length)
                    ];

            int edad =
                    ThreadLocalRandom.current()
                            .nextInt(18,66);

            String ciudad =
                    CIUDADES[
                            ThreadLocalRandom.current()
                                    .nextInt(CIUDADES.length)
                    ];

            personas.add(
                    new Persona(
                            documento,
                            nombre,
                            apellido,
                            edad,
                            ciudad
                    )
            );
        }

        return personas;
    }
}