package co.edu.uniquindio.marketpruebas.model;

import co.edu.uniquindio.marketpruebas.services.IInteraccionEntreContactos;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace implements IInteraccionEntreContactos {
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
    public void darMeGustaPublicacion(Vendedor usuario, Publicacion publicacion) {
        publicacion.agregarMeGusta(usuario);
    }
}
