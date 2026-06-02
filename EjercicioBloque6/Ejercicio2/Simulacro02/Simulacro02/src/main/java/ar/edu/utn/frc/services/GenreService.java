package ar.edu.utn.frc.services;

import java.util.HashMap;
import java.util.Map;

import ar.edu.utn.frc.entities.Genre;

public class GenreService {

    private Map<String, Genre> genres;

    public GenreService() {
        this.genres = new HashMap<>();
    }

    public Genre getOrCreateGenre(String name) {
        if (genres.containsKey(name)) {
            return genres.get(name);
        }

        Genre genre = new Genre(name);
        genres.put(name, genre);
        return genre;
    }

}
