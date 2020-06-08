package App.BackEnd.Aviones;

import App.BackEnd.Avion;

public class Silver extends Avion {

    /*
     * public Silver() { super(5000, 225, 15, 850.2, "de pistones", true); }
     */

    /**
     *
     */
    private static final long serialVersionUID = 5743963326617596030L;

    public Silver() {
    }

    public Silver(String nombre, double combustible, double costo, int capacidad, double velocidad, String motor) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, true, 4000);
    }

}