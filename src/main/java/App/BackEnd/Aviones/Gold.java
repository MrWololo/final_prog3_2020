package App.BackEnd.Aviones;

import App.BackEnd.Avion;

public class Gold extends Avion {

    private static final long serialVersionUID = 532365598491366848L;
    private boolean wifi;

    /*public Gold(boolean wifi) {
        super(100000, 300, 5, 2000, "a reaccion", true);
        this.wifi = wifi;
    }*/

    public Gold() {
    }

    public Gold(String nombre, double combustible, double costo, int capacidad, double velocidad, String motor, boolean wifi) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, true, 6000);
        this.wifi = wifi;
    }

    public boolean hasWifi() {
        return this.wifi;
    }

}