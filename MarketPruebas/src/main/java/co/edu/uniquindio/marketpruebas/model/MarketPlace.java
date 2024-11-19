package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.ICrudPublicacion;
import co.edu.uniquindio.marketpruebas.services.IInteraccionEntreContactos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Usuario usuario = new Usuario();
        usuario.setNombre("juan");
        usuario.setApellido("lopez");
        usuario.setUsuario("juanUser");
        usuario.setPassword("123");
        usuario.setCedula("123");
        usuario.setDireccion("mi casa");

        listaUsuarios.add(usuario);
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
    public List<Mensaje> getMensajesChat(String id){
        for (Vendedor v: listaVendedores){
            for (Chat c: v.getMuro().getListaChats()){
                if (c.getIdChat().equals(id)){
                    return c.getListaMensajes();
                }
            }
        }
        return null;
    }

    public Usuario getUsuarioPorId(String id){
        for (Vendedor vendedor : listaVendedores){
            if (vendedor.getIdVendedor().equals(id)){
                return vendedor;
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

    public Chat getChat(Vendedor vendedor, Vendedor contacto){
        for(Vendedor v : listaVendedores){
            if(v.getIdVendedor().equals(vendedor.getIdVendedor())){
                for (Chat c : v.getMuro().getListaChats()){
                    if (c.getUsuario1().getIdVendedor().equals(vendedor.getIdVendedor()) && c.getUsuario2().getIdVendedor().equals(contacto.getIdVendedor())
                            || c.getUsuario1().getIdVendedor().equals(contacto.getIdVendedor()) && c.getUsuario2().getIdVendedor().equals(vendedor.getIdVendedor())){
                        return c;
                    }
                }
            }
        }
        return null;
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
    public List<Comentario> getListaComentariosGenerales(String idVendedor){
        List<Comentario> comentarios = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(idVendedor)){
                for (Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()){
                    if(publicacion.getIdVendedor().equals(idVendedor)){
                        comentarios.addAll(publicacion.getListaComentarios());
                    }
                }
            }
        }
        return comentarios;
    }
    public List<Comentario> getListaComentarios(String idVendedor, LocalDate fecha, LocalTime hora){
        List<Comentario> comentarios = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(idVendedor)){
                for (Publicacion p : vendedor.getMuro().getListaPublicaciones()){
                    if(p.getFechaPublicacion() == fecha && p.getHoraPublicacion() == hora){
                        comentarios = p.getListaComentarios();
                        break;
                    }
                }
            }
        }
        return comentarios;
    }
    public List<Vendedor> getListaMeGusta(String idVendedor, Producto producto){
        for (Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(idVendedor)){
                for (Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()){
                    if(publicacion.getProducto().getImagen() == producto.getImagen()){
                        return publicacion.getListaMegustas();
                    }
                }
            }
        }
        System.out.println(" ");
        return null;
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
    public void darMeGustaPublicacion(Vendedor usuario, String id, LocalDate fecha, LocalTime hora) {
        for(Vendedor vendedor : listaVendedores){
            if(vendedor.getIdVendedor().equals(id)){
                for (Publicacion p : vendedor.getMuro().getListaPublicaciones()){
                    if(p.getFechaPublicacion() == fecha && p.getHoraPublicacion() == hora){
                        p.agregarMeGusta(usuario);
                        break;
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
            publicaciones.addAll(vendedor.getMuro().getListaPublicaciones());
        }

        for (Publicacion p: publicaciones){
            if (p.getProducto() == publicacion.getProducto()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean crearPublicacion(Publicacion publicacion, String id) {
        if(!verificarPublicacionExiste(publicacion)){
            for (Vendedor vendedor: listaVendedores){
                if(vendedor.getIdVendedor().equals(id)){
                    vendedor.setEstadoProducto(publicacion.getProducto());
                    vendedor.getMuro().agregarPublicacion(publicacion);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean crearUsuario(Vendedor vendedor){
        if (vendedor !=null){
            listaVendedores.add(vendedor);
            listaUsuarios.add(vendedor);
            return true;
        }else {
            return false;
        }

    }
    public boolean agregarMensajeChat(Mensaje mensaje, Chat chat){
        for (Vendedor vendedor: listaVendedores){
            for (Chat c : vendedor.getMuro().getListaChats()){
                if (c.getIdChat().equals(chat.getIdChat())){
                    c.agregarMensaje(mensaje);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean agregarComentario(Comentario comentario, Publicacion publicacion){
        for (Vendedor v : listaVendedores){
            if (v.getIdVendedor().equals(publicacion.getIdVendedor())){
                for (Publicacion p : v.getMuro().getListaPublicaciones()){
                    if (p.getFechaPublicacion().equals(publicacion.getFechaPublicacion())
                            && p.getHoraPublicacion().equals(publicacion.getHoraPublicacion())){
                        p.agregarComentario(comentario);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void darLikeComentario(Comentario comentario, Publicacion publicacion){
        for (Vendedor v : listaVendedores){
            if (v.getIdVendedor().equals(publicacion.getIdVendedor())){
                for (Publicacion p : v.getMuro().getListaPublicaciones()){
                    if (p.getFechaPublicacion().equals(publicacion.getFechaPublicacion())
                            && p.getHoraPublicacion().equals(publicacion.getHoraPublicacion())){
                        for (Comentario c : p.getListaComentarios()){
                            if (c.getHora().equals(comentario.getHora()) && c.getFecha().equals(comentario.getFecha())){
                                c.setNumMeGustas(c.getNumMeGustas() + 1);
                            }
                        }
                    }
                }
            }
        }
    }
    public int getLikesComentario(Comentario comentario, Publicacion publicacion){
        for (Vendedor v : listaVendedores){
            if (v.getIdVendedor().equals(publicacion.getIdVendedor())){
                for (Publicacion p : v.getMuro().getListaPublicaciones()){
                    if (p.getFechaPublicacion().equals(publicacion.getFechaPublicacion())
                            && p.getHoraPublicacion().equals(publicacion.getHoraPublicacion())){
                        for (Comentario c : p.getListaComentarios()){
                            if (c.getHora().equals(comentario.getHora()) && c.getFecha().equals(comentario.getFecha())){
                                return c.getNumMeGustas();
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    public List<Vendedor> getListaVendedoresSinAgregar(Vendedor vendedor1) {
        List<Vendedor> listaVendedores= new ArrayList<>();
        for (Vendedor i : this.listaVendedores){
            for(Vendedor j : getListaContactos(vendedor1.getIdVendedor())){
                if(!i.getIdVendedor().equals(j.getIdVendedor())){
                    listaVendedores.add(i);
                }
            }
        }
        return listaVendedores;
    }

    public List<Vendedor> buscarPerfiles(String nombre) {
        if(nombre!=null&&!nombre.isEmpty()){
            return listaVendedores.stream().filter(vendedor -> vendedor.getNombre().contains(nombre)).collect(Collectors.toCollection(ArrayList::new));
        }else {
            return listaVendedores;
        }
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
