package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.model.Usuario;

import java.util.List;

public interface IMarketPlaceMapping {
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
    public List<UsuarioDto> getUsuariosDto(List<Usuario> usuarios);
}
