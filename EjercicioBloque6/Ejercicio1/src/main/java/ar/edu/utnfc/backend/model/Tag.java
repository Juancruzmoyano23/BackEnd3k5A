package ar.edu.utnfc.backend.model;

import java.util.HashSet;
import java.util.Set;

public class Tag implements Comparable<Tag>{

    private String name;

    private Set<Repository> repositories;

    public Tag(String name) {

        this.name = name;
        this.repositories = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    @Override
    public int compareTo(Tag o) {

        return name.compareTo(
                o.name
        );
    }

    @Override
    public String toString() {

        return name;
    }
}
