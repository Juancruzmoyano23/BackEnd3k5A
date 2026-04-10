package ar.edu.utnfc.backend.EjercicioVehiculos;

public class EspacioEstacionamiento<T extends Vehiculos> extends Vehiculos {

    private T vehiculo;

    public EspacioEstacionamiento(String p){
        super(p);
        this.vehiculo = null;
    }

    public void ocupar(T v){
        if (this.vehiculo != null){
            throw new IllegalStateException("Espacio ocupado");
        }

        this.vehiculo = v;
        System.out.println("Espacio ocupado con: " + v.getPatente());
    }
}