package ar.edu.utnfc.backend.EjercicioMatriz;

public class App2 {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        Matriz matriz1 = new Matriz(2, 3);
        matriz1.cargarMatriz();
        matriz1.mostrarMatriz(matriz1.getDatos());

        Matriz matriz2 = new Matriz(3, 3);
        matriz2.cargarMatriz();
        matriz2.mostrarMatriz(matriz2.getDatos());

        int[][] resultado = matriz1.multiplicarMatri(matriz2.getDatos());
        System.out.println("Resultado de la multiplicacion:");
        matriz1.mostrarMatriz(resultado);

    }
}
