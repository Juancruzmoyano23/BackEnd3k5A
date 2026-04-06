package ar.edu.utnfc.backend;


import java.util.List;
import java.util.ArrayList;

public class App1 {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        Vehiculos v = new Autos("Ford", "Focus");
        v.mostrarIdentificacion();

        Motos m = new Motos("Honda", "CBR");
        Barco b = new Barco(100);
        Avion a = new Avion(10000);

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
