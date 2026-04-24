package ar.edu.utnfc.backend.EjercicioProcesarArchivo;


import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Series {

    // titulo (String), genero (String), plataforma (String), anio (int), temporadas (int), calificacion

    // variables

    private String titulo;
    @Getter
    @Setter
    private String genero;
    @Getter
    @Setter
    private String plataforma;
    @Getter
    @Setter
    private int anio;
    @Getter
    @Setter
    private int temporada;
    @Getter
    @Setter
    private double calificacion;

    // constructor
    public Series(String t, String g, String p, int a, double c, int temp) {
        this.titulo = t;
        this.anio = a;
        this.calificacion = c;
        this.genero = g;
        this.temporada = temp;
        this.plataforma = p;

    }

    public String getTtitulo() {
        return titulo;
    }

    // Metodos

    @Override
    public String toString() {
        return "Titulo: " + titulo + " Genero: " + genero + " Plataforma: " + plataforma + " Año: " + anio + " Calificacion: " + calificacion + " Temporada: " + temporada;
    }

    public static String toCsv(ArrayList<Series> seriesArray)   {
        StringBuilder sb = new StringBuilder();
        for (Series serie : seriesArray) {
            sb.append(serie.getTtitulo()).append(",")
                    .append(serie.getGenero()).append(",")
                    .append(serie.getPlataforma()).append(",")
                    .append(serie.getAnio()).append(",")
                    .append(serie.getTemporada()).append(",")
                    .append(serie.getCalificacion()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Series> leerSeries(String arhivo) {

        int contador = 0;
        ArrayList<Series> seriesArray = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(
                Path.of(arhivo), StandardCharsets.UTF_8)) {

            String linea;

            // la primera linea es el encabezado, por lo que se lee y se descarta
            linea = lector.readLine();


            while ((linea = lector.readLine()) != null) {

                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                contador++;

                String[] columnas = linea.split(",");

                String titulo = columnas[0];
                String genero = columnas[1];
                String plataforma = columnas[2];
                int anio = Integer.parseInt(columnas[3]);
                double calificacion = Double.parseDouble(columnas[5]);
                int temporada = Integer.parseInt(columnas[4]);

                Series series = new Series(titulo, genero, plataforma, anio, calificacion, temporada);

                seriesArray.add(series);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return seriesArray;
    }

    public void agregarSeries(String archivo, ArrayList<Series> nuevasSeries) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Series serie : nuevasSeries) {
            sb.append("\n").append(serie.getTtitulo()).append(",")
                    .append(serie.getGenero()).append(",")
                    .append(serie.getPlataforma()).append(",")
                    .append(serie.getAnio()).append(",")
                    .append(serie.getTemporada()).append(",")
                    .append(serie.getCalificacion());
        }
        Files.write(Path.of(archivo), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    public static void guardarSeries(String archivo, ArrayList<Series> nuevasSeries) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {

            // Escribir la línea de encabezado
            writer.write("titulo,genero,plataforma,anio,temporadas,calificacion\n");

            // Usar toCsv() para obtener el formato CSV
            String contenidoCsv = toCsv(nuevasSeries);
            writer.write(contenidoCsv);
        }
    }

    public static boolean eliminarSerie(ArrayList<Series> series, String titulo) {
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getTtitulo().equalsIgnoreCase(titulo)) {
                series.remove(i);
                return true;
            }
        }
        return false;
    }

    public static boolean actualizarSerie(ArrayList<Series> series, String titulo, int nuevasTemporadas, double nuevaCalificacion){
        for (Series s : series){
            if (s.getTtitulo().equalsIgnoreCase(titulo)){
                s.setTemporada(nuevasTemporadas);
                s.setCalificacion(nuevaCalificacion);
                return true;
            }
        }
        return false;
    }
}