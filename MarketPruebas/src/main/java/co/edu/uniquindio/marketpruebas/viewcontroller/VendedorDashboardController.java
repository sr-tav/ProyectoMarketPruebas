package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.MuroController;
import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class VendedorDashboardController {
    ModelFactory modelFactory;
    VendedorDto vendedor;
    PublicacionController publicacionController;
    UsuarioController usuarioController;
    MuroController muroController;

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
    private Label labelNombreMuro;

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
            LoginViewController loginViewController = loader.getController();
            loginViewController.setModelFactory(modelFactory);
            login.setScene(scene);
            login.show();
        }
    }

    /**
     * Metodo para inicializar los datos en el dashboard de un vendedor
     * @param vendedor
     */
    public void inicializarDashboard(VendedorDto vendedor) throws IOException {
        modelFactory = ModelFactory.getInstance();
        publicacionController = new PublicacionController();
        usuarioController = new UsuarioController();
        muroController = new MuroController();

        this.vendedor = vendedor;
        mostrarContactos();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        selectProducto.setItems(FXCollections.observableArrayList(usuarioController.getListaProductosDisponibles(vendedor)));
        selectProducto.setCellFactory(lv -> new ListCell<ProductoDto>(){
            @Override
            protected void updateItem(ProductoDto item, boolean empty){
                super.updateItem(item, empty);
                setText(empty ? "" : "Producto = " + item.getNombre() + " / " + item.getEstado());
            }
        });
    }

    /**
     *  ////////////////////////////////////SECCION PANEL CONTACTOS///////////////////////////////////////////////////
     */

    @FXML
    void clickContacto(ActionEvent event) {
        paneContactos.setVisible(true);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
    }

    public void mostrarContactos() throws IOException {
        int columna = 0;
        int fila = 0;
        for (int i = 0; i<usuarioController.getListaContactos(vendedor).size(); i++) {
            VendedorDto vendedor1 = usuarioController.getListaContactos(vendedor).get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/casillaContacto.fxml"));
            Button boton = loader.load();

            boton.setOnAction(event -> {
                try {
                    mostrarPublicaciones(vendedor1);
                    labelNombreMuro.setText("Publicaciones de " + vendedor1.getNombre());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            CasillaContactoController controller = loader.getController();
            controller.setData(usuarioController.getListaContactos(vendedor).get(i));

            gridContacto.add(boton, columna, fila);

            fila ++;
        }
    }

    public void mostrarPublicaciones(VendedorDto vendedor) throws IOException {
        int columna = 0;
        int fila = 0;
        for (int i = 0; i<muroController.getListaPublicaciones(vendedor).size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion.fxml"));
            AnchorPane pane = loader.load();

            PublicacionViewController controller = loader.getController();
            controller.setVendedor((VendedorDto) new UsuarioDto());
            controller.setInteractVendedor(this.vendedor);
            controller.setData(muroController.getListaPublicaciones(vendedor).get(i));
            gridPaneMuro.add(pane, columna, fila);
            fila ++;
        }
    }

    /**
     * //////////////////////////////////////////SECCION PANEL ESTADISTICAS/////////////////////////////////////////////
     */
    @FXML
    void clickEstadistica(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(true);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
    }

    /**
     * /////////////////////////////////////////// SECCION PANEL INICIO/////////////////////////////////////////////////
     */

    @FXML
    private Button btnCargarImagen;

    @FXML
    private Button btnPublicar;

    @FXML
    private TextArea textAreaPublicar;

    private FileChooser fileChooser;

    private String rutaImagenCargada;

    @FXML
    private ComboBox<ProductoDto> selectProducto;

    private ProductoDto productoSeleccionado;

    @FXML
    private GridPane gridInicio;

    @FXML
    private ScrollPane scrollInicio;

    /**
     * Metodo para que al darle click al inicio se oculten los otros paneles y solo se muestre el inicio
     * @param event
     */
    @FXML
    void clickInicio(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(true);
        panePerfil.setVisible(false);
        rutaImagenCargada = null;
        textAreaPublicar.clear();
    }

    @FXML
    void clickCargarImagen(ActionEvent event) {
        File archivo = fileChooser.showOpenDialog(null);
        if (archivo != null) {
            this.rutaImagenCargada = archivo.toURI().toString();
        }
    }

    @FXML
    void clickPublicar(ActionEvent event) throws IOException {

        if (textAreaPublicar.getText() != null) {
            JOptionPane.showMessageDialog(null, "Paso");
            PublicacionDto dto = new PublicacionDto();
            dto.setDescripcion(textAreaPublicar.getText());
            dto.setHoraPublicacion(LocalTime.now());
            dto.setFechaPublicacion(LocalDate.now());
            dto.setProducto(selectProducto.getSelectionModel().getSelectedItem());
            if (publicacionController.agregarPublicacion(dto,vendedor)){
                JOptionPane.showMessageDialog(null, "Publicacion realizada con exito");
                textAreaPublicar.clear();
                selectProducto.getSelectionModel().clearSelection();
                mostrarPublicacionesPersonal();
            }else {
                JOptionPane.showMessageDialog(null, "No se puede agregar el publicacion");
            }


        }else {
            JOptionPane.showMessageDialog(null, "Escribe una descripcion para poder realizar la publicacion");
        }

    }

    public void mostrarPublicacionesPersonal() throws IOException {
        int columna = 0;
        int fila = 0;
        for(int i = 0;i<muroController.getListaPublicaciones(vendedor).size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion.fxml"));
            AnchorPane pane = loader.load();

            PublicacionViewController controller = loader.getController();
            controller.setVendedor(this.vendedor);
            controller.setData(muroController.getListaPublicaciones(vendedor).get(i));


            gridInicio.add(pane, columna, fila);
            fila ++;

        }
    }


    /**
     * //////////////////////////////////////////// SECCION PANEL PERFIL ///////////////////////////////////////////////
     */
    @FXML
    void clickPerfil(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(false);
        panePerfil.setVisible(true);
    }
    /**
     * ///////////////////////////////////////////// SECCION PANEL CHATS ///////////////////////////////////////////////
     */

    @FXML
    void clickChats(ActionEvent event) {

    }

    /**
     * ///////////////////////////////////////////// SECCION GETTERS Y SETTERS /////////////////////////////////////////
     */

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public void setModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }
}

