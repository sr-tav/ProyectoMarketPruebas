package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Comentario;
import co.edu.uniquindio.marketpruebas.model.Producto;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PublicacionDto {
    private LocalDate fechaPublicacion;
    private LocalTime horaPublicacion;
    private String descripcion;
    private ProductoDto producto;
    private List<Comentario> listaComentarios;
    private List<Vendedor> listaMegustas;

    public PublicacionDto() {
        listaComentarios = new ArrayList<>();
        listaMegustas = new ArrayList<>();
    }

    public PublicacionDto(LocalDate fechaPublicacion, LocalTime horaPublicacion, String descripcion, ProductoDto producto) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.descripcion = descripcion;
        this.producto = producto;
        listaComentarios = new ArrayList<>();
        listaMegustas = new ArrayList<>();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public List<Vendedor> getListaMegustas() {
        return listaMegustas;
    }

    public void setListaMegustas(List<Vendedor> listaMegustas) {
        this.listaMegustas = listaMegustas;
    }
}
