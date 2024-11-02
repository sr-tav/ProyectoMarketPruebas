package co.edu.uniquindio.marketpruebas.viewcontroller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegisterViewController implements Initializable {
    @FXML
    private Button btnCrearCuenta;

    @FXML
    private CheckBox checkTerminos;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtConfirmarPass;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUsuario;

    @FXML
    void clickCrearCuenta(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
