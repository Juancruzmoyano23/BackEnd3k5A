package ar.edu.utnfc.backend.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Repository {

    private String repositoryName;
    private String description;
    private LocalDate lastUpdate;
    private Double stars;
    private String url;

    private User user;

    private Set<Language> languages;
    private Set<Tag> tags;

    public Repository(String repositoryName,
                      String description,
                      LocalDate lastUpdate,
                      Double stars,
                      String url) {

        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;

        this.languages = new HashSet<>();
        this.tags = new HashSet<>();
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public Double getStars() {
        return stars;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public long getDaysSinceUpdate() {

        return ChronoUnit.DAYS.between(
                lastUpdate,
                LocalDate.now()
        );
    }

    @Override
    public String toString() {

        return repositoryName +
                " (" +
                stars +
                " stars)";
    }
}
