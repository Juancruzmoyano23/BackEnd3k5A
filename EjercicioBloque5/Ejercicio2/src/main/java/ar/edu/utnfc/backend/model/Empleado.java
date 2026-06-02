package ar.edu.utnfc.backend.model;

public abstract class Empleado {

    protected int legajo;
    protected String nombre;
    protected double montoBase;
    protected Categoria categoria;

    public Empleado(
            int legajo,
            String nombre,
            double montoBase,
            Categoria categoria) {

        this.legajo = legajo;
        this.nombre = nombre;
        this.montoBase = montoBase;
        this.categoria = categoria;
    }

    public abstract double calcularSueldo();

    public int getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
