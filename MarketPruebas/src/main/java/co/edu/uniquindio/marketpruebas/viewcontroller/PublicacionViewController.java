package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.mapping.dto.ComentarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.PublicacionDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @FXML
    private HBox boxEscribirComentario;
    @FXML
    private Button btnEnviarComentario;
    @FXML
    private TextField txtEscribirComenario;

    //El vendedor que esta usando el programa
    private VendedorDto interactVendedor;

    public void setData(PublicacionDto publicacion1) throws IOException {

        //llenar los datos de la vista con los de la publicacion
        labelNombre.setText(vendedor.getNombre());
        imgProducto.setImage(publicacion1.getProducto().getImagen());
        labelTitulo.setText(publicacion1.getProducto().getNombre());
        labelDescrip.setText(publicacion1.getDescripcion());
        labelPrecio.setText(Double.toString(publicacion1.getProducto().getPrecio()));
        labelNumMegustas.setText(Integer.toString(publicacionController.getListaMeGustas(publicacion1.getIdVendedor(), publicacion1).size()));
        labelFecha.setText(publicacion1.getFechaPublicacion().toString());
        labelHora.setText(publicacion1.getHoraPublicacion().getHour() + " : " + publicacion1.getHoraPublicacion().getMinute());
        labelComentarios.setText(Integer.toString(publicacionController.getListaComentarios(vendedor.getIdVendedor(), publicacion1).size()));

        //llenar los comentarios de la vista con los de la publicacion
        publicacion = publicacion1;
        actualizarComentarios();

        //Cambiar la publicacion en la vista
        boxEscribirComentario.setManaged(false);
    }

    public void actualizarComentarios() throws IOException {
        grindPaneComentarios.getChildren().clear();
        int columna = 0;
        int fila = 0;
        for (int i = 0; i < publicacionController.getListaComentarios(vendedor.getIdVendedor(),publicacion).size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/comentario.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ComentarioViewController comentarioViewController = fxmlLoader.getController();
            comentarioViewController.setData(publicacionController.getListaComentarios(vendedor.getIdVendedor(), publicacion).get(i));
            comentarioViewController.setPublicacion(publicacion);

            grindPaneComentarios.add(anchorPane, columna, fila);
            fila++;
        }
    }

    @FXML
    void clickDarMegusta(ActionEvent event) {

        //Dar like solamente una vez
        publicacionController.darMeGusta(interactVendedor, vendedor.getIdVendedor(), publicacion);
        labelNumMegustas.setText(Integer.toString(publicacionController.getListaMeGustas(vendedor.getIdVendedor(), publicacion).size()));
        btnDarMegusta.setDisable(true);
        JOptionPane.showMessageDialog(null,publicacionController.getListaMeGustas(vendedor.getIdVendedor(), publicacion).size());
    }
    @FXML
    void clickComentar(ActionEvent event) {
        oculparEscribirComentario();
    }
    private void oculparEscribirComentario(){
        boolean visible = boxEscribirComentario.isVisible();
        boxEscribirComentario.setVisible(!visible);
        boxEscribirComentario.setManaged(!visible);
    }

    @FXML
    void clickEnviarComentario(ActionEvent event) throws IOException {
        if (!txtEscribirComenario.getText().isEmpty()) {
            ComentarioDto dto = new ComentarioDto();
            dto.setUsuario(interactVendedor);
            dto.setHora(LocalTime.now());
            dto.setFecha(LocalDate.now());
            dto.setMensaje(txtEscribirComenario.getText());
            if (publicacionController.agregarComentario(dto,publicacion)) {
                txtEscribirComenario.clear();
                oculparEscribirComentario();
                actualizarComentarios();
                JOptionPane.showMessageDialog(null, "Comentario agregado con exito!");
            }else {
                JOptionPane.showMessageDialog(null, "error al enviar comentario");
            }
        }else {
            JOptionPane.showMessageDialog(null,"Escribe tu comentario antes de enviar!");
        }

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

