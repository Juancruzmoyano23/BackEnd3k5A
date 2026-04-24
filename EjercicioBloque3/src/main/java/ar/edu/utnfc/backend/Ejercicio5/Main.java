package ar.edu.utnfc.backend.Ejercicio5;

import ar.edu.utnfc.backend.Ejercicio4.Autor;
import ar.edu.utnfc.backend.Ejercicio4.Libro;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        String csv = "Data/clientes.csv";
        List<Cliente> clientes = new ArrayList<>();
        int ctLinea = 0;
        int sum = 0;
        double total = 0;


        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))){

            String linea;

            // saltea la primera linea
            br.readLine();

            while (((linea = br.readLine()) != null)){

                ctLinea++;

                String[] columnas = linea.split(",");

                // nombre,dni,edad,ocupacion,cantidadPosteos,horasEnPlataforma,verificado

                String nombre = columnas[0];
                int dni = Integer.parseInt(columnas[1]);
                short edad = Short.parseShort(columnas[2]);
                String ocupacion = columnas[3];
                int cantidadPosteos = Integer.parseInt(columnas[4]);
                float horas = Float.parseFloat(columnas[5]);
                boolean verificado = Boolean.parseBoolean(columnas[6]);

                clientes.add(new Cliente(nombre, dni, edad, ocupacion, cantidadPosteos, horas, verificado));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Cliente c : clientes){
            if(ctLinea <= 10){
                System.out.println(c.toString());
            }

            c.contarMayoresDe(25);

            sum += c.totalPosteos();
            total = c.totalPuntuacion();
        }

        System.out.println("La cantidad total de posteos es de: " + sum);
        System.out.println("El total de puntuacion de todos los clientes es de: " + total);
    }
}
