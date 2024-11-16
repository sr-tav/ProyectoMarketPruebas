package co.edu.uniquindio.marketpruebas.mapping.dto;

import co.edu.uniquindio.marketpruebas.model.Mensaje;
import co.edu.uniquindio.marketpruebas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ChatDto {
    private String id;
    private final int maxUsuarios = 2;

    public ChatDto(){
        this.id = id;
    }

    public int getMaxUsuarios() {
        return maxUsuarios;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
