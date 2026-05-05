package org.instituto.ProyectoBackEnd.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @OneToOne
    @JoinColumn(name = "codigo")
    private Segmento codigo_segmento;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha_lanzamiento")
    private Date fecha_lanzamiento;
    
}
