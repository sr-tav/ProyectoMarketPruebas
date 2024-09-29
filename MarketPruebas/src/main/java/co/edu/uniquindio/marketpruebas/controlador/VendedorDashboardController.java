package co.edu.uniquindio.marketpruebas.controlador;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.model.Muro;
import co.edu.uniquindio.marketpruebas.model.Publicacion;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDashboardController {
    private ModelFactory modelFactory;
    private Vendedor vendedor;

    @FXML
    private Button btnCon1;

    @FXML
    private Button btnCon10;

    @FXML
    private Button btnCon2;

    @FXML
    private Button btnCon3;

    @FXML
    private Button btnCon4;

    @FXML
    private Button btnCon5;

    @FXML
    private Button btnCon6;

    @FXML
    private Button btnCon7;

    @FXML
    private Button btnCon8;

    @FXML
    private Button btnCon9;

    private Button[] botonesContactos = new Button[10];
    @FXML
    private Button btnContacto;

    @FXML
    private Button btnEstadistica;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnPerfil;

    @FXML
    private Pane paneCon1;

    @FXML
    private BorderPane paneContactos;

    @FXML
    private BorderPane paneEstadistica;

    @FXML
    private BorderPane paneInicio;

    @FXML
    private BorderPane panePerfil;

    @FXML
    private GridPane gridPaneMuro;

    @FXML
    private ScrollPane scrollPaneMuro;

    /**
     * Metodo para actualizar los botones del menu Contactos
     */
    public void actualizarContactos(){
        int numContactos = vendedor.getListaContactos().size();
        for (int i = 0; i < botonesContactos.length; i++) {
            if (i<numContactos){
                botonesContactos[i].setVisible(true);
                botonesContactos[i].setText(vendedor.getListaContactos().get(i).getNombre());
            }else{
                botonesContactos[i].setVisible(false);
            }
        }
    }
    public void inicializarBotonesContactos(){
        botonesContactos[0] = btnCon1;
        botonesContactos[1] = btnCon2;
        botonesContactos[2] = btnCon3;
        botonesContactos[3] = btnCon4;
        botonesContactos[4] = btnCon5;
        botonesContactos[5] = btnCon6;
        botonesContactos[6] = btnCon7;
        botonesContactos[7] = btnCon8;
        botonesContactos[8] = btnCon9;
        botonesContactos[9] = btnCon10;
    }
    /**
     * Metodo para inicializar los datos en el dashboard de un vendedor
     * @param modelFactory
     * @param vendedor
     */
    public void inicializarDashboard(ModelFactory modelFactory, Vendedor vendedor) {
        this.modelFactory = modelFactory;
        this.vendedor = vendedor;
        inicializarBotonesContactos();
        actualizarContactos();
    }

    @FXML
    void clickCon1(ActionEvent event) throws IOException {
        paneCon1.setVisible(true);
        int columna = 0;
        int fila = 0;
        Muro muro = vendedor.getListaContactos().get(0).getMuro();

        for(int i=0 ; i<muro.getListaPublicaciones().size() ; i++){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            PublicacionController publicacionController = fxmlLoader.getController();
            publicacionController.setData(muro.getListaPublicaciones().get(i));

            gridPaneMuro.add(anchorPane, columna, fila);
            GridPane.setMargin(anchorPane, new Insets(10));
            fila++;
        }
    }

    @FXML
    void clickCon10(ActionEvent event) {

    }

    @FXML
    void clickCon2(ActionEvent event) {

    }

    @FXML
    void clickCon3(ActionEvent event) {

    }

    @FXML
    void clickCon4(ActionEvent event) {

    }

    @FXML
    void clickCon5(ActionEvent event) {

    }

    @FXML
    void clickCon6(ActionEvent event) {

    }

    @FXML
    void clickCon7(ActionEvent event) {

    }

    @FXML
    void clickCon8(ActionEvent event) {

    }

    @FXML
    void clickCon9(ActionEvent event) {

    }

    @FXML
    void clickContacto(ActionEvent event) {
        paneContactos.setVisible(true);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
    }

    @FXML
    void clickEstadistica(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(true);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
    }

    @FXML
    void clickInicio(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(true);
        panePerfil.setVisible(false);
    }

    @FXML
    void clickPerfil(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(false);
        panePerfil.setVisible(true);
    }

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public void setModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }
}

