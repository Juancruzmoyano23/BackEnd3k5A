package ar.edu.utnfc.backend.service;

import ar.edu.utnfc.backend.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvLoader {

    public List<Empleado> cargar(
            String ruta,
            Map<String, Categoria> categorias
    ) {

        List<Empleado> empleados =
                new ArrayList<>();

        try {

            List<String> lineas =
                    Files.readAllLines(
                            Path.of(ruta)
                    );

            for (int i = 1; i < lineas.size(); i++) {

                String[] datos =
                        lineas.get(i)
                                .split(",");

                int legajo =
                        Integer.parseInt(
                                datos[0]
                        );

                String nombre =
                        datos[1];

                String tipo =
                        datos[2];

                String nombreCategoria =
                        datos[3];

                LocalDate fecha =
                        LocalDate.parse(
                                datos[4]
                        );

                double montoBase =
                        Double.parseDouble(
                                datos[5]
                        );

                Categoria categoria =
                        categorias.get(
                                nombreCategoria
                        );

                Empleado empleado;

                if (
                        tipo.equalsIgnoreCase(
                                "PERMANENTE"
                        )
                ) {

                    empleado =
                            new EmpleadoPermanente(
                                    legajo,
                                    nombre,
                                    montoBase,
                                    categoria,
                                    fecha
                            );

                } else {

                    empleado =
                            new EmpleadoContratado(
                                    legajo,
                                    nombre,
                                    montoBase,
                                    categoria,
                                    fecha
                            );
                }

                empleados.add(
                        empleado
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error leyendo CSV"
            );

            e.printStackTrace();
        }

        return empleados;
    }
}
