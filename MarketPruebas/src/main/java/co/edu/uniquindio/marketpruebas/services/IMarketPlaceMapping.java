package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.*;

import java.util.List;
import java.util.Set;

public interface IMarketPlaceMapping {
    //Convertidor clases
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    Publicacion publicacionDtoToPublicacion(PublicacionDto publicacion);
    PublicacionDto publicacionToPublicacionDto(Publicacion publicacion);

    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);

    Mensaje mesajeDtoToMensaje(MensajeDto mensaje);
    MensajeDto mensajeToMensajeDto(Mensaje mensaje);

    Comentario comentarioDtoToComentario(ComentarioDto comentario);
    ComentarioDto comentarioToComentarioDto(Comentario comentario);

    Muro muroDtoToMuro(MuroDto muro);
    MuroDto muroToMuroDto(Muro muro);


    //Convertidor listas
    <T extends UsuarioDto> List<T> UsuariosToUsuariosDto(List<? extends Usuario> usuarios, Set<Usuario> procesados);
    <T extends Usuario> List<T> UsuariosDtoToUsuarios(List<? extends UsuarioDto> usuariosDto);

    List<PublicacionDto> publicacionesToPublicacionesDto(List<Publicacion> publicaciones);
    List<Publicacion> publicacionesDtoToPublicaciones(List<PublicacionDto> publicacionesDto);

    List<ProductoDto> productosToProductosDto(List<Producto> productos);
    List<Producto> productosDtoToProductos(List<ProductoDto> productosDto);

    <T extends MensajeDto> List<T> mensajesToMensajesDto(List<? extends Mensaje> mensajes);
    <T extends Mensaje> List<T> mensajesDtoToMensajes(List<? extends MensajeDto> mensajesDto);


}
