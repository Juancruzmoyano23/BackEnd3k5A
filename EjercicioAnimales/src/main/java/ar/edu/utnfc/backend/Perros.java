package ar.edu.utnfc.backend;

public class Perros extends Animales implements Walker {

    public Perros(String n){
        super(n, "guau");
    }

    @Override
    public String walk() {
        return name + " is walking";
    }
}