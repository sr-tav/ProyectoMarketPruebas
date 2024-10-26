package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Mensaje;
import co.edu.uniquindio.marketpruebas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ChatDto {
    private String id;
    private final int maxUsuarios = 2;
    private List<UsuarioDto> listaUsuarios;
    private List<MensajeDto> listaMensajes;

    public ChatDto(){
        listaUsuarios = new ArrayList<>();
        listaMensajes = new ArrayList<>();
    }

    public int getMaxUsuarios() {
        return maxUsuarios;
    }

    public List<UsuarioDto> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDto> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<MensajeDto> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<MensajeDto> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
}
