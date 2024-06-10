package classes;

public class Tablero {
    // attributes
    private int filas;
    private int columnas;
    private int cantidadMinas;
    private Casillero[] casilleros;

    // methods
    public Casillero encontrarCasillero(int fila, int columna) {
        /* encuentra y devuelve un objeto Casillero específico contenido en este tablero */
        /* recorre la lista de casilleros del tablero y devuelve el correspondiente a la posición recibida por parámetro (como fila y columna) */
        Casillero casilleroEncontrado = null;
        for (int i=0;i<casilleros.length;i++) {
            if (casilleros[i].getPosicion()[0]==fila && casilleros[i].getPosicion()[1]==columna) {
                casilleroEncontrado = casilleros[i];
            }
        }
        return (casilleroEncontrado);
    }

    public boolean todosCasillerosLibresDescubiertos() {
        /* indica si todos los casilleros que no tienen mina están descubiertos (es decir, si los cliqueó) */
        /* recorre la lista de casilleros del tablero y devuelve un booleano indicando si todos los que no tienen minas están descubiertos */
        /* si soluciona con un "true" significa que el usuario ganó la partida, ya que dejó todos los casilleros sin minas descubiertos */
        if (casilleros!=null) {
            for (int i=0;i<casilleros.length;i++) {
                if (!casilleros[i].isMinado() && !casilleros[i].isDescubierto()) { // si encontramos un solo casillero sin mina && que no cliqueó (es decir, no está descubierto) devuelve falso
                    return (false);
                }
            }
            return (true);
        } else {
            return (false);
        }
    }

    public boolean todosCasillerosNoDescubiertos() {
        /* indica si todos los casilleros (con mina y sin mina) no están descubiertos (es decir, que no cliqueó ninguno) */
        /* recorre la lista de casilleros del tablero y devuelve un booleano indicando si todos están sin descubrir */
        /* si soluciona con un "true" significa que el usuario no inició la partida, ya que no descubrió ninguno */
        if (casilleros!=null) {
            for (int i=0;i<casilleros.length;i++) {
                if (casilleros[i].isDescubierto()) { // si encontramos un solo casillero (con o sin mina) que ya está descubierto (es decir, que ya fue cliqueado) devuelve false
                    return (false);
                }
            }
            return (true);
        } else {
            return (true);
        }
    }

    // constructors
    public Tablero () {}
    public Tablero(int filas, int columnas, int cantidadMinas, Casillero[] casilleros) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadMinas = cantidadMinas;
        this.casilleros = casilleros;
    }

    // getters & setters
    public int getFilas() {
        return filas;
    }
    public void setFilas(int filas) {
        this.filas = filas;
    }
    public int getColumnas() {
        return columnas;
    }
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    public int getCantidadMinas() {
        return cantidadMinas;
    }
    public void setCantidadMinas(int cantidadMinas) {
        this.cantidadMinas = cantidadMinas;
    }
    public Casillero[] getCasilleros() {
        return casilleros;
    }
    public void setCasilleros(Casillero[] casilleros) {
        this.casilleros = casilleros;
    }
}