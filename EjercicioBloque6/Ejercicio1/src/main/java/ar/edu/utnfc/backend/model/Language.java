package ar.edu.utnfc.backend.model;

import java.util.HashSet;
import java.util.Set;

public class Language implements Comparable<Language>{

    private String name;

    private Set<Repository> repositories;

    public Language(String name) {

        this.name = name;
        this.repositories = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public int getTotalRepositories() {

        return repositories.size();
    }

    public double getTotalStars() {

        return repositories.stream()
                .mapToDouble(
                        Repository::getStars
                )
                .sum();
    }

    @Override
    public int compareTo(Language o) {

        return name.compareTo(
                o.name
        );
    }

    @Override
    public String toString() {

        return name;
    }
}
