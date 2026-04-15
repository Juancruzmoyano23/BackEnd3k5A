package ar.edu.utnfc.backend.EjercicioProcesarArchivo;

import java.io.IOException;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) throws IOException {

        String csv = "data/archivo.csv";
        Series s = new Series("", "", "", 0, 0.0, 0);

        // Paso 1: Leer las series existentes con leerSeries y mostrarlas por pantalla
        System.out.println("=== Series existentes (antes de agregar) ===");
        ArrayList<Series> series = s.leerSeries(csv);
        for (Series se : series) {
            System.out.println(se);
        }

        // Paso 2: Crear un ArrayList con algunas series nuevas
        System.out.println("\n=== Nuevas series a agregar ===");
        ArrayList<Series> nuevasSeries = new ArrayList<>();
        nuevasSeries.add(new Series("Dark", "Ciencia Ficcion", "Netflix", 2017, 8.8, 3));
        nuevasSeries.add(new Series("The Witcher", "Fantasia", "Netflix", 2019, 8.2, 3));
        nuevasSeries.add(new Series("Fallout", "Ciencia Ficcion", "Amazon Prime", 2024, 8.5, 1));
        for (Series se : nuevasSeries) {
            System.out.println(se);
        }

        // Paso 3: Llamar a agregarSeries para escribir al final del archivo
        System.out.println("\nAgregando series al archivo...");
        s.agregarSeries(csv, nuevasSeries);
        System.out.println("Series agregadas correctamente");

        // Paso 4: Volver a leer el archivo y mostrar el catálogo actualizado
        System.out.println("\n=== Catálogo actualizado (después de agregar) ===");
        ArrayList<Series> seriesActualizadas = s.leerSeries(csv);
        for (Series se : seriesActualizadas) {
            System.out.println(se);
        }

        // eliminar serie
        System.out.println("=== Listado antes de eliminar ===");
        for (Series se : seriesActualizadas) {
            System.out.println(se);
        }

        boolean EsEliminada = s.eliminarSerie(seriesActualizadas, "Stranger Things");
        if (EsEliminada){
            System.out.println("La serie se elimino correctamente ");
        }
        else {
            System.out.println("La serie no se encontro");
        }

        // guardar archivo modificado
        s.guardarSeries(csv, seriesActualizadas);
        System.out.println("El archivo se actualizo con exito...");

        System.out.println();
        System.out.println("=== Listado final ===");
        for (Series se : seriesActualizadas) {
            System.out.println(se);
        }

        // actualziar series
        s.actualizarSerie(seriesActualizadas, "Arcane", 9, 2.0);
        s.guardarSeries(csv, seriesActualizadas);
        System.out.println("=== SERIE ACTUALIZADA ===");
        for (Series se : seriesActualizadas) {
            System.out.println(se);
        }
    }
}

