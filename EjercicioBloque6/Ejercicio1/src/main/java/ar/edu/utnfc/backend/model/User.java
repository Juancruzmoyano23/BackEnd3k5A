package ar.edu.utnfc.backend.model;

import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>{

    private String name;

    private List<Repository> repositories;

    public User(String name) {

        this.name = name;
        this.repositories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Repository> getRepositories() {
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
    public int compareTo(User o) {

        return name.compareTo(
                o.name
        );
    }

    @Override
    public String toString() {

        return name +
                " | repos=" +
                getTotalRepositories() +
                " | stars=" +
                getTotalStars();
    }
}
