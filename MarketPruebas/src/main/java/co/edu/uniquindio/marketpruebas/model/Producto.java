package co.edu.uniquindio.marketpruebas.model;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String idVendedor;
    private String nombre;
    private Image imagen;
    private String categoria;
    private Estado estado;
    private double precio;
    private List<VendedorObserver> observers = new ArrayList();

    public Producto(String nombre, String rutaImagen, String categoria, Estado estado, double precio) {
        this.nombre = nombre;
        this.imagen = new Image(getClass().getResource(rutaImagen).toString());
        this.categoria = categoria;
        this.estado = estado;
        this.precio = precio;
    }
    public void agregarObserver(VendedorObserver o) {
        observers.add(o);
    }
    public void eliminarObserver(VendedorObserver o) {
        observers.remove(o);
    }
    public void setPrecioNotify(double precio){
        this.precio = precio;
        notificarObservers();
    }
    public void notificarObservers(){
        for(VendedorObserver o : observers){
            o.actualizar(precio);
        }
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

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
}
