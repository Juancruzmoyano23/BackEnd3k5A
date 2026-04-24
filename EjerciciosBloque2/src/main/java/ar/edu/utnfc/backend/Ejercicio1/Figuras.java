package ar.edu.utnfc.backend.Ejercicio1;


public class Figuras {

    public static void figura1() {
        System.out.println("=== FIGURA 1 ===");
        
        int filas = 4;
        int columnas = 6;
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // imprimo el asterisco despues de cada columna por cada fila que voy recorriendo
                System.out.print('*');
                // imprimo el espacio
                if (j < columnas - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void figura2() {
        System.out.println("=== FIGURA 2 ===");

        int filas = 4;
        int columnas = 6;
        
        for (int i = 0; i < filas; i++) {
            // Si la fila es impar, agrego un espacio en blanco al inicio
            if (i % 2 == 1) {
                System.out.print(' ');
            }

            else {
                for (int j = 0; j < columnas; j++) {

                    System.out.print('*');

                    if (j < columnas - 1) {
                        System.out.print(' ');
                    }
                }
                System.out.println();
            }   
        }
        System.out.println();
    }

    public static void figura3() {

        System.out.println("=== FIGURA 3 ===");

        int maxFilas = 5;
        
        for (int i = 1; i <= maxFilas; i++) {
            for (int j = 0; j < i; j++) {

                System.out.print('*');
                // solo va a poner espacios cuando se cumpla la condicion de abajo
                if (j < i - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void figura4() {

        System.out.println("=== FIGURA 4 ===");

        int maxFilas = 5;
        
        // primera parte igual que la figura 3
        for (int i = 1; i <= maxFilas; i++) {
            for (int j = 0; j < i; j++) {

                System.out.print('*');

                if (j < i - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        
        // segunda parte es la inversa de la figura 3, por eso el for va de maxFilas - 1 hasta 1
        for (int i = maxFilas - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {

                System.out.print('*');
                
                if (j < i - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
