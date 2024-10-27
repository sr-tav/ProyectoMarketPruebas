package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.*;

import java.util.List;

public interface IModelFactoryService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarLogin(UsuarioDto usuario);
    public Usuario getUsuarioCompleto(UsuarioDto usuario);
    public void darMeGustaPublicacion(UsuarioDto usuario, String idVendedor);
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario);
    boolean agregarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    List<PublicacionDto> getListaPublicaciones(Muro muro);

    //Retorno de listas asociadas a clases
    List<ProductoDto> getListaProductosDto(String id);

    List<VendedorDto> getListaContactosDto(String id);
    List<Vendedor> getListaContactos(String id);

    List<Comentario> getListaComentarios(String idVendedor);
    List<ComentarioDto> getListaComentariosDto(String idVendedor);

    List<Vendedor> getListaMeGusta(String idVendedor);
    List<VendedorDto> getListaMeGustaDto(String idVendedor);

    List<Publicacion> getListaPublicaciones(String idVendedor);
    List<PublicacionDto> getListaPublicacionesDto(String idVendedor);



}
