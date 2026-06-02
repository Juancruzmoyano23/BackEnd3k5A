package ar.edu.utnfc.backend.menu;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final String title;
    private final List<MenuOption> options;

    public Menu(String title,
                List<MenuOption> options) {

        this.title = title;
        this.options = options;
    }

    public void run(ApplicationContext ctx) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                displayMenu();
                System.out.print("Seleccione una opción: ");
                
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consumir salto de línea
                    
                    var selectedOption = options.stream()
                            .filter(opt -> opt.code() == choice)
                            .findFirst();
                    
                    if (selectedOption.isPresent()) {
                        selectedOption.get().action().run(ctx);
                    } else if (choice == 0) {
                        running = false;
                        System.out.println("¡Hasta luego!");
                    } else {
                        System.out.println("Opción inválida.");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Intente nuevamente.");
                    scanner.nextLine();
                }
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println(title);
        System.out.println("=".repeat(40));
        options.stream()
                .sorted(Comparator.comparingInt(MenuOption::code))
                .forEach(opt -> System.out.println(opt.code() + ". " + opt.label()));
        System.out.println("0. Salir");
    }
}