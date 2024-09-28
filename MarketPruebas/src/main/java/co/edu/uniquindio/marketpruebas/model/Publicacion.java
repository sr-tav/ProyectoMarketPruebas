package co.edu.uniquindio.marketpruebas.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Publicacion {
    private LocalDate fechaPublicacion;
    private LocalTime horaPublicacion;
    private Producto producto;
    private List<Comentario> listaComentarios;
    private int numMeGustas;
}
