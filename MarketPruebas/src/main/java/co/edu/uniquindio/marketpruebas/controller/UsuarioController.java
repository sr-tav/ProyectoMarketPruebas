package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.services.IUsuarioControllerService;

public class UsuarioController implements IUsuarioControllerService {
    ModelFactory modelFactory;

    public UsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        return modelFactory.getUsuario(usuario);
    }

    @Override
    public boolean validarUsuario(UsuarioDto usuario) {
        if (modelFactory.validarLogin(usuario)) {
            return true;
        }
        return false;
    }
}
