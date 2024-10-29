package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

public interface ICrudPublicacion {
    boolean crearPublicacion(Publicacion publicacion, String id);
    boolean eliminarPublicacion(Publicacion publicacion, Vendedor vendedor);
    boolean actualizarPublicacion(Publicacion publicacion, Vendedor vendedor);
}
