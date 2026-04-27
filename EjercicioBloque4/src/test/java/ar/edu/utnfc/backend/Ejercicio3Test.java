package ar.edu.utnfc.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ar.edu.utnfc.backend.Ejercicio3.App;
import ar.edu.utnfc.backend.Ejercicio3.CursoOnline;
import ar.edu.utnfc.backend.Ejercicio3.Ebook;
import ar.edu.utnfc.backend.Ejercicio3.Etiqueta;
import ar.edu.utnfc.backend.Ejercicio3.ItemNotFoundException;
import ar.edu.utnfc.backend.Ejercicio3.Main;
import ar.edu.utnfc.backend.Ejercicio3.Plataforma;
import ar.edu.utnfc.backend.Ejercicio3.ProductoDigital;

public class Ejercicio3Test {

    @Test
    void cargaPolimorficaCorrectaPorTipo() {
        // Crear productos de cada tipo
        List<Etiqueta> etiquetas = new ArrayList<>();
        etiquetas.add(new Etiqueta("java"));

        ProductoDigital ebook = new Ebook("E001", "Java Ebook", 100, etiquetas, 200);
        ProductoDigital app = new App("A001", "Java App", 50, etiquetas, Plataforma.WEB);
        ProductoDigital curso = new CursoOnline("C001", "Java Curso", 150, etiquetas, 30);

        // Verificar que son instancias correctas
        assertTrue(ebook instanceof Ebook);
        assertTrue(app instanceof App);
        assertTrue(curso instanceof CursoOnline);

        // Verificar tipos
        assertEquals("EBOOK", ebook.getTipo());
        assertEquals("APP", app.getTipo());
        assertEquals("CURSO", curso.getTipo());
    }

    @Test
    void rankingTop3PorPrecioFinal() {
        List<Etiqueta> etiquetas = new ArrayList<>();

        // Crear 5 productos con precios finales distintos
        List<ProductoDigital> productos = new ArrayList<>();
        productos.add(new Ebook("E1", "E1", 100, etiquetas, 100)); // precioFinal = 100 * 1.05 = 105.0
        productos.add(new App("A1", "A1", 100, etiquetas, Plataforma.WEB)); // precioFinal = 100 * 1.15 = 115.0
        productos.add(new CursoOnline("C1", "C1", 100, etiquetas, 25)); // precioFinal = 100 * 1.18 = 118.0
        productos.add(new Ebook("E2", "E2", 50, etiquetas, 100)); // precioFinal = 50 * 1.05 = 52.5
        productos.add(new App("A2", "A2", 80, etiquetas, Plataforma.WEB)); // precioFinal = 80 * 1.15 = 92.0

        // Simular ordenamiento por precio final descendente
        productos.sort((a, b) -> Double.compare(b.precioFinal(), a.precioFinal()));

        // Tomar top 3
        List<ProductoDigital> top3 = productos.subList(0, Math.min(3, productos.size()));

        assertEquals(3, top3.size());
        assertEquals("C1", top3.get(0).getSku()); // 118.0
        assertEquals("A1", top3.get(1).getSku()); // 115.0
        assertEquals("E1", top3.get(2).getSku()); // 105.0
    }

    @Test
    void facturacionPorTipo() {
        List<Etiqueta> etiquetas = new ArrayList<>();

        List<ProductoDigital> productos = new ArrayList<>();
        productos.add(new Ebook("E1", "E1", 100, etiquetas, 100)); // 105.0
        productos.add(new Ebook("E2", "E2", 100, etiquetas, 100)); // 105.0
        productos.add(new App("A1", "A1", 100, etiquetas, Plataforma.WEB)); // 115.0
        productos.add(new CursoOnline("C1", "C1", 100, etiquetas, 10)); // 112.0

        // Simular facturación por tipo (suma de precioFinalExacto)
        Map<String, Double> facturacion = new java.util.HashMap<>();
        for (ProductoDigital p : productos) {
            facturacion.merge(p.getTipo(), p.precioFinalExacto(), Double::sum);
        }

        assertEquals(210.0, facturacion.getOrDefault("EBOOK", 0.0), 0.01);
        assertEquals(115.0, facturacion.getOrDefault("APP", 0.0), 0.01);
        assertEquals(112.0, facturacion.getOrDefault("CURSO", 0.0), 0.01);
    }

