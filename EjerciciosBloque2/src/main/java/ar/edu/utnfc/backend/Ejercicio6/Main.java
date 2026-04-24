package ar.edu.utnfc.backend.Ejercicio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] agrs){

        String csv = "data/numeros.txt";
        int contador = 0;
        int noValidos = 0;
        int pares = 0;
        int inPares = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))){

            String linea;

            while ((linea = br.readLine()) != null){

                try {
                    // convertir el String a int
                    Integer.parseInt(linea);

                    contador++;

                    if (Integer.parseInt(linea) % 2 == 0){
                        pares++;
                    }

                    if (Integer.parseInt(linea) % 2 != 0){
                        inPares++;
                    }


                } catch (NumberFormatException e) {
                    System.out.println("La linea '" + linea + "' no es un numero valido. Se omitira.");
                    noValidos++;
                    continue;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Cantidad total de numeros leidos validos: " + contador);
        System.out.println("Cantidad total de numeros leidos invalidos: " + noValidos);
        System.out.println("Cantidad de pares: " + pares);
        System.out.println("Cantidad de impares: " + inPares);
    }
}
