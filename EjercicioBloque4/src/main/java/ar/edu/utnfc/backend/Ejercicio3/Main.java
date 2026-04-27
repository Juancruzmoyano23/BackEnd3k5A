package ar.edu.utnfc.backend.Ejercicio3;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.ProtectionDomain;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String csv = "Data/productos.csv";
        List<ProductoDigital> productos = new ArrayList<>();
        int ctEbook = 0;
        int ctApp = 0;
        int ctCurso = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(csv))) {

            String line;

            br.readLine(); // Salteo la primera línea (cabecera)

            while ((line = br.readLine()) != null) {
                try {
                    String[] columna = line.split(";", -1);

                    // sku;tipo;nombre;precioBase;extra;Etiquetas
                    if (columna.length < 6) {
                        continue;
                    }

                    String sku = columna[0];
                    String tipo = columna[1].trim().toUpperCase();
                    String nombre = columna[2];
                    double precioBase = Double.parseDouble(columna[3]);
                    String extra = columna[4];
                    String etiqueta = columna[5];

                    List<Etiqueta> etiquetas = new ArrayList<>();
                    for (String e : etiqueta.split("\\|")) {
                        if (!e.isBlank()) {
                            etiquetas.add(new Etiqueta(e));
                        }
                    }

                    ProductoDigital productoDigital;

                    switch (tipo) {
                        case "EBOOK":
                            int paginas = Integer.parseInt(extra);
                            productoDigital = new Ebook(sku, nombre, precioBase, etiquetas, paginas);
                            break;
                        case "CURSO":
                            int horas = Integer.parseInt(extra);
                            productoDigital = new CursoOnline(sku, nombre, precioBase, etiquetas, horas);
                            break;
                        case "APP":
                            Plataforma plataforma = Plataforma.valueOf(extra.trim().toUpperCase());
                            productoDigital = new App(sku, nombre, precioBase, etiquetas, plataforma);
                            break;
                        default:
                            continue; // tipo desconocido
                    }

                    productos.add(productoDigital);
                } catch (Exception ex) {
                    // Linea malformada: se salta y se sigue con la siguiente.
                }
            }

            // === 1 Mostrar cantidad de registros cargados ===
            System.out.println("Productos cargados: " + productos.size());

            // === 2 Mostrar cantidad por tipo (EBOOK, APP, CURSOONLINE). ===
            for (ProductoDigital p : productos) {
                switch (p.getTipo()) {
                    case "EBOOK":
                        ctEbook++;
                        break;
                    case "CURSO":
                        ctCurso++;
                        break;
                    case "APP":
                        ctApp++;
                        break;
                }
            }

            System.out.println("Cantidad de Cursos: " + ctCurso);
            System.out.println("Cantidad de Ebooks: " + ctEbook);
            System.out.println("Cantidad de Apps: " + ctApp);

            //Agrupamientos:
            //por tipo → Map<String, List<ProductoDigital>>

            Map<String, List<ProductoDigital>> productosPorTipo = new HashMap<>();

            for (ProductoDigital p : productos) {
                productosPorTipo
                        .computeIfAbsent(p.getTipo(), k -> new ArrayList<>())
                        .add(p);
            }

            System.out.println("Agrupados por tipo:");
            for (Map.Entry<String, List<ProductoDigital>> entry : productosPorTipo.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().size());
            }

            //por Etiqueta → Map<String, List<ProductoDigital>>
            Map<String, List<ProductoDigital>> productosPorEtiqueta = new HashMap<>();

            for (ProductoDigital p : productos) {
                for (Etiqueta et : p.getEtiquetas()) {
                    String nombreEtiqueta = et.getNombre(); // ya en minuscula si tu clase Etiqueta la normaliza
                    productosPorEtiqueta
                            .computeIfAbsent(nombreEtiqueta, k -> new ArrayList<>())
                            .add(p);
                }
            }

            System.out.println("Agrupados por etiqueta:");
            for (Map.Entry<String, List<ProductoDigital>> entry : productosPorEtiqueta.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().size());
            }

            String etiquetaBuscada = "backend";
            System.out.println("SKUs con etiqueta \"" + etiquetaBuscada + "\": " + skusPorEtiqueta(productosPorEtiqueta, etiquetaBuscada));

            // === 3 Top 3 por precio final (descendente) ===
            productos.stream()
                    .sorted(Comparator.comparingDouble(ProductoDigital::precioFinalExacto).reversed())
                    .limit(3)
                    .forEach(p -> System.out.println(p.getNombre() + " - Precio Final: $" + p.precioFinalExacto()));

            // === 4 Facturación simulada por tipo (suma de precioFinalExacto) ===
            Map<String, Double> facturacionPorTipo = new HashMap<>();

            for (ProductoDigital p : productos) {
                facturacionPorTipo.merge(p.getTipo(), p.precioFinalExacto(), Double::sum);
            }
            facturacionPorTipo.forEach((tipo, total) -> System.out.println(tipo + ": $" + total));

            // === 5 Búsqueda por nombre (substring, case-insensitive) ===
            String busqueda = "java"; // <-- cambiar según lo que se quiera buscar
            List<ProductoDigital> resultados = buscarPorNombre(productos, busqueda);
            System.out.println("Resultados para \"" + busqueda + "\": " + resultados.size());
            resultados.forEach(p -> System.out.println("  " + p.getSku() + " - " + p.getNombre()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ProductoDigital> buscarPorNombre(List<ProductoDigital> productos, String substring) {
        List<ProductoDigital> resultados = new ArrayList<>();
        if (substring == null || substring.isBlank()) {
            return resultados;
        }

        String criterio = substring.toLowerCase(Locale.ROOT);
        for (ProductoDigital p : productos) {
            if (p.getNombre() != null && p.getNombre().toLowerCase(Locale.ROOT).contains(criterio)) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    // === 6 dado un Etiqueta, listar los SKUs asociados. ===

    public static List<String> skusPorEtiqueta(Map<String, List<ProductoDigital>> productosPorEtiqueta, String etiqueta) {
        List<String> skus = new ArrayList<>();
        if (etiqueta == null || etiqueta.isBlank()) {
            return skus;
        }

        List<ProductoDigital> productos = productosPorEtiqueta.get(etiqueta.toLowerCase(Locale.ROOT));
        if (productos == null) {
            return skus;
        }

        for (ProductoDigital p : productos) {
            skus.add(p.getSku());
        }
        return skus;
    }

    // == 7 Calcular el total de un carrito, con redondeo a dos decimales (lista de SKUs). Si falta alguno, lanzar ItemNotFoundException. //
    public static double totalCarrito(List<ProductoDigital> productos, List<String> carritoSkus) {
        double total = 0.0;

        for (String skuBuscado : carritoSkus) {
            ProductoDigital encontrado = null;

            for (ProductoDigital p : productos) {
                if (p.getSku() != null && p.getSku().equalsIgnoreCase(skuBuscado)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado == null) {
                throw new ItemNotFoundException("SKU inexistente en carrito: " + skuBuscado);
            }

            total += encontrado.precioFinalExacto();
        }

        return Math.round(total * 100.0) / 100.0;
    }

    public static List<Etiqueta> parsearEtiquetas(String etiquetasRaw) {
        List<Etiqueta> etiquetas = new ArrayList<>();
        if (etiquetasRaw == null || etiquetasRaw.isBlank()) {
            return etiquetas;
        }

        for (String e : etiquetasRaw.split("\\|")) {
            if (!e.isBlank()) {
                etiquetas.add(new Etiqueta(e));
            }
        }
        return etiquetas;
    }
}
