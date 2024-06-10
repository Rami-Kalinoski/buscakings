package classes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class Partida {
    // attributes
    private Tablero tablero;
    private int banderasRestantes;
    private int escudosRestantes;
    private int lupasRestantes;
    private int dinamitasRestantes;
    private int tortugasRestantes;
    private static boolean escudoActivado;
    private static boolean lupaActivada;
    private static boolean dinamitaActivada;

    // methods
    public void crearTablero(int filas, int columnas) {
        /* crea y carga su atributo tablero */
        tablero = new Tablero();
        tablero.setFilas(filas);
        tablero.setColumnas(columnas);
        if (filas==9 && columnas==9) {
            banderasRestantes = 10;
            tablero.setCantidadMinas(10);
        } else if (filas==16 && columnas==16) {
            banderasRestantes = 40;
            tablero.setCantidadMinas(40);
        } else if (filas==16 && columnas==30) {
            banderasRestantes = 99;
            tablero.setCantidadMinas(99);
        } else {
            banderasRestantes = (int) ((filas*columnas)*0.2);
            tablero.setCantidadMinas((int) ((filas*columnas)*0.2));
        }

        escudoActivado = false;
        lupaActivada = false;
        dinamitaActivada = false;
    }

    public void cargarCasilleros() {
        /* crea y carga los casilleros correspondientes al atributo "tablero" */
        int cantidadCasilleros = tablero.getFilas() * tablero.getColumnas();
        Casillero[] casilleros = new Casillero[cantidadCasilleros];
        int i = 0;
        int max = cantidadCasilleros;
        int min = 1;
        int mina = 0;
        boolean usaMina = false;
        Random random = new Random();

        for (int fila=0;fila<tablero.getFilas();fila++) {
            for (int columna=0;columna< tablero.getColumnas();columna++) {
                mina = random.nextInt(max-min+1)+min;
                if (mina<=tablero.getCantidadMinas()) {
                    usaMina = true;
                    min++;
                } else {
                    usaMina = false;
                    max--;
                }
                casilleros[i] = crearCasillero(fila, columna, usaMina);
                i++;
            }
        }

        tablero.setCasilleros(casilleros);

        for (int fila=0;fila<tablero.getFilas();fila++) {
            for (int columna=0;columna< tablero.getColumnas();columna++) {
                terminarCasillero(fila, columna);
            }
        }
    }

    public Casillero crearCasillero(int fila, int columna, boolean mina) {
        /* crea y devuelve un casillero con la información básica */
        int[] pos = new int[2];
        pos[0]=fila; pos[1]=columna;
        return (new Casillero(pos, false, false, mina));
    }

    public void terminarCasillero(int fila, int columna) {
        /* encuentra los casilleros del radio de un casillero recibido por parámetro (en posición fila y columna) y carga la cantidad de minas que este tiene en su radio */
        int cantidadCasillerosRadio = 0;
        String caso = ""; // las letras de los posibles casos están definidas en un documento, la explicación se deja al lado de cada caso

        if (fila > 0) {
            if (columna > 0) {
                if (columna != tablero.getColumnas() - 1) {
                    if (fila != tablero.getFilas() - 1) {
                        caso = "a"; // Casillero en el medio del tablero
                        cantidadCasillerosRadio = 8;
                    } else {
                        caso = "b"; // Casillero en el medio del tablero y en el margen inferior
                        cantidadCasillerosRadio = 5;
                    }
                } else {
                    if (fila != tablero.getFilas() - 1) {
                        caso = "e"; // Casillero en el medio del tablero y en el margen derecho
                        cantidadCasillerosRadio = 5;
                    } else {
                        caso = "i"; // Casillero en la esquina inferior derecha del tablero
                        cantidadCasillerosRadio = 3;
                    }
                }
            } else {
                if (fila != tablero.getFilas() - 1) {
                    caso = "d"; // Casillero en el medio del tablero y en el margen izquierdo
                    cantidadCasillerosRadio = 5;
                } else {
                    caso = "h"; // Casillero en la esquina inferior izquierda del tablero
                    cantidadCasillerosRadio = 3;
                }
            }
        } else {
            if (columna > 0) {
                if (fila!=tablero.getFilas()-1 && columna!=tablero.getColumnas()-1) {
                    caso = "c"; // Casillero en el medio del tablero y en el margen superior
                    cantidadCasillerosRadio = 5;
                } else {
                    caso = "g"; // Casillero en la esquina superior derecha del tablero
                    cantidadCasillerosRadio = 3;
                }
            } else {
                caso = "f"; // Casillero en la esquina superior izquierda del tablero
                cantidadCasillerosRadio = 3;
            }
        }

        int contadorMinasRadio = 0;
        Casillero[] radio = new Casillero[cantidadCasillerosRadio]; // los for de abajo recorren el radio de diferentes maneras dependiendo el caso
        int i = 0;
        if (caso.equals("a")) {
            for (int f=fila-1;f<=fila+1;f++) {
                for (int c=columna-1;c<=columna+1;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("b")) {
            for (int f=fila-1;f<=fila;f++) {
                for (int c=columna-1;c<=columna+1;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("e")) {
            for (int f=fila-1;f<=fila+1;f++) {
                for (int c=columna-1;c<=columna;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("i")) {
            for (int f=fila-1;f<=fila;f++) {
                for (int c=columna-1;c<=columna;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("d")) {
            for (int f=fila-1;f<=fila+1;f++) {
                for (int c=columna;c<=columna+1;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("h")) {
            for (int f=fila-1;f<=fila;f++) {
                for (int c=columna;c<=columna+1;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("c")) {
            for (int f=fila;f<=fila+1;f++) {
                for (int c=columna-1;c<=columna+1;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("g")) {
            for (int f=fila;f<=fila+1;f++) {
                for (int c=columna-1;c<=columna;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        } else if (caso.equals("f")) {
            for (int f=fila;f<=fila+1;f++) {
                for (int c=columna;c<=columna+1;c++) {
                    if (f!=fila || c!=columna) {
                        radio[i] = tablero.encontrarCasillero(f, c);
                        i++;
                        if (tablero.encontrarCasillero(f, c).isMinado()) {
                            contadorMinasRadio++;
                        }
                    }
                }
            }
        }

        Casillero casillero = tablero.encontrarCasillero(fila, columna);
        casillero.setCantidadMinasRadio(contadorMinasRadio);
        casillero.setCasillerosRadio(radio);
    }

    public void cargarPoderes(int filas, int columnas, boolean powers) {
        /* recibe información del tamaño de la grilla y carga la cantidad de poderes en base a esa dificultad */
        if (powers) {
            if (filas==9 && columnas==9) {
                escudosRestantes = 1;
                lupasRestantes = 1;
                dinamitasRestantes = 1;
                tortugasRestantes = 1;
            } else if (filas==16 && columnas==16) {
                escudosRestantes = 1;
                lupasRestantes = 2;
                dinamitasRestantes = 1;
                tortugasRestantes = 2;
            } else if (filas==16 && columnas==30) {
                escudosRestantes = 2;
                lupasRestantes = 2;
                dinamitasRestantes = 2;
                tortugasRestantes = 2;
            } else {
                escudosRestantes = (int) ((filas*columnas)*0.005);
                lupasRestantes = (int) ((filas*columnas)*0.01);
                dinamitasRestantes = (int) ((filas*columnas)*0.005);
                tortugasRestantes = (int) ((filas*columnas)*0.005);
            }
        } else {
            escudosRestantes = 0;
            lupasRestantes = 0;
            dinamitasRestantes = 0;
            tortugasRestantes = 0;
        }
    }

    public void cargarInformacionEnPantalla(JButton useBombBtn, JButton useShieldBtn, JButton useLensBtn, JButton useTurtleBtn, JLabel flagsLeftLabelNumber) {
        /* recibe los JComponents de la pantalla de Game referentes a los botones y label de banderas restantes para cargar esa información correctamente a partir de la información contenida en los atributos de esta Partida */
        useBombBtn.setText(String.valueOf(dinamitasRestantes));
        useShieldBtn.setText(String.valueOf(escudosRestantes));
        useLensBtn.setText(String.valueOf(lupasRestantes));
        useTurtleBtn.setText(String.valueOf(tortugasRestantes));

        ImageIcon flagImage = new ImageIcon("src\\assets\\flag.png");
        Image flagSizedImage = flagImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon flagSizedImageIcon = new ImageIcon(flagSizedImage);
        flagsLeftLabelNumber.setIcon(flagSizedImageIcon);
        flagsLeftLabelNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
        flagsLeftLabelNumber.setText(String.valueOf(banderasRestantes));
    }

    public void primerClick(JPanel grid, JButton celda, int fila, int columna) {
        /* verifica que no haya mina donde se cliqueó, y en caso de haberla, la desplaza al primer lugar más cerca de arriba a la izquierda donde no haya mina; también descubre un gran rango de casilleros sin mina */
        Casillero casilleroCliqueado = tablero.encontrarCasillero(fila, columna);
        if (casilleroCliqueado.isMinado()) {
            casilleroCliqueado.setMinado(false);

            Casillero casillero = null;
            int f=0; int c=0; boolean encontrado=false;
            while (!encontrado && f<tablero.getFilas()) {
                while (!encontrado && c<tablero.getColumnas()) {
                    casillero = tablero.encontrarCasillero(f, c);
                    if (!casillero.isMinado()) {
                        casillero.setMinado(true);
                        encontrado = true;
                    }
                    c++;
                }
                f++;
            }
        }

        actualizarCeldaEnTablero(celda, fila, columna, true);
        Casillero[] radio = casilleroCliqueado.getCasillerosRadio();
        Casillero[] radioAux = null;
        JButton celdaRadio = null;
        JButton celdaRadioAux = null;
        for (int i=0;i<radio.length;i++) {
            if (!radio[i].isMinado()) {
                radio[i].setDescubierto(true);
                celdaRadio = obtenerBotonEnCelda(grid, radio[i].getPosicion()[0], radio[i].getPosicion()[1]);
                actualizarCeldaEnTablero(celdaRadio, radio[i].getPosicion()[0], radio[i].getPosicion()[1], true);
                radioAux = radio[i].getCasillerosRadio();
                for (int y=0;y<radioAux.length;y++) {
                    if (!radioAux[y].isMinado()) {
                        radioAux[y].setDescubierto(true);
                        celdaRadioAux = obtenerBotonEnCelda(grid, radioAux[y].getPosicion()[0], radioAux[y].getPosicion()[1]);
                        actualizarCeldaEnTablero(celdaRadioAux, radioAux[y].getPosicion()[0], radioAux[y].getPosicion()[1], true);
                    }
                }
            }
        }
    }

    public void actualizarCeldaEnTablero(JButton celda, int fila, int columna, boolean clickIzquierdo) {
        /* actualiza el aspecto de la celda en el tablero según su situación */
        Casillero casillero = tablero.encontrarCasillero(fila, columna);
        if (clickIzquierdo) {
            if (casillero.isDescubierto() && !casillero.isMinado()) {
                celda.setBackground(Color.GRAY);
                String ruta = "";
                int cantidadMinas = casillero.getCantidadMinasRadio();
                if (cantidadMinas>0) {
                    if (cantidadMinas==1) {
                        ruta = "one.png";
                    } else if (cantidadMinas==2) {
                        ruta = "two.png";
                    } else if (cantidadMinas==3) {
                        ruta = "three.png";
                    } else if (cantidadMinas==4) {
                        ruta = "four.png";
                    } else if (cantidadMinas==5) {
                        ruta = "five.png";
                    } else if (cantidadMinas==6) {
                        ruta = "six.png";
                    } else if (cantidadMinas==7) {
                        ruta = "seven.png";
                    } else if (cantidadMinas==8) {
                        ruta = "eight.png";
                    }
                    ImageIcon numberImage = new ImageIcon("src\\assets\\"+ruta);
                    Image numberSizedImage = numberImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
                    ImageIcon numberSizedImageIcon = new ImageIcon(numberSizedImage);
                    celda.setIcon(numberSizedImageIcon);
                    celda.setAlignmentX(Component.LEFT_ALIGNMENT);
                }
            } else if (casillero.isDescubierto() && casillero.isMinado()) {
                ImageIcon mineImage = new ImageIcon("src\\assets\\mine.png");
                Image mineSizedImage = mineImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
                ImageIcon mineSizedImageIcon = new ImageIcon(mineSizedImage);
                celda.setBackground(Color.DARK_GRAY);
                celda.setIcon(mineSizedImageIcon);
            }
        } else if (!clickIzquierdo) {
            if (casillero.isBandereado()) {
                ImageIcon flagImage = new ImageIcon("src\\assets\\flag.png");
                Image flagSizedImage = flagImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
                ImageIcon flagSizedImageIcon = new ImageIcon(flagSizedImage);
                celda.setBackground(Color.LIGHT_GRAY);
                celda.setIcon(flagSizedImageIcon);
            } else if (!casillero.isBandereado()) {
                celda.setBackground(Color.LIGHT_GRAY);
                celda.setIcon(null);
            }
        }
    }

    public void finalizarTablero(JPanel grid, int filas, int columnas) {
        /* recorre toda la grilla y actualiza el estilo de cada casillero para mostrar finalmente cómo estaba compuesto el tablero, al finalizar la partida (gane o pierda) */
        for (int f=0;f<filas;f++) {
            for (int c =0;c<columnas;c++) {
                Casillero casillero = tablero.encontrarCasillero(f, c);
                casillero.setBandereado(false);
                casillero.setDescubierto(true);
                JButton celdaActual = obtenerBotonEnCelda(grid, f, c);
                actualizarCeldaEnTablero(celdaActual, f, c, true);
            }
        }
    }

    public void terminarPartida(JFrame gameframe, JPanel grid, JButton celdaFinal, int filas, int columnas, int fila, int columna, String username, int segundosPartida, boolean wins) {
        /* descubre todos los casilleros para mostrar como estaba compuesto el tablero (llamando a finalizarTablero) y registra las estadísticas de esta partida (llamando a registrarPartida) */
        actualizarCeldaEnTablero(celdaFinal, fila, columna, true);
        try {
            Thread.sleep(3000);
            finalizarTablero(grid, filas, columnas);
        } catch (InterruptedException ex) {
            finalizarTablero(grid, filas, columnas);
        }
        if (!username.equals("invitado/a")) {
            registrarPartida(gameframe, username, wins, segundosPartida);
        }
    }

    public JButton obtenerBotonEnCelda(JPanel grid, int fila, int columna) {
        /* recorre la grilla y devuelve el JButton correspondiente a la celda que esté ubicada en la posición recibida por parámetro (en fila y columna) */
        Component[] componentes = grid.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JButton) {
                JButton boton = (JButton) componente;
                int filaBoton = (int) boton.getClientProperty("fila");
                int columnaBoton = (int) boton.getClientProperty("columna");
                if (filaBoton == fila && columnaBoton == columna) {
                    return (boton);
                }
            }
        }
        return (null);
    }

    // privates
    private boolean existeRegistroUsuario(JFrame gameframe, String username) {
        /* (en la versión final no se usa, pero se deja porque funciona correctamente y por las dudas) */
        /* recorre el archivo donde se almacenan los registros de estadísticas y devuelve un booleano indicando si existe registro de un usuario cuyo nombre se recibe por parámetro */
        boolean existe = false;
        File archivo = new File("src\\stats\\stats.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));) {
            String[] campos;
            String linea;
            while (!existe && (linea = reader.readLine()) !=null) {
                campos = linea.split(";");
                if (campos[0].equals(username)) {
                    existe = true;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(gameframe, "Ha ocurrido un error con el archivo de estadísticas.\nLo lamentamos, pero no se guardará registro de esta partida.", "Error con archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gameframe, "Ha ocurrido un error con el archivo de estadísticas.\nLo lamentamos, pero no se guardará registro de esta partida.", "Error con archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
        }
        return (existe);
    }

    private String encontrarRegistro(JFrame gameframe, String username) {
        /* (en la versión final no se usa, pero se deja porque funciona correctamente y por las dudas) */
        /* recorre el archivo donde se almacenan los registros de estadísticas y devuelve el registro correspondiente a un usuario cuyo nombre se recibe por parámetro (en formato String) */
        /* si el registro no se encuentra devuelve un String vacío */
        boolean encontrado = false;
        String registro = "";
        File archivo = new File("src\\stats\\stats.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));) {
            String[] campos;
            String linea;
            while (!encontrado && (linea = reader.readLine()) !=null) {
                campos = linea.split(";");
                if (campos[0].equals(username)) {
                    registro = linea;
                    encontrado = true;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(gameframe, "Ha ocurrido un error con el archivo de estadísticas.\nLo lamentamos, pero no se guardará registro de esta partida.", "Error con archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gameframe, "Ha ocurrido un error con el archivo de estadísticas.\nLo lamentamos, pero no se guardará registro de esta partida.", "Error con archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
        }
        return (registro);
    }

    private void registrarPartida(JFrame gameframe, String username, boolean win, int segundosPartida) {
        /* utiliza un archivo temporal para actualizar los registros de estadísticas, recibiendo el nombre de usuario, si ganó o no, y los segundos que tardó en completar la partida */
        /* al reescribir el archivo, si el usuario ya existía se acutalizan sus datos, sino, se agregan al final */
        /* finalmente reemplaza el archivo temporal por el que ya existía, dejando así registro actualizado de las estadísticas de la última partida jugada */
        /* este método no se utiliza si se jugó como invitado */
        File archivo = new File("src\\stats\\stats.txt");
        File temporal = new File("src\\stats\\temporal.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo)); BufferedWriter writer = new BufferedWriter(new FileWriter(temporal))) {
            boolean encontrado = false;
            String linea = "";
            while ((linea=reader.readLine())!=null) {
                linea = linea.replaceAll("\\r\\n|\\r|\\n", "");
                String[] campos = linea.split(";");
                if (!campos[0].equals(username)) {
                    writer.write(linea); writer.newLine();
                } else {
                    encontrado = true;
                    // se suma una partida jugada
                    int partidasJugadas = Integer.parseInt(campos[1])+1;
                    campos[1] = String.valueOf(partidasJugadas);
                    if (win) { // se suma una victoria
                        int partidasGanadas = Integer.parseInt(campos[2])+1;
                        campos[2] = String.valueOf(partidasGanadas);
                    } else { // se suma una derrota
                        int partidasPerdidas = Integer.parseInt(campos[3])+1;
                        campos[3] = String.valueOf(partidasPerdidas);
                    }
                    // se suma el tiempo jugado
                    int segundosJugados = Integer.parseInt(campos[4])+segundosPartida;
                    campos[4] = String.valueOf(segundosJugados);
                    // se verifica (y registra si es necesario) el mejor tiempo
                    int segundosMejor = Integer.parseInt(campos[5]);
                    if (win && (segundosPartida<segundosMejor || segundosMejor==0)) {
                        segundosMejor = segundosPartida;
                    }
                    campos[5] = String.valueOf(segundosMejor);

                    String nuevaLinea = String.join(";", campos);
                    writer.write(nuevaLinea); writer.newLine();
                }
            }

            if (!encontrado) {
                String nuevaLinea = username + ";1;";
                if (win) {
                    nuevaLinea += "1;0;";
                } else {
                    nuevaLinea += "0;1;";
                }
                nuevaLinea += segundosPartida + ";";
                if (win) {
                    nuevaLinea += segundosPartida;
                } else {
                    nuevaLinea += "0";
                }
                writer.write(nuevaLinea);
                writer.newLine();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(gameframe, "Ha ocurrido un error y no se ha podido abrir el archivo de estadísticas.\nLo lamentamos, pero no se guardará registro de esta partida.", "Error al abrir archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gameframe, "Ha ocurrido un error con el archivo de estadísticas.\nLo lamentamos, pero no se guardará registro de esta partida.", "Error con archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
        }
        // reemplazar el archivo original con el archivo temporal
        if (archivo.delete()) {
            temporal.renameTo(archivo);
        }
    }

    // constructors
    public Partida() {
        this.tablero = new Tablero();
    }
    public Partida(Tablero tablero, int banderasRestantes, int escudosRestantes, int lupasRestantes, int dinamitasRestantes, int tortugasRestantes) {
        this.tablero = tablero;
        this.banderasRestantes = banderasRestantes;
        this.escudosRestantes = escudosRestantes;
        this.lupasRestantes = lupasRestantes;
        this.dinamitasRestantes = dinamitasRestantes;
        this.tortugasRestantes = tortugasRestantes;
    }

    // getters & setters
    public Tablero getTablero() {
        return tablero;
    }
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    public int getBanderasRestantes() {
        return banderasRestantes;
    }
    public void setBanderasRestantes(int banderasRestantes) {
        this.banderasRestantes = banderasRestantes;
    }
    public int getEscudosRestantes() {
        return escudosRestantes;
    }
    public void setEscudosRestantes(int escudosRestantes) {
        this.escudosRestantes = escudosRestantes;
    }
    public int getLupasRestantes() {
        return lupasRestantes;
    }
    public void setLupasRestantes(int lupasRestantes) {
        this.lupasRestantes = lupasRestantes;
    }
    public int getDinamitasRestantes() {
        return dinamitasRestantes;
    }
    public void setDinamitasRestantes(int dinamitasRestantes) {
        this.dinamitasRestantes = dinamitasRestantes;
    }
    public int getTortugasRestantes() {
        return tortugasRestantes;
    }
    public void setTortugasRestantes(int tortugasRestantes) {
        this.tortugasRestantes = tortugasRestantes;
    }
    public boolean isEscudoActivado() {
        return escudoActivado;
    }
    public void setEscudoActivado(boolean escudoActivado) {
        this.escudoActivado = escudoActivado;
    }
    public boolean isLupaActivada() {
        return lupaActivada;
    }
    public void setLupaActivada(boolean lupaActivada) {
        this.lupaActivada = lupaActivada;
    }
    public boolean isDinamitaActivada() {
        return dinamitaActivada;
    }
    public void setDinamitaActivada(boolean dinamitaActivada) {
        this.dinamitaActivada = dinamitaActivada;
    }
}