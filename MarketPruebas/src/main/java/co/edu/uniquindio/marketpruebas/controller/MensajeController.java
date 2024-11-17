package co.edu.uniquindio.marketpruebas.controller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.ChatDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.MensajeDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Chat;
import co.edu.uniquindio.marketpruebas.model.Mensaje;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import co.edu.uniquindio.marketpruebas.services.IServiceMensaje;

import java.io.Serializable;
import java.util.List;

public class MensajeController implements IServiceMensaje {
    ModelFactory modelFactory;
    public MensajeController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public ChatDto getChat(VendedorDto vendedor, VendedorDto contacto) {
        return modelFactory.getChat(vendedor, contacto);
    }
    @Override
    public List<MensajeDto> getListaMensaje(String id) {
        return modelFactory.getListaMensajeChat(id);
    }

    @Override
    public boolean agregarMensajeChat(MensajeDto mensaje, ChatDto chat) {
        return modelFactory.agregarMensajeChat(mensaje, chat);
    }
}
