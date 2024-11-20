package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Usuario;
import co.edu.uniquindio.marketpruebas.services.ILoginProxy;
import co.edu.uniquindio.marketpruebas.services.IUsuarioControllerService;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService{
    ModelFactory modelFactory;

    public UsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        return modelFactory.getUsuario(usuario);
    }

    @Override
    public Usuario getUsuarioCompleto(UsuarioDto usuario) {
        return modelFactory.getUsuarioCompleto(usuario);
    }

    @Override
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario) {
        return modelFactory.getListaProductosDisponibles(usuario);
    }

    @Override
    public List<VendedorDto> getListaContactos(UsuarioDto usuario) {
        return modelFactory.getListaContactosDto(((VendedorDto)usuario).getIdVendedor());
    }

    @Override
    public UsuarioDto getUsuarioPorId(String id) {
        return modelFactory.getUsuarioPorId(id);
    }

    @Override
    public boolean crearUsuario(VendedorDto vendedor) {
        return modelFactory.crearUsuario(vendedor);
    }
    public List<VendedorDto> getListaVendedoresSinAgregar(VendedorDto vendedor) {
        return modelFactory.getListaVendedoresSinAgregar(vendedor);
    }

    public List<VendedorDto> buscarPerfiles(String nombre) {
        return modelFactory.buscarPerfiles(nombre);
    }
}
