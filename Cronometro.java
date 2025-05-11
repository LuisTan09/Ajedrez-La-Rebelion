/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilos;

/**
 *
 * @author ubaldo
 */
/**
 * Clase que implementa un cronómetro en un hilo separado.
 */
public class Cronometro extends Thread {
    private int segundos;
    private boolean enEjecucion;

    public Cronometro() {
        this.segundos = 0;
        this.enEjecucion = true;
    }

    @Override
    public void run() {
        while (enEjecucion) {
            try {
                Thread.sleep(1000); // Espera 1 segundo
                segundos++;
                System.out.println("Tiempo transcurrido: " + segundos + " segundos");
            } catch (InterruptedException e) {
                System.out.println("Cronómetro interrumpido.");
                enEjecucion = false;
            }
        }
    }

    /**
     * Detiene el cronómetro.
     */
    public void detener() {
        enEjecucion = false;
    }

    public int getSegundos() {
        return segundos;
    }
}

