package ar.edu.utn.frc.services;

import java.util.HashMap;
import java.util.Map;

import ar.edu.utn.frc.entities.MediaType;

public class MediaTypeService {

    private Map<String, MediaType> mediaTypes;

    public MediaTypeService() {
        this.mediaTypes = new HashMap<>();
    }

    public MediaType getOrCreateMediaType(String name) {
        if (mediaTypes.containsKey(name)) {
            return mediaTypes.get(name);
        }
        MediaType mediaType = new MediaType(name);
        mediaTypes.put(name, mediaType);
        return mediaType;
    }

}
