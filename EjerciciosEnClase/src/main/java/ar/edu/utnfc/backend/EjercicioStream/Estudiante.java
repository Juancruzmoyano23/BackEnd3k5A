package ar.edu.utnfc.backend.EjercicioStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    private int legajo;
    private String nombre;
    private String ciudad;
    private int edad;
    private int nota;

    public Estudiante(String linea) {
        this(
                Integer.parseInt(linea.split(";")[0]),
                linea.split(";")[1],
                linea.split(";")[2],
                Integer.parseInt(linea.split(";")[3]),
                Integer.parseInt(linea.split(";")[4])
        );
    }
}
