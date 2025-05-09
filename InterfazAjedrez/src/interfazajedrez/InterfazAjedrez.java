package interfazajedrez;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InterfazAjedrez extends JFrame {

    private JButton[][] casillas = new JButton[8][8];
    private Color colorClaro = new Color(255, 255, 204); // Blanco hueso (aproximado)
    private Color colorOscuro = new Color(204, 153, 204); // Rosamorado c  n     laro (aproximado)
    private Map<String, ImageIcon> imagenesFichas = new HashMap<>();

    public InterfazAjedrez() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        // Cargar las imágenes de las fichas
        cargarImagenes();

        // Inicializar y agregar las casillas con las imágenes
        inicializarTablero();

        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Asegurar que la ventana se repinte al final
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    private void cargarImagenes() {
        try {
            imagenesFichas.put("rey_verde", new ImageIcon(getClass().getResource("/Imagenes/rey_verde.jpeg")));
            imagenesFichas.put("rey_rosamorada", new ImageIcon(getClass().getResource("/Imagenes/rey_rosamorada.jpeg")));
            imagenesFichas.put("torre_rosamorada", new ImageIcon(getClass().getResource("/Imagenes/torre_rosamorada.jpeg")));
            imagenesFichas.put("torre_verdee", new ImageIcon(getClass().getResource("/Imagenes/torre_verdee.jpeg")));
            imagenesFichas.put("alfil_rosamorado", new ImageIcon(getClass().getResource("/Imagenes/alfil_rosamorado.jpeg")));
            imagenesFichas.put("alfil_verde", new ImageIcon(getClass().getResource("/Imagenes/alfil_verde.jpeg")));
            imagenesFichas.put("peon_rosamorado", new ImageIcon(getClass().getResource("/Imagenes/peon_rosamorado.jpeg")));
            imagenesFichas.put("peon_verde", new ImageIcon(getClass().getResource("/Imagenes/peon_verde.jpeg")));
            imagenesFichas.put("caballo_rosamorado", new ImageIcon(getClass().getResource("/Imagenes/caballo_rosamorado.jpeg")));
            imagenesFichas.put("caballo_verde", new ImageIcon(getClass().getResource("/Imagenes/caballo_verde.jpeg")));
            imagenesFichas.put("reina_rosamorada", new ImageIcon(getClass().getResource("/Imagenes/reina_rosamorada.jpeg")));
            imagenesFichas.put("reina_verde", new ImageIcon(getClass().getResource("/Imagenes/reina_verde.jpeg")));

            // Imprimir las URLs después de intentar cargarlas
            for (Map.Entry<String, ImageIcon> entry : imagenesFichas.entrySet()) {
                String nombreFicha = entry.getKey();
                ImageIcon icono = entry.getValue();
                if (icono != null) {
                   
                } else {
                  
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las imágenes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void inicializarTablero() {
        // Lógica para la disposición inicial de las piezas
        String[][] posicionesIniciales = {
                {"torre_rosamorada", "caballo_rosamorado", "alfil_rosamorado", "rey_rosamorada", "reina_rosamorada", "alfil_rosamorado", "caballo_rosamorado", "torre_rosamorada"}, // Fila 0 (negras)
                {"peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado"},       // Fila 1 (negras)
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde"},           // Fila 6 (blancas)
                {"torre_verdee", "caballo_verde", "alfil_verde", "rey_verde", "reina_verde", "alfil_verde", "caballo_verde", "torre_verdee"}    // Fila 7 (blancas)
        };

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                casillas[fila][columna] = new JButton();
                if ((fila + columna) % 2 == 0) {
                    casillas[fila][columna].setBackground(colorClaro);
                } else {
                    casillas[fila][columna].setBackground(colorOscuro);
                }
                casillas[fila][columna].setLayout(new BorderLayout()); // Para que la imagen se centre

                String nombreFicha = posicionesIniciales[fila][columna];
                if (nombreFicha != null) {
                    ImageIcon icono = imagenesFichas.get(nombreFicha);
                    if (icono != null) {
                        Image imagenRedimensionada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
                        JLabel etiquetaImagen = new JLabel(iconoRedimensionado);

                        // *** Establecer el fondo de la etiqueta al color de la casilla ***
                        if ((fila + columna) % 2 == 0) {
                            etiquetaImagen.setBackground(colorClaro);
                        } else {
                            etiquetaImagen.setBackground(colorOscuro);
                        }
                        etiquetaImagen.setOpaque(true); // Hacer la etiqueta opaca para que se pinte el fondo

                        casillas[fila][columna].add(etiquetaImagen, BorderLayout.CENTER);
                        casillas[fila][columna].setEnabled(false);
                        casillas[fila][columna].setFocusPainted(false);
                        casillas[fila][columna].setBorderPainted(false);
                        casillas[fila][columna].setContentAreaFilled(false);
                        casillas[fila][columna].revalidate();
                        casillas[fila][columna].repaint();
                    } else {
                        System.err.println("No se encontró la imagen para: " + nombreFicha);
                    }
                }
                add(casillas[fila][columna]);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazAjedrez::new);
    }
}