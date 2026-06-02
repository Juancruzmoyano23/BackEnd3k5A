package ar.edu.utn.frc.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "artists")
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "artists")
    private Set<Track> tracks;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTrack(Track track) {
        if ( tracks == null) {
            tracks = new HashSet<>();
        }
        tracks.add(track);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        String stringTemplate = "Artist { id = %d, name = %s }";
        return String.format(stringTemplate, id, name);
    }
}
