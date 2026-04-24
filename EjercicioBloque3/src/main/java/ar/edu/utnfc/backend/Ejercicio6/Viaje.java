package ar.edu.utnfc.backend.Ejercicio6;

// id_viaje: identificador único del viaje
//linea: nombre de la línea de tren
//origen: estación de salida
//destino: estación de llegada
//horario_salida: hora de partida (formato HH:mm)
//horario_llegada: hora de llegada (formato HH:mm)
//pasajeros: cantidad de pasajeros transportados en ese viaje

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viaje {

    private int id_viaje;
    private String linea;
    private String origen;
    private String destino;
    private String horario_salida;
    private String horario_llegada;
    private int pasajeros;
    private int duracion;

    public void calcularDuracion(){
        LocalTime salida = LocalTime.parse(horario_salida);
        LocalTime llegada = LocalTime.parse(horario_llegada);

        long minutos = Duration.between(salida, llegada).toMinutes();

        if(minutos < 0){
            minutos += 24 * 60;
        }

        this.duracion = (int) minutos;

    }


}
