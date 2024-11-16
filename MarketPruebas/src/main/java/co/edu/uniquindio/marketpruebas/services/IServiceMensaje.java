package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.ChatDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.MensajeDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;

import java.util.List;


public interface IServiceMensaje {

    ChatDto getChat(VendedorDto vendedor, VendedorDto contacto);
    List<MensajeDto> getListaMensaje(String id);
    void agregarMensajeChat();

}
