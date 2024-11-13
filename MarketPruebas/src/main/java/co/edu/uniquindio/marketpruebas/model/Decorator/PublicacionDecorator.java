package co.edu.uniquindio.marketpruebas.model.Decorator;

import co.edu.uniquindio.marketpruebas.services.IPublicacionDecorator;

public abstract class PublicacionDecorator implements IPublicacionDecorator {
    protected IPublicacionDecorator publicacion;

    public PublicacionDecorator(IPublicacionDecorator publicacion) {
        this.publicacion = publicacion;
    }

    public String getDescripcion() {
        return publicacion.getDescripcion();
    }

}
