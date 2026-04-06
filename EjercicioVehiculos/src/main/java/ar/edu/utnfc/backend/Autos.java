package ar.edu.utnfc.backend;

public class Autos extends Vehiculos{

    public Autos(String ma, String mo){
        super(ma, mo);
    }

    @Override
    public void Conducir(){
        System.out.println("El auto avanza por la carretera");
    }
}