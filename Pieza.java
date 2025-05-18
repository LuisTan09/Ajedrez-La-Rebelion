/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;
import java.io.Serializable;
/**
 *
 * @author ubaldo
 */
/**
 * Clase abstracta que representa una pieza del ajedrez.
 */
public abstract class Pieza {
    protected String color; // "blanco" o "negro"
    protected boolean capturada;
    protected boolean habilidadEspecial; // Para saber si heredó un movimiento

    public Pieza(String color) {
        this.color = color;
        this.capturada = false;
        this.habilidadEspecial = false;
    }

    public String getColor() {
        return color;
    }

    public boolean estaCapturada() {
        return capturada;
    }

    public void capturar() {
        this.capturada = true;
    }

    public boolean tieneHabilidadEspecial() {
        return habilidadEspecial;
    }

    public void activarHabilidadEspecial() {
        this.habilidadEspecial = true;
    }

    /**
     * Método abstracto para validar si un movimiento es válido.
     */
    public abstract boolean esMovimientoValido(int origenX, int origenY, int destinoX, int destinoY, Pieza[][] tablero);
    
    /**
     * Retorna el nombre de la pieza.
     */
    public abstract String getNombre();
}
