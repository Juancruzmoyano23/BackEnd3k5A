package ar.edu.utnfc.backend.Ejercicio2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    private String codigo;
    private int nroReserva;
    private double precio;
    private int tipo; // 1=Aereo, 2=Terrestre, 3=Maritimo
    private Cliente cliente;
}

