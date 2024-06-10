package myswing;

import classes.Casillero;
import classes.Partida;
import classes.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
    // crear pantalla de la partida
    public static void crearPantallaGame(JFrame gameframe, JFrame frame, CardLayout card, JPanel panelCard, int filas, int columnas, String username, boolean powers, String difficulty) {
        /* crea y muestra la interfaz referente a la partida en sí */
        // crear pantalla
        JPanel gameScreen = new JPanel();
        gameScreen.setLayout(new BoxLayout(gameScreen, BoxLayout.Y_AXIS));

        // crear secciones
        JPanel gameScreenTopPanel = new JPanel();
        JPanel gameScreenMiddlePanel = new JPanel();
        JPanel gameScreenBottomPanel = new JPanel();
        gameScreenTopPanel.setLayout(new BoxLayout(gameScreenTopPanel, BoxLayout.X_AXIS));
        gameScreenMiddlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gameScreenBottomPanel.setLayout(new BoxLayout(gameScreenBottomPanel, BoxLayout.X_AXIS));

        // top section--------------------------------------------------------------------------------------------------
        // timer section-----------------------------------------------------------------
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel timerImagePanel = new JPanel();
        timerImagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ImageIcon timerImage = new ImageIcon("src\\assets\\timer.png");
        Image timerSizedImage = timerImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon timerSizedImageIcon = new ImageIcon(timerSizedImage);
        timerImagePanel.add(new JLabel(timerSizedImageIcon));
        timerImagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel timerLabel = new JLabel("00:00");

        timerPanel.add(timerLabel);
        timerPanel.add(timerImagePanel);
        Cronometro.stopTimer();
        Cronometro.resetTimer(timerLabel);

        // buttons section---------------------------------------------------------------
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton goBackGameScreenBtn = new JButton();
        JButton resetGameBtn = new JButton();
        JButton useBombBtn = new JButton();
        JButton useShieldBtn = new JButton();
        JButton useLensBtn = new JButton();
        JButton useTurtleBtn = new JButton();

        JLabel flagsLeftLabel = new JLabel("Banderas restantes: ");
        JLabel flagsLeftLabelNumber = new JLabel();
        JPanel flagsLeftPanel = new JPanel();
        flagsLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        flagsLeftPanel.add(flagsLeftLabel);
        flagsLeftPanel.add(flagsLeftLabelNumber);

        // powers buttons
        // dinamita
        ImageIcon bombImage = new ImageIcon("src\\assets\\bomb.png");
        Image bombSizedImage = bombImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon bombSizedImageIcon = new ImageIcon(bombSizedImage);
        useBombBtn.setIcon(bombSizedImageIcon);
        useBombBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // escudo
        ImageIcon shieldImage = new ImageIcon("src\\assets\\shield.png");
        Image shieldSizedImage = shieldImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon shieldSizedImageIcon = new ImageIcon(shieldSizedImage);
        useShieldBtn.setIcon(shieldSizedImageIcon);
        useShieldBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // lupa
        ImageIcon lensImage = new ImageIcon("src\\assets\\lens.png");
        Image lensSizedImage = lensImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon lensSizedImageIcon = new ImageIcon(lensSizedImage);
        useLensBtn.setIcon(lensSizedImageIcon);
        useLensBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // tortuga
        ImageIcon turtleImage = new ImageIcon("src\\assets\\turtle.png");
        Image turtleSizedImage = turtleImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon turtleSizedImageIcon = new ImageIcon(turtleSizedImage);
        useTurtleBtn.setIcon(turtleSizedImageIcon);
        useTurtleBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // go back button
        ImageIcon arrowImage = new ImageIcon("src\\assets\\arrow.png");
        Image arrowSizedImage = arrowImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon arrowSizedImageIcon = new ImageIcon(arrowSizedImage);
        goBackGameScreenBtn.setIcon(arrowSizedImageIcon);
        goBackGameScreenBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // reset game button
        ImageIcon resetImage = new ImageIcon("src\\assets\\reset.png");
        Image resetSizedImage = resetImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon resetSizedImageIcon = new ImageIcon(resetSizedImage);
        resetGameBtn.setIcon(resetSizedImageIcon);
        resetGameBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // agregar al top section
        buttonsPanel.add(goBackGameScreenBtn);
        buttonsPanel.add(resetGameBtn);
        buttonsPanel.add(useBombBtn);
        buttonsPanel.add(useShieldBtn);
        buttonsPanel.add(useLensBtn);
        buttonsPanel.add(useTurtleBtn);
        buttonsPanel.add(flagsLeftPanel);
        gameScreenTopPanel.add(buttonsPanel);
        gameScreenTopPanel.add(Box.createHorizontalGlue());
        gameScreenTopPanel.add(timerPanel);

        // bottom section-----------------------------------------------------------------------------------------------
        // izquierda --------------------------------------------------------------------
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        ImageIcon userImage = new ImageIcon("src\\assets\\user.png");
        Image userSizedImage = userImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon userSizedImageIcon = new ImageIcon(userSizedImage);

        String usernameInfo = "usuario: " + username;
        JLabel userLabelInfo = new JLabel();
        userLabelInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
        userLabelInfo.setText(usernameInfo);

        userPanel.add(new JLabel(userSizedImageIcon));
        userPanel.add(userLabelInfo);
        userPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        // derecha ----------------------------------------------------------------------
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        String powersInfo = "poderes: " + (powers ? "si" : "no");
        String difficultyInfo = "dificultad: " + difficulty;
        String gridInfo = "cuadrilla: " + filas + "x" + columnas;
        JLabel powersInfoPanel = new JLabel(powersInfo);
        JLabel difficultyInfoPanel = new JLabel(difficultyInfo);
        JLabel gridInfoPanel = new JLabel(gridInfo);

        gameInfoPanel.add(powersInfoPanel);
        gameInfoPanel.add(Box.createHorizontalStrut(10));
        gameInfoPanel.add(difficultyInfoPanel);
        gameInfoPanel.add(Box.createHorizontalStrut(10));
        gameInfoPanel.add(gridInfoPanel);
        gameInfoPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gameInfoPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        // agregar al bottom section
        gameScreenBottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        gameScreenBottomPanel.add(userPanel);
        gameScreenBottomPanel.add(Box.createHorizontalGlue());
        gameScreenBottomPanel.add(gameInfoPanel);

        // middle section (grid)----------------------------------------------------------------------------------------
        // middle section (grid)----------------------------------------------------------------------------------------
        // middle section (grid)----------------------------------------------------------------------------------------
        Partida partida = crearPartida(filas, columnas, powers, useBombBtn, useShieldBtn, useLensBtn, useTurtleBtn, flagsLeftLabelNumber);
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(filas, columnas));

        // acciones botones
        goBackGameScreenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cronometro.stopTimer();
                if (partida.getTablero().todosCasillerosLibresDescubiertos()) {
                    int resultado = JOptionPane.showConfirmDialog(frame, "El progreso de esta partida ya ha sido registrado.\n¿Está seguro/a de que desea volver al menú?", "Retroceder", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado==JOptionPane.OK_OPTION) {
                        gameframe.setVisible(false);
                        frame.setVisible(true);
                        card.show(panelCard, "New Game Screen");
                    } else {
                        Cronometro.resetTimer(timerLabel);
                    }
                } else {
                    int resultado = JOptionPane.showConfirmDialog(frame, "Si retrocedes, perderás el progreso de esta partida.\n¿Está seguro/a de que desea volver al menú?", "Retroceder", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado==JOptionPane.OK_OPTION) {
                        gameframe.setVisible(false);
                        frame.setVisible(true);
                        card.show(panelCard, "New Game Screen");
                    } else {
                        Cronometro.resetTimer(timerLabel);
                    }
                }
            }
        });
        resetGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cronometro.stopTimer();
                if (partida.getTablero().todosCasillerosLibresDescubiertos()) {
                    int resultado = JOptionPane.showConfirmDialog(frame, "El progreso de esta partida ya ha sido registrado.\n¿Está seguro/a de que desea reiniciar la partida?", "Reiniciar partida", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado==JOptionPane.OK_OPTION) {
                        Cronometro.resetTimer(timerLabel);
                        gameframe.setVisible(false);
                        gameframe.remove(gameScreen);
                        gameframe.revalidate();
                        gameframe.repaint();
                        crearPantallaGame(gameframe, frame, card, panelCard, filas, columnas, username, powers, difficulty);
                    } else {
                        Cronometro.resumeTimer(timerLabel);
                    }
                } else {
                    int resultado = JOptionPane.showConfirmDialog(frame, "Si reinicias la partida, perderás el progreso de la misma.\n¿Está seguro/a de que desea reiniciar la partida?", "Reiniciar partida", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado==JOptionPane.OK_OPTION) {
                        Cronometro.resetTimer(timerLabel);
                        gameframe.setVisible(false);
                        gameframe.remove(gameScreen);
                        gameframe.revalidate();
                        gameframe.repaint();
                        crearPantallaGame(gameframe, frame, card, panelCard, filas, columnas, username, powers, difficulty);
                    } else {
                        Cronometro.resumeTimer(timerLabel);
                    }
                }
            }
        });
        useBombBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partida.getTablero().todosCasillerosNoDescubiertos() && partida.getDinamitasRestantes()>0) {
                    partida.setDinamitaActivada(true);
                }
            }
        });
        useShieldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partida.getTablero().todosCasillerosNoDescubiertos() && partida.getEscudosRestantes()>0) {
                    partida.setEscudoActivado(true);
                }
            }
        });
        useLensBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partida.getTablero().todosCasillerosNoDescubiertos() && partida.getLupasRestantes()>0) {
                    partida.setLupaActivada(true);
                }
            }
        });
        useTurtleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partida.getTablero().todosCasillerosNoDescubiertos() && partida.getTortugasRestantes()>0) {
                    int secondsPause = 0;
                    if (difficulty.equals("fácil")) {
                        secondsPause = 5;
                    } else if (difficulty.equals("medio")) {
                        secondsPause = 10;
                    } else if (difficulty.equals("difícil")) {
                        secondsPause = 15;
                    } else if (difficulty.equals("personalizada")) {
                        secondsPause = columnas*2-((columnas*2)/2);
                    }
                    Cronometro.pauseForSeconds(timerLabel, secondsPause);
                    partida.setTortugasRestantes(partida.getTortugasRestantes()-1);
                    useTurtleBtn.setText(String.valueOf(partida.getTortugasRestantes()));
                }
            }
        });

        // generar grilla
        for (int f=0;f<filas;f++) {
            for (int c=0;c<columnas;c++) {
                JButton celda = new JButton();
                celda.setPreferredSize(new Dimension(30, 30));
                celda.setMaximumSize(new Dimension(30, 30));
                celda.setBackground(Color.LIGHT_GRAY);
                //celda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                celda.putClientProperty("fila", f);
                celda.putClientProperty("columna", c);
                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JButton celdaPresionada = (JButton) e.getSource();
                        int fila = (int) celdaPresionada.getClientProperty("fila");
                        int columna = (int) celdaPresionada.getClientProperty("columna");
                        Casillero casilleroPresionado = partida.getTablero().encontrarCasillero(fila, columna);

                        if (SwingUtilities.isLeftMouseButton(e) && !casilleroPresionado.isBandereado() && !partida.getTablero().todosCasillerosLibresDescubiertos()) { // click izquierdo, no tiene bandera y la partida aún no terminó
                            if (!partida.isEscudoActivado() && !partida.isLupaActivada() && !partida.isDinamitaActivada()) { // click sin poderes ------------------------------------------------
                                if (partida.getTablero().todosCasillerosNoDescubiertos()) { // primer click
                                    partida.primerClick(grid, celdaPresionada, fila, columna);
                                    casilleroPresionado.setDescubierto(true);
                                    partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);
                                    Cronometro.startTimer(timerLabel);
                                } else { // no es el primer click
                                    casilleroPresionado.setDescubierto(true);

                                    if (!partida.getTablero().todosCasillerosLibresDescubiertos() && !casilleroPresionado.isMinado()) { // cliqueó y no había mina pero todavía no ganó
                                        partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);
                                    } else if (partida.getTablero().todosCasillerosLibresDescubiertos() && !casilleroPresionado.isMinado()) { // cliqueó y no había mina, ya GANÓ
                                        Cronometro.stopTimer();
                                        JOptionPane.showMessageDialog(gameframe, "¡Ganaste! Sos un/a crack.", "¡Ganaste!", JOptionPane.INFORMATION_MESSAGE);
                                        int segundos = Cronometro.getSeconds() + (Cronometro.getMinutes()*60);
                                        partida.terminarPartida(gameframe, grid, celdaPresionada, filas, columnas, fila, columna, username, segundos, true);
                                    } else if (casilleroPresionado.isMinado()) { // cliqueó y había mina, ya PERDIÓ
                                        partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);
                                        Cronometro.stopTimer();
                                        JOptionPane.showMessageDialog(gameframe, "¡Perdiste! No te preocupes, podés intentarlo de nuevo.", "Perdiste", JOptionPane.INFORMATION_MESSAGE);
                                        int segundos = Cronometro.getSeconds() + (Cronometro.getMinutes()*60);
                                        partida.terminarPartida(gameframe, grid, celdaPresionada, filas, columnas, fila, columna, username, segundos, false);
                                    }
                                }
                            } else if (partida.isEscudoActivado()) {
                                casilleroPresionado.setDescubierto(true);

                                if (!partida.getTablero().todosCasillerosLibresDescubiertos() && !casilleroPresionado.isMinado()) { // cliqueó y no había mina pero todavía no ganó
                                    partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);
                                } else if (partida.getTablero().todosCasillerosLibresDescubiertos() && !casilleroPresionado.isMinado()) { // cliqueó y no había mina, ya GANÓ
                                    Cronometro.stopTimer();
                                    JOptionPane.showMessageDialog(gameframe, "¡Ganaste! Sos un/a crack.", "¡Ganaste!", JOptionPane.INFORMATION_MESSAGE);
                                    int segundos = Cronometro.getSeconds() + (Cronometro.getMinutes()*60);
                                    partida.terminarPartida(gameframe, grid, celdaPresionada, filas, columnas, fila, columna, username, segundos, true);
                                } else if (casilleroPresionado.isMinado()) { // cliqueó y había mina, pero el ESCUDO ESTÁ ACTIVADO, no PIERDE
                                    partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);
                                    partida.setEscudoActivado(false);
                                    partida.setEscudosRestantes(partida.getEscudosRestantes()-1);
                                    useShieldBtn.setText(String.valueOf(partida.getEscudosRestantes()));
                                }

                            } else if (partida.isLupaActivada()) {
                                casilleroPresionado.setDescubierto(true);
                                partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);

                                partida.setLupaActivada(false);
                                partida.setLupasRestantes(partida.getLupasRestantes()-1);
                                useLensBtn.setText(String.valueOf(partida.getLupasRestantes()));

                                if (partida.getTablero().todosCasillerosLibresDescubiertos()) { // con esa lupa ganó
                                    Cronometro.stopTimer();
                                    JOptionPane.showMessageDialog(gameframe, "¡Ganaste! Sos un/a crack.", "¡Ganaste!", JOptionPane.INFORMATION_MESSAGE);
                                    int segundos = Cronometro.getSeconds() + (Cronometro.getMinutes()*60);
                                    partida.terminarPartida(gameframe, grid, celdaPresionada, filas, columnas, fila, columna, username, segundos, true);
                                }

                            } else if (partida.isDinamitaActivada()) {
                                casilleroPresionado.setDescubierto(true);
                                partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, true);
                                int radioDelCasillero = casilleroPresionado.getCasillerosRadio().length;
                                Casillero casilleroRadio = null;
                                JButton celdaRadio = null;
                                int filaRadio = 0;
                                int columnaRadio = 0;
                                for (int i=0;i<radioDelCasillero;i++) {
                                     casilleroRadio = casilleroPresionado.getCasillerosRadio()[i];
                                     casilleroRadio.setDescubierto(true);
                                     filaRadio = casilleroRadio.getPosicion()[0];
                                     columnaRadio = casilleroRadio.getPosicion()[1];
                                     celdaRadio = partida.obtenerBotonEnCelda(grid, filaRadio, columnaRadio);
                                     partida.actualizarCeldaEnTablero(celdaRadio, filaRadio, columnaRadio, true);
                                }

                                partida.setDinamitaActivada(false);
                                partida.setDinamitasRestantes(partida.getDinamitasRestantes()-1);
                                useBombBtn.setText(String.valueOf(partida.getDinamitasRestantes()));

                                if (partida.getTablero().todosCasillerosLibresDescubiertos()) { // con esa dinamita ganó
                                    Cronometro.stopTimer();
                                    JOptionPane.showMessageDialog(gameframe, "¡Ganaste! Sos un/a crack.", "¡Ganaste!", JOptionPane.INFORMATION_MESSAGE);
                                    int segundos = Cronometro.getSeconds() + (Cronometro.getMinutes()*60);
                                    partida.terminarPartida(gameframe, grid, celdaPresionada, filas, columnas, fila, columna, username, segundos, true);
                                }

                            }
                        } else if (SwingUtilities.isRightMouseButton(e) && !partida.getTablero().todosCasillerosNoDescubiertos() && !casilleroPresionado.isDescubierto() && !partida.getTablero().todosCasillerosLibresDescubiertos()) { // click derecho, no primer click, no sobre un casillero descubierto, quedan banderas restantes, y la partida aún no terminó
                            if (!casilleroPresionado.isBandereado()) {
                                casilleroPresionado.setBandereado(true);
                                flagsLeftLabelNumber.setText(String.valueOf(Integer.parseInt(flagsLeftLabelNumber.getText())-1));
                                partida.setBanderasRestantes(partida.getBanderasRestantes()-1);
                                partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, false);
                            } else if (casilleroPresionado.isBandereado()) {
                                casilleroPresionado.setBandereado(false);
                                flagsLeftLabelNumber.setText(String.valueOf(Integer.parseInt(flagsLeftLabelNumber.getText())+1));
                                partida.setBanderasRestantes(partida.getBanderasRestantes()+1);
                                partida.actualizarCeldaEnTablero(celdaPresionada, fila, columna, false);
                            }
                        }
                    }
                });
                grid.add(celda);
            }
        }

        gameScreenMiddlePanel.add(Box.createVerticalGlue());
        gameScreenMiddlePanel.add(grid);
        gameScreenMiddlePanel.add(Box.createVerticalGlue());

        // agregar secciones a la pantalla -----------------------------------------------------------------------------
        gameScreen.add(gameScreenTopPanel);
        gameScreen.add(gameScreenMiddlePanel);
        gameScreen.add(gameScreenBottomPanel);

        // agregar la pantalla
        gameframe.add(gameScreen);
        frame.setVisible(false);
        gameframe.setVisible(true);
    }

    // private
    private static Partida crearPartida(int filas, int columnas, boolean powers, JButton useBombBtn, JButton useShieldBtn, JButton useLensBtn, JButton useTurtleBtn, JLabel flagsLeftLabel) {
        /* crea, carga, prepara y devuelve un objeto Partida que será utilizado para esta sesión de juego */
        Partida partida = new Partida();
        partida.crearTablero(filas, columnas);
        partida.cargarCasilleros();
        partida.cargarPoderes(filas, columnas, powers);
        partida.cargarInformacionEnPantalla(useBombBtn, useShieldBtn, useLensBtn, useTurtleBtn, flagsLeftLabel);
        return (partida);
    }
}