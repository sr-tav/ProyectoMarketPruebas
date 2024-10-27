package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.services.IMuroControllerServices;

import java.util.List;

public class MuroController implements IMuroControllerServices {
    ModelFactory modelFactory;

    public MuroController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public List<PublicacionDto> getListaPublicaciones(UsuarioDto usuario) {
        return modelFactory.getListaPublicacionesDto(((VendedorDto)usuario).getIdVendedor());
    }
}
