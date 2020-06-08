package App.BackEnd.Aviones;

import App.BackEnd.Avion;

public class Bronze extends Avion {

    /*
     * public Bronze() { super(4000, 150, 10, 878, "a helice", false); }
     */

    /**
     *
     */
    private static final long serialVersionUID = -4879557623190390144L;

    public Bronze() {
    }

    public Bronze(String nombre, double combustible, double costo, int capacidad, double velocidad, String motor) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, false, 3000);
    }

}