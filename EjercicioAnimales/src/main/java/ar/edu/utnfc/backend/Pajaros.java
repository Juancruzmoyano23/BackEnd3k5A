package ar.edu.utnfc.backend;

public abstract class Pajaros extends Animales implements Flyer, Walker {

    protected boolean canFly;

    public Pajaros(String n, String s, boolean canFly){
        super(n, s);
        this.canFly = canFly;
    }

    @Override
    public String fly() {
        return name + " is flying";
    }

    @Override
    public String walk() {
        return name + " is walking";
    }

    @Override
    public String toString(){
        return canFly ? "walk" : "fly";
    }

    
}