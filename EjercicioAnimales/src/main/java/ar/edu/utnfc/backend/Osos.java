package ar.edu.utnfc.backend;

import ar.edu.utnfc.backend.Walker;
import ar.edu.utnfc.backend.Swimmer;

public class Osos extends Animales implements Walker, Swimmer {

    public Osos(String n){
        super(n, "Rooar");
    }

    public String move()
    {
        return this.name + " is walking and swimming";
    }

    public String walk()
    {
        return this.name + " is walking";
    }

    public String swim(){
        return this.name + " is swimming";
    }
}