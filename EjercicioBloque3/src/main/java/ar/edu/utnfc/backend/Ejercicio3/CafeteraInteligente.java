package ar.edu.utnfc.backend.Ejercicio3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeteraInteligente {

    private String marca;
    private String modelo;
    private int capacidadMaxima;
    private int contenidoActual;
    private boolean encendida;
    private int cafesServidos;
    private int temperatura;

    public void apagar(){
        this.encendida = false;
        this.cafesServidos = 0;
    }

    public void encendido(){
        this.encendida = true;
        this.temperatura = 20;
        this.contenidoActual = 0;
    }

    public boolean cargarAgua(int ml){
        if (this.encendida){

            if(ml >= this.capacidadMaxima){
                contenidoActual = capacidadMaxima;
                return true;
            } else {
                contenidoActual += ml;
                return true;
            }

        }
        return false;
    }

    public void calentar(){
        if(this.encendida){
            if(this.temperatura >= 100){
                this.temperatura = 100;
            }else {
                this.temperatura += 40;
            }
        }
    }

    @Override
    public String toString(){
        return "Cafetera: " + marca + "/" +modelo + " - Agua:" + contenidoActual + "ml - Temperatura: " + temperatura + "°C - Servidos:" + cafesServidos + " - Estado:" + encendida;
    }


}
