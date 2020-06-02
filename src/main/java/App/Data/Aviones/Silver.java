package App.Data.Aviones;

import App.Data.Avion;

public class Silver extends Avion {

    /*
     * public Silver() { super(5000, 225, 15, 850.2, "de pistones", true); }
     */
    public Silver(String nombre, double combustible, double costo, double capacidad, double velocidad, String motor) {
        super(nombre, combustible, costo, capacidad, velocidad, motor, true);
    }

}