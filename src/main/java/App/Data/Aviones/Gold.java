package App.Data.Aviones;

import App.Data.Avion;

public class Gold extends Avion {

    private boolean wifi;

    /*public Gold(boolean wifi) {
        super(100000, 300, 5, 2000, "a reaccion", true);
        this.wifi = wifi;
    }*/

    public Gold(String nombre, double combustible, double costo, double capacidad, double velocidad, String motor, boolean wifi) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, true);
        this.wifi = wifi;
    }

    public boolean hasWifi() {
        return this.wifi;
    }

}