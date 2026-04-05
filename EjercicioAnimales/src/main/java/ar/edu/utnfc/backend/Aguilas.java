package ar.edu.utnfc.backend;

public class Aguilas extends Pajaros implements Walker, Flyer{

    public Aguilas(String n, String s, boolean f){

        super(n, "chaAaa", true);
    }

    @Override
    public String walk(){
        return this.name + " is walking";
    }

    public String fly(){
        return this.name + " is flying";
    }

    public String move(){
        return this.name + " is flying and walking";
    }
}