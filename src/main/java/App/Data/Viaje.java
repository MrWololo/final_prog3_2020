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

}