package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.model.Publicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PublicacionController {

    @FXML
    private ImageView ImagenProd;

    @FXML
    private Button btnDarMegusta;

    @FXML
    private GridPane grindPaneComentarios;

    @FXML
    private Label labelNumMegustas;

    @FXML
    private Label labelPrecio;

    @FXML
    private Label labelTitulo;

    @FXML
    private TextArea areaDescrip;
    @FXML
    private Label labelFecha;

    @FXML
    private Label labelHora;

    @FXML
    private ScrollPane scrollPaneComentarios;
    private Publicacion publicacion;

    public void setData(Publicacion publicacion1) throws IOException {
        ImagenProd.setImage(publicacion1.getProducto().getImagen());
        labelTitulo.setText(publicacion1.getProducto().getNombre());
        areaDescrip.setText(publicacion1.getDescripcion());
        labelPrecio.setText(Double.toString(publicacion1.getProducto().getPrecio()));
        labelNumMegustas.setText(Integer.toString(publicacion1.getNumMeGustas()));
        labelFecha.setText(publicacion1.getFechaPublicacion().toString());
        labelHora.setText(publicacion1.getHoraPublicacion().getHour() + " : " + publicacion1.getHoraPublicacion().getMinute());
        int columna = 0;
        int fila = 0;
        for (int i = 0; i < publicacion1.getListaComentarios().size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/comentario.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ComentarioController comentarioController = fxmlLoader.getController();
            comentarioController.setData(publicacion1.getListaComentarios().get(i));

            grindPaneComentarios.add(anchorPane, columna, fila);
            fila++;
        }
        publicacion = publicacion1;
    }
    @FXML
    void clickDarMegusta(ActionEvent event) {
        publicacion.setNumMeGustas(publicacion.getNumMeGustas() + 1);
        labelNumMegustas.setText(Integer.toString(publicacion.getNumMeGustas()));
    }

}

