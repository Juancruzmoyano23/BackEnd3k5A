package ar.edu.utnfc.backend.EjercicioStream;

import lombok.Data;

@Data
public class PerfilEstudiante {

    private Estudiante estudiante;
    private CategoriaRendimiento categoria;
    private boolean elegibleBeca;


    public PerfilEstudiante(){
        this.estudiante = new Estudiante();
        this.categoria = new CategoriaRendimiento();
        this.elegibleBeca = false;
    }

    public boolean obtenerElegibleBeca(int nota, int edad) {
        if (nota >= 8 && edad <= 22) {
            return true;
        }
        return false;
    }
}
