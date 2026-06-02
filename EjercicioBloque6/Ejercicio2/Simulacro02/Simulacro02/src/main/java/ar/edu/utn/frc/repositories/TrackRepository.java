package ar.edu.utn.frc.repositories;

import java.util.Collection;

import ar.edu.utn.frc.entities.Track;
import jakarta.persistence.EntityManager;

public class TrackRepository {

    private EntityManager entityManager;

    public TrackRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void saveAll(Collection<Track> toBeSaved) {
        toBeSaved
        .forEach(this::save);
    }

    public void save(Track toBeSaved) {
        begin();
        entityManager.persist(toBeSaved);
        commit();
    }

    private void begin() {
        entityManager.getTransaction()
                .begin();
    }

    private void commit() {
        entityManager.getTransaction()
                .commit();
    }
}
