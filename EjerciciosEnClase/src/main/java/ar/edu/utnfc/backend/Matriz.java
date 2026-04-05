package ar.edu.utnfc.backend;

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

    public void setValor(int f, int c, int valor){
        this.m[f][c] = valor;
    }

    public Matriz multilicarMatrices(Matriz otra){
        if (this.columnas != otra.filas) {
            throw new IllegalArgumentException("Se espera una matriz con" + this.columnas + "filas");
        }

        Matriz resultado = new Matriz(this.filas, otra.columnas);

        return null;
    }

}
