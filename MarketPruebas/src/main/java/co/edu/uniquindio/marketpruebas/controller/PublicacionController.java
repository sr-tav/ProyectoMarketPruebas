package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.services.IPublicacionControllerServices;

public class PublicacionController implements IPublicacionControllerServices {
    ModelFactory modelFactory;

    public PublicacionController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public void darMeGusta(UsuarioDto usuario, Publicacion publicacion) {
        modelFactory.darMeGustaPublicacion(usuario, publicacion);
    }
}
