package App.Data;

import java.util.Date;

public class Viaje {

    final private Date fecha;
    final private String origen;
    final private String destino;
    final private int acompañantes;
    final private Avion avion;

    public Viaje(Date fecha, String origen, String destino, int acompañantes, Avion avion) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.acompañantes = acompañantes;
        this.avion = avion;
    }


    public Date getFecha() {
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