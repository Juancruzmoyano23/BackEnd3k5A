package ar.edu.utnfc.backend.Ejercicio1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setLibros(new ArrayList<>());

        Libro l1 = new Libro("1984", "George Orwell", 1949, "Distopia");
        Libro l2 = new Libro("Rayuela", "Julio Cortazar", 1963, "Novela");
        Libro l3 = new Libro("El Aleph", "Jorge Luis Borges", 1945, "Cuento");
        Libro l4 = new Libro("Rebelion en la granja", "George Orwell", 1945, "Satira");

        biblioteca.agregar(l1);
        biblioteca.agregar(l2);
        biblioteca.agregar(l3);
        biblioteca.agregar(l4);

        System.out.println("=== Listado inicial ===");
        biblioteca.listar(biblioteca.getLibros());

        System.out.println("\n=== Buscar por autor: George Orwell ===");
        biblioteca.buscar("George Orwell");

        System.out.println("\n=== Promedio de antiguedad ===");
        System.out.println(biblioteca.obtenerPromedioAntiguedad());

        System.out.println("\n=== Eliminar: Rayuela ===");
        biblioteca.eliminar("Rayuela");

        System.out.println("\n=== Listado luego de eliminar ===");
        biblioteca.listar(biblioteca.getLibros());

    }
}
