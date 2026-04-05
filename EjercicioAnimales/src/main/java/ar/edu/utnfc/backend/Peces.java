package ar.edu.utnfc.backend;

public class Peces extends Animales implements Swimmer {

    public Peces(String n, String s){
        super(n, "gluc gluc");
    }

    @Override
    public String swim() {
        return name + " is swimming";
    }

    public String toString(){
        return "swim";
    }
}