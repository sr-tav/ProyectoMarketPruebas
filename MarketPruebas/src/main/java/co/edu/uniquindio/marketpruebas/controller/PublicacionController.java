package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.ComentarioDto;
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
    public void darMeGusta(UsuarioDto usuario, String idVendedor) {
        modelFactory.darMeGustaPublicacion(usuario, idVendedor);
    }

    @Override
    public List<VendedorDto> getListaMeGustas(String id) {
        return modelFactory.getListaMeGustaDto(id);
    }

    @Override
    public List<ComentarioDto> getListaComentarios(String id) {
        return modelFactory.getListaComentariosDto(id);
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
