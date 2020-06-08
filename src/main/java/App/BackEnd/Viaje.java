package App.BackEnd;

import java.time.LocalDate;

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