package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
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
    private Button btnComentar;
    @FXML
    private ImageView imgProducto;

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
    private Label labelDescrip;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelHora;

    @FXML
    private ScrollPane scrollPaneComentarios;

    @FXML
    private Label labelComentarios;

    @FXML
    private Label labelNombre;

    private Publicacion publicacion;

    private Vendedor vendedor;

    public void setData(Publicacion publicacion1) throws IOException {

        //llenar los datos de la vista con los de la publicacion
        labelNombre.setText(vendedor.getNombre());
        imgProducto.setImage(publicacion1.getProducto().getImagen());
        labelTitulo.setText(publicacion1.getProducto().getNombre());
        labelDescrip.setText(publicacion1.getDescripcion());
        labelPrecio.setText(Double.toString(publicacion1.getProducto().getPrecio()));
        labelNumMegustas.setText(Integer.toString(publicacion1.getNumMeGustas()));
        labelFecha.setText(publicacion1.getFechaPublicacion().toString());
        labelHora.setText(publicacion1.getHoraPublicacion().getHour() + " : " + publicacion1.getHoraPublicacion().getMinute());
        labelComentarios.setText(Integer.toString(publicacion1.getListaComentarios().size()));

        //llenar los comentarios de la vista con los de la publicacion
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

        //Cambiar la publicacion en la vista
        publicacion = publicacion1;
    }

    @FXML
    void clickDarMegusta(ActionEvent event) {

        //Dar like solamente una vez
        publicacion.setNumMeGustas(publicacion.getNumMeGustas() + 1);
        labelNumMegustas.setText(Integer.toString(publicacion.getNumMeGustas()));
        btnDarMegusta.setDisable(true);
    }
    @FXML
    void clickComentar(ActionEvent event) {

    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}

