/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
/**
 *
 * @author ubaldo
/**
 * Clase que representa la Reina.
 */
public class Reina extends Pieza {

    public Reina(String color) {
        super(color);
    }

    @Override
    public boolean esMovimientoValido(int origenX, int origenY, int destinoX, int destinoY, Pieza[][] tablero) {
        int dx = Math.abs(destinoX - origenX);
        int dy = Math.abs(destinoY - origenY);
        return (dx == dy || origenX == destinoX || origenY == destinoY);
    }

    @Override
    public String getNombre() {
        return "Reina";
    }
}

