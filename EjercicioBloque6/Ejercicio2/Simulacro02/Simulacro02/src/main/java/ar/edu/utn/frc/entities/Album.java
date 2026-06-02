package ar.edu.utn.frc.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {
    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_milliseconds")
    private int totalMilliseconds;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    private List<Track> tracks;
    
    public Album(String name) {
        this.name = name;
    }

    public Album() {
    }

    @Override
    public int compareTo(Album o) {
        return this.name.compareTo(o.name);
    }

    public void addTrack(Track toBeAdded) {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        tracks.add(toBeAdded);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTotalMiliseconds() {
        this.totalMilliseconds = (int) tracks
                            .stream()
                            .mapToLong(Track::getMilliseconds)
                            .sum();
    }

    public String getTotalTime() {
        setTotalMiliseconds();
        int total = totalMilliseconds;
        long hours = total / 3600000;
        long minutes = (total % 3600000) / 60000;
        long seconds = (total % 60000) / 1000;
        long milliseconds = total % 1000;
        return String.format("%dhs:%02dm:%02ds:%03dms", hours, minutes, seconds, milliseconds);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public String getAlbumInfo() {
        String stringTemplate = "- %s tiene un total de %d tracks culminando en una duracion de %s";
        return String.format(stringTemplate, name, tracks.size(), getTotalTime());
    }

    @Override
    public boolean equals(Object o) {
        if ( o == null  || o.getClass() != getClass()) return false;
        if (this == o) return true;
        Album other = (Album) o;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalTime: " + getTotalTime() +
                ", tracks=" + tracks +
                '}';
    }
}
