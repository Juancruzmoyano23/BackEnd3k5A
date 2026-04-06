package ar.edu.utnfc.backend;


import java.util.List;
import java.util.ArrayList;

public class App1 {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        List<Vehiculos> lista = new ArrayList<>();
        lista.add(new Autos("Toyota", "Corolla"));
        lista.add(new Motos("Yamaha", "R1"));
        lista.add(new Barco(200));
        lista.add(new Avion(15000));

        for (Vehiculos ve : lista){
            ve.Conducir();
        }

    }
}
