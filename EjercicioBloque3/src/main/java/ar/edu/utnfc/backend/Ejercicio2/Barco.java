package ar.edu.utnfc.backend.Ejercicio2;

import lombok.Data;

@Data
public class Barco {

    private String matricula;
    private int nroMuelleCarga;
    private int capacidadCargaPermitida;
    private double costoAlquilerHoraAmarre;
    private Capitan capitan;

    public Barco(){}


}
