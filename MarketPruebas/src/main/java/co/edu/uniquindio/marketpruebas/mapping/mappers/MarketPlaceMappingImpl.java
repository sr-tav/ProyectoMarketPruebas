package co.edu.uniquindio.marketpruebas.mapping.mappers;

import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.*;
import co.edu.uniquindio.marketpruebas.services.IMarketPlaceMapping;

import java.util.*;

@SuppressWarnings("ALL")
public class MarketPlaceMappingImpl implements IMarketPlaceMapping {
    /**
     * ////////////////////////////////////////////////// CONVERTIDOR CLASES ////////////////////////////////////////////////////////////
     */
    private UsuarioDto usuarioToUsuarioDto(Usuario usuario, Set<Usuario> procesados) {
        if(procesados.contains(usuario)){
            return null;
        }
        procesados.add(usuario);

        if(usuario instanceof Vendedor){
            Vendedor vendedor = (Vendedor)usuario;
            VendedorDto vendedorDto = new VendedorDto(
                    vendedor.getIdVendedor(),
                    vendedor.getNombre(),
                    vendedor.getCedula(),
                    vendedor.getDireccion(),
                    vendedor.getUsuario(),
                    vendedor.getPassword(),
                    vendedor.getApellido()
            );

            vendedorDto.setListaContactos(UsuariosToUsuariosDto(vendedor.getListaContactos(),procesados));
            vendedorDto.setListaProductos(productosToProductosDto(vendedor.getListaProductos()));
            vendedorDto.setMuro(muroToMuroDto(vendedor.getMuro()));

            return vendedorDto;
        }else if(usuario instanceof Administrador){
            Administrador administrador = (Administrador)usuario;
            AdministradorDto administradorDto = new AdministradorDto(
                    administrador.getNombre(),
                    administrador.getApellido(),
                    administrador.getCedula(),
                    administrador.getDireccion(),
                    administrador.getUsuario(),
                    administrador.getPassword(),
                    administrador.getApellido()
            );
            return administradorDto;
        }
        return null;
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        if(usuarioDto instanceof VendedorDto){
            VendedorDto vendedorDto = (VendedorDto) usuarioDto;
            Vendedor vendedor = new Vendedor();
            vendedor.setIdVendedor(vendedorDto.getIdVendedor());
            vendedor.setNombre(vendedorDto.getNombre());
            vendedor.setApellido(vendedorDto.getApellido());
            vendedor.setCedula(vendedorDto.getCedula());
            vendedor.setDireccion(vendedorDto.getDireccion());
            vendedor.setUsuario(vendedorDto.getUsuario());
            vendedor.setPassword(vendedorDto.getPassword());

            vendedor.setListaProductos(productosDtoToProductos(vendedorDto.getListaProductos()));
            vendedor.setMuro(muroDtoToMuro(vendedorDto.getMuro()));
            vendedor.setListaContactos( UsuariosDtoToUsuarios(vendedorDto.getListaContactos()));

            return vendedor;

        }else if(usuarioDto instanceof AdministradorDto){
            AdministradorDto administradorDto = (AdministradorDto) usuarioDto;
            Administrador admin = new Administrador();
            admin.setIdAdmin(administradorDto.getIdAdmin());
            admin.setNombre(administradorDto.getNombre());
            admin.setApellido(administradorDto.getApellido());
            admin.setCedula(administradorDto.getCedula());
            admin.setDireccion(administradorDto.getDireccion());
            admin.setUsuario(administradorDto.getUsuario());
            admin.setPassword(administradorDto.getPassword());
            return admin;
        }
        return null;
    }

    @Override
    public Publicacion publicacionDtoToPublicacion(PublicacionDto publicacion) {

        Publicacion publi = new Publicacion();
        publi.setDescripcion(publicacion.getDescripcion());
        publi.setFechaPublicacion(publicacion.getFechaPublicacion());
        publi.setProducto(productoDtoToProducto(publicacion.getProducto()));
        publi.setHoraPublicacion(publicacion.getHoraPublicacion());

        publi.setListaComentarios(mensajesDtoToMensajes(publicacion.getListaComentarios()));
        publi.setListaMegustas(UsuariosDtoToUsuarios(publicacion.getListaMegustas()));

        return publi;
    }

    @Override
    public PublicacionDto publicacionToPublicacionDto(Publicacion publicacion) {

        PublicacionDto dto = new PublicacionDto();
        dto.setDescripcion(publicacion.getDescripcion());
        dto.setFechaPublicacion(publicacion.getFechaPublicacion());
        dto.setProducto(productoToProductoDto(publicacion.getProducto()));
        dto.setHoraPublicacion(publicacion.getHoraPublicacion());

        dto.setListaComentarios(mensajesToMensajesDto(publicacion.getListaComentarios()));
        //dto.setListaMegustas(UsuariosToUsuariosDto(publicacion.getListaMegustas()));

        return dto;
    }

    @Override
    public ProductoDto productoToProductoDto(Producto producto) {
        ProductoDto dto = new ProductoDto();
        dto.setCategoria(producto.getCategoria());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setImagen(producto.getImagen());
        dto.setEstado(producto.getEstado());
        return dto;
    }

    @Override
    public Producto productoDtoToProducto(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setCategoria(productoDto.getCategoria());
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        producto.setImagen(productoDto.getImagen());
        producto.setEstado(productoDto.getEstado());
        return producto;
    }

