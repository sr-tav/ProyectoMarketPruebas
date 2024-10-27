package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.services.ICrudPublicacion;
import co.edu.uniquindio.marketpruebas.services.IInteraccionEntreContactos;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace implements IInteraccionEntreContactos, ICrudPublicacion {
    private String nombre;
    private List<Administrador> listaAdministradores;
    private List<Usuario> listaUsuarios;
    private List<Vendedor> listaVendedores;

    public MarketPlace(String nombre) {
        this.nombre = nombre;
        this.listaAdministradores = new ArrayList<Administrador>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.listaVendedores = new ArrayList<Vendedor>();
    }
    public MarketPlace() {
        this.listaAdministradores = new ArrayList<Administrador>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.listaVendedores = new ArrayList<Vendedor>();
    }

    public <T> void agregarAutomatico(T objeto){
        if (objeto instanceof Usuario){
            listaUsuarios.add((Usuario)objeto);
            if(objeto instanceof Administrador){
                listaAdministradores.add((Administrador)objeto);
            }else if(objeto instanceof Vendedor) {
                listaVendedores.add((Vendedor)objeto);
            }
        }
    }
    public Usuario getUsuarioLogin(String usuario, String password){
        if (verificarUsuario(usuario, password)){
            for (Usuario usuario1 : listaUsuarios){
                if (usuario1.getUsuario().equals(usuario) && usuario1.getPassword().equals(password)){
                    return usuario1;
                }
            }
        }
        return null;
    }

    public Usuario getUsuario(String usuario, String password){
        if (verificarUsuario(usuario, password)){
            for (Usuario usuario1 : listaUsuarios){
                if (usuario1.getUsuario().equals(usuario) && usuario1.getPassword().equals(password)){
                    return usuario1;
                }
            }
        }
        return null;
    }

    public boolean verificarUsuario(String usuario, String password){
        for(Usuario u : listaUsuarios){
            if(u.getUsuario().equals(usuario) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    /**
     * ////////////////////////////////// RETORNO DE LISTAS ASOCIADAS A UNA CLASE //////////////////////////////////////
     */
    /**
     * Metodo para buscar la lista de productos de un vendedor por id
     * @param id
     * @return
     */
    public List<Producto> getListaProductosVendedor(String id) {
        List<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(id)){
                productos = vendedor.getListaProductos();
            }
        }
        return productos;
    }
    public List<Vendedor> getListaContactos(String id){
        List<Vendedor> contactos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(id)){
                contactos = vendedor.getListaContactos();
            }
        }
        return contactos;
    }
    public List<Comentario> getListaComentarios(String idVendedor){
        List<Comentario> comentarios = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(idVendedor)){
                for (Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()){
                    if(publicacion.getIdVendedor().equals(idVendedor)){
                        comentarios = publicacion.getListaComentarios();
                    }
                }
            }
        }
        return comentarios;
    }
    public List<Vendedor> getListaMeGusta(String idVendedor){
        List<Vendedor> meGustas = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(idVendedor)){
                for (Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()){
                    if(publicacion.getIdVendedor().equals(idVendedor)){
                        meGustas = publicacion.getListaMegustas();
                    }
                }
            }
        }
        return meGustas;
    }
    public List<Publicacion> getListaPublicaciones(String idVendedor){
        List<Publicacion> publicaciones = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(idVendedor)){
                for(Publicacion p : vendedor.getMuro().getListaPublicaciones()){
                    if (p.getIdVendedor().equals(idVendedor)){
                        publicaciones.add(p);
                    }
                }
            }
        }
        return publicaciones;
    }
    /**
     * Seccion Getter y Setters
     */
    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }
    public void setListaAdministradores(List<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }
    public void setListaVendedores(List<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void agregarContactosEntreSi(Vendedor contacto1, Vendedor contacto2) {
        contacto1.agregarContacto(contacto2);
        contacto2.agregarContacto(contacto1);
    }

    @Override
    public void darMeGustaPublicacion(Vendedor usuario, String id) {
        for(Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(id)){
                for (Publicacion p : vendedor.getMuro().getListaPublicaciones()){
                    if(p.getIdVendedor().equals(id)){
                        p.agregarMeGusta(usuario);
                    }
                }
            }
        }
    }

    /**
     * //////////////////////////////////////////// CRUD PUBLICACION ///////////////////////////////////////////////////
     */
    public boolean verificarProductoExiste(Producto producto){
        List<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor: listaVendedores){
            for (Producto p: vendedor.getListaProductos()){
                productos.add(p);
            }
        }

        for (Producto p: productos){
            if (p.getImagen() == producto.getImagen()){
                return true;
            }
        }
        return false;
    }

    public boolean verificarPublicacionExiste(Publicacion publicacion){
        List<Publicacion> publicaciones = new ArrayList<>();
        for (Vendedor vendedor: listaVendedores){
            for(Publicacion p : vendedor.getMuro().getListaPublicaciones()){
                publicaciones.add(p);
            }
        }

        for (Publicacion p: publicaciones){
            if (p.getProducto() == publicacion.getProducto()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean crearPublicacion(Publicacion publicacion, Vendedor vendedor) {
        if(verificarPublicacionExiste(publicacion)){
            vendedor.getMuro().agregarPublicacion(publicacion);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarPublicacion(Publicacion publicacion, Vendedor vendedor) {
        return false;
    }

    @Override
    public boolean actualizarPublicacion(Publicacion publicacion, Vendedor vendedor) {
        return false;
    }
}
