package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Muro;
import co.edu.uniquindio.marketpruebas.model.Producto;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

import java.util.List;

public class VendedorDto extends UsuarioDto {
    private String IdVendedor;
    private List<VendedorDto> listaContactos;
    private List<ProductoDto> listaProductos;
    private MuroDto muro;

    public VendedorDto(String nombre, String apellido, String cedula, String direccion, String usuario, String password, String idVendedor) {
        super(nombre, apellido, cedula, direccion, usuario, password);
        this.IdVendedor = idVendedor;
    }
    /**
     * SECCION GETTERS Y SETTERS
     */
    public String getIdVendedor() {
        return IdVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        IdVendedor = idVendedor;
    }

    public MuroDto getMuro() {
        return muro;
    }

    public void setMuro(MuroDto muro) {
        this.muro = muro;
    }

    public List<VendedorDto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<VendedorDto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public List<ProductoDto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
