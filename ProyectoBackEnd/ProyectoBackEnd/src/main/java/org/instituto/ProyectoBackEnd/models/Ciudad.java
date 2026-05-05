package org.instituto.ProyectoBackEnd.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ciudades")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "provincia")
    private Long provincia;
    
}
