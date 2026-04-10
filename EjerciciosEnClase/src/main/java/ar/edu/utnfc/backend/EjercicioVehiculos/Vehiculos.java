package ar.edu.utnfc.backend.EjercicioVehiculos;

public abstract class Vehiculos{

    private String patente;

    public Vehiculos(String p){
        this.patente = p;
    }

    public String getPatente(){
        return patente;
    }
}