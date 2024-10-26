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
    List<VendedorDto> VendedoresToVendedoresDto(List<Vendedor> vendedores);
    <T extends Usuario> List<T> UsuariosDtoToUsuarios(List<? extends UsuarioDto> usuariosDto);

    List<PublicacionDto> publicacionesToPublicacionesDto(List<Publicacion> publicaciones);
    List<Publicacion> publicacionesDtoToPublicaciones(List<PublicacionDto> publicacionesDto);

    List<ProductoDto> productosToProductosDto(List<Producto> productos);
    List<Producto> productosDtoToProductos(List<ProductoDto> productosDto);

    List<Comentario> comentariosDtoToComentarios(List<ComentarioDto> comentariosDto);
    List<ComentarioDto> comentariosToComentariosDto(List<Comentario> comentarios);

    List<Mensaje> mensajesDtoToMensajes(List<MensajeDto> mensajesDto);
    List<MensajeDto> mensajeToMensajesDto(List<Mensaje> mensajes);



}
