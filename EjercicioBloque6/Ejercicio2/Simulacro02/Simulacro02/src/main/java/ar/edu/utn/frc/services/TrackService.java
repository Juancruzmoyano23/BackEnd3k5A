package ar.edu.utn.frc.services;

import ar.edu.utn.frc.entities.*;
import ar.edu.utn.frc.repositories.TrackRepository;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TrackService {

    private static final String TRACKS_FILE_PATH = "src\\main\\resources\\Tracks_Data.txt";
    private static final String GENRE_FILE_PATH = "src\\main\\resources\\Genre_Data.txt";

    private TrackRepository repository;
    private AlbumService albumService;
    private ArtistService artistService;
    private ComposerService composerService;
    private GenreService genreService;
    private MediaTypeService mediaTypeService;
    private List<Track> tracks;

    public TrackService(EntityManager entityManager) {
        this.repository = new TrackRepository(entityManager);
        albumService = new AlbumService();
        artistService = new ArtistService();
        composerService = new ComposerService();
        genreService = new GenreService();
        mediaTypeService = new MediaTypeService();
        tracks = new ArrayList<>();
    }

    // Punto 1
    public void loadTracksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRACKS_FILE_PATH))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                Track track = lineToTrack(line);
                tracks.add(track);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Punto 2
    public void countTracksAndTotalTime() {
        int trackSize = tracks.size();
        Long totalMilliSeconds = tracks.stream()
                .mapToLong(track -> track.getMilliseconds())
                .sum();
        
        long segundos = totalMilliSeconds / 1000;
        long milisegundosRestantes = totalMilliSeconds % 1000;
        long minutos = segundos / 60;
        long segundosRestantes = segundos % 60;
        long horas = minutos / 60;
        long minutosRestantes = minutos % 60;
        System.out.printf("Se cargaron: %d tracks\n", trackSize);
        System.out.printf("Duracion total de la lista: %02dhs:%02dm:%02ds:%03dms\n", horas, minutosRestantes, segundosRestantes, milisegundosRestantes);
    }

    // Punto 3
    public void genreTracksFile() {
        Map<String, AtomicInteger> counters = new HashMap<>();
        tracks.forEach(track -> {
            String genreName = track.getGenre().getName();
            if (counters.containsKey(genreName)) {
                counters.get(genreName)
                        .getAndIncrement();
            } else {
                counters.put(genreName, new AtomicInteger());
            }
        });

         try (FileWriter escritor = new FileWriter(GENRE_FILE_PATH)) {
            escritor.append("Género,Cantidad de Tracks\n");
            
            for (Map.Entry<String, AtomicInteger> entrada : counters.entrySet()) {
                escritor.append(entrada.getKey())
                        .append(",")
                        .append(entrada.getValue().toString())
                        .append("\n");
            }

            escritor.flush();
            System.out.println("Archivo Texto creado correctamente: " + GENRE_FILE_PATH);

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }

    }

    // Punto 4
    public void showAlbumsInfo() {
        List<String> albumsInfo = albumService.getAlbumsInfo();
        System.out.println("----- Albums ----");
        albumsInfo.forEach(System.out::println);
    }
    // Punto 5
    public void saveTracksToDb() {
        repository.saveAll(tracks);
    }

    private Track lineToTrack(String line) {
        String[] parts = line.split("\\|");
        String trackName = parts[1];
        String albumName = parts[2];
        Album album = albumService.getOrCreateAlbum(albumName);
        String artistsNames = parts[3];
        Set<Artist> artists = artistService.getOrCreateArtists(artistsNames);
        String composerNames = parts[4];
        Set<Composer> composers = composerService.getOrCreateComposers(composerNames);
        int duration = Integer.parseInt(parts[5]);
        String genreName = parts[6];
        Genre genre = genreService.getOrCreateGenre(genreName);
        String mediaTypeName = parts[7];
        MediaType mediaType = mediaTypeService.getOrCreateMediaType(mediaTypeName);
        return new Track(trackName, duration, album, genre, mediaType, artists, composers);
    }

}
