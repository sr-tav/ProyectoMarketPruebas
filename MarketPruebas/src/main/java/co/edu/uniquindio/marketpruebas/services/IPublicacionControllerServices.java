package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;

public interface IPublicacionControllerServices {
    void darMeGusta(UsuarioDto usuario, Publicacion publicacion);
}
