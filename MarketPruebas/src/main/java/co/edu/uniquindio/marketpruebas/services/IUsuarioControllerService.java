package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;

public interface IUsuarioControllerService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarUsuario(UsuarioDto usuario);
}
