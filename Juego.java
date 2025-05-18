/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ubaldo
 */
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * Clase que maneja la lógica general del juego de ajedrez.
 */
public class Juego {
    private Tablero tablero;
    private String turnoActual; // "blanco" o "negro"
    private List<Pieza> piezasCapturadasBlanco;
    private List<Pieza> piezasCapturadasNegro;
    private boolean habilidadUsadaBlanco;
    private boolean habilidadUsadaNegro;

    public Juego() {
        tablero = new Tablero();
        turnoActual = "blanco";
        piezasCapturadasBlanco = new ArrayList<>();
        piezasCapturadasNegro = new ArrayList<>();
        habilidadUsadaBlanco = false;
        habilidadUsadaNegro = false;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public String getTurnoActual() {
        return turnoActual;
    }

    public void cambiarTurno() {
        turnoActual = turnoActual.equals("blanco") ? "negro" : "blanco";
    }

    /**
     * Realiza un movimiento en el tablero.
     */
    public boolean mover(int origenX, int origenY, int destinoX, int destinoY) {
        Pieza piezaOrigen = tablero.getPieza(origenX, origenY);

        if (piezaOrigen == null) {
            System.out.println("No hay pieza en la casilla de origen.");
            return false;
        }

        if (!piezaOrigen.getColor().equals(turnoActual)) {
            System.out.println("No es el turno de " + piezaOrigen.getColor() + ".");
            return false;
        }

        Pieza piezaDestino = tablero.getPieza(destinoX, destinoY);

        if (piezaDestino != null && piezaDestino.getColor().equals(turnoActual)) {
            System.out.println("No puedes capturar tus propias piezas.");
            return false;
        }

        if (tablero.moverPieza(origenX, origenY, destinoX, destinoY)) {
            if (piezaDestino != null) {
                registrarCaptura(piezaDestino);
                verificarHabilidadEspecial(piezaOrigen, piezaDestino);
            }
            cambiarTurno();
            return true;
        }

        System.out.println("Movimiento inválido.");
        return false;
    }

    /**
     * Registra una captura.
     */
    private void registrarCaptura(Pieza piezaCapturada) {
        if (turnoActual.equals("blanco")) {
            piezasCapturadasBlanco.add(piezaCapturada);
        } else {
            piezasCapturadasNegro.add(piezaCapturada);
        }
    }

    /**
     * Permite heredar habilidad especial al capturar piezas importantes.
     */
    private void verificarHabilidadEspecial(Pieza piezaCaptora, Pieza piezaCapturada) {
        if (piezaCapturada instanceof Reina || piezaCapturada instanceof Torre || piezaCapturada instanceof Caballo) {
            piezaCaptora.activarHabilidadEspecial();
            System.out.println("¡" + piezaCaptora.getNombre() + " ha heredado un movimiento especial!");
        }
    }

    /**
     * Movimiento especial de Rey si está en jaque y domina más piezas.
     */
    public boolean movimientoEspecialRey(int origenX, int origenY, int destinoX, int destinoY) {
        Pieza pieza = tablero.getPieza(origenX, origenY);
        if (pieza instanceof Rey && pieza.getColor().equals(turnoActual)) {
            if (puedeUsarMovimientoEspecial(turnoActual)) {
                return tablero.moverPieza(origenX, origenY, destinoX, destinoY);
            }
        }
        return false;
    }

    /**
     * Verifica si el jugador puede usar la habilidad especial de jaque.
     */
    private boolean puedeUsarMovimientoEspecial(String color) {
        if (color.equals("blanco")) {
            return piezasCapturadasBlanco.size() > piezasCapturadasNegro.size() && !habilidadUsadaBlanco;
        } else {
            return piezasCapturadasNegro.size() > piezasCapturadasBlanco.size() && !habilidadUsadaNegro;
        }
    }

    /**
     * Marca que el jugador ya usó su movimiento especial.
     */
    public void marcarHabilidadUsada() {
        if (turnoActual.equals("blanco")) {
            habilidadUsadaBlanco = true;
        } else {
            habilidadUsadaNegro = true;
        }
    }

    /**
     * Permite invocar una pieza rival capturada.
     */
    public boolean invocarPiezaRival(int fila, int columna, String tipoPieza) {
        List<Pieza> piezasRivales = turnoActual.equals("blanco") ? piezasCapturadasNegro : piezasCapturadasBlanco;

        for (Pieza pieza : piezasRivales) {
            if (pieza.getNombre().equalsIgnoreCase(tipoPieza)) {
                tablero.getCasillas()[fila][columna] = clonarPieza(pieza, turnoActual);
                piezasRivales.remove(pieza);
                System.out.println("¡Pieza rival " + tipoPieza + " invocada!");
                return true;
            }
        }
        return false;
    }

    /**
     * Clona una pieza cambiando el color.
     */
    private Pieza clonarPieza(Pieza original, String nuevoColor) {
        if (original instanceof Torre) return new Torre(nuevoColor);
        if (original instanceof Caballo) return new Caballo(nuevoColor);
        if (original instanceof Alfil) return new Alfil(nuevoColor);
        if (original instanceof Reina) return new Reina(nuevoColor);
        if (original instanceof Rey) return new Rey(nuevoColor);
        if (original instanceof Peon) return new Peon(nuevoColor);
        return null;
    }
}
