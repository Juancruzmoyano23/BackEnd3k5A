package ar.edu.utnfc.backend.EjercicioVehiculos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static void main(String[] args) {
        
        Autos a = new Autos("1312r");
        Motos m = new Motos("1312r");
        Camiones c = new Camiones("1312r");

        List<Vehiculos> ListaV = new ArrayList<>();

        ListaV.add(a);
        ListaV.add(m);
        ListaV.add(c);

        Map<String, Vehiculos> vehiculosPorPatentes = new HashMap<>();

        for(Vehiculos v : ListaV){
            System.out.println(v);
            vehiculosPorPatentes.put(v.getPatente(), v);
        }

    }
}
