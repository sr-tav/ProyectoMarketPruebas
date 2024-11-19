package co.edu.uniquindio.marketpruebas.viewController;

import co.edu.uniquindio.marketpruebas.mapping.dto.MensajeDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MensajeViewController {
    MensajeDto mensaje;
    @FXML
    private Label labelFechaMensaje;

    @FXML
    private Label labelMensaje;

    public void setData(MensajeDto mensaje){
        labelMensaje.setText(mensaje.getMensaje());
        labelFechaMensaje.setText(mensaje.getFecha().toString());
        this.mensaje = mensaje;
    }
}
