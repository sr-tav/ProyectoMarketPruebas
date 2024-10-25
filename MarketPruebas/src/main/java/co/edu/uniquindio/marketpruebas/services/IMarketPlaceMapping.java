package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.model.Producto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Usuario;

import java.util.List;

public interface IMarketPlaceMapping {
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
    public List<UsuarioDto> getUsuariosDto(List<Usuario> usuarios);
    public Publicacion publicacionDtoToPublicacion(PublicacionDto publicacion);
    public PublicacionDto publicacionToPublicacionDto(Publicacion publicacion);
    public ProductoDto productoToProductoDto(Producto producto);
    public Producto productoDtoToProducto(ProductoDto productoDto);

}
