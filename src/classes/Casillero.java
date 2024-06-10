package classes;

public class Casillero {
    // attributes
    private int[] posicion;
    private boolean descubierto;
    private boolean bandereado;
    private boolean minado;
    private int cantidadMinasRadio;
    private Casillero[] casillerosRadio;

    // constructors
    public Casillero() {}
    public Casillero(int[] posicion, boolean descubierto, boolean bandereado, boolean minado) {
        this.posicion = posicion;
        this.descubierto = descubierto;
        this.bandereado = bandereado;
        this.minado = minado;
    }
    // getters & setters
    public int[] getPosicion() {
        return posicion;
    }
    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }
    public boolean isDescubierto() {
        return descubierto;
    }
    public void setDescubierto(boolean descubierto) {
        this.descubierto = descubierto;
    }
    public boolean isBandereado() {
        return bandereado;
    }
    public void setBandereado(boolean bandereado) {
        this.bandereado = bandereado;
    }
    public boolean isMinado() {
        return minado;
    }
    public void setMinado(boolean minado) {
        this.minado = minado;
    }
    public int getCantidadMinasRadio() {
        return cantidadMinasRadio;
    }
    public void setCantidadMinasRadio(int cantidadMinasRadio) {
        this.cantidadMinasRadio = cantidadMinasRadio;
    }
    public Casillero[] getCasillerosRadio() {
        return casillerosRadio;
    }
    public void setCasillerosRadio(Casillero[] casillerosRadio) {
        this.casillerosRadio = casillerosRadio;
    }
}