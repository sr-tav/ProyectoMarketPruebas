package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Usuario;

public interface IModelFactoryService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarLogin(UsuarioDto usuario);
    public Usuario getUsuarioCompleto(UsuarioDto usuario);
    public void darMeGustaPublicacion(UsuarioDto usuario, Publicacion publicacion);
}
