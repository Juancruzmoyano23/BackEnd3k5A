package ar.edu.utnfc.backend.Ejercicio2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Barco {

    private String matricula;
    private int nroMuelleCarga;
    private int capacidadCargaPermitida;
    private double costoAlquilerHoraAmarre;
    private Capitan capitan;


}
