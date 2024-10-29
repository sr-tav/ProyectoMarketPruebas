package co.edu.uniquindio.marketpruebas.viewcontroller;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CasillaContactoViewController {
    private VendedorDto vendedor;

    @FXML
    private Label lablNombre;

    public void setData(VendedorDto vendedor) {
        this.vendedor = vendedor;
        lablNombre.setText(vendedor.getNombre());
    }

    public VendedorDto getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDto vendedor) {
        this.vendedor = vendedor;
    }
}

