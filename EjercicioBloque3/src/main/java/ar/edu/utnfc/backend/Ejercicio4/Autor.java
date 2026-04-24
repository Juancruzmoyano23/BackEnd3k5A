package ar.edu.utnfc.backend.Ejercicio4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {
    private String id;
    private String name;
    private String lastname;
    private int aniosCarrera;
}
