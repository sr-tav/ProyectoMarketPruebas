package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.Observer;

public class VendedorObserver implements Observer {
    private Producto producto;

    public VendedorObserver(Producto producto) {
        this.producto = producto;
        this.producto.agregarObserver(this);
    }
    @Override
    public void actualizar(double precio) {

    }
}