    @Test
    void busquedaPorNombreCoincidenciaParc() {
        List<Etiqueta> etiquetas = new ArrayList<>();

        List<ProductoDigital> productos = new ArrayList<>();
        productos.add(new Ebook("E1", "Java Ebook", 100, etiquetas, 100));
        productos.add(new App("A1", "Java App", 50, etiquetas, Plataforma.WEB));
        productos.add(new CursoOnline("C1", "Python Curso", 150, etiquetas, 30));
        productos.add(new Ebook("E2", "React Ebook", 80, etiquetas, 150));

        // Buscar "java" (case-insensitive)
        List<ProductoDigital> resultados = Main.buscarPorNombre(productos, "java");

        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(p -> p.getSku().equals("E1")));
        assertTrue(resultados.stream().anyMatch(p -> p.getSku().equals("A1")));
    }

    @Test
    void carritoValidoYCarritoConSkuInexistente() {
        List<Etiqueta> etiquetas = new ArrayList<>();

        List<ProductoDigital> productos = new ArrayList<>();
        productos.add(new Ebook("A001", "E1", 100, etiquetas, 100)); // precioFinal = 105.0
        productos.add(new App("A002", "A1", 100, etiquetas, Plataforma.WEB)); // precioFinal = 115.0
        productos.add(new CursoOnline("A003", "C1", 100, etiquetas, 25)); // precioFinal = 118.0

        // Caso válido: A001 + A002 = 105.0 + 115.0 = 220.0
        List<String> carritoValido = List.of("A001", "A002");
        double total = Main.totalCarrito(productos, carritoValido);
        assertEquals(220.0, total, 0.01);

        // Caso con SKU inexistente
        List<String> carritoInvalido = List.of("A001", "NOEXISTE");
        ItemNotFoundException ex = assertThrows(ItemNotFoundException.class,
                () -> Main.totalCarrito(productos, carritoInvalido));
        assertTrue(ex.getMessage().contains("SKU inexistente"));
    }

    @Test
    void carritoRedondeo() {
        List<Etiqueta> etiquetas = new ArrayList<>();

        List<ProductoDigital> productos = new ArrayList<>();
        productos.add(new Ebook("SKU1", "P1", 33.33, etiquetas, 100)); // precioFinal ≈ 35.00 (33.33 * 1.05)
        productos.add(new Ebook("SKU2", "P2", 33.33, etiquetas, 100)); // precioFinal ≈ 35.00

        List<String> carrito = List.of("SKU1", "SKU2");
        double total = Main.totalCarrito(productos, carrito);

        // Verificar redondeo a 2 decimales
        assertEquals(70.00, total, 0.01);
    }

    @Test
    void etiquetasParseoAgrupamientoYBusqueda() {
        // Parsear etiquetas desde string
        List<Etiqueta> etiquetas = Main.parsearEtiquetas("Java|backend|Avanzado");

        assertEquals(3, etiquetas.size());
        assertEquals("java", etiquetas.get(0).getNombre()); // normalizado a minúsculas
        assertEquals("backend", etiquetas.get(1).getNombre());
        assertEquals("avanzado", etiquetas.get(2).getNombre());

        // Crear productos con etiquetas
        List<ProductoDigital> productos = new ArrayList<>();
        productos.add(new Ebook("E1", "Ebook Java", 100,
            List.of(new Etiqueta("java"), new Etiqueta("backend")), 100));
        productos.add(new App("A1", "App Backend", 50,
            List.of(new Etiqueta("backend")), Plataforma.WEB));
        productos.add(new CursoOnline("C1", "Curso Java", 150,
            List.of(new Etiqueta("java")), 25));

        // Agrupar por etiqueta
        Map<String, List<ProductoDigital>> productosPorEtiqueta = new java.util.HashMap<>();
        for (ProductoDigital p : productos) {
            for (Etiqueta et : p.getEtiquetas()) {
                productosPorEtiqueta
                    .computeIfAbsent(et.getNombre(), k -> new ArrayList<>())
                    .add(p);
            }
        }

        // Buscar por etiqueta "backend"
        List<String> skus = Main.skusPorEtiqueta(productosPorEtiqueta, "backend");

        assertEquals(2, skus.size());
        assertTrue(skus.contains("E1"));
        assertTrue(skus.contains("A1"));
    }

}

