package ar.edu.utnfc.backend.Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){

        String csv = "Data/viajes.csv";
        Map<String, Cliente> clientesPorCuit = new HashMap<>();
        Collection<Viaje> viajes = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))){

            String line;
            boolean esCabecera = true;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                if (esCabecera) {
                    esCabecera = false;
                    continue;
                }

                String[] values = line.split(";");

                String codigo = values[0];
                int nroReserva = Integer.parseInt(values[1]);
                double precio = Double.parseDouble(values[2]);
                int tipo = Integer.parseInt(values[3]);
                int millasAcumuladas = Integer.parseInt(values[4]);
                String codAerolinea = values[5];
                int provinciasVisitadas = Integer.parseInt(values[6]);
                int cantidadPasajeros = Integer.parseInt(values[7]);
                int cantidadContenedores = Integer.parseInt(values[8]);
                double costoPorKilo = Double.parseDouble(values[9]);
                double pesoTransportado = Double.parseDouble(values[10]);
                String nombreEmpresa = values[11];
                String cuit = values[12];

                Cliente cliente = clientesPorCuit.computeIfAbsent(cuit, clave -> new Cliente(nombreEmpresa, clave));

                Viaje viaje;
                switch (tipo) {
                    case 1:
                        viaje = new Aereo(codigo, nroReserva, precio, tipo, cliente, millasAcumuladas, codAerolinea);
                        break;
                    case 2:
                        viaje = new Terrestre(codigo, nroReserva, precio, tipo, cliente, provinciasVisitadas, cantidadPasajeros);
                        break;
                    case 3:
                        viaje = new Maritimo(codigo, nroReserva, precio, tipo, cliente, cantidadContenedores, costoPorKilo, pesoTransportado);
                        break;
                    default:
                        continue;
                }

                viajes.add(viaje);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
