package co.edu.uniquindio.marketpruebas.model;

import java.util.ArrayList;
import java.util.List;

public class Muro {
    private List<Publicacion> listaPublicaciones;
    private List<Chat> listaChats;

    public Muro(){
        listaPublicaciones = new ArrayList<Publicacion>();
        listaChats = new ArrayList<Chat>();
    }
    public void agregarPublicacion(Publicacion publicacion){
        listaPublicaciones.add(publicacion);
    }
    public void agregarChat(Chat chat){
        listaChats.add(chat);
    }
    public List<Chat> getListaChats() {
        return listaChats;
    }
    public void setListaChats(List<Chat> listaChats) {
        this.listaChats = listaChats;
    }
    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }
    public void setListaPublicaciones(List<Publicacion> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }
}
