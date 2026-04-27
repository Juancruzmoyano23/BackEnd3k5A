package ar.edu.utnfc.backend.Ejercicio3;

import java.util.List;

public class CursoOnline extends ProductoDigital {

    private int horas;

    public CursoOnline(String sku, String nombre, double precioBase, List<Etiqueta> etiquetas, int horas) {
        super(sku, nombre, precioBase, etiquetas);
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public double impuesto() {
        return horas > 20 ? 0.18 : 0.12;
    }

    @Override
    public String getTipo() {
        return "CURSO";
    }
}
