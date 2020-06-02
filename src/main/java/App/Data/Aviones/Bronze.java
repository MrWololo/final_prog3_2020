package App.Data.Aviones;

import App.Data.Avion;

public class Bronze extends Avion {

    /*
     * public Bronze() { super(4000, 150, 10, 878, "a helice", false); }
     */
    public Bronze(String nombre, double combustible, double costo, double capacidad, double velocidad, String motor) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, false);
    }

}