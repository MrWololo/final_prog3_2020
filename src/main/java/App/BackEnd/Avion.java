package App.BackEnd;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import App.Data.Provider;
import App.TableUtils.TableAdapter;

//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonSerializer;

/*@JsonSubTypes({
    @JsonSubTypes.Type(value = Gold.class, name = "Gold"),
    @JsonSubTypes.Type(value = Silver.class, name = "Silver"),
    @JsonSubTypes.Type(value = Bronze.class, name = "Bronze")
})*/

public abstract class Avion implements Serializable, TableAdapter {

    private static final long serialVersionUID = -3565962209839080101L;
    private String nombre;
    private double combustible;
    private double costoKM;
    private int capacidad;
    private double velocidad;
    private String motor;
    private boolean catering;
    private double tarifaTipoAvion;

    public Avion() {
    }

    public Avion(String nombre, double combustible, double costoKM, int capacidad, double velocidad, String motor,
            boolean catering, double tarifaTipoAvion) {
        this.nombre = nombre;
        this.combustible = combustible;
        this.costoKM = costoKM;
        this.capacidad = capacidad;
        this.velocidad = velocidad;
        this.motor = motor;
        this.catering = catering;
        this.tarifaTipoAvion = tarifaTipoAvion;
    }

    @JsonIgnore
    public String[] getValuesString() {
        String[] array = { getNombre().toString(), String.valueOf(getCombustible()), String.valueOf(getCostoKM()),
                String.valueOf(getCapacidad()), String.valueOf(getVelocidad()), getMotor().toString(),
                hasCatering() ? "Con Catering" : "Sin Catering" };

        return array;
    }

    @JsonIgnore
    public double getValorTotal(Viaje viaje, String curso) {
        return (getCostoKM() * Provider.getDistancias().get(curso)) + ((viaje.getAcompañantes()) * 3500)
                + (getTarifaTipoAvion());
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

    public int getCapacidad() {
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

    public double getTarifaTipoAvion() {
        return tarifaTipoAvion;
    }

}