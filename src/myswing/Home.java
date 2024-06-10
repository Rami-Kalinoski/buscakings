package myswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    // crear la pantalla home
    public static void crearPantallaHome(JFrame frame, CardLayout card, JPanel panelCard) {
        /* crea la interfaz de la pantalla de inicio y la agrega a la carta de la pantalla principal */
        // crear pantalla
        JPanel homeScreen = new JPanel();
        homeScreen.setLayout(new BoxLayout(homeScreen, BoxLayout.Y_AXIS));

        // crear elementos de contenido
        JLabel titleHome = new JLabel("Buscaminas");
        JButton newgameBtn = new JButton("Nueva partida");
        JButton viewstatsBtn = new JButton("Estadísticas");

        titleHome.setAlignmentY(Component.CENTER_ALIGNMENT);
        newgameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        viewstatsBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        titleHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewstatsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        newgameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(panelCard, "New Game Screen");
            }
        });
        viewstatsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stats.crearPantallaStats(frame, card, panelCard);
                card.show(panelCard, "Stats Screen");
            }
        });

        // agregar todos los elementos a la pantalla de home-------------------
        homeScreen.add(Box.createVerticalGlue());
        homeScreen.add(titleHome);
        homeScreen.add(Box.createVerticalStrut(50));
        homeScreen.add(newgameBtn);
        homeScreen.add(Box.createVerticalStrut(10));
        homeScreen.add(viewstatsBtn);
        homeScreen.add(Box.createVerticalGlue());

        // agregar pantalla a la carta-----------------------------------------
        panelCard.add(homeScreen, "Home Screen");
    }
}