package myswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Newgame {
    // crear la pantalla nueva partida
    public static void crearPantallaNewgame(JFrame frame, CardLayout card, JPanel panelCard) {
        /* crea la interfaz de la pantalla de nueva partida y la agrega a la carta de la pantalla principal */
        // crear pantalla
        JPanel newgameScreen = new JPanel();
        newgameScreen.setLayout(new BoxLayout(newgameScreen, BoxLayout.Y_AXIS));

        // crear secciones
        JPanel newgameScreenTopPanel = new JPanel();
        JPanel newgameScreenMiddleTopPanel = new JPanel();
        JPanel newgameScreenMiddleMiddlePanel = new JPanel();
        JPanel newgameScreenMiddleBottomPanel = new JPanel();
        JPanel newgameScreenBottomPanel = new JPanel();
        newgameScreenTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newgameScreenMiddleTopPanel.setLayout(new BoxLayout(newgameScreenMiddleTopPanel, BoxLayout.Y_AXIS));
        newgameScreenMiddleMiddlePanel.setLayout(new BoxLayout(newgameScreenMiddleMiddlePanel, BoxLayout.Y_AXIS));
        newgameScreenMiddleBottomPanel.setLayout(new BoxLayout(newgameScreenMiddleBottomPanel, BoxLayout.Y_AXIS));
        newgameScreenBottomPanel.setLayout(new BoxLayout(newgameScreenBottomPanel, BoxLayout.X_AXIS));

        newgameScreenTopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameScreenMiddleTopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameScreenMiddleMiddlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameScreenMiddleBottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameScreenBottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // top section---------------------------------------------------------
        JButton goBackNewgameScreenBtn = new JButton();
        ImageIcon arrowImage = new ImageIcon("src\\assets\\arrow.png");
        Image arrowSizedImage = arrowImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon arrowSizedImageIcon = new ImageIcon(arrowSizedImage);
        goBackNewgameScreenBtn.setIcon(arrowSizedImageIcon);
        goBackNewgameScreenBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        goBackNewgameScreenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(panelCard, "Home Screen");
            }
        });

        newgameScreenTopPanel.add(goBackNewgameScreenBtn);

        // middle-top section--------------------------------------------------
        JLabel titleUserNewgame = new JLabel("Ingresar usuario");
        JTextField usernameField = new JTextField(20);

        titleUserNewgame.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleUserNewgame.setAlignmentY(Component.CENTER_ALIGNMENT);
        usernameField.setAlignmentY(Component.CENTER_ALIGNMENT);

        usernameField.setPreferredSize(new Dimension(250, 30));
        usernameField.setMaximumSize(new Dimension(250, 30));

        newgameScreenMiddleTopPanel.add(titleUserNewgame);
        newgameScreenMiddleTopPanel.add(Box.createVerticalStrut(5));
        newgameScreenMiddleTopPanel.add(usernameField);

        // middle-middle section-----------------------------------------------
        JLabel titleDifficultyNewgame = new JLabel("Seleccionar dificultad");
        titleDifficultyNewgame.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel difficultiesPanel = new JPanel();
        difficultiesPanel.setLayout(new BoxLayout(difficultiesPanel, BoxLayout.X_AXIS));
        difficultiesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton easyDifficultyBtn = new JButton("Fácil");
        JButton mediumDifficultyBtn = new JButton("Medio");
        JButton hardDifficultyBtn = new JButton("Difícil");
        difficultiesPanel.add(easyDifficultyBtn);
        difficultiesPanel.add(Box.createHorizontalStrut(15));
        difficultiesPanel.add(mediumDifficultyBtn);
        difficultiesPanel.add(Box.createHorizontalStrut(15));
        difficultiesPanel.add(hardDifficultyBtn);

        JPanel gridsizePanel = new JPanel();
        JPanel rowsPanel = new JPanel();
        JPanel colsPanel = new JPanel();
        gridsizePanel.setLayout(new BoxLayout(gridsizePanel, BoxLayout.X_AXIS));
        gridsizePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.X_AXIS));
        colsPanel.setLayout(new BoxLayout(colsPanel, BoxLayout.X_AXIS));
        JLabel rowsLabel = new JLabel("Filas:");
        JLabel colsLabel = new JLabel("Columnas:");
        JTextField rowsNumberField = new JTextField();
        JTextField colsNumberField = new JTextField();

        rowsNumberField.setPreferredSize(new Dimension(50, 20));
        rowsNumberField.setMaximumSize(new Dimension(50, 20));
        colsNumberField.setPreferredSize(new Dimension(50, 20));
        colsNumberField.setMaximumSize(new Dimension(50, 20));

        easyDifficultyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowsNumberField.setText("9");
                colsNumberField.setText("9");
            }
        });
        mediumDifficultyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowsNumberField.setText("16");
                colsNumberField.setText("16");
            }
        });
        hardDifficultyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowsNumberField.setText("16");
                colsNumberField.setText("30");
            }
        });

        gridsizePanel.add(rowsPanel);
        gridsizePanel.add(Box.createHorizontalStrut(25));
        gridsizePanel.add(colsPanel);

        rowsPanel.add(rowsLabel);
        rowsPanel.add(Box.createHorizontalStrut(10));
        rowsPanel.add(rowsNumberField);

        colsPanel.add(colsLabel);
        colsPanel.add(Box.createHorizontalStrut(10));
        colsPanel.add(colsNumberField);

        newgameScreenMiddleMiddlePanel.add(titleDifficultyNewgame);
        newgameScreenMiddleMiddlePanel.add(Box.createVerticalStrut(5));
        newgameScreenMiddleMiddlePanel.add(difficultiesPanel);
        newgameScreenMiddleMiddlePanel.add(Box.createVerticalStrut(10));
        newgameScreenMiddleMiddlePanel.add(gridsizePanel);

        // middle-bottom section-----------------------------------------------
        JLabel titlePowersNewGame = new JLabel("Seleccionar uso de poderes");
        titlePowersNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        JRadioButton powerTrueBtn = new JRadioButton("Sí");
        JRadioButton powerFalseBtn = new JRadioButton("No");
        ButtonGroup powersButtonsGroup = new ButtonGroup();
        powersButtonsGroup.add(powerTrueBtn);
        powersButtonsGroup.add(powerFalseBtn);

        JPanel powersButtonsGroupPanel = new JPanel();
        powersButtonsGroupPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        powersButtonsGroupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        powersButtonsGroupPanel.add(powerTrueBtn);
        powersButtonsGroupPanel.add(powerFalseBtn);

        newgameScreenMiddleBottomPanel.add(titlePowersNewGame);
        newgameScreenMiddleBottomPanel.add(Box.createVerticalStrut(5));
        newgameScreenMiddleBottomPanel.add(powersButtonsGroupPanel);

        // bottom section------------------------------------------------------
        JButton startGameBtn = new JButton("Iniciar partida");
        startGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

        startGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // crea variables necesarias
                int filas=0; int columnas=0; boolean usesPowers=false; String difficultySelected=""; String username="";

                // define si puede comenzar la partida
                boolean canStart = true;

                // define dificultad seleccionada
                if (!rowsNumberField.getText().equals("") && !colsNumberField.getText().equals("")) {
                    boolean error = false;
                    try {
                        filas = Integer.parseInt(rowsNumberField.getText());
                        columnas = Integer.parseInt(colsNumberField.getText());
                        if (filas>20 || filas<9) {
                            throw new NumberFormatException("El número de filas debe ser entre 9 y 20 (incluyendo extremos).");
                        }
                        if (columnas>30 || columnas<9) {
                            throw new NumberFormatException("El número de columnas debe ser 9 y 30 (incluyendo extremos).");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Las filas y columnas deben ser únicamente números enteros a partir de 9, filas menores o iguales a 20 y columnas menores o iguales a 30.\nPor favor, indique valores válidos.", "Error al iniciar partida", JOptionPane.ERROR_MESSAGE);
                        error = true;
                        canStart = false;
                    }

                    if (!error) {
                        if (filas == 9 && columnas == 9) {
                            difficultySelected = "fácil";
                        } else if (filas == 16 && columnas == 16) {
                            difficultySelected = "medio";
                        } else if (filas == 16 && columnas == 30) {
                            difficultySelected = "difícil";
                        } else {
                            difficultySelected = "personalizada";
                        }

                        // define si utiliza o no poderes
                        if (powerTrueBtn.isSelected() || powerFalseBtn.isSelected()) {
                            usesPowers = powerTrueBtn.isSelected();

                            // define si utiliza nombre de usuario y cuál utiliza
                            if (!usernameField.getText().trim().equals("")) {
                                if (!usernameField.getText().trim().contains(";") && !usernameField.getText().trim().equals("invitado/a")) {
                                    username = usernameField.getText().trim();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "El nombre de usuario no puede contener punto y coma (;) ni ser 'invitado/a'.\nPor favor, indique valores válidos.", "Error al iniciar partida", JOptionPane.ERROR_MESSAGE);
                                    canStart = false;
                                }
                            } else {
                                int emptyUsernameResult = JOptionPane.showConfirmDialog(frame, "No ha ingresado un nombre de usuario. Si juega la partida como invitado/a no quedarán registros de las estadísticas.\n¿Desea comenzar la partida como invitado/a?", "Iniciar partida como invitado/a", JOptionPane.YES_NO_OPTION);
                                if (emptyUsernameResult == JOptionPane.NO_OPTION) {
                                    canStart = false;
                                }
                                if (emptyUsernameResult == JOptionPane.YES_OPTION) {
                                    username = "invitado/a";
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "No ha especificado si desea utilizar poderes en esta partida.\nPor favor, indique si desea o no utilizarlos.", "Error al iniciar partida", JOptionPane.ERROR_MESSAGE);
                            canStart = false;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "No ha ingresado una dificultad (filas y columnas).\nPor favor, seleccione una dificultad antes de comenzar.", "Error al iniciar partida", JOptionPane.ERROR_MESSAGE);
                    canStart = false;
                }

                if (canStart) {
                    frame.setVisible(false);

                    JFrame gameframe = new JFrame("Game Screen");
                    gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameframe.setSize(1000, 725);
                    gameframe.setLocationRelativeTo(null);
                    gameframe.setLayout(new BorderLayout());
                    Game.crearPantallaGame(gameframe, frame, card, panelCard, filas, columnas, username, usesPowers, difficultySelected);
                }
            }
        });

        newgameScreenBottomPanel.add(startGameBtn);

        // agregar todos los section a la pantalla de new game-----------------
        newgameScreen.add(newgameScreenTopPanel);
        newgameScreen.add(Box.createVerticalGlue());
        newgameScreen.add(newgameScreenMiddleTopPanel);
        newgameScreen.add(Box.createVerticalGlue());
        newgameScreen.add(newgameScreenMiddleMiddlePanel);
        newgameScreen.add(Box.createVerticalStrut(10));
        newgameScreen.add(newgameScreenMiddleBottomPanel);
        newgameScreen.add(Box.createVerticalStrut(30));
        newgameScreen.add(newgameScreenBottomPanel);
        newgameScreen.add(Box.createVerticalStrut(30));

        // agregar pantalla a la carta-----------------------------------------
        panelCard.add(newgameScreen, "New Game Screen");
    }
}