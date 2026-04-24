package ar.edu.utnfc.backend.Ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        String csv = "Data/libros.csv";
        List<Libro> libros = new ArrayList<>();
        double sum = 0;
        int cantAutores = 0;
        int sumPaginas = 0;
        int ctPaginas = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))){

            String linea;
            int capMax = 1000;
            int ctLineas = 0;

            // saltea la primera linea
            br.readLine();

            while (((linea = br.readLine()) != null) && ctLineas < capMax){

                ctLineas++;

                String[] columnas = linea.split(",");

                String isbn = columnas[0];
                String titulo = columnas[1];
                int nroEstante = Integer.parseInt(columnas[2]);
                int paginas = Integer.parseInt(columnas[3]);
                double precioPorDia = Double.parseDouble(columnas[4]);
                String id = columnas[5];
                String nombre = columnas[6];
                String apellido = columnas[7];
                int anios = Integer.parseInt(columnas[8]);

                libros.add(new Libro(isbn, titulo, nroEstante, paginas, precioPorDia, new Autor(id, nombre, apellido, anios)));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Libro l : libros){
            sum += l.getPrecioPorDia();

            if (l.getAutor().getAniosCarrera() > 18){
                cantAutores++;
            }

            if (l.getNroEstante() % 2 != 0){
                sumPaginas += l.getPaginas();
                ctPaginas++;
            }
        }

        int promedio = sumPaginas / ctPaginas;

        System.out.println("La cantidad recaudada por dia de los libros cargados es de: " + sum);
        System.out.println("La cantidad de autores con mas de 18 año de carrera es de: " + cantAutores);
        System.out.println("El promedio de paginas de los libros en estanterias impares es de: " + promedio);
    }
}
