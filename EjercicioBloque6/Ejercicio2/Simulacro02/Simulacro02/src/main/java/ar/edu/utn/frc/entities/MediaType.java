package ar.edu.utn.frc.entities;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "media_types")
public class MediaType  {

    @Id
    @Column(name = "media_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public MediaType() {
    }

    public MediaType(String name) {
        this.name = name;
    }

    public MediaType(Long id, String name) {
        this.id = id;
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
        if (o == null || o.getClass() != getClass()) return false;
        if (this == o) return true;
        MediaType other = (MediaType) o;
        return id.equals(other.id) && name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        String stringTemplate = "MediaType { id = %d, name = %s }";
        return String.format(stringTemplate, id, name);

    }
}
