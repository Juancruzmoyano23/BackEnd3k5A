package ar.edu.utnfc.backend.Ejercicio2;

public class Libro {

    public Libro(){}

    public boolean evaluar(String codigo){
        // remplace hace que de la cadena se reemplace algun caracter por otro
        String singuiones = codigo.replace("-", "");

        int suma = 0;

        for (int i=0; i < 10; i++){

            // agarro cada caracter en cada vuelta
            char c = singuiones.charAt(i);

            // evaluo si los digitos ingresados son 10
            if (singuiones.length() != 10){
                return false;
            }

            // evaluar si lo ingresado son digitos y no es otro tipo de caracter
            if (!Character.isDigit(c)){ return false;}

            // convierto el digito en un valor int
            int digito = c - '0';
            // esta es una forma de convertirlo usando el caracter - '0'

            // sumo mi digito mas el valor menos la posicion
            int valor = digito * (10 - i);

            // sumo los valores
            suma += valor;
        }

        if(suma % 11 == 0){

        return true;
        }

        return false;
    }
}
