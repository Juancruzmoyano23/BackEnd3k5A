package ar.edu.utnfc.backend.Ejercicio3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ejercicio3Test {

    @Test
    void cargaPolimorficaCorrectaPorTipo() {
        List<ProductoDigital> productos = Main.cargarProductosDesdeCsv("Data/productos.csv");

        assertFalse(productos.isEmpty());
        assertInstanceOf(App.class, productos.get(0));
        assertInstanceOf(CursoOnline.class, productos.get(1));
        assertInstanceOf(Ebook.class, productos.get(2));
    }

    @Test
    void rankingTop3PorPrecioFinalEsperado() {
        List<ProductoDigital> productos = crearMuestra();

        List<ProductoDigital> top3 = Main.top3PorPrecioFinal(productos);

        assertEquals(3, top3.size());
        assertEquals("E1", top3.get(0).getSku());
        assertEquals("A1", top3.get(1).getSku());
        assertEquals("C1", top3.get(2).getSku());
    }

    @Test
    void facturacionPorTipo() {
        List<ProductoDigital> productos = crearMuestra();

        Map<String, Double> facturacion = Main.facturacionPorTipo(productos);

        assertEquals(315.0, facturacion.get("EBOOK"), 0.0001);
        assertEquals(172.5, facturacion.get("APP"), 0.0001);
        assertEquals(112.0, facturacion.get("CURSO"), 0.0001);
    }

    @Test
    void busquedaPorNombreConCoincidenciaParcial() {
        List<ProductoDigital> productos = crearMuestra();

        List<ProductoDigital> encontrados = Main.buscarPorNombre(productos, "java");

        assertEquals(2, encontrados.size());
        assertTrue(encontrados.stream().anyMatch(p -> p.getSku().equals("E1")));
        assertTrue(encontrados.stream().anyMatch(p -> p.getSku().equals("C1")));
    }

    @Test
    void carritoValidoYCarritoConSkuInexistente() {
        List<ProductoDigital> productos = crearMuestra();

        double total = Main.totalCarrito(productos, List.of("A2", "C1"));
        assertEquals(169.5, total, 0.0001);

        assertThrows(ItemNotFoundException.class,
                () -> Main.totalCarrito(productos, List.of("A2", "NOEXISTE")));
    }

    @Test
    void etiquetasParseoAgrupamientoYBusqueda() {
        List<Etiqueta> etiquetas = Main.parsearEtiquetas("Java|backend|Avanzado");

        assertEquals(3, etiquetas.size());
        assertEquals("java", etiquetas.get(0).getNombre());
        assertEquals("backend", etiquetas.get(1).getNombre());
        assertEquals("avanzado", etiquetas.get(2).getNombre());

        List<ProductoDigital> productos = crearMuestra();
        Map<String, List<ProductoDigital>> agrupados = Main.agruparPorEtiqueta(productos);
        List<String> skus = Main.skusPorEtiqueta(agrupados, "backend");

        assertEquals(2, skus.size());
        assertTrue(skus.contains("E1"));
        assertTrue(skus.contains("A1"));
    }

    private List<ProductoDigital> crearMuestra() {
        List<ProductoDigital> productos = new ArrayList<>();

        productos.add(new Ebook("E1", "Java desde cero", 200, List.of(new Etiqueta("java"), new Etiqueta("backend")), 300));
        productos.add(new App("A1", "App gestion", 100, List.of(new Etiqueta("backend")), Plataforma.WEB));
        productos.add(new CursoOnline("C1", "Curso Java avanzado", 100, List.of(new Etiqueta("java")), 10));
        productos.add(new App("A2", "App mobile", 50, List.of(new Etiqueta("mobile")), Plataforma.ANDROID));
        productos.add(new Ebook("E2", "Docker practico", 100, List.of(new Etiqueta("devops")), 200));

        return productos;
    }
}

