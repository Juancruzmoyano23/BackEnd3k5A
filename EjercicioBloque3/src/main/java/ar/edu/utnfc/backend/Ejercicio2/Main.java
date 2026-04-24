package ar.edu.utnfc.backend.Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        String csv = "Data/barcos.csv";
        List<Barco> barcos = new ArrayList<>();
        int sum = 0;
        List<Barco> barcosCapitanesExperiencias = new ArrayList<>();
        int sumImpares = 0;
        int ctBarcos = 0;
        int ctBarcosImpares = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))){

            String linea;

            // salteamos la primera
            br.readLine();

            while((linea = br.readLine()) != null){

                String[] columnas = linea.split(",");
                // alphanumeric,name,normaldist,currency,guid,name2,name1,numberrange

                String matricula = columnas[0];
                int nroMuelle = Integer.parseInt(columnas[1]);
                int capacidadCarga = Integer.parseInt(columnas[2]);
                double costoAlquiler = Double.parseDouble(columnas[3]);
                String idCapitan = columnas[4];
                String name = columnas[5];
                String apellido = columnas[6];
                int antiguedadCapitan = Integer.parseInt(columnas[7]);

                barcos.add(new Barco(matricula, nroMuelle, capacidadCarga, costoAlquiler, new Capitan(idCapitan, name, apellido, antiguedadCapitan)));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Ejercicios

        for (Barco b : barcos){
            sum += b.getCapacidadCargaPermitida();
            ctBarcos++;

            if (b.getCapitan().getAntiguedadCargo() > 18){
                barcosCapitanesExperiencias.add(b);
            }

            if (b.getNroMuelleCarga() % 2 != 0){
                sumImpares += b.getCapacidadCargaPermitida();
                ctBarcosImpares++;
            }
        }

        System.out.println("Cantidad total de carga es de: " + sum);
        System.out.println("Cantidad total de barcos es de: " + ctBarcos);
        System.out.println("Barcos con capitanes con mas de 18 años de antiguedad: " + barcosCapitanesExperiencias);

        double promedio = sumImpares / ctBarcosImpares;

        System.out.println("La cantidad de barcos con amarres impares es de: " + ctBarcosImpares);
        System.out.println("La cantidad de cargas de barcos con amarres impares es de: " + sumImpares);
        System.out.println("El promedio de cargas de barcos con amarres impares es de: " + promedio);
    }
}
