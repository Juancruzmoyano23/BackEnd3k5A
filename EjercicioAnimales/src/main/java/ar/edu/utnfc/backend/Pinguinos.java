package ar.edu.utnfc.backend;

import ar.edu.utnfc.backend.Swimmer;
import ar.edu.utnfc.backend.Walker;

public class Pinguinos extends Pajaros implements Walker, Swimmer {

    public Pinguinos(String n, String s, boolean f) {
        super(n, s, false);
    }

    public String toString() {
        return "sonido de pinguino";
    }

    public String walk() {
        return this.name + "nada y camina";
    }

    public String move() {
        return this.name + " is walkingand swimming";
    }

    public String swim() {
        return this.name + " is swimming";
    }

}