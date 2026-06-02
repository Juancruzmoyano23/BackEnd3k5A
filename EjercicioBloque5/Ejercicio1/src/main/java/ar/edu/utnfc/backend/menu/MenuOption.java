package ar.edu.utnfc.backend.menu;

public record MenuOption(
        int code,
        String label,
        FuncAction action
) {
}
