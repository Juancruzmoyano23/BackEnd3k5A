package ar.edu.utnfc.backend;

public final class Avion extends VehiculosAereos{

    public Avion(int pro){
        super(pro);
    }

    public void Conducir(){
        System.out.println("El avión vuela por las nubes");
    }
}