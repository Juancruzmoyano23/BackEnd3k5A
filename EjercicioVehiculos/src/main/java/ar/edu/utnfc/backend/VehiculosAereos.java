package ar.edu.utnfc.backend;

public abstract class VehiculosAereos extends Vehiculos{

    protected int altitudMaxima;

    public VehiculosAereos(int a){
        super("Vehiculo Aereo", "Modelo Aereo");
        this.altitudMaxima = a;
    }
}