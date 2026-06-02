package ar.edu.utnfc.backend.model;

public class Categoria {

    private String nombre;
    private double coeficiente;

    public Categoria(String nombre,
                     double coeficiente) {

        this.nombre = nombre;
        this.coeficiente = coeficiente;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCoeficiente() {
        return coeficiente;
    }
}