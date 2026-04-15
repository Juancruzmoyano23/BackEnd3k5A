package ar.edu.utnfc.backend.EjercicioTamagochi;

import lombok.Data;

@Data
public class Mascota {


    private String nombre;
    private int energia;
    private int humor;
    private boolean durmiendo = false;
    private boolean EstaDespierto = false;
    private boolean puedeIngestar = false;
    private boolean puedeRealizarActividad = false;


    public void comer(){
        energia = energia + (int) (energia * 0.10);
        humor = humor + 1;
    }

    public void beber(){
        energia = energia + (int) (energia * 0.05);
        humor = humor + 1;
    }

    public void correr(){
        energia = energia - (int) (energia * 0.35);
        humor = humor - 2;
    }

    public void saltar(){
        energia = energia - (int) (energia * 0.15);
        humor = humor - 2;
    }
    public void dormir(){
        durmiendo = true;
        EstaDespierto = true;
        energia = energia + 25;
        humor = humor + 2;
    }

    public void despertar(){
        EstaDespierto = true;
        durmiendo = false;
        puedeIngestar = true;
        puedeRealizarActividad = true;
        humor = humor + 1;
    }


}
