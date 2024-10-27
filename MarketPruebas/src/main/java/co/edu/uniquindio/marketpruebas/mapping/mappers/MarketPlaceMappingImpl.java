package co.edu.uniquindio.marketpruebas.mapping.mappers;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.*;
import co.edu.uniquindio.marketpruebas.services.IMarketPlaceMapping;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class MarketPlaceMappingImpl implements IMarketPlaceMapping {
    ModelFactory modelFactory;

    public MarketPlaceMappingImpl() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * ////////////////////////////////////////////////// CONVERTIDOR CLASES ////////////////////////////////////////////////////////////
     */

    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        Vendedor vendedor = (Vendedor) usuario;

        if(usuario instanceof Vendedor){
            VendedorDto vendedorDto = new VendedorDto(
                    vendedor.getIdVendedor(),
                    vendedor.getNombre(),
                    vendedor.getCedula(),
                    vendedor.getDireccion(),
                    vendedor.getUsuario(),
                    vendedor.getPassword(),
                    vendedor.getApellido()
            );
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

            vendedor.setListaProductos(productosDtoToProductos(modelFactory.getListaProductosDto(vendedorDto.getIdVendedor())));
            vendedor.setMuro(muroDtoToMuro(vendedorDto.getMuro()));
            vendedor.setListaContactos(modelFactory.getListaContactos(vendedorDto.getIdVendedor()));

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

        publi.setListaComentarios(modelFactory.getListaComentarios(publicacion.getIdVendedor()));
        publi.setListaMegustas(modelFactory.getListaMeGusta(publicacion.getIdVendedor()));

        return publi;
    }

    @Override
    public PublicacionDto publicacionToPublicacionDto(Publicacion publicacion) {

        PublicacionDto dto = new PublicacionDto();
        dto.setDescripcion(publicacion.getDescripcion());
        dto.setFechaPublicacion(publicacion.getFechaPublicacion());
        dto.setProducto(productoToProductoDto(publicacion.getProducto()));
        dto.setHoraPublicacion(publicacion.getHoraPublicacion());
        dto.setIdVendedor(publicacion.getIdVendedor());
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
        dto.setIdVendedor(producto.getIdVendedor());
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

        m.setIdVendedor(muro.getIdVendedor());
        //m.setListaChats();
        m.setListaPublicaciones(modelFactory.getListaPublicaciones(muro.getIdVendedor()));
        return m;
    }

    @Override
    public MuroDto muroToMuroDto(Muro muro) {
        MuroDto muroDto = new MuroDto();
        muroDto.setIdVendedor(muro.getIdVendedor());
        return muroDto;
    }

    /**
     * ///////////////////////////////////////////////////// CONVERTIDOR LISTAS /////////////////////////////////////////////////////////////////
     */
    @Override
    public List<VendedorDto>  VendedoresToVendedoresDto(List<Vendedor> vendedores) {
        List<VendedorDto> vendedoresDto = new ArrayList<VendedorDto>();
        for (Vendedor vendedor : vendedores) {
            vendedoresDto.add((VendedorDto) usuarioToUsuarioDto(vendedor));
        }
        return vendedoresDto;
    }
    @Override
    public List<Vendedor>  VendedoresDtoToVendedores(List<VendedorDto> dto) {
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        for (VendedorDto vendedor : dto) {
            vendedores.add((Vendedor) usuarioDtoToUsuario(vendedor));
        }
        return vendedores;
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
    public List<Comentario> comentariosDtoToComentarios(List<ComentarioDto> comentariosDto) {
        List<Comentario> comentarios = new ArrayList<>();
        for (ComentarioDto comentarioDto : comentariosDto) {
            comentarios.add(comentarioDtoToComentario(comentarioDto));
        }
        return comentarios;
    }

    @Override
    public List<ComentarioDto> comentariosToComentariosDto(List<Comentario> comentarios) {
        List<ComentarioDto> dtos = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            dtos.add(comentarioToComentarioDto(comentario));
        }
        return dtos;
    }

    @Override
    public List<Mensaje> mensajesDtoToMensajes(List<MensajeDto> mensajesDto) {
        List<Mensaje> mensajes = new ArrayList<>();
        for (MensajeDto mensajeDto : mensajesDto) {
            mensajes.add(mesajeDtoToMensaje(mensajeDto));
        }
        return mensajes;
    }

    @Override
    public List<MensajeDto> mensajeToMensajesDto(List<Mensaje> mensajes) {
        List<MensajeDto> mensajesDto = new ArrayList<>();
        for (Mensaje mensaje : mensajes) {
            mensajesDto.add(mensajeToMensajeDto(mensaje));
        }
        return mensajesDto;
    }


}
