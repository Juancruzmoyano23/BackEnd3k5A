package ar.edu.utnfc.backend;

public final class Barco extends VehiculosAcuaticos{

    public Barco(int altitud){
        super("Barco", "Marina", altitud);
    }

    public void Conducir(){
        System.out.println("El barco navega por el mar");
    }
}