package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.IProductoComposite;

import java.util.ArrayList;
import java.util.List;

public class Combo implements IProductoComposite {
    private List<IProductoComposite> productos = new ArrayList<>();

    public void agregarProducto(IProductoComposite producto) {
        productos.add(producto);
    }

    @Override
    public double obtenerPrecio() {
        double total = 0;
        for (IProductoComposite producto : productos) {
            total += producto.obtenerPrecio();
        }
        return total;
    }

    @Override
    public void mostrar() {
        System.out.println("Combo de productos: \n");
        for (IProductoComposite producto : productos) {
            producto.mostrar();
        }
    }
}
