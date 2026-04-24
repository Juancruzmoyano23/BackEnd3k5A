package ar.edu.utnfc.backend.Ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sn = new Scanner(System.in);
        System.out.println("Ingrese un numero entero positivo: ");
        int valor = sn.nextInt();

        while (valor <= 0){
            System.out.println("Vuelva a ingresar el idea: ");
            valor = sn.nextInt();
        }

        // creo un array
        List<Integer> valores = new ArrayList<>();


        MultiplosDivisores DM = new MultiplosDivisores();
        valores = DM.procesar(valor);

        for (int a : valores){
            System.out.println(a);
        }
    }
}
