package App.Data;

public abstract class Avion {
    private double combustible;
    private double costo;
    private double capacidad;
    private double velocidad;
    private String motor;

    public Avion(double combustible, double costo, double capacidad, double velocidad, String motor) {
        this.combustible = combustible;
        this.costo = costo;
        this.capacidad = capacidad;
        this.velocidad = velocidad;
        this.motor = motor;
    }

    public double getCombustible() {
        return this.combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCapacidad() {
        return this.capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public double getVelocidad() {
        return this.velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public String getMotor() {
        return this.motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

}