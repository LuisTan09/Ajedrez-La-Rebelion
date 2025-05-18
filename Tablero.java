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
 * Clase que representa el tablero de ajedrez.
 */
public class Tablero {
    private Pieza[][] casillas;

    public Tablero() {
        casillas = new Pieza[8][8];
        inicializarTablero();
    }

    /**
     * Llena el tablero con las piezas en su posición inicial.
     */
    private void inicializarTablero() {
        // Blancas
        casillas[7][0] = new Torre("blanco");
        casillas[7][1] = new Caballo("blanco");
        casillas[7][2] = new Alfil("blanco");
        casillas[7][3] = new Reina("blanco");
        casillas[7][4] = new Rey("blanco");
        casillas[7][5] = new Alfil("blanco");
        casillas[7][6] = new Caballo("blanco");
        casillas[7][7] = new Torre("blanco");

        for (int i = 0; i < 8; i++) {
            casillas[6][i] = new Peon("blanco");
        }

        // Negras
        casillas[0][0] = new Torre("negro");
        casillas[0][1] = new Caballo("negro");
        casillas[0][2] = new Alfil("negro");
        casillas[0][3] = new Reina("negro");
        casillas[0][4] = new Rey("negro");
        casillas[0][5] = new Alfil("negro");
        casillas[0][6] = new Caballo("negro");
        casillas[0][7] = new Torre("negro");

        for (int i = 0; i < 8; i++) {
            casillas[1][i] = new Peon("negro");
        }
    }

    /**
     * Retorna la pieza en una posición dada.
     */
    public Pieza getPieza(int fila, int columna) {
        return casillas[fila][columna];
    }

    /**
     * Mueve una pieza de una casilla a otra.
     */
    public boolean moverPieza(int origenX, int origenY, int destinoX, int destinoY) {
        Pieza pieza = casillas[origenX][origenY];
        if (pieza != null && pieza.esMovimientoValido(origenX, origenY, destinoX, destinoY, casillas)) {
            if (casillas[destinoX][destinoY] != null) {
                casillas[destinoX][destinoY].capturar();
            }
            casillas[destinoX][destinoY] = pieza;
            casillas[origenX][origenY] = null;
            return true;
        }
        return false;
    }

    /**
     * Retorna toda la matriz del tablero.
     */
    public Pieza[][] getCasillas() {
        return casillas;
    }
}
