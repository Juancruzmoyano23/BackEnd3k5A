package ar.edu.utnfc.backend.Ejercicio2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Maritimo extends Viaje {

    private int cantidadContenedores;
    private double costoPorKilo;
    private double pesoTransportado;

    public Maritimo(String codigo, int nroReserva, double precio, int tipo, Cliente cliente,
                    int cantidadContenedores, double costoPorKilo, double pesoTransportado) {
        super(codigo, nroReserva, precio, tipo, cliente);
        this.cantidadContenedores = cantidadContenedores;
        this.costoPorKilo = costoPorKilo;
        this.pesoTransportado = pesoTransportado;
    }

    public double costoTotalDeViaje(){
        return this.costoPorKilo * this.pesoTransportado;
    }
}

