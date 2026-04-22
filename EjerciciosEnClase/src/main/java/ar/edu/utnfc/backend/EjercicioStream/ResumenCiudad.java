package ar.edu.utnfc.backend.EjercicioStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumenCiudad {

    private String nombre;
    private int cantidadDeEstudiantes;
    private double promedioNotas;
    private int cantidadPromocionados;


    public ResumenCiudad(){
        this.cantidadDeEstudiantes = 0;
        this.promedioNotas = 0.0;
        this.cantidadPromocionados = 0;
    }
}
