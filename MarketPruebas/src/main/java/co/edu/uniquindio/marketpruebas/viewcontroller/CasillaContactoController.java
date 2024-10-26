package co.edu.uniquindio.marketpruebas.viewcontroller;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class CasillaContactoController {
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

