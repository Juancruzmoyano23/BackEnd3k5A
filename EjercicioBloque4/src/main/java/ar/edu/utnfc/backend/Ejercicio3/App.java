package ar.edu.utnfc.backend.Ejercicio3;

import java.util.List;

public class App extends ProductoDigital {

    private Plataforma plataforma;

    public App(String sku, String nombre, double precioBase, List<Etiqueta> etiquetas, Plataforma plataforma) {
        super(sku, nombre, precioBase, etiquetas);
        this.plataforma = plataforma;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public double impuesto() {
        return 0.15;
    }

    @Override
    public String getTipo() {
        return "APP";
    }
}
