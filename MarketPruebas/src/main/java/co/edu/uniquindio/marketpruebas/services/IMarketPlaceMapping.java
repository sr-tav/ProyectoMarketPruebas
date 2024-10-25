package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.*;

import java.util.List;

public interface IMarketPlaceMapping {
    //Convertidor clases
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    Publicacion publicacionDtoToPublicacion(PublicacionDto publicacion);
    PublicacionDto publicacionToPublicacionDto(Publicacion publicacion);

    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);

    Mensaje mesajeDtoToMensaje(Mensaje mensaje);
    MensajeDto mensajeToMensajeDto(MensajeDto mensaje);

    Comentario comentarioDtoToComentario(Comentario comentario);
    ComentarioDto comentarioToComentarioDto(ComentarioDto comentario);


    //Convertidor listas
    List<? extends UsuarioDto> UsuariosToUsuariosDto(List<? extends Usuario> usuarios);
    List<? extends Usuario> UsuariosDtoToUsuarios(List<? extends UsuarioDto> usuariosDto);

    List<PublicacionDto> publicacionesToPublicacionesDto(List<Publicacion> publicaciones);
    List<Publicacion> publicacionesDtoToPublicaciones(List<PublicacionDto> publicacionesDto);

    List<ProductoDto> productosToProductosDto(List<Producto> productos);
    List<Producto> productosDtoToProductos(List<ProductoDto> productosDto);

    List<? extends MensajeDto> mensajesToMensajesDto(List<? extends Mensaje> mensajes);
    List<? extends Mensaje> mensajesDtoToMensajes(List<? extends MensajeDto> mensajesDto);

    List<? extends ComentarioDto> comentariosToComentariosDto(List<? extends Comentario> comentarios);
    List<? extends Comentario> comentariosDtosToComentarios(List<? extends ComentarioDto> comentariosDto);


}
