package ar.edu.utn.frc.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "composers")
public class Composer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composer_id")
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "composers")
    private Set<Track> tracks;

    public Composer(String name) {
        this.name = name;
    }
    public Composer() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addTrack(Track toBeAdded) {

        if (tracks == null) {
            tracks = new HashSet<>();
        }

        tracks.add(toBeAdded);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        if (this == o) return true;

        Composer other = (Composer) o;
        return Objects.equals(id, other.id) 
        && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    

    @Override
    public String toString() {
        String stringTemplate = "Composer { id = %d , name = %s}";
        return String.format(stringTemplate, id, name);
    }
}
