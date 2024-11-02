package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.ComentarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

import java.util.List;

public interface IPublicacionControllerServices {
    void darMeGusta(UsuarioDto usuario, String idVendedor, PublicacionDto publicacion);
    boolean agregarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    List<VendedorDto> getListaMeGustas(String idVendedor, PublicacionDto dto);
    List<ComentarioDto> getListaComentarios(String idPublicacion, PublicacionDto dto);
    boolean agregarComentario(ComentarioDto dto, PublicacionDto publicacion);
    void darLikeComentario(ComentarioDto dto, PublicacionDto publicacion);
    int getLikesComentario(ComentarioDto dto, PublicacionDto publicacion);
}
