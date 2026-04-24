package ar.edu.utnfc.backend.EjercicioCalculadora;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){


    List<Operacion> operaciones = new ArrayList<>();

    Operacion suma = (double a, double b) -> a + b;
    Operacion division = (double a, double b) -> {
        if (b == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero");
        }
        return a / b;
    };


    for (Operacion o : operaciones){
        System.out.print(o.operar(34, 15));
    }
    }
}