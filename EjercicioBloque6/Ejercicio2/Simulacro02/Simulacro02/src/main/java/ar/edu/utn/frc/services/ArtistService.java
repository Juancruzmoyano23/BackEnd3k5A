package ar.edu.utn.frc.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ar.edu.utn.frc.entities.Artist;

public class ArtistService {

    private Map<String, Artist> artists;
    
    public ArtistService() {
        artists = new HashMap<>();
    }

    public Set<Artist> getOrCreateArtists(String names) {
        Set<Artist> result = new HashSet();
        String[] artistNames = names.split("[,&]");
        for (String artistName : artistNames) {
            if ( artists.containsKey(artistName)) {
                result.add(artists.get(artistName));
            } else {
                Artist newArtist = new Artist(artistName);
                artists.put(artistName, newArtist);
                result.add(newArtist);
            }
        }
        return result;
    }

}
