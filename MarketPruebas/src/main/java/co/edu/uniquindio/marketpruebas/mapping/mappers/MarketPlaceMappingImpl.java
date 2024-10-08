package co.edu.uniquindio.marketpruebas.mapping.mappers;

import co.edu.uniquindio.marketpruebas.mapping.dto.AdministradorDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Administrador;
import co.edu.uniquindio.marketpruebas.model.Usuario;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
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
}
