package ar.edu.utnfc.backend;

public abstract class VehiculosAcuaticos extends Vehiculos {

    protected int profundidadMaxima;

    public VehiculosAcuaticos(String barco, String marina, int p){
        super("Vehiculo Acuatico", "Modelo Acuatico");
        this.profundidadMaxima = p;
    }
}