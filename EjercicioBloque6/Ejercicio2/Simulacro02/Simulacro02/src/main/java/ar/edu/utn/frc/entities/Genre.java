package ar.edu.utn.frc.entities;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if ( o == null || o.getClass() != getClass()) return false;
        if (this == o) return true;
        Genre other = (Genre) o;
        return id == other.id && name.equalsIgnoreCase(other.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        String stringTemplate = "Genre { id = %d, name = %s }";
        return String.format(stringTemplate, id, name);
    }
}
