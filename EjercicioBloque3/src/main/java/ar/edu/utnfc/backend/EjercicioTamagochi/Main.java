package ar.edu.utnfc.backend.EjercicioTamagochi;

public class Main {

    public static void main(String[] args){

        Mascota miMascota = new Mascota("Firulais", 100, 3, false, true, 0, 0);

        System.out.println("=== MASCOTA CREADA ===");
        System.out.println(miMascota);
        System.out.println();

        // PRUEBAS DE INGESTA - COMER
        System.out.println("--- COMER 1 ---");
        miMascota.comer();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("--- COMER 2 ---");
        miMascota.comer();
        System.out.println(miMascota);
        System.out.println();

        /*System.out.println("--- COMER 3 (humor baja) ---");
        miMascota.comer();
        System.out.println(miMascota);
        System.out.println();

        // PRUEBAS DE INGESTA - BEBER
        System.out.println("--- BEBER 1 ---");
        miMascota.beber();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("--- BEBER 2 ---");
        miMascota.beber();
        System.out.println(miMascota);
        System.out.println();
        */


        // PRUEBAS DE ACTIVIDAD - CORRER
        System.out.println("--- CORRER 1 ---");
        miMascota.correr();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("--- SALTAR 1 ---");
        miMascota.saltar();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("--- CORRER 2 (3ª actividad = se duerme) ---");
        miMascota.correr();
        System.out.println(miMascota);
        System.out.println();

        // PRUEBA: No puede hacer nada durmiendo
        System.out.println("--- INTENTA COMER DURMIENDO ---");
        System.out.println("¿Pudo comer? " + miMascota.comer());
        System.out.println(miMascota);
        System.out.println();

        // DESPERTAR
        System.out.println("--- DESPERTAR ---");
        miMascota.despertar();
        System.out.println(miMascota);
        System.out.println();

        // DORMIR
        System.out.println("--- DORMIR ---");
        miMascota.dormir();
        System.out.println(miMascota);
        System.out.println();

        // DESPERTAR de nuevo
        System.out.println("--- DESPERTAR DE NUEVO ---");
        miMascota.despertar();
        System.out.println(miMascota);
        System.out.println();

        // PRUEBA DE MUERTE POR EMPACHO
        System.out.println("=== PRUEBA DE MUERTE POR EMPACHO ===");
        System.out.println("Ingesta 3:");
        miMascota.comer();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("Ingesta 4:");
        miMascota.comer();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("Ingesta 5 (MUERE):");
        miMascota.comer();
        System.out.println(miMascota);
        System.out.println();

        System.out.println("Intenta comer estando muerto:");
        System.out.println("¿Pudo comer? " + miMascota.comer());
    }
}
