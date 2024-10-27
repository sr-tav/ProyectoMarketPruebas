package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;

import java.util.List;

public interface IMuroControllerServices {
    List<PublicacionDto> getListaPublicaciones(UsuarioDto usuario);
}
