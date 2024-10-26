package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PublicacionViewController implements Initializable {
    PublicacionController publicacionController;
    UsuarioController usuarioController;

    /**
     * Metodo para inicializar el controlador a la par que se carga la vista
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioController = new UsuarioController();
        publicacionController = new PublicacionController();
    }

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

    private PublicacionDto publicacion;

    private VendedorDto vendedor;

    //El vendedor que esta usando el programa
    private VendedorDto interactVendedor;

    public void setData(PublicacionDto publicacion1) throws IOException {

        //llenar los datos de la vista con los de la publicacion
        labelNombre.setText(vendedor.getNombre());
        imgProducto.setImage(publicacion1.getProducto().getImagen());
        labelTitulo.setText(publicacion1.getProducto().getNombre());
        labelDescrip.setText(publicacion1.getDescripcion());
        labelPrecio.setText(Double.toString(publicacion1.getProducto().getPrecio()));
        labelNumMegustas.setText(Integer.toString(publicacionController.getListaMeGustas(publicacion1.getIdVendedor()).size()));
        labelFecha.setText(publicacion1.getFechaPublicacion().toString());
        labelHora.setText(publicacion1.getHoraPublicacion().getHour() + " : " + publicacion1.getHoraPublicacion().getMinute());
        labelComentarios.setText(Integer.toString(publicacionController.getListaMeGustas(publicacion1.getIdVendedor()).size()));

        //llenar los comentarios de la vista con los de la publicacion
        int columna = 0;
        int fila = 0;
        for (int i = 0; i < publicacionController.getListaComentarios(vendedor.getIdVendedor()).size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/comentario.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ComentarioController comentarioController = fxmlLoader.getController();
            comentarioController.setData(publicacionController.getListaComentarios(vendedor.getIdVendedor()).get(i));

            grindPaneComentarios.add(anchorPane, columna, fila);
            fila++;
        }

        //Cambiar la publicacion en la vista
        publicacion = publicacion1;
    }

    @FXML
    void clickDarMegusta(ActionEvent event) {

        //Dar like solamente una vez
        publicacionController.darMeGusta(interactVendedor, vendedor.getIdVendedor());
        labelNumMegustas.setText(Integer.toString(publicacionController.getListaMeGustas(vendedor.getIdVendedor()).size()));
        btnDarMegusta.setDisable(true);
        JOptionPane.showMessageDialog(null,publicacionController.getListaMeGustas(vendedor.getIdVendedor()).size());
    }
    @FXML
    void clickComentar(ActionEvent event) {

    }

    public VendedorDto getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDto vendedor) {
        this.vendedor = vendedor;
    }
    public void setInteractVendedor(VendedorDto InteractVendedor) {
        this.interactVendedor = InteractVendedor;
    }
}

