package ar.edu.utnfc.backend.Ejercicio4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Libro {

    private String isbn;
    private String titulo;
    private int nroEstante;
    private int paginas;
    private double precioPorDia;
    private Autor autor;

    @Override
    public String toString(){
        String aut = (autor == null) ? "N/D" : autor.getName() + " " + autor.getLastname();
        return "Libro{titulo='" + titulo + "', autor=" + aut + "', estante" + nroEstante + "}";
    }
}
