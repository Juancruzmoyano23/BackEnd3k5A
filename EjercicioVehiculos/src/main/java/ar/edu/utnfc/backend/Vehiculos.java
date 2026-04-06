package ar.edu.utnfc.backend;

public abstract class Vehiculos{

    protected String marca, modelo;

    public Vehiculos(String ma, String mo){
        this.marca = ma;
        this.modelo = mo;
    }

    public void Conducir(){
        System.out.println("Conduciendo el vehiculo");
    }

    public final void mostrarIdentificacion(){
        System.out.println("modelo: " + modelo);
        System.out.println("marca: " + marca);
    }

}