package ar.edu.utnfc.backend.EjercicioStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaRendimiento {

    private int categoria;

    public CategoriaRendimiento(){
        this.categoria = 0;
    }

    public static String desdeNota(int nota){
        if (nota >= 9) {
            return "EXELENTE";
        } else if (nota >= 7) {
            return "BUENO";
        } else if (nota >= 5){
            return "REGULAR";
        }
        else {
            return "INSUFICIENTE";
        }
    }
}
