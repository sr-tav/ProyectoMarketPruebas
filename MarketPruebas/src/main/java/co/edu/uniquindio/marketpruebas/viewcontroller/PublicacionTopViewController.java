package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.MuroController;
import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PublicacionTopViewController implements Initializable {

    @FXML
    private ImageView imgenPuesto;

    @FXML
    private Label labelFechaTop;

    @FXML
    private Label labelNomProductoTop;

    @FXML
    private Label labelNumeroLikes;
    PublicacionController publicacionController;

    public void setData(PublicacionDto publicacion) {
        labelNomProductoTop.setText(publicacion.getProducto().getNombre());
        labelNumeroLikes.setText(Integer.toString(publicacionController.getListaMeGustas(publicacion.getIdVendedor(),publicacion).size()));
        labelFechaTop.setText(publicacion.getFechaPublicacion().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publicacionController = new PublicacionController();
    }
    public ImageView getImgenPuesto() {
        return imgenPuesto;
    }

    public void setImgenPuesto(ImageView imgenPuesto) {
        this.imgenPuesto.setImage(imgenPuesto.getImage());
    }


}

