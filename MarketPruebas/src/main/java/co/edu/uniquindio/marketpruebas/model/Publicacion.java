package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.IInteractuar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Publicacion implements IInteractuar {
    private LocalDate fechaPublicacion;
    private LocalTime horaPublicacion;
    private Producto producto;
    private List<Comentario> listaComentarios;
    private int numMeGustas;

    public Publicacion(LocalDate fechaPublicacion, LocalTime horaPublicacion, Producto producto) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.producto = producto;
        this.numMeGustas = 0;
        listaComentarios = new ArrayList<Comentario>();
    }
    public Publicacion() {
        listaComentarios = new ArrayList<Comentario>();
        this.numMeGustas = 0;
    }

    /**
     * Metodo para agregar un comentario a la publicacion
     * @param comentario
     */
    public void agregarComentario(Comentario comentario) {
        listaComentarios.add(comentario);
    }
    /**
     * SECCION GETTERS Y SETTERS
     */
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalTime getHoraPublicacion() {
        return horaPublicacion;
    }

    public void setHoraPublicacion(LocalTime horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public int getNumMeGustas() {
        return numMeGustas;
    }

    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }

    @Override
    public void agregarMeGusta() {
        numMeGustas++;
    }
}
