package ar.edu.utnfc.backend.Ejercicio3;

import java.util.List;

public class Ebook extends ProductoDigital {

    private int paginas;

    public Ebook(String sku, String nombre, double precioBase, List<Etiqueta> etiquetas, int paginas) {
        super(sku, nombre, precioBase, etiquetas);
        this.paginas = paginas;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public double impuesto() {
        return 0.05;
    }

    @Override
    public String getTipo() {
        return "EBOOK";
    }
}
