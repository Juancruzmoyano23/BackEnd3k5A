
package ar.edu.utnfc.backend;

public abstract class Animales {

    protected String name, sound; 
    
    public Animales(String n, String s) {
        this.name = n;
        this.sound = s;
    }

    public String makeSound() {
        return name + " says " + sound + "!";
    }

    public String move() {
        return "walks"; // Comportamiento por defecto
    }

    @Override
    public String toString() {
        return name + " (" + getClass().getSimpleName() + ")";
    }

}