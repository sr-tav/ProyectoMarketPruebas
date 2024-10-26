package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import co.edu.uniquindio.marketpruebas.services.IPublicacionControllerServices;

import java.util.List;

public class PublicacionController implements IPublicacionControllerServices {
    ModelFactory modelFactory;

    public PublicacionController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public void darMeGusta(UsuarioDto usuario, PublicacionDto publicacion) {
        modelFactory.darMeGustaPublicacion(usuario, publicacion);
    }

    @Override
    public List<PublicacionDto> getPublicacionesDto() {
        return List.of();
    }

    @Override
    public boolean agregarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.agregarPublicacion(publicacion, vendedor);
    }

    @Override
    public boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.eliminarPublicacion(publicacion, vendedor);
    }

    @Override
    public boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.actualizarPublicacion(publicacion, vendedor);
    }
}
