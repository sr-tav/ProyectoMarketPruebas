package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Usuario;

import java.io.IOException;

public interface AbrirVentanasStrategy {
    void abrirVentana2(VendedorDto usuario) throws IOException;
}
