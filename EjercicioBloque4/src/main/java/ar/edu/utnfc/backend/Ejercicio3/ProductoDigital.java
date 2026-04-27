package ar.edu.utnfc.backend.Ejercicio3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProductoDigital {

    // Campos: String sku (Stock-keeping unit, unico, case-insensitive) · String nombre · double precioBase · List<Etiqueta> Etiquetas
    // Metodos: double impuesto() (abstracto) · double precioFinalExacto() · double precioFinal() · String getTipo()

    private String sku;
    private String nombre;
    private double precioBase;
    private List<Etiqueta> etiquetas;

    public abstract double impuesto();

    public double precioFinalExacto() {
        return precioBase + (precioBase * impuesto());
    }

    public double precioFinal() {
        return Math.round(precioFinalExacto() * 100.0) / 100.0;
    }

    public abstract String getTipo();
}
