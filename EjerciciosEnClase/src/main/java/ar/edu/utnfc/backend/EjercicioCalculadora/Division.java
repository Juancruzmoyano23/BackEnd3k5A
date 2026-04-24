package ar.edu.utnfc.backend.EjercicioCalculadora;

public class Division {
    
    public double operar(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero");
        }
        return a / b;
    }
}
