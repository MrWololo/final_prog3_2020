package App.BackEnd;

import java.io.Serializable;

//Gson es la unica razon por la cual esta clase no
//es Abstracta
public class Avion implements Serializable{
    private static final long serialVersionUID = -3012858995760993125L;
    
    private String nombre;
    private double combustible;
    private double costoKM;
    private double capacidad;
    private double velocidad;
    private String motor;
    private boolean catering;

    public Avion(String nombre, double combustible, double costoKM, double capacidad, double velocidad, String motor,
            boolean catering) {
        this.nombre = nombre;
        this.combustible = combustible;
        this.costoKM = costoKM;
        this.capacidad = capacidad;
        this.velocidad = velocidad;
        this.motor = motor;
        this.catering = catering;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCombustible() {
        return this.combustible;
    }

    public double getCostoKM() {
        return this.costoKM;
    }

    public double getCapacidad() {
        return this.capacidad;
    }

    public double getVelocidad() {
        return this.velocidad;
    }

    public String getMotor() {
        return this.motor;
    }

    public boolean hasCatering() {
        return this.catering;
    }

}