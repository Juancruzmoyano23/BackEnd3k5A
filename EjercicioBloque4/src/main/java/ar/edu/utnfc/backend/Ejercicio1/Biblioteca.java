package ar.edu.utnfc.backend.Ejercicio1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Biblioteca {

    private ArrayList<Libro> libros;

    public void agregar(Libro l){
        libros.add(l);
    }

    public void listar(ArrayList<Libro> libros){
        for (Libro l : libros){
            System.out.println(l);
        }
    }

    public void buscar(String autor){
        Iterator<Libro> it = libros.iterator();
        while (it.hasNext()){
            Libro l = it.next();
            if (l.getAutor().equals(autor)) {
                System.out.println(l);
            }
            }
    }

    public void eliminar(String titulo){
        for (int i = 0; i < libros.size(); i++){
            if (libros.get(i).getTitulo().equals(titulo)){
                libros.remove(i);
            }
        }

    }

    public double obtenerPromedioAntiguedad(){
        // Obtener el promedio de antigüedad de los libros (año actual - añoPublicacion).

            int sumaAntiguedad = 0;
            int cantidadLibros = libros.size();
            int anioActual = java.time.Year.now().getValue();

            for (Libro l : libros){
                int antiguedad = anioActual - l.getAnioPublicacion();
                sumaAntiguedad += antiguedad;
            }

            if (cantidadLibros > 0) {
                return (double) sumaAntiguedad / cantidadLibros;
            } else {
                return 0.0; // Evitar división por cero
            }

    }
}
