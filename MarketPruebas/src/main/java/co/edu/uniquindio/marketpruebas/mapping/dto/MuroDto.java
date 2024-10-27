package co.edu.uniquindio.marketpruebas.mapping.dto;

public class MuroDto {
    private String idVendedor;

    public MuroDto(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public MuroDto(){

    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
}

