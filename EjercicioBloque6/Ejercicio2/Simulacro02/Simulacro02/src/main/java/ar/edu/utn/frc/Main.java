package ar.edu.utn.frc;

import ar.edu.utn.frc.services.TrackService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        TrackService trackService = new TrackService(em);
        trackService.loadTracksFromFile();
        trackService.countTracksAndTotalTime();
        trackService.genreTracksFile();
        trackService.showAlbumsInfo();
        trackService.saveTracksToDb();
        em.close();
        emf.close();
    }
}
