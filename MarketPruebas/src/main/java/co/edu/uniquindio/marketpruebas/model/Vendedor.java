package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.IRealizarPublicacion;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario implements IRealizarPublicacion {
    private final int maxContactos = 10;
    private String IdVendedor;
    private List<Vendedor> listaContactos;
    private List<Producto> listaProductos;
    private Muro muro;

    public Vendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String password, String idVendedor) {
        super(nombre,apellido,cedula,direccion,usuario,password);
        this.IdVendedor = idVendedor;
        listaContactos = new ArrayList<Vendedor>();
        listaProductos = new ArrayList<Producto>();
        muro = new Muro();
    }

    /**
     * Metodo para verificar si hay un contacto repetido
     * @param vendedor
     * @return
     */
    public boolean verificarContactoRepetido(Vendedor vendedor){
        boolean repetido = false;
        for (Vendedor vendedor1 : listaContactos){
            if(vendedor1.getIdVendedor().equals(vendedor.getIdVendedor())){
                repetido = true;
            }
        }
        return repetido;
    }

    /**
     * Metodo para agregar contactos dentro del limite
     * @param vendedor
     */
    public void agregarContacto(Vendedor vendedor) {
        if(!verificarContactoRepetido(vendedor) && listaContactos.size() < maxContactos){
            listaContactos.add(vendedor);
        }
    }

    /**
     * Metodo para agregar productos
     * @param producto
     */
    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }
    @Override
    public void publicar(Muro muro, Producto producto) {

    }

    public int getMaxContactos() {
        return maxContactos;
    }

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