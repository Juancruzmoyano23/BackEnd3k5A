package ar.edu.utnfc.backend.Ejercicio3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        CafeteraInteligente cafetera = new CafeteraInteligente("cuchuflito", "90B7", 100, 10, false, 0, 20);
        System.out.println(cafetera.toString());
        Scanner sc = new Scanner(System.in);

        cafetera.encendido();
        System.out.println("Ingrese la cantidad de ml a sumar: ");
        int ml = sc.nextInt();

        boolean cargo = cafetera.cargarAgua(ml);
        System.out.println("El contenido fue sumado? " + cargo);
        System.out.println(cafetera.toString());

        cafetera.calentar();
        System.out.println("se calento el contendio.. ");
        System.out.println(cafetera.toString());
        System.out.println();

        cafetera.calentar();
        System.out.println("se calento el contendio.. ");
        System.out.println(cafetera.toString());
        System.out.println();

        cafetera.calentar();
        System.out.println("se calento el contendio.. ");
        System.out.println(cafetera.toString());
        System.out.println();
    }
}
