package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.MensajeController;
import co.edu.uniquindio.marketpruebas.controller.MuroController;
import co.edu.uniquindio.marketpruebas.controller.PublicacionController;
import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.*;
import co.edu.uniquindio.marketpruebas.model.Chat;
import co.edu.uniquindio.marketpruebas.model.Estado;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
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
import javafx.scene.chart.XYChart;
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
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class VendedorDashboardViewController {
    ModelFactory modelFactory;
    VendedorDto vendedor;
    PublicacionController publicacionController;
    UsuarioController usuarioController;
    MuroController muroController;
    MensajeController mensajeController;

    @FXML
    private BorderPane paneChat;

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
        mensajeController = new MensajeController();


        //Seccion contactos
        this.vendedor = vendedor;
        mostrarContactos();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        //Seccion Inicio
        actualizarSelectProductoInicio();
        modeloRecomendacion();

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
        paneChat.setVisible(false);
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
     * /////////////////////////////////////////////////////////////SECCION PANEL ESTADISTICAS//////////////////////////////////////////////////////////////////
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
    private BarChart<String, Number> chartPublicaciones;

    private Timeline timeline;
    private int segEnlapso = 0;

    @FXML
    void clickEstadistica(ActionEvent event) throws IOException {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(true);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
        paneChat.setVisible(false);
        actualizarEstadisticas();
    }

    @FXML
    void clickExportarInforme(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar informe de estadisticas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt"));

        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(generarReporteEstadisticas());
                System.out.println("Reporte exportado correctamente!");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El reporte no se pudo exportar");
        }

    }
    public String generarReporteEstadisticas() {
        StringBuilder info = new StringBuilder();
        List<PublicacionDto> top = getTopProductos();

        info.append("█////////////////////////////////////////////////////////////////////////////////////////////█\n");
        info.append("█                       R E P O R T E   D E   E S T A D I S T I C A S                        █\n");
        info.append("█                                     FECHA: ").append(LocalDate.now()).append("                                      \n");
        info.append("█                            Reporte generado por: ").append(vendedor.getNombre()).append(" ").append(vendedor.getApellido()).append("                              \n");
        info.append("█////////////////////////////////////////////////////////////////////////////////////////////█\n");
        info.append("█--------------------------------------------------------------------------------------------█\n");
        info.append("█                                   DETALLE DEL REPORTE                                      █\n");
        info.append("█--------------------------------------------------------------------------------------------█\n");
        info.append("█ Cantidad de productos publicados: ").append(labelCantProductosPublicados.getText()).append("\n");
        info.append("█ Cantidad de contactos: ").append(labelCantidadContactos.getText()).append("\n");
        info.append("█ Tiempo de uso en la aplicacion: ").append(labelTiempoUso.getText()).append("\n");
        info.append("█ Cantidad de mensajes enviados con ").append(menuButtonContacto.getText()).append(" : ").append(labelNumMensajesEstadistic.getText()).append("\n");

        info.append("█--------------------------------------------------------------------------------------------█\n");
        info.append("█                              = TOP 1O PUBLICACIONES CON MAS LIKES =                        █\n");
        info.append("█--------------------------------------------------------------------------------------------█\n");
        for (int i = 0; i < 10; i++) {
            if (i < top.size()) {
                PublicacionDto pub = top.get(i);
                int likes = publicacionController.getListaMeGustas(pub.getIdVendedor(), pub).size();
                info.append(String.format("█ %2d: %-30s / %s / Me gustas: %d\n",
                        i + 1,
                        pub.getProducto().getNombre(),
                        pub.getFechaPublicacion(),
                        likes));
            } else {
                info.append(String.format("█ %2d: %-30s                                          \n", i + 1, "Sin datos"));
            }
        }
        info.append("█--------------------------------------------------------------------------------------------█\n");
        info.append("█                 DIAGRAMA DE BARRAS: PUBLICACIONES POR LOS ULTIMOS 30 DIAS                  █\n");
        info.append("█--------------------------------------------------------------------------------------------█\n");
        if (!chartPublicaciones.getData().isEmpty()) {
            XYChart.Series<String, Number> series = chartPublicaciones.getData().getFirst();
            int maxValor = series.getData().stream().mapToInt(data -> data.getYValue().intValue()).max().orElse(1);

            for (XYChart.Data<String, Number> data : series.getData()) {
                String fecha = data.getXValue();
                int publicaciones = data.getYValue().intValue();
                int longitudBarra = (int) ((double) publicaciones / maxValor * 30);

                info.append(String.format("█ %-12s | %s %3d █\n", fecha, "█".repeat(longitudBarra), publicaciones));
            }

        } else {
            info.append("█ No hay datos de publicaciones disponibles para mostrar.                                   █\n");
        }

        info.append("█--------------------------------------------------------------------------------------------█\n");
        info.append("█ Nota: Este es un informe generado automáticamente.                                         █\n");
        info.append("█////////////////////////////////////////////////////////////////////////////////////////////█\n");

        return info.toString();
    }

    public void inicializarDiagrama(){
        chartPublicaciones.getData().clear();
        axisFecha.setLabel("Dia");
        axisNumero.setLabel("Numero de publicaciones");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Publicaciones");

        List<PublicacionDto> publicaciones = muroController.getListaPublicaciones(vendedor);

        for (int i = 0; i<32;i++){
            LocalDate date = LocalDate.now().plusDays(i);
            int num = buscarPublicacionesPorDia(date, publicaciones);
            series.getData().add(new XYChart.Data<>(date.toString(), num));
        }
        chartPublicaciones.getData().add(series);

    }

    public int buscarPublicacionesPorDia(LocalDate date, List<PublicacionDto> publicaciones){
        int cont = 0;
        for(PublicacionDto dto: publicaciones){
            if (dto.getFechaPublicacion().equals(date)){
                cont++;
            }
        }
        return cont;
    }

    public void actualizarEstadisticas() throws IOException {
        labelNombreEstadistica.setText(vendedor.getNombre()+" "+vendedor.getApellido());
        labelCantProductosPublicados.setText(Integer.toString(muroController.getListaPublicaciones(vendedor).size()));
        labelCantidadContactos.setText(Integer.toString(usuarioController.getListaContactos(vendedor).size()));
        inicializarMenuBtnEstadisticas();
        inicializarTop(getTopProductos());
        inicializarDiagrama();
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
        menuButtonContacto.getItems().clear();
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
     * ///////////////////////////////////////////////////////////// SECCION PANEL INICIO//////////////////////////////////////////////////////////////////
     */

    @FXML
    private Button btnCargarImagen;

    @FXML
    private Button btnPublicar;

    @FXML
    private TextArea textAreaPublicar;

    private FileChooser fileChooser;

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
        paneChat.setVisible(false);
        selectProducto.setItems(FXCollections.observableArrayList(usuarioController.getListaProductosDisponibles(vendedor)));
        textAreaPublicar.clear();
        modeloRecomendacion();
    }

    @FXML
    void clickPublicar(ActionEvent event) throws IOException {

        if (!textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() != null) {
            PublicacionDto dto = new PublicacionDto();
            dto.setDescripcion(textAreaPublicar.getText());
            dto.setHoraPublicacion(LocalTime.now());
            dto.setFechaPublicacion(LocalDate.now().plusDays(10));
            dto.setProducto(selectProducto.getSelectionModel().getSelectedItem());

            if (publicacionController.agregarPublicacion(dto, vendedor) ){
                JOptionPane.showMessageDialog(null, "Publicacion realizada con exito");
                selectProducto.getSelectionModel().clearSelection();
                selectProducto.getItems().clear();
                actualizarSelectProductoInicio();
                textAreaPublicar.clear();

            }else {
                JOptionPane.showMessageDialog(null, "No se puede agregar el publicacion");

            }


        }else if (textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() != null) {
            JOptionPane.showMessageDialog(null, "Escribe una descripcion para poder realizar la publicacion");
            selectProducto.getSelectionModel().clearSelection();

        }else if (!textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto para publicar,\n " +
                    "Si no lo tienes ningun producto, agregalo! (implementar boton para agregar producto desde ahi)");
            textAreaPublicar.clear();

        }else if (textAreaPublicar.getText().isEmpty() && selectProducto.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto y escribir una descripcion!");
        }

    }
    public void actualizarSelectProductoInicio() {
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
        gridInicio.getChildren().clear();
        int columna = 0;
        int fila = 0;
        for (VendedorDto v: vendedores) {
            for(int i = 0; i < muroController.getListaPublicaciones(v).size() ; i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/publicacion.fxml"));
                AnchorPane pane = loader.load();

                PublicacionViewController controller = loader.getController();
                controller.setVendedor(v);
                controller.setInteractVendedor(this.vendedor);
                controller.setData(muroController.getListaPublicaciones(v).get(i));


                gridInicio.add(pane, columna, fila);
                fila ++;
            }
        }
    }
    /**
     * ////////////////////////////////////////////////////////// SECCION PANEL PERFIL ////////////////////////////////////////////////////////////
     */
    @FXML
    void clickPerfil(ActionEvent event) {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(false);
        panePerfil.setVisible(true);
        paneChat.setVisible(false);
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
     * /////////////////////////////////////////////////////////// SECCION PANEL CHATS //////////////////////////////////////////////////////////////////////
     */
    @FXML
    private Label labelNombreChat;

    @FXML
    private TextField txtEscribir;

    @FXML
    private GridPane gridContactoChat;

    @FXML
    private GridPane gridMensajes;

    @FXML
    void clickChats(ActionEvent event) throws IOException {
        paneContactos.setVisible(false);
        paneEstadistica.setVisible(false);
        paneInicio.setVisible(false);
        panePerfil.setVisible(false);
        paneChat.setVisible(true);
        mostrarContactosChat();
    }
    public void mostrarContactosChat() throws IOException {
        int columna = 0;
        int fila = 0;
        for (int i = 0; i<usuarioController.getListaContactos(vendedor).size(); i++) {
            VendedorDto vendedor1 = usuarioController.getListaContactos(vendedor).get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/casillaContacto.fxml"));
            Button boton = loader.load();

            boton.setOnAction(event -> {
                try {
                    mostrarChat(vendedor1, vendedor);
                    labelNombreChat.setText(vendedor1.getNombre());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            CasillaContactoViewController controller = loader.getController();
            controller.setData(usuarioController.getListaContactos(vendedor).get(i));

            gridContactoChat.add(boton, columna, fila);

            fila ++;
        }
    }
    public void mostrarChat(VendedorDto contacto, VendedorDto vendedor) throws IOException {
        int columnas = 0;
        int filas = 0;
        gridMensajes.getChildren().clear();
        ChatDto chat = mensajeController.getChat(vendedor, contacto);
        for (int i = 0; i<mensajeController.getListaMensaje(chat.getId()).size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/mensaje-view.fxml"));
            AnchorPane pane = loader.load();

            MensajeViewController controller = loader.getController();
            controller.setData(mensajeController.getListaMensaje(chat.getId()).get(i));

            gridMensajes.add(pane, columnas, filas);
            filas ++;
        }
    }
    @FXML
    void clickEnviarMensaje(ActionEvent event) {

    }
    @FXML
    void clickVaciasChat(ActionEvent event) {

    }

    /**
     * //////////////////////////////////////////////////////// SECCION GETTERS Y SETTERS /////////////////////////////////////////////////////////////////////////
     */

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public void setModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }
}

