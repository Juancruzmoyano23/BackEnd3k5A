package ar.edu.utnfc.backend.Ejercicio2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Aereo extends Viaje {

    private int millasAcumuladas;
    private String codAerolinea;

    public Aereo(String codigo, int nroReserva, double precio, int tipo, Cliente cliente,
                 int millasAcumuladas, String codAerolinea) {
        super(codigo, nroReserva, precio, tipo, cliente);
        this.millasAcumuladas = millasAcumuladas;
        this.codAerolinea = codAerolinea;
    }
}

