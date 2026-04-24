package ar.edu.utnfc.backend.EjercicioMatriz;

import java.util.Arrays;
import java.util.Scanner;

public class Matriz {

    // variables
    private int[][] m;
    private int filas;
    private int columnas;

    // constructor
    public Matriz(int f, int c){
        this.filas = f;
        this.columnas = c;
        this.m = new int[filas][columnas];
    }

    // metodo obtener para obtener la matriz
    public int[][] getDatos() {
        return m;
    }

    // metodos para cargar cada valor de la matriz
    public void cargarMatriz(){
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                System.out.println("Ingrese el valor ["+ i + "][" + j + "]");
                m[i][j] = sc.nextInt();
            }
        }

    }

    // metodo para settear los valores (para el test)
    public void setValor(int f, int c, int valor){
        this.m[f][c] = valor;
    }

    // metodo para mostrar cada valor de la matriz
    public void mostrarMatriz(int[][] m){
        for (int[] fila : m){
            for (int valor : fila){
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    // metodo para multiplicar la matriz actual por otra que se pasa por parametro
    public int[][] multiplicarMatri(int[][] otra){
        int filas = m.length;
        int columnas = otra[0].length;
        int filasDeB = otra.length;

        int[][] resultado = new int[filas][columnas];

        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                for (int k = 0; k < filasDeB; k++) {
                    resultado[i][j] += m[i][k] * otra[k][j];
                }
            }
        }
        return resultado;
    }

    // Equals  HashCode rederefinidos
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matriz matriz = (Matriz) o;
        return filas == matriz.filas
                && columnas == matriz.columnas
                && Arrays.deepEquals(m, matriz.m);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(filas);
        result = 31 * result + Integer.hashCode(columnas);
        result = 31 * result + Arrays.deepHashCode(m);
        return result;
    }

}
