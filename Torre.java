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
 * Clase que representa la Torre.
 */
public class Torre extends Pieza {

    public Torre(String color) {
        super(color);
    }

    @Override
    public boolean esMovimientoValido(int origenX, int origenY, int destinoX, int destinoY, Pieza[][] tablero) {
        return (origenX == destinoX || origenY == destinoY);
    }

    @Override
    public String getNombre() {
        return "Torre";
    }
}
