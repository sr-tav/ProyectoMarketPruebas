package co.edu.uniquindio.marketpruebas.model;

import javafx.scene.image.Image;

public class Producto {
    private String nombre;
    private Image imagen;
    private String categoria;
    private Estado estado;
    private double precio;

    public Producto(String nombre, String rutaImagen, String categoria, Estado estado, double precio) {
        this.nombre = nombre;
        this.imagen = new Image(rutaImagen);
        this.categoria = categoria;
        this.estado = estado;
        this.precio = precio;
    }
    public Producto(){}
    /**
     * SECCION GETTERS Y SETTERS
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
