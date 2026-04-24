package ar.edu.utnfc.backend.Ejercicio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        System.out.println("=== APLICACIÓN DE TRANSPORTE DE TRENES ===");
        System.out.println("Bienvenido al sistema de gestión de transporte ferroviario");
        System.out.println("Versión: 1.0.0");
        System.out.println("==========================================");

        // Aquí se implementará la lógica principal de la aplicación
        System.out.println("La aplicación está lista para ser implementada.");

        String csv = "Data/viajes.csv";
        List<Viaje> viajes = new ArrayList<>();
        int sumaDuracion = 0;
        int ct = 0;
        Viaje viajeMasPasajeros = null;
        Viaje viajeMasLargo = null;

        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))){

            String linea;

            // saltea la primera linea
            br.readLine();

            while (((linea = br.readLine()) != null)){


                String[] columnas = linea.split(",");

                // id_viaje,linea,origen,destino,horario_salida,horario_llegada,pasajeros

                int id = Integer.parseInt(columnas[0]);
                String lineaa = columnas[1];
                String origen = columnas[2];
                String destino = columnas[3];
                String horaSalida = columnas[4];
                String horasLlegada = columnas[5];
                int pasajeros = Integer.parseInt(columnas[6]);

                Viaje viaje = new Viaje(id, lineaa, origen, destino, horaSalida, horasLlegada, pasajeros, 0);
                viaje.calcularDuracion();
                viajes.add(viaje);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Viaje v : viajes){

            ct++;
            sumaDuracion += v.getDuracion();

            if (viajeMasPasajeros == null || v.getPasajeros() > viajeMasPasajeros.getPasajeros()) {
                viajeMasPasajeros = v;
            }

            if (viajeMasLargo == null || v.getDuracion() > viajeMasLargo.getDuracion()) {
                viajeMasLargo = v;
            }
        }

        int promedio = ct == 0 ? 0 : sumaDuracion / ct;

        System.out.println("El promedio de duracion de viaje es de: " + promedio);
        if (viajeMasPasajeros != null) {
            System.out.println("El viaje con mayor cantidad de pasajeros es el ID "
                    + viajeMasPasajeros.getId_viaje() + " con " + viajeMasPasajeros.getPasajeros() + " pasajeros.");
        }
        if (viajeMasLargo != null) {
            System.out.println("El viaje mas largo en duracion es el ID "
                    + viajeMasLargo.getId_viaje() + " con " + viajeMasLargo.getDuracion() + " minutos.");
        }

    }
}
