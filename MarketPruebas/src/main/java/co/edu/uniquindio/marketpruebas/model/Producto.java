package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.IProductoComposite;
import co.edu.uniquindio.marketpruebas.services.Observer;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Producto implements IProductoComposite {
    private String idVendedor;
    private String nombre;
    private Image imagen;
    private String categoria;
    private Estado estado;
    private double precio;
    private List<Observer> observers = new ArrayList();

    public Producto(String nombre, String rutaImagen, String categoria, Estado estado, double precio) {
        this.nombre = nombre;
        this.imagen = new Image(getClass().getResource(rutaImagen).toString());
        this.categoria = categoria;
        this.estado = estado;
        this.precio = precio;
    }
    public void agregarObserver(Observer o) {
        observers.add(o);
    }
    public void eliminarObserver(Observer o) {
        observers.remove(o);
    }
    public void setPrecioNotify(double precio){
        this.precio = precio;
        notificarObservers();
    }
    public void notificarObservers(){
        for(Observer o : observers){
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

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    @Override
    public double obtenerPrecio() {
        return precio;
    }

    @Override
    public void mostrar() {
        System.out.println("Producto: " + nombre + " - Precio: " + precio);
    }
}
