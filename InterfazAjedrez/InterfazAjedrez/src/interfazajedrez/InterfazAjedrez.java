package interfazajedrez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class InterfazAjedrez extends JFrame {

    private JButton[][] casillas = new JButton[8][8];
    private Color colorClaro = Color.WHITE; // Blanco puro
    private Color colorOscuro = new Color(204, 153, 204); // Rosamorado claro
    private Map<String, ImageIcon> imagenesFichas = new HashMap<>();

    // Variables para selección y movimiento
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;
    private ImageIcon iconoSeleccionado = null;

    public InterfazAjedrez() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        cargarImagenes();
        inicializarTablero();

        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cargarImagenes() {
        try {
            imagenesFichas.put("rey_verde", new ImageIcon(getClass().getResource("/Imagenes/rey_verde.png")));
            imagenesFichas.put("rey_rosamorada", new ImageIcon(getClass().getResource("/Imagenes/rey_rosamorada.png")));
            imagenesFichas.put("torre_rosamorada", new ImageIcon(getClass().getResource("/Imagenes/torre_rosamorada.png")));
            imagenesFichas.put("torre_verdee", new ImageIcon(getClass().getResource("/Imagenes/torre_verdee.png")));
            imagenesFichas.put("alfil_rosamorado", new ImageIcon(getClass().getResource("/Imagenes/alfil_rosamorado.png")));
            imagenesFichas.put("alfil_verde", new ImageIcon(getClass().getResource("/Imagenes/alfil_verde.png")));
            imagenesFichas.put("peon_rosamorado", new ImageIcon(getClass().getResource("/Imagenes/peon_rosamorado.png")));
            imagenesFichas.put("peon_verde", new ImageIcon(getClass().getResource("/Imagenes/peon_verde.png")));
            imagenesFichas.put("caballo_rosamorado", new ImageIcon(getClass().getResource("/Imagenes/caballo_rosamorado.png")));
            imagenesFichas.put("caballo_verde", new ImageIcon(getClass().getResource("/Imagenes/caballo_verde.png")));
            imagenesFichas.put("reina_rosamorada", new ImageIcon(getClass().getResource("/Imagenes/reina_rosamorada.png")));
            imagenesFichas.put("reina_verde", new ImageIcon(getClass().getResource("/Imagenes/reina_verde.png")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las imágenes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void inicializarTablero() {
        String[][] posicionesIniciales = {
                {"torre_rosamorada", "caballo_rosamorado", "alfil_rosamorado", "rey_rosamorada", "reina_rosamorada", "alfil_rosamorado", "caballo_rosamorado", "torre_rosamorada"},
                {"peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado", "peon_rosamorado"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde", "peon_verde"},
                {"torre_verdee", "caballo_verde", "alfil_verde", "rey_verde", "reina_verde", "alfil_verde", "caballo_verde", "torre_verdee"}
        };

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                JButton casilla = new JButton();
                casilla.setMargin(new Insets(0, 0, 0, 0));
                casilla.setFocusPainted(false);
                casilla.setContentAreaFilled(true);
                casilla.setOpaque(true);

                // Fondo alternante
                Color colorFondo = (fila + columna) % 2 == 0 ? colorClaro : colorOscuro;
                casilla.setBackground(colorFondo);

                // Colocar imagen si corresponde
                String nombreFicha = posicionesIniciales[fila][columna];
                if (nombreFicha != null) {
                    ImageIcon icono = imagenesFichas.get(nombreFicha);
                    if (icono != null) {
                        Image imagenRedimensionada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        casilla.setIcon(new ImageIcon(imagenRedimensionada));
                    }
                }

                // Listener para seleccionar y mover piezas
                final int f = fila, c = columna;
                casilla.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (filaSeleccionada == -1 && casillas[f][c].getIcon() != null) {
                            // Primera selección
                            filaSeleccionada = f;
                            colSeleccionada = c;
                            iconoSeleccionado = (ImageIcon) casillas[f][c].getIcon();
                            deseleccionarTodas();
                            casillas[f][c].setBorder(BorderFactory.createLineBorder(new Color(153, 102, 204), 3));
                        } else if (filaSeleccionada != -1) {
                            // Segunda selección -> mover
                            if (f != filaSeleccionada || c != colSeleccionada) {
                                casillas[f][c].setIcon(iconoSeleccionado);
                                casillas[filaSeleccionada][colSeleccionada].setIcon(null);
                            }
                            deseleccionarTodas();
                            filaSeleccionada = -1;
                            colSeleccionada = -1;
                            iconoSeleccionado = null;
                        }
                    }
                });

                casillas[fila][columna] = casilla;
                add(casilla);
            }
        }
    }

    // Quitar selección (borde) de todas las casillas
    private void deseleccionarTodas() {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                casillas[fila][col].setBorder(null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazAjedrez::new);
    }
}
