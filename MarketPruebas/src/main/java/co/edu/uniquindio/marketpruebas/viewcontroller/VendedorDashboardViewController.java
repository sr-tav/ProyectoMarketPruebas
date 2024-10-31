package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.MuroController;
import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class VendedorDashboardViewController {
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


        //Seccion contactos
        this.vendedor = vendedor;
        mostrarContactos();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        //Seccion Inicio
        selectProducto.setItems(FXCollections.observableArrayList(usuarioController.getListaProductosDisponibles(vendedor)));
        selectProducto.setCellFactory(lv -> new ListCell<ProductoDto>(){
            @Override
            protected void updateItem(ProductoDto item, boolean empty){
                super.updateItem(item, empty);
                setText(empty ? "" : "Producto = " + item.getNombre() + " / " + item.getEstado());
            }
        });

        // Seccion estadisticas
        actualizarEstadisticas();
        inicializarTimeLine();
    }

    /**
     *  /////////////////////////////////////////////////////////////SECCION PANEL CONTACTOS///////////////////////////////////////////////////////////////
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

            CasillaContactoViewController controller = loader.getController();
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
            controller.setVendedor(vendedor);
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
    private Label labelNombreEstadistica;

    @FXML
    private Button btnExportar;

    @FXML
    private Label labelCantProductosPublicados;

    @FXML
    private Label labelCantidadContactos;

    @FXML
    private Label labelTiempoUso;

    @FXML
    private GridPane gridTop;

    @FXML
    private Label labelNumMensajesEstadistic;

    @FXML
    private MenuButton menuButtonContacto;

    @FXML
    private CategoryAxis axisFecha;

    @FXML
    private NumberAxis axisNumero;

    @FXML
    private BarChart<?, ?> chartPublicaciones;

    private Timeline timeline;
    private int segEnlapso = 0;

    @FXML
    void clickEstadistica(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(true);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
    }

    @FXML
    void clickExportarInforme(ActionEvent event) {

    }

    public void actualizarEstadisticas() throws IOException {
        labelNombreEstadistica.setText(vendedor.getNombre()+" "+vendedor.getApellido());
        labelCantProductosPublicados.setText(Integer.toString(muroController.getListaPublicaciones(vendedor).size()));
        labelCantidadContactos.setText(Integer.toString(usuarioController.getListaContactos(vendedor).size()));
        inicializarMenuBtnEstadisticas();
        inicializarTop(getTopProductos());
    }

    private void inicializarTimeLine(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> actualizarTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void actualizarTimer(){
        segEnlapso++;
        int hora = segEnlapso/3600;
        int minutos = (segEnlapso%3600)/60;
        int seg = segEnlapso%60;
        labelTiempoUso.setText(String.format("%02d:%02d:%02d", hora, minutos, seg));
    }

    public void inicializarMenuBtnEstadisticas(){
        for (VendedorDto v :usuarioController.getListaContactos(vendedor)) {
            MenuItem item = new MenuItem(v.getNombre());

            item.setOnAction(event -> {menuButtonContacto.setText(v.getNombre());
                seleccionarContactoEstadistica(v);
            });
            menuButtonContacto.getItems().add(item);
        }
    }

    public void seleccionarContactoEstadistica(VendedorDto v){
        labelNumMensajesEstadistic.setText(Integer.toString(usuarioController.getListaContactos(v).size()));
    }

    public List<PublicacionDto> getTopProductos(){
        Map<PublicacionDto, Integer> publicaciones = new HashMap<>();
        for (PublicacionDto p : muroController.getListaPublicaciones(vendedor)) {
            int cont= publicacionController.getListaMeGustas(vendedor.getIdVendedor(), p).size();
            publicaciones.put(p, cont);
        }
        return publicaciones.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

    public void inicializarTop(List<PublicacionDto> publicaciones) throws IOException {
        int columna = 0;
        int fila = 0;
        for (int i = 0; i<11; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion-top-estadisticas.fxml"));
            AnchorPane anchorPane = loader.load();

            PublicacionTopViewController controller = loader.getController();
            if (i<publicaciones.size()) {
                controller.setData(publicaciones.get(i));
                URL url;
                switch (i){
                    case 0:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/number-1-yelllow.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 1:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/number-2.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 2:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/number-3.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 3:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/4.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 4:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/5.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 5:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/6.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 6:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/7.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 7:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/8.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 8:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/9.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    case 9:
                        url = getClass().getResource("/co/edu/uniquindio/marketpruebas/10.png");
                        assert url != null;
                        controller.setImgenPuesto(new ImageView(url.toExternalForm()));
                        break;
                    default:
                        break;
                }
                gridTop.add(anchorPane, columna, fila);
                fila++;
            }
        }
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
    void clickInicio(ActionEvent event) throws IOException {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(true);
        panePerfil.setVisible(false);
        rutaImagenCargada = null;
        textAreaPublicar.clear();
        modeloRecomendacion();
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

        if (!textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() != null) {
            PublicacionDto dto = new PublicacionDto();
            dto.setDescripcion(textAreaPublicar.getText());
            dto.setHoraPublicacion(LocalTime.now());
            dto.setFechaPublicacion(LocalDate.now());
            dto.setProducto(selectProducto.getSelectionModel().getSelectedItem());

            if (publicacionController.agregarPublicacion(dto, vendedor) ){
                JOptionPane.showMessageDialog(null, "Publicacion realizada con exito");
                textAreaPublicar.clear();
                selectProducto.getSelectionModel().clearSelection();
                JOptionPane.showMessageDialog(null, muroController.getListaPublicaciones(vendedor).size());

            }else {
                JOptionPane.showMessageDialog(null, "No se puede agregar el publicacion");

            }


        }else if (textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() != null) {
            JOptionPane.showMessageDialog(null, "Escribe una descripcion para poder realizar la publicacion");

        }else if (!textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto para publicar,\n " +
                    "Si no lo tienes ningun producto, agregalo! (implementar boton para agregar producto desde ahi)");

        }else if (textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto y escribir una descripcion!");
        }

    }
    /**
     * Metodo que genera una lista de publicaciones dependiendo de las interacciones que tenga un usuario con sus contactos
     * @return
     */
    public void modeloRecomendacion() throws IOException {
        List<PublicacionDto> publicaciones = new ArrayList<>();
        Map<VendedorDto, Integer> interaccionesMap = new HashMap<>();

        for (VendedorDto v: usuarioController.getListaContactos(vendedor)) {
            int interacciones = contadorDeInteraccion(muroController.getListaPublicaciones(v), v.getIdVendedor());
            interaccionesMap.put(v, interacciones);
        }

        List<VendedorDto> vendedoresOrdenados = interaccionesMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .toList();

        for (VendedorDto v: vendedoresOrdenados){
            publicaciones.addAll(muroController.getListaPublicaciones(v));
        }

        llenarInicio(vendedoresOrdenados);
    }

    /**
     * Metodo que cuenta la cantidad de veces que aparece un usuario en los likes de una publicacion
     * @return
     */
    public int contadorDeInteraccion(List<PublicacionDto> publicaciones, String id) {
        int cont = 0;
        for (PublicacionDto p: publicaciones) {
            for (VendedorDto dto : publicacionController.getListaMeGustas(id, p)){
                if (dto.getIdVendedor().equals(vendedor.getIdVendedor())) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public void llenarInicio(List<VendedorDto> vendedores) throws IOException {
        int columna = 0;
        int fila = 0;
        for (VendedorDto v: vendedores) {
            for(int i = 0; i < muroController.getListaPublicaciones(v).size() ; i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion.fxml"));
                AnchorPane pane = loader.load();

                PublicacionViewController controller = loader.getController();
                controller.setVendedor(v);
                controller.setData(muroController.getListaPublicaciones(v).get(i));


                gridInicio.add(pane, columna, fila);
                fila ++;
            }
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

