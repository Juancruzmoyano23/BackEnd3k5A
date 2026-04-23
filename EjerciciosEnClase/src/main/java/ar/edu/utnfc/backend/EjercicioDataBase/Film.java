package ar.edu.utnfc.backend.EjercicioDataBase;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "FILM")
public class Film {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILM_ID")
    private long id;

    @Column(name = "TITULO")
    private String title;
    
    public Film(){}


}
