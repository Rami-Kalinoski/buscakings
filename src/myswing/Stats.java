package myswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Stats {
    // crear la pantalla de estadísticas
    public static void crearPantallaStats(JFrame frame, CardLayout card, JPanel panelCard) {
        /* crea la interfaz de la pantalla de estadísticas y la agrega a la carta de la pantalla principal */
        JPanel statsScreen = new JPanel();
        statsScreen.setLayout(new BoxLayout(statsScreen, BoxLayout.Y_AXIS));

        // crear secciones
        JPanel statsScreenTopPanel = new JPanel();
        JPanel statsScreenMiddlePanel = new JPanel();
        JPanel statsScreenBottomPanel = new JPanel();
        statsScreenTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statsScreenMiddlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        statsScreenBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // top section---------------------------------------------------------
        JButton goBackStatsScreenBtn = new JButton(); // crear botón
        ImageIcon arrowImage = new ImageIcon("src\\assets\\arrow.png"); // creas un objeto ÍCONO CON la imágen que le pasas la ruta por parámetro (pero esa imágen tiene el tamaño original)
        Image arrowSizedImage = arrowImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH); // guardas en un objeto IMAGEN la imagen que utiliza el ícono anterior (para obtener esa imágen es que se usa el .getImage() del objeto ÍCONO), modificada al tamaño que querés
        ImageIcon arrowSizedImageIcon = new ImageIcon(arrowSizedImage); // creas un objeto ÍCONO CON la imágen de tamaño personalizado
        goBackStatsScreenBtn.setIcon(arrowSizedImageIcon); // seteas como ÍCONO (porque no podes setear directamente una imagen) del botón el objeto ÍCONO creado en la linea anterior
        goBackStatsScreenBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        goBackStatsScreenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(panelCard, "Home Screen");
            }
        });

        statsScreenTopPanel.add(goBackStatsScreenBtn);

        // middle section------------------------------------------------------
        JLabel titleStats = new JLabel("Estadísticas");
        titleStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleStats.setAlignmentY(Component.CENTER_ALIGNMENT);

        statsScreenMiddlePanel.add(titleStats);

        // bottom section------------------------------------------------------
        JTextArea usersInfoTextarea = new JTextArea();
        usersInfoTextarea.setAlignmentX(Component.CENTER_ALIGNMENT);
        usersInfoTextarea.setAlignmentY(Component.CENTER_ALIGNMENT);
        usersInfoTextarea.setEditable(false); // el usuario no pueda editarlo
        usersInfoTextarea.setLineWrap(true); // cuando el contenido llega al margen derecho lo tira para abajo
        usersInfoTextarea.setWrapStyleWord(true); // cuando el contenido llega al margen derecho lo tira para abajo (similar pero con las palabras)

        cargarInformacionUsuarios(frame, card, panelCard, usersInfoTextarea);

        JScrollPane scroll = new JScrollPane(usersInfoTextarea);
        scroll.setPreferredSize(new Dimension(400, 300));
        scroll.setMaximumSize(new Dimension(400, 300));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel usersInfoPanel = new JPanel();
        usersInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        usersInfoPanel.add(scroll);

        statsScreenBottomPanel.add(usersInfoPanel);

        // agregar todos los section a la pantalla de stats--------------------
        statsScreen.add(statsScreenTopPanel);
        statsScreen.add(statsScreenMiddlePanel);
        statsScreen.add(Box.createVerticalStrut(5));
        statsScreen.add(statsScreenBottomPanel);
        statsScreen.add(Box.createVerticalGlue());

        // agregar pantalla a la carta-----------------------------------------
        panelCard.add(statsScreen, "Stats Screen");
    }

    // privates
    private static void cargarInformacionUsuarios(JFrame frame, CardLayout card, JPanel panelCard, JTextArea textArea) {
        /* recibe un JTextArea donde cargará la información de estadísticas de usuario reistrados en el archivo "stats.txt" */
        File archivo = new File("src\\stats\\stats.txt");
        System.out.println(archivo.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));) {
            String registro;
            int cantidadJugadores = 0;
            String text = "";
            String[] campos;
            while ((registro=reader.readLine())!=null) {
                registro = registro.replaceAll("\\r\\n|\\r|\\n", "");
                campos = registro.split(";");

                float porcentajeWins = ((float) Integer.parseInt(campos[2])/Integer.parseInt(campos[1])) * 100;
                float porcentajeLoses = ((float) Integer.parseInt(campos[3])/Integer.parseInt(campos[1])) * 100;
                long totalSegundosJugados = Long.parseLong(campos[4]);
                int horasJugadas = (int) (totalSegundosJugados/3600);
                int minutosJugados = (int) ((totalSegundosJugados%3600)/60);
                int segundosJugados = (int) ((totalSegundosJugados%3600)%60);
                long totalMejoresSegundos = Long.parseLong(campos[5]);
                int mejoresMinutos = (int) (totalMejoresSegundos/60);
                int mejoresSegundos = (int) (totalMejoresSegundos%60);

                text += campos[0] + ":\n";
                text += "    Partidas jugadas: " + campos[1] + ".\n";
                text += "    Partidas ganadas: " + porcentajeWins + "% (" + campos[2] + " partidas).\n";
                text += "    Partidas perdidas: " + porcentajeLoses + "% (" + campos[3] + " partidas).\n";
                text += "    Tiempo jugado: " + horasJugadas + " horas, " + minutosJugados + " minutos y " + segundosJugados + " segundos.\n";
                text += "    Mejor tiempo: " + mejoresMinutos + " minutos y " + mejoresSegundos + " segundos.\n\n";

                cantidadJugadores++;
            }

            if (!text.equals("")) {
                textArea.setText(text);
            } else {
                textArea.setText("No existen registros de estadísticas.");
                cantidadJugadores++;
            }

            textArea.setPreferredSize(new Dimension(400, cantidadJugadores*100+50));
            textArea.setMaximumSize(new Dimension(400, cantidadJugadores*100+50));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "Ha ocurrido un error al abrir el archivo y no se han podido cargar las estadísticas.", "Error al abrir archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
            textArea.setText("Ha ocurrido un error al abrir el archivo y no se han podido cargar las estadísticas.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Ha ocurrido un error con el archivo y no se han podido cargar las estadísticas.", "Error con archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
            textArea.setText("Ha ocurrido un error al abrir el archivo y no se han podido cargar las estadísticas.");
        }
    }
}