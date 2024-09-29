package co.edu.uniquindio.marketpruebas.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Mensaje {
    private Usuario usuario;
    private LocalDate fecha;
    private LocalTime hora;
    private String mensaje;

    public Mensaje(Usuario usuario, LocalDate fecha, LocalTime hora, String mensaje) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
    }
    public Mensaje() {}


}
