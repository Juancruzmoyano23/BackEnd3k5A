package ar.edu.utnfc.backend.Ejercicio4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sn = new Scanner(System.in);

        System.out.println("Ingrese su nombre: ");
        String name = sn.next();

        System.out.println("Ingrese la cantidad de horas que labura: ");
        int horas = sn.nextInt();

        if (horas < 8) {
            horas = 8 - horas;
        } else {
            horas = horas + 5;
        }

        System.out.println("Ingrese cantidad de tareas realizadas: ");
        int task = sn.nextInt();


        int indice = (task * 10) - horas;


        System.out.println("Nombre: " + name);
        System.out.println("Cantidad de horas: " + horas);
        System.out.println("Cantidad de tareas realizadas: " + task);
        System.out.println("Indice de productividad: " + indice);


    }
}
