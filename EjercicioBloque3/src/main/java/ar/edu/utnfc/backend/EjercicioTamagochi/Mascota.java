package ar.edu.utnfc.backend.EjercicioTamagochi;

import lombok.Data;

@Data
public class Mascota {


    private String nombre;
    private int energia;
    private int humor;
    private boolean durmiendo;
    private boolean estaVivo;
    private int ctIngestas;
    private int ctActividades;

    public Mascota(String n, int e, int h, boolean d, boolean ev, int ctI, int ctA){
        this.nombre = n;
        this.energia = e;
        this.humor = h;
        this.durmiendo = d;
        this.estaVivo = ev;
        this.ctIngestas = ctI;
        this.ctActividades = ctA;

    }

    public boolean comer(){
        // comprobar si la mascota esta viva o si esta durmiendo
        if (!estaVivo){return false;}
        if (durmiendo){return false;}


        // aumento de energia
        energia = Math.min(energia + (int) (energia * 0.10),100);
        System.out.println("Energia aumentada +10%");

        ctIngestas++;
        ctActividades = 0;

        if (ctIngestas <= 2){
            humor = Math.min(humor + 1, 5);
            System.out.println("Humor aumentado +1");

        } else {
            humor = Math.max(humor - 1, 1);
            System.out.println("Humor decrementado -1");
        }

        if (ctIngestas >= 5){
            estaVivo = false;
            System.out.println("Murio de empacho");
        }
        return true;
    }

    public boolean beber(){
        // comprobar si la mascota esta viva o si esta durmiendo
        if (!estaVivo){return false;}
        if (durmiendo){return false;}

        // aumento de energia
        energia = Math.min(energia + (int) (energia * 0.05), 100);
        System.out.println("Energia aumentada +5%");

        ctIngestas++;
        ctActividades = 0;

        if (ctIngestas <= 2){
            humor = Math.min(humor + 1, 5);
            System.out.println("Humor aumentado +1");

        } else {
            humor = Math.max(humor - 1, 1);
            System.out.println("Humor decrementado -1");
        }

        if (ctIngestas >= 5){
            estaVivo = false;
            System.out.println("Murio de empacho");
        }
        return true;
    }

    public boolean correr(){
        // comprobar si la mascota esta viva o si esta durmiendo
        if (!estaVivo){return false;}
        if (durmiendo){return false;}

        ctActividades++;
        ctIngestas = 0;
        System.out.println("La mascota esta corriendo");

        humor = Math.max(humor - 2, 1);
        System.out.println("Humor decrementado -2");

        energia -= (int) (energia * 0.35);
        System.out.println("Energia decrementada -35%");

        if (energia <= 0) {
            estaVivo = false;
            System.out.println("Murio de cansada");
            return true;
        }

        if (ctActividades >= 3){
            durmiendo = true;
            System.out.println("Se durmio por exeso de actividad");
            return true;
        }

        return true;
    }

    public boolean saltar() {
        // comprobar si la mascota esta viva o si esta durmiendo
        if (!estaVivo){return false;}
        if (durmiendo){return false;}

        humor = Math.max(humor - 2, 1);
        System.out.println("Humor decrementado -2");

        ctActividades++;
        ctIngestas = 0;
        System.out.println("La mascota esta saltando");


        if (ctActividades >= 3){
            durmiendo = true;
            System.out.println("Se durmio por exeso de actividad");
            return true;
        }

        energia -= (int) (energia * 0.15);
        System.out.println("Energia decrementada -15%");

        if (energia <= 0) {
            estaVivo = false;
            System.out.println("Murio de cansada");
            return true;
        }
        return true;
    }

    public boolean dormir(){
        if (!estaVivo){return false;}

        durmiendo = true;
        System.out.println("La mascota se durmio");

        energia = Math.min(energia + 25, 100);
        System.out.println("Energia incrementada +25");
        humor = Math.min(humor + 2, 5);
        System.out.println("Humor incrementado +2");


        ctActividades = 0;
        ctIngestas = 0;

        return true;
    }

    public boolean despertar(){
        if (!estaVivo){return false;}
        if (!durmiendo){return false;}

        System.out.println("La mascota despierto");
        durmiendo = false;

        ctIngestas = 0;
        ctActividades = 0;

        humor = Math.max(humor - 1, 1);
        System.out.println("Humor decrementado -1");

        return true;
    }

    @Override
    public String toString(){
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", energia=" + energia +
                ", humor=" + humor +
                ", durmiendo=" + durmiendo +
                ", estaVivo=" + estaVivo +
                ", ctIngestas=" + ctIngestas +
                ", ctActividades=" + ctActividades +
                '}';
    }
}
