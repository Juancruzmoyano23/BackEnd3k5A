package org.instituto.ProyectoBackEnd.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ventas")
public class Venta {

    @Id
    @Column(name = "nro_ticket")
    private Integer nroTicket;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "cod_forma_pago", nullable = false)
    private Integer codFormaPago;

    @Column(name = "legajo_vend", nullable = false)
    private Integer legajoVend;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

}
