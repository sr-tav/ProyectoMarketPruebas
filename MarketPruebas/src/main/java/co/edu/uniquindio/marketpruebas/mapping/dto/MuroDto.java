package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Chat;
import co.edu.uniquindio.marketpruebas.model.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class MuroDto {
    private List<PublicacionDto> listaPublicaciones;
    private List<ChatDto> listaChats;

    public MuroDto() {
        listaPublicaciones = new ArrayList<>();
        listaChats = new ArrayList<>();
    }

    public List<PublicacionDto> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<PublicacionDto> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public List<ChatDto> getListaChats() {
        return listaChats;
    }

    public void setListaChats(List<ChatDto> listaChats) {
        this.listaChats = listaChats;
    }
}