    @Override
    public Mensaje mesajeDtoToMensaje(MensajeDto mensaje) {
        Mensaje m = new Mensaje();
        m.setMensaje(mensaje.getMensaje());
        m.setUsuario(usuarioDtoToUsuario(mensaje.getUsuario()));
        m.setFecha(mensaje.getFecha());
        m.setHora(mensaje.getHora());
        return m;
    }

    @Override
    public MensajeDto mensajeToMensajeDto(Mensaje mensaje) {
        MensajeDto dto = new MensajeDto();
        dto.setMensaje(mensaje.getMensaje());
        dto.setUsuario(usuarioToUsuarioDto(mensaje.getUsuario()));
        dto.setFecha(mensaje.getFecha());
        dto.setHora(mensaje.getHora());
        return dto;
    }

    @Override
    public Comentario comentarioDtoToComentario(ComentarioDto comentario) {
        Comentario com = new Comentario();
        com.setFecha(comentario.getFecha());
        com.setHora(comentario.getHora());
        com.setMensaje(comentario.getMensaje());
        com.setUsuario(usuarioDtoToUsuario(comentario.getUsuario()));
        com.setNumMeGustas(comentario.getNumMeGustas());
        return com;
    }

    @Override
    public ComentarioDto comentarioToComentarioDto(Comentario comentario) {
        ComentarioDto dto = new ComentarioDto();
        dto.setFecha(comentario.getFecha());
        dto.setFecha(comentario.getFecha());
        dto.setUsuario(usuarioToUsuarioDto(comentario.getUsuario()));
        dto.setMensaje(comentario.getMensaje());
        dto.setNumMeGustas(comentario.getNumMeGustas());
        return dto;
    }

    @Override
    public Muro muroDtoToMuro(MuroDto muro) {
        Muro m = new Muro();
        //m.setListaChats();
        m.setListaPublicaciones(publicacionesDtoToPublicaciones(muro.getListaPublicaciones()));
        return m;
    }

    @Override
    public MuroDto muroToMuroDto(Muro muro) {
        MuroDto muroDto = new MuroDto();
        //muroDto.setListaChats();
        muroDto.setListaPublicaciones(publicacionesToPublicacionesDto(muro.getListaPublicaciones()));
        return muroDto;
    }

    /**
     * ///////////////////////////////////////////////////// CONVERTIDOR LISTAS /////////////////////////////////////////////////////////////////
     */
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario){
        return usuarioToUsuarioDto(usuario, new HashSet<>());
    }
    @Override
    public <T extends UsuarioDto> List<T> UsuariosToUsuariosDto(List<? extends Usuario> usuarios, Set<Usuario> procesados) {
        List<T> dtos = new ArrayList<>();
        for (Usuario u : usuarios) {
            UsuarioDto dto = usuarioToUsuarioDto(u, procesados);
            if(dto != null){
                dtos.add((T) dto);
            }
        }
        return dtos;
    }

    @Override
    public <T extends Usuario> List<T> UsuariosDtoToUsuarios(List<? extends UsuarioDto> usuariosDto) {
        List<T> usuarios = new ArrayList<>();
        for (UsuarioDto u : usuariosDto) {
            if (u instanceof VendedorDto){
                usuarios.add((T)usuarioDtoToUsuario(u));
            } else if (u instanceof AdministradorDto) {
                usuarios.add((T)usuarioDtoToUsuario(u));
            }
        }
        return usuarios;
    }

    @Override
    public List<PublicacionDto> publicacionesToPublicacionesDto(List<Publicacion> publicaciones) {
        List<PublicacionDto> dtos = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            dtos.add(publicacionToPublicacionDto(p));
        }
        return dtos;
    }

    @Override
    public List<Publicacion> publicacionesDtoToPublicaciones(List<PublicacionDto> publicacionesDto) {
        List<Publicacion> publicaciones = new ArrayList<>();
        for (PublicacionDto p : publicacionesDto) {
            publicaciones.add(publicacionDtoToPublicacion(p));
        }
        return publicaciones;
    }

    @Override
    public List<ProductoDto> productosToProductosDto(List<Producto> productos) {
        List<ProductoDto> dtos = new ArrayList<>();
        for (Producto p : productos) {
            dtos.add(productoToProductoDto(p));
        }
        return dtos;
    }

    @Override
    public List<Producto> productosDtoToProductos(List<ProductoDto> productosDto) {
        List<Producto> productos = new ArrayList<>();
        for (ProductoDto productoDto : productosDto) {
            productos.add(productoDtoToProducto(productoDto));
        }
        return productos;
    }

    @Override
    public <T extends MensajeDto> List<T> mensajesToMensajesDto(List<? extends Mensaje> mensajes) {
        List<T> dtos = new ArrayList<>();
        for (Mensaje m : mensajes) {
            if(m instanceof Comentario){
                dtos.add((T)comentarioToComentarioDto((Comentario) m));
            } else if (m instanceof Mensaje) {
                dtos.add((T)mensajeToMensajeDto(m));
            }
        }
        return dtos;
    }

    @Override
    public <T extends Mensaje> List<T> mensajesDtoToMensajes(List<? extends MensajeDto> mensajesDto) {
        List<T> mensajes = new ArrayList<>();
        for (MensajeDto m : mensajesDto) {
            if (m instanceof MensajeDto){
                mensajes.add((T)mesajeDtoToMensaje(m));
            }else if(m instanceof ComentarioDto){
                mensajes.add((T)comentarioDtoToComentario((ComentarioDto) m));
            }
        }
        return mensajes;
    }

}
