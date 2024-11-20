package co.edu.uniquindio.marketpruebas.controller.Proxy;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.services.ILoginProxy;


public class ProxyLogin implements ILoginProxy {

    public ProxyLogin() {modelFactory = ModelFactory.getInstance();}

    private ModelFactory modelFactory;

    @Override
    public boolean validarUsuario(UsuarioDto usuario) {
        if (modelFactory.validarLogin(usuario)) {
            return true;
        }
        return false;
    }
}
