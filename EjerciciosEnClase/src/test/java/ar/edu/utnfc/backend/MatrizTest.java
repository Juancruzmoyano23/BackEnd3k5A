package ar.edu.utnfc.backend;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class MatrizTest {

    @Test
    void multiplicarMatrizTest() {
        Matriz matriz1 = new Matriz(2, 3);
        matriz1.setValor(0, 0, 1);
        matriz1.setValor(0, 1, 2);
        matriz1.setValor(0, 2, 3);
        matriz1.setValor(1, 0, 4);
        matriz1.setValor(1, 1, 5);
        matriz1.setValor(1, 2, 6);

        Matriz matriz2 = new Matriz(3, 2);
        matriz2.setValor(0, 0, 7);
        matriz2.setValor(0, 1, 8);
        matriz2.setValor(1, 0, 9);
        matriz2.setValor(1, 1, 10);
        matriz2.setValor(2, 0, 11);
        matriz2.setValor(2, 1, 12);

        int[][] resultado = matriz1.multiplicarMatri(matriz2.getDatos());

        int[][] esperado = {{58, 64}, {139, 154}};

        assertArrayEquals(esperado[0], resultado[0]);
        assertArrayEquals(esperado[1], resultado[1]);
    }

    @Test
    void equalsYHashCode_deberianCoincidirParaMatricesIguales() {
        Matriz a = new Matriz(2, 2);
        a.setValor(0, 0, 1);
        a.setValor(0, 1, 2);
        a.setValor(1, 0, 3);
        a.setValor(1, 1, 4);

        Matriz b = new Matriz(2, 2);
        b.setValor(0, 0, 1);
        b.setValor(0, 1, 2);
        b.setValor(1, 0, 3);
        b.setValor(1, 1, 4);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}


