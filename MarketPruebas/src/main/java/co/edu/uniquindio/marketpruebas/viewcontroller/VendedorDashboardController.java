package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.model.Muro;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class VendedorDashboardController {
    private ModelFactory modelFactory;
    private VendedorDto vendedor;

    @FXML
    private Button btnContacto;

    @FXML
    private Button btnEstadistica;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnPerfil;


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

    @FXML
    private Button btnSkip;
    @FXML
    private Button btnChats;
    @FXML
    private GridPane gridContacto;
    @FXML
    void clickSkip(ActionEvent event) throws IOException {
        int result = JOptionPane.showConfirmDialog (null, "¿Seguro que deseas salir?","LOG OUT", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            Stage cerrar = (Stage) btnContacto.getScene().getWindow();
            cerrar.close();
            Stage login = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/login.fxml"));
            Scene scene = new Scene(loader.load(), 468, 531);
            LoginController loginController = loader.getController();
            loginController.setModelFactory(modelFactory);
            login.setScene(scene);
            login.show();
        }
    }
    @FXML
    void clickChats(ActionEvent event) {

    }
    /**
     * Metodo para inicializar los datos en el dashboard de un vendedor
     * @param vendedor
     */
    public void inicializarDashboard(VendedorDto vendedor) throws IOException {
        modelFactory = ModelFactory.getInstance();
        this.vendedor = vendedor;
        mostrarContactos();
    }

    public void mostrarContactos() throws IOException {
        int columna = 0;
        int fila = 0;
        for (int i = 0; i<vendedor.getListaContactos().size(); i++) {
            Vendedor vendedor1 = vendedor.getListaContactos().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/casillaContacto.fxml"));
            Button boton = loader.load();

            boton.setOnAction(event -> {
                try {
                    mostrarPublicaciones(vendedor1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            CasillaContactoController controller = loader.getController();
            controller.setData(vendedor.getListaContactos().get(i));

            gridContacto.add(boton, columna, fila);

            fila ++;
        }
    }

    public void mostrarPublicaciones(Vendedor vendedor) throws IOException {
        int columna = 0;
        int fila = 0;
        Muro muro = vendedor.getMuro();
        for (int i = 0; i<muro.getListaPublicaciones().size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion.fxml"));
            AnchorPane pane = loader.load();

            PublicacionController controller = loader.getController();
            controller.setData(muro.getListaPublicaciones().get(i));
            gridPaneMuro.add(pane, columna, fila);
            fila ++;
        }
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

