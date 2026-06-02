package ar.edu.utn.frc.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ar.edu.utn.frc.entities.Composer;

public class ComposerService {

    private Map<String, Composer> composers;

    public ComposerService() {
        composers = new HashMap<>();
    }

    public Set<Composer> getOrCreateComposers(String names) {
        Set<Composer> result = new HashSet<>();
        String[] composerNames = names.split("[,&]");
        for (String composerName : composerNames) {
            if (composers.containsKey(composerName)) {
                result.add(composers.get(composerName));
            } else {
                Composer newComposer = new Composer(composerName);
                composers.put(composerName, newComposer);
                result.add(newComposer);
            }
        }
        return result;
    }

}
