package ar.edu.utnfc.backend.Ejercicio5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private String name;
    private int dni;
    private short edad;
    private String ocupacion;
    private int cantidadPosteos;
    private float horasEnPlataforma;
    private boolean verificado;

    @Override
    public String toString(){
        return "Nombre: " + name + " DNI: " + dni + " Edad: " + edad + " Ocupacion: " + ocupacion + " Cantidad de posteos: " + cantidadPosteos + " Horas en plataforma: " + horasEnPlataforma + " Verificado: " + verificado;
    }

    public void contarMayoresDe(int edad){
        if (this.edad > edad){
            System.out.println("El cliente " + name + " es mayor a " + edad);
        }
    }

    public int totalPosteos(){
        return this.cantidadPosteos;
    }

    public double calcularPuntuacion(){
        if(this.edad > 25){
            if (verificado){
                return (this.horasEnPlataforma * 2) + 25;
            }
            return (this.horasEnPlataforma * 2);
        } else {
            if (verificado){
                return (this.horasEnPlataforma * 3) + 25;
            }
            return (this.horasEnPlataforma * 3);
        }
    }

    public double totalPuntuacion(){
        double sum = 0;
        sum += calcularPuntuacion();
        return sum;
    }

}
