package co.edu.uniquindio.marketpruebas.viewController;

import co.edu.uniquindio.marketpruebas.controller.MuroController;
import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfilViewController {
    UsuarioController usuarioController;
    MuroController muroController;
    VendedorDto vendedor;
    @FXML
    private TableColumn<VendedorDto, String> ctvNombreUsuarios;

    @FXML
    private TextField textFBuscarPersonas;

    @FXML
    private TableView<VendedorDto> tvUsuarios;

    @FXML
    private Text txtApellido;

    @FXML
    private Text txtId;

    @FXML
    private Text txtDireccion;

    @FXML
    private Text txtNombre;

    @FXML
    private Text txtNombreUsuario;

    @FXML
    private Button btnRegresar;

    @FXML
    private GridPane gridPublicacionesPerfil;

    @FXML
    void clickBuscarPerfil(ActionEvent event) {
        if(!textFBuscarPersonas.getText().isEmpty()){
            tvUsuarios.getItems().clear();
            tvUsuarios.getItems().addAll(usuarioController.buscarPerfiles(textFBuscarPersonas.getText()));
        }else {
            //mostrar mensaje de que
        }
    }


    public void initialize(VendedorDto dto) throws IOException {
        usuarioController = new UsuarioController();
        muroController = new MuroController();
        this.vendedor = dto;
        inicializarPerfil();
        formatearColumnaTabla();
        llenarTabla();
    }
    private void llenarTabla() {
        tvUsuarios.getItems().clear();
        tvUsuarios.setItems(FXCollections.observableArrayList(usuarioController.getListaVendedoresSinAgregar(vendedor)));
    }

    private void formatearColumnaTabla() {
        ctvNombreUsuarios.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNombre()));
    }

    public void inicializarPerfil() throws IOException {
        inicializarInforPersonal();
        mostrarPublicacionesPersonal();
    }

    private void inicializarInforPersonal() {
        txtNombreUsuario.setText(vendedor.getUsuario());
        txtNombre.setText(vendedor.getNombre());
        txtDireccion.setText(vendedor.getDireccion());
        txtDireccion.setText(vendedor.getDireccion());
        txtId.setText(vendedor.getIdVendedor());
    }


    public void clickEnviarSolicitud(ActionEvent actionEvent) {
        VendedorDto vendedor = tvUsuarios.getSelectionModel().getSelectedItem();
        if(vendedor != null){
            //aca va la logica para enviar la solicitud
        }else {
            // mostrar mensaje de que no seleciono a nadie
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


            gridPublicacionesPerfil.add(pane, columna, fila);
            fila ++;
        }
    }

    @FXML
    void clickRegresar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/vendedor-dashboard.fxml"));
        Scene scene = new Scene(loader.load(),1012,809);

        VendedorDashboardViewController controller = loader.getController();
        controller.inicializarDashboard(vendedor);

        Stage stage = new Stage();
        Stage stage1 = (Stage) btnRegresar.getScene().getWindow();
        stage1.close();

        stage.setScene(scene);
        stage.show();
    }
}

