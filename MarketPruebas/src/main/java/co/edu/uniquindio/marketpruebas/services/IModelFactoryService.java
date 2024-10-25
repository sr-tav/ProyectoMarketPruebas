package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Muro;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Usuario;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

import java.util.List;

public interface IModelFactoryService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarLogin(UsuarioDto usuario);
    public Usuario getUsuarioCompleto(UsuarioDto usuario);
    public void darMeGustaPublicacion(UsuarioDto usuario, Publicacion publicacion);
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario);
    boolean agregarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    List<PublicacionDto> getListaPublicaciones(Muro muro);


}
