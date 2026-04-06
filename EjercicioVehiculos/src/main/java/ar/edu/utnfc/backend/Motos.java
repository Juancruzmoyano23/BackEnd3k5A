package ar.edu.utnfc.backend;

public class Motos extends Vehiculos{

    public Motos(String ma, String mo){
        super(ma, mo);

    }

    @Override
    public void Conducir(){
        System.out.println("La moto zigzaguea entre los carriles");
    }
}