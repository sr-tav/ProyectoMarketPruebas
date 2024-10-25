package co.edu.uniquindio.marketpruebas.mapping.mappers;

import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.*;
import co.edu.uniquindio.marketpruebas.services.IMarketPlaceMapping;

import java.util.List;

public class MarketPlaceMappingImpl implements IMarketPlaceMapping {
    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
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
            vendedorDto.setListaContactos(vendedor.getListaContactos());
            vendedorDto.setListaProductos(vendedor.getListaProductos());
            vendedorDto.setMuro(vendedor.getMuro());
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
            vendedor.setListaProductos(vendedorDto.getListaProductos());
            vendedor.setMuro(vendedorDto.getMuro());
            vendedor.setListaContactos(vendedorDto.getListaContactos());
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
    public List<UsuarioDto> getUsuariosDto(List<Usuario> usuarios) {
        return List.of();
    }

    @Override
    public Publicacion publicacionDtoToPublicacion(PublicacionDto publicacion) {
        Publicacion publi = new Publicacion();
        publi.setDescripcion(publicacion.getDescripcion());
        publi.setFechaPublicacion(publicacion.getFechaPublicacion());
        publi.setProducto(productoDtoToProducto(publicacion.getProducto()));
        publi.setListaComentarios(publicacion.getListaComentarios());
        publi.setListaMegustas(publicacion.getListaMegustas());
        publi.setHoraPublicacion(publicacion.getHoraPublicacion());
        return publi;
    }

    @Override
    public PublicacionDto publicacionToPublicacionDto(Publicacion publicacion) {
        PublicacionDto dto = new PublicacionDto();
        dto.setDescripcion(publicacion.getDescripcion());
        dto.setFechaPublicacion(publicacion.getFechaPublicacion());
        dto.setProducto(productoToProductoDto(publicacion.getProducto()));
        dto.setListaComentarios(publicacion.getListaComentarios());
        dto.setListaMegustas(publicacion.getListaMegustas());
        dto.setHoraPublicacion(publicacion.getHoraPublicacion());
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

}
