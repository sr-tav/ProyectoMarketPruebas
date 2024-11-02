package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.mapping.dto.ComentarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ComentarioViewController implements Initializable {
    PublicacionController publicacionController;
    @FXML
    private Label labelFecha;

    @FXML
    private Label labelHora;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelNumLikes;

    private ComentarioDto comentario;

    @FXML
    private Button btnLike;
    private PublicacionDto publicacion;

    @FXML
    private Label labelMensaje;

    public void setData(ComentarioDto comentario1) {
        if (comentario1!=null){
            labelFecha.setText(comentario1.getFecha().toString());
            labelHora.setText(comentario1.getHora().getHour() + " : " + comentario1.getHora().getMinute());
            labelNombre.setText(comentario1.getUsuario().getNombre());
            labelMensaje.setText(comentario1.getMensaje());
            labelNumLikes.setText(Integer.toString(comentario1.getNumMeGustas()));
            comentario = comentario1;
        }
    }
    @FXML
    void clickLike(ActionEvent event) {
        publicacionController.darLikeComentario(comentario, publicacion);
        labelNumLikes.setText(Integer.toString(publicacionController.getLikesComentario(comentario, publicacion)));
        btnLike.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publicacionController = new PublicacionController();
    }

    public void setPublicacion(PublicacionDto publicacion) {
        this.publicacion = publicacion;
    }
}

