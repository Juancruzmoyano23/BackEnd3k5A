package org.instituto.ProyectoBackEnd.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_doc")
    private String tipo_doc;

    @Column(name = "nro_doc")
    private Long nro_doc;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombres")
    private String nombre;
    @Column(name = "calle")
    private String calle;

    @Column(name = "nro_calle")
    private Integer nro_calle;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = true)
    private Ciudad ciudad;
    
}
