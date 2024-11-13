package co.edu.uniquindio.marketpruebas.model.Decorator;

import co.edu.uniquindio.marketpruebas.services.IPublicacionDecorator;

public class DescuentoDecorator extends PublicacionDecorator{
    public DescuentoDecorator(IPublicacionDecorator publicacion) {
        super(publicacion);
    }

    @Override
    public String getDescripcion() {
        return publicacion.getDescripcion()+", Solo por tiempo limitado un descuento del: 50%";
    }
}
