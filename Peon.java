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
 * Clase que representa el Peón.
 */
public class Peon extends Pieza {

    public Peon(String color) {
        super(color);
    }

    @Override
    public boolean esMovimientoValido(int origenX, int origenY, int destinoX, int destinoY, Pieza[][] tablero) {
        int direccion = color.equals("blanco") ? -1 : 1;
        if (origenX + direccion == destinoX && origenY == destinoY && tablero[destinoX][destinoY] == null) {
            return true; // avanzar 1 casilla
        }
        if (origenX + direccion == destinoX && Math.abs(destinoY - origenY) == 1 && tablero[destinoX][destinoY] != null) {
            return true; // captura diagonal
        }
        return false;
    }

    @Override
    public String getNombre() {
        return "Peon";
    }
}
