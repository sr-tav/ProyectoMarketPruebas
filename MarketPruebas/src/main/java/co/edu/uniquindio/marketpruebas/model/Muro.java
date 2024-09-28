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
