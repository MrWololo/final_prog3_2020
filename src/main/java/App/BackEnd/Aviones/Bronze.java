package App.BackEnd.Aviones;

import App.BackEnd.Avion;

public class Bronze extends Avion {

    private static final long serialVersionUID = -5148864708989146263L;

    /*
     * public Bronze() { super(4000, 150, 10, 878, "a helice", false); }
     */
    public Bronze(String nombre, double combustible, double costo, double capacidad, double velocidad, String motor) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, false);
    }

}