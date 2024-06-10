package myswing;

import javax.swing.*;
import java.awt.*;

public class Screen {
    // CREAR PANTALLA DE INICIO (home, estadísticas, configuración de nueva partida) --------------------------------------------------------------------------------------<<<<<<
    public static void crearPantalla() {
        /* crea la interfaz de la pantalla principal utilizando una carta, la cual contiene inicio (Home), estadísticas (Stats), nueva partida (New Game) */
        // crear frame, panelCard y card
        JFrame frame = new JFrame("Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        CardLayout card = new CardLayout();
        JPanel panelCard = new JPanel(card);

        // crear pantallas home, newgame, stats
        Home.crearPantallaHome(frame, card, panelCard);
        Newgame.crearPantallaNewgame(frame, card, panelCard);

        // agregar panelCard al frame
        frame.setLayout(new BorderLayout());
        frame.add(panelCard, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}