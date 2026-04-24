package ar.edu.utnfc.backend.Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class MultiplosDivisores {

    public MultiplosDivisores(){}

    public List<Integer> procesar(int valor){

        List<Integer> valores = new ArrayList<>();

        for (int i=0; i < valor; i++){

            if (((i % 3 == 0) || (i % 5 == 0)) && i % 15 != 0){
                valores.add(i);
            }
        }
        return valores;
    }
}
