/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author ubaldo
 */
import modelo.Pieza;
import modelo.Tablero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la persistencia de partidas y puntajes.
 */
public class GestorArchivos {

    /**
     * Guarda el estado del tablero en un archivo.
     */
    public static void guardarPartida(Tablero tablero, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(tablero.getCasillas());
            System.out.println("Partida guardada exitosamente en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    /**
     * Carga un estado del tablero desde un archivo.
     */
    public static Pieza[][] cargarPartida(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Pieza[][] casillas = (Pieza[][]) ois.readObject();
            System.out.println("Partida cargada exitosamente desde " + rutaArchivo);
            return casillas;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }

    /**
     * Guarda los puntajes en un archivo de texto simple.
     */
    public static void guardarPuntaje(String jugador, int puntaje, String rutaArchivo) {
        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(jugador + ":" + puntaje);
            bw.newLine();
            System.out.println("Puntaje guardado para " + jugador);
        } catch (IOException e) {
            System.out.println("Error al guardar el puntaje: " + e.getMessage());
        }
    }

    /**
     * Lee los puntajes almacenados.
     */
    public static List<String> cargarPuntajes(String rutaArchivo) {
        List<String> puntajes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                puntajes.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer los puntajes: " + e.getMessage());
        }
        return puntajes;
    }
}

