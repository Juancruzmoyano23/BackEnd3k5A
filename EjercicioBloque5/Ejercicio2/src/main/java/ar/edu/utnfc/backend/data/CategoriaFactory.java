package ar.edu.utnfc.backend.data;

import ar.edu.utnfc.backend.model.Categoria;

import java.util.HashMap;
import java.util.Map;

public class CategoriaFactory {

    public static Map<String, Categoria> crearCategorias() {

        Map<String, Categoria> categorias =
                new HashMap<>();

        categorias.put(
                "A",
                new Categoria(
                        "A",
                        1.2
                )
        );

        categorias.put(
                "B",
                new Categoria(
                        "B",
                        1.0
                )
        );

        categorias.put(
                "C",
                new Categoria(
                        "C",
                        0.9
                )
        );

        return categorias;
    }
}