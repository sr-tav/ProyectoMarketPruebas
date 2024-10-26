package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Comentario;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComentarioDto extends MensajeDto{
    private int numMeGustas;

    public ComentarioDto(UsuarioDto usuario , LocalDate fecha , LocalTime hora , String mensaje, int numMeGustas ) {
        super(usuario,fecha,hora,mensaje);
        this.numMeGustas = numMeGustas;
    }
    public ComentarioDto() {}

    public int getNumMeGustas() {
        return numMeGustas;
    }

    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }
}
