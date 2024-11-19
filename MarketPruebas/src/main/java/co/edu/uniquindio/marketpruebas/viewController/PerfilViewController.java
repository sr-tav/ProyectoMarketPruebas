package co.edu.uniquindio.marketpruebas.viewController;

import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilViewController {
    UsuarioController usuarioController;
    VendedorDto vendedor;

    @FXML
    private Button btnPublicar;

    @FXML
    private TableColumn<VendedorDto, String> ctvNombreUsuarios;

    @FXML
    private GridPane gridPerfil;

    @FXML
    private ComboBox<ProductoDto> selectProducto;

    @FXML
    private TextArea textAreaPublicar;

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
    void clickBuscarPerfil(ActionEvent event) {
        if(!textFBuscarPersonas.getText().isEmpty()){
            tvUsuarios.getItems().clear();
            tvUsuarios.getItems().addAll(usuarioController.buscarPerfiles(textFBuscarPersonas.getText()));
        }else {
            //mostrar mensaje de que
        }
    }


    @FXML
    void clickPublicar(ActionEvent event) {

    }


    public void initialize(VendedorDto dto) {
        usuarioController = new UsuarioController();
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

    public void inicializarPerfil(){
        selectProducto.setItems(FXCollections.observableArrayList(usuarioController.getListaProductosDisponibles(vendedor)));
        inicializarInforPersonal();

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
}

