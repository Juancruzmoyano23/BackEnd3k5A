package org.instituto.ProyectoBackEnd.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "segmentos")
public class Segmento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "descripcion")
    private String descripcion;

    
    
}
