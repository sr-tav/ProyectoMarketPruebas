package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Muro;
import co.edu.uniquindio.marketpruebas.model.Producto;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

import java.util.List;

public class VendedorDto extends UsuarioDto {
    private String IdVendedor;
    private List<Vendedor> listaContactos;
    private List<Producto> listaProductos;
    private Muro muro;

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

    public List<Vendedor> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Vendedor> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }
}
