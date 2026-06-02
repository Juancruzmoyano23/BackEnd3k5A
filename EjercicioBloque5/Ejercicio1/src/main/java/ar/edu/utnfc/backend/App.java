package ar.edu.utnfc.backend;

import ar.edu.utnfc.backend.menu.*;
import ar.edu.utnfc.backend.app.*;

import java.util.List;

public class App {

    public static void main(String[] args) {

        var ctx = ApplicationContext.getInstance();

        ctx.put(
                "personas",
                DataSeeder.generar(200)
        );

        var actions = new Actions();

        var opciones = List.of(

                new MenuOption(
                        1,
                        "Listar (20 primeras)",
                        actions::listar
                ),

                new MenuOption(
                        2,
                        "Buscar por texto",
                        actions::buscar
                ),

                new MenuOption(
                        3,
                        "Top N por edad",
                        actions::topEdades
                ),

                new MenuOption(
                        4,
                        "Conteo por ciudad",
                        actions::conteoPorCiudad
                ),

                new MenuOption(
                        5,
                        "Estadísticas de edad",
                        actions::estadisticasEdad
                )
        );

        new Menu(
                "Menú Funcional - Etapa 3",
                opciones
        ).run(ctx);
    }
}