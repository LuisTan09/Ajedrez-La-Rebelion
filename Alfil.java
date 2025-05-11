/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
/**
 *
 * @author ubaldo
 */
/**
 * Clase que representa el Alfil.
 */
public class Alfil extends Pieza {

    public Alfil(String color) {
        super(color);
    }

    @Override
    public boolean esMovimientoValido(int origenX, int origenY, int destinoX, int destinoY, Pieza[][] tablero) {
        return Math.abs(destinoX - origenX) == Math.abs(destinoY - origenY);
    }

    @Override
    public String getNombre() {
        return "Alfil";
    }
}
