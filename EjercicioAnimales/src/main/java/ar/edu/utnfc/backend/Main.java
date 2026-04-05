package ar.edu.utnfc.backend;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        // creo los objetos
        Perros perros = new Perros("Firulais");
        Gatos gatos = new Gatos("Michi");
        Osos osos = new Osos("Baloo");
        Aguilas aguilas = new Aguilas("Aguila", "chaAaa", true);
        Pinguinos pinguinos = new Pinguinos("Pingu", "wiiiii", false);
        Peces peces = new Peces("Nemo", "blub");

        // creo el array de animales
        Animales[] animales = new Animales[]{perros, gatos, osos, aguilas, pinguinos, peces};

        // recorro el array
        for (Animales a : animales) {
            System.out.println(a);
            System.out.println(" " + a.makeSound());
            System.out.println(" " + a.move());

            if (a instanceof Walker) {
                System.out.println(" " + ((Walker) a).walk());

            }

            if (a instanceof Swimmer) {
                System.out.println(" " + ((Swimmer) a).swim());
            }

            if (a instanceof  Flyer){
                System.out.println(" " + ((Flyer) a).fly());
            }
        }
    }
}
