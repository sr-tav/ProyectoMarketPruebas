package co.edu.uniquindio.marketpruebas.model.builder;

import co.edu.uniquindio.marketpruebas.model.Vendedor;

public class VendedorBuilder extends UsuarioBuilder<VendedorBuilder> {
    protected String IdVendedor;

    public VendedorBuilder idVendedor(String idVendedor) {
        this.IdVendedor = idVendedor;
        return this;
    }
    @Override
    public Vendedor build() {
        return new Vendedor(nombre,apellido,cedula,direccion,usuario,password,IdVendedor);
    }
    @Override
    protected VendedorBuilder self(){
        return this;
    }
}
