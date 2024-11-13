package co.edu.uniquindio.marketpruebas.model.builder;

import co.edu.uniquindio.marketpruebas.model.Administrador;

public class AdministradorBuilder extends UsuarioBuilder<AdministradorBuilder>{
    protected String idAdmin;

    public AdministradorBuilder idAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
        return this;
    }
    @Override
    public Administrador build() {
        return new Administrador(nombre,apellido,cedula,direccion,usuario,password,idAdmin);
    }
    @Override
    protected AdministradorBuilder self(){
        return this;
    }
}
