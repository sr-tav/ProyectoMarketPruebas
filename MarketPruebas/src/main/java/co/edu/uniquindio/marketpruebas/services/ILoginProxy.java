package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;

public interface ILoginProxy {
    boolean validarUsuario(UsuarioDto usuario);
}
