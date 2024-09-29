package co.edu.uniquindio.marketpruebas.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private final int maxUsuarios = 2;
    private List<Usuario> listaUsuarios;
    private List<Mensaje> listaMensajes;

    public Chat() {
        listaUsuarios = new ArrayList<Usuario>();
        listaMensajes = new ArrayList<Mensaje>();
    }
    public void agregarUsuario(Usuario usuario) {
        if (listaUsuarios.size() < maxUsuarios) {
            listaUsuarios.add(usuario);
        }
    }
    public void agregarMensaje(Mensaje mensaje) {
        listaMensajes.add(mensaje);
    }
    /**
     * SECCION GETTERS Y SETTERS
     */
    public int getMaxUsuarios() {
        return maxUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
}
