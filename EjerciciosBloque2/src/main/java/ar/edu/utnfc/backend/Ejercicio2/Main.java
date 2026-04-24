package ar.edu.utnfc.backend.Ejercicio2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){


        Scanner sn = new Scanner(System.in);
        Libro libro = new Libro();

        // entrada
        System.out.println("Ingrese el codigo ISBN");
        String codigo = sn.next();
        boolean validez = libro.evaluar(codigo);
        System.out.println("El codigo ISBN es valido? " + validez);
    }
}
