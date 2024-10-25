package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.model.Usuario;

import java.util.List;

public interface IUsuarioControllerService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarUsuario(UsuarioDto usuario);
    public Usuario getUsuarioCompleto(UsuarioDto usuario);
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario);
}
