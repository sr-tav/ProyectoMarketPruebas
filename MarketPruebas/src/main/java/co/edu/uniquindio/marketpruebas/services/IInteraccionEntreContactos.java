package co.edu.uniquindio.marketpruebas.services;

import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Usuario;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

public interface IInteraccionEntreContactos {
    public void agregarContactosEntreSi(Vendedor contacto1, Vendedor contacto2);
    public void darMeGustaPublicacion(Vendedor usuario, String id);
}
