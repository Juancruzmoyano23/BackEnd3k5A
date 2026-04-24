package ar.edu.utnfc.backend.Ejercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sn = new Scanner(System.in);
        List<Integer> lista = new ArrayList<>();

        while (true){

            System.out.println("Ingrese un valor:");
            int valor = sn.nextInt();

            if(valor == -1) {
                break;
            }

            if (valor < 0 || valor > 10){
                System.out.println("Ingrese nuevamente otro valor:");
                valor = sn.nextInt();
            }

            lista.add(valor);
        }

        System.out.println("Los valores ingresados son: " + lista);

        // calcular el maximo y minimo
        int maximo = 0;
        int minimo = 10;
        int sum = 0;
        int cantidad = 0;
        int aprobados = 0;
        int desaprobados = 0;

        for (int v : lista){

            cantidad++;
            sum += v;


            if(v > maximo){
                maximo = v;
            }

            if (v < minimo){
                minimo = v;
            }

            if (v >= 6){
                aprobados++;
            }

            if (v < 6){
                desaprobados++;
            }
        }

        System.out.println("La nota maxima es: " + maximo);
        System.out.println("La nota minima es: " + minimo);

        // calcular el promedio con decimales
        int promedio = (sum / cantidad);

        System.out.println("el promedio de nota es: " + promedio);

        // cantidad de aprobados
        System.out.println("la cantidad de aprobados: " + aprobados);

        // cantidad de desaprobados
        System.out.println("la cantidad de desaprobados: " + desaprobados);



    }
}
