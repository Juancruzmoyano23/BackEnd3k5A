package ar.edu.utn.frc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.frc.entities.Album;

public class AlbumService {

    private Map<String, Album> albums;

    public AlbumService() {
        this.albums = new HashMap<>();
    }

    public Album getOrCreateAlbum(String albumName) {        
        if (albums.containsKey(albumName)) {
            return albums.get(albumName);
        }
        Album album = new Album(albumName);
        albums.put(albumName, album);
        return album;
    }

    public List<String> getAlbumsInfo() {
        return albums.values()
                    .stream()
                    .sorted()
                    .map(Album::getAlbumInfo)
                    .toList();
    }

}
