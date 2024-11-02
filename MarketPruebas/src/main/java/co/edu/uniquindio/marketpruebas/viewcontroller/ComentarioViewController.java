package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.mapping.dto.ComentarioDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ComentarioViewController {

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelHora;

    @FXML
    private Label labelNombre;

    private ComentarioDto comentario;
    @FXML
    private Label labelMensaje;

    public void setData(ComentarioDto comentario1) {
        labelFecha.setText(comentario1.getFecha().toString());
        labelHora.setText(comentario1.getHora().getHour() + " : " + comentario1.getHora().getMinute());
        labelNombre.setText(comentario1.getUsuario().getNombre());
        labelMensaje.setText(comentario1.getMensaje());
        comentario = comentario1;
    }

}

