package ar.edu.utnfc.backend.app;

public class Persona {

    private String documento;
    private String nombre;
    private String apellido;
    private int edad;
    private String ciudad;

    public Persona(String documento,
                   String nombre,
                   String apellido,
                   int edad,
                   String ciudad) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return documento +
                " | " +
                nombre +
                " " +
                apellido +
                " | Edad: " +
                edad +
                " | " +
                ciudad;
    }
}
