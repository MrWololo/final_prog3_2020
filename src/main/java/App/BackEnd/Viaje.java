package App.BackEnd;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Viaje {

    private LocalDate fecha;

    private String origen;

    private String destino;

    private int acompañantes;

    private Avion avion;

    public Viaje() {
    }

    public Viaje(LocalDate fecha, String origen, String destino, int acompañantes, Avion avion) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.acompañantes = acompañantes;
        this.avion = avion;
    }

    @JsonIgnore
    public String[] getValuesString() {
        String[] array = { getFecha().toString(), getOrigen().toString(), getDestino().toString(),
                String.valueOf(getAcompañantes()), getAvion().getNombre() };

        return array;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getOrigen() {
        return this.origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public int getAcompañantes() {
        return this.acompañantes;
    }

    public Avion getAvion() {
        return this.avion;
    }

}