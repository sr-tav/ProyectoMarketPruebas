package co.edu.uniquindio.marketpruebas.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Comentario extends Mensaje{
    private int numMeGustas;

    public Comentario(Usuario usuario, LocalDate fecha, LocalTime hora, String mensaje, int numMeGustas) {
        super(usuario, fecha, hora, mensaje);
        this.numMeGustas = numMeGustas;
    }
    public Comentario(){}
    /**
     * SECCION GETTERS Y SETTERS
     */
    public int getNumMeGustas() {
        return numMeGustas;
    }
    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }
}
