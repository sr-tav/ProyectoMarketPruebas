package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;

public interface IModelFactoryService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarLogin(UsuarioDto usuario);
}
