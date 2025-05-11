/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author ubaldo
 */
import hilos.Cronometro;
import modelo.Juego;
import modelo.Pieza;
import persistencia.GestorArchivos;

import java.util.Scanner;

/**
 * Clase Main de prueba funcional (sin interfaz gráfica).
 */
public class MainJuego {
    public static void main(String[] args) {
        Juego juego = new Juego();
        Cronometro cronometro = new Cronometro();
        cronometro.start(); // Iniciar cronómetro
        Scanner scanner = new Scanner(System.in);
        boolean jugando = true;

        System.out.println("=== Bienvenido a Ajedrez: La Rebelión ===");

        while (jugando) {
            mostrarTablero(juego);
            System.out.println("\nTurno de: " + juego.getTurnoActual());

            System.out.print("Ingrese coordenada de origen (fila columna): ");
            int origenX = scanner.nextInt();
            int origenY = scanner.nextInt();

            System.out.print("Ingrese coordenada de destino (fila columna): ");
            int destinoX = scanner.nextInt();
            int destinoY = scanner.nextInt();

            boolean movio = juego.mover(origenX, origenY, destinoX, destinoY);

            if (!movio) {
                System.out.println("Movimiento inválido, intenta de nuevo.");
            }

            System.out.print("\n¿Desea guardar la partida? (s/n): ");
            String opcion = scanner.next();
            if (opcion.equalsIgnoreCase("s")) {
                GestorArchivos.guardarPartida(juego.getTablero(), "partidaGuardada.dat");
            }

            System.out.print("\n¿Desea salir del juego? (s/n): ");
            String salir = scanner.next();
            if (salir.equalsIgnoreCase("s")) {
                jugando = false;
            }
        }

        cronometro.detener(); // Detener cronómetro
        System.out.println("Juego terminado. Tiempo total: " + cronometro.getSegundos() + " segundos.");
    }

    /**
     * Muestra el tablero en consola.
     */
    public static void mostrarTablero(Juego juego) {
        Pieza[][] casillas = juego.getTablero().getCasillas();
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Pieza pieza = casillas[i][j];
                if (pieza == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(obtenerLetraPieza(pieza) + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Asocia una letra a cada pieza para el tablero de consola.
     */
    public static String obtenerLetraPieza(Pieza pieza) {
        String inicial = pieza.getNombre().substring(0, 1).toUpperCase();
        if (pieza.getColor().equals("negro")) {
            return inicial.toLowerCase();
        }
        return inicial;
    }
}

