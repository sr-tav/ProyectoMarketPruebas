package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

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
    private TextField txtDireccion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtConfirmarPass;
    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtPassVisible;
    @FXML
    private TextField txtConfirmarVisible;
    UsuarioController usuarioController;

    @FXML
    void clickCrearCuenta(ActionEvent event) throws IOException {
        if(checkTerminos.isSelected() && !txtApellido.getText().isEmpty() && !txtCedula.getText().isEmpty()
                && !txtDireccion.getText().isEmpty() &&!txtId.getText().isEmpty() && !txtNombre.getText().isEmpty()
                && !txtUsuario.getText().isEmpty()) {
            if (!txtPass.getText().isEmpty() || !txtPassVisible.getText().isEmpty() && !txtConfirmarPass.getText().isEmpty() || !txtConfirmarVisible.getText().isEmpty()) {
                if (txtPass.getText().equals(txtConfirmarPass.getText()) || txtPassVisible.getText().equals(txtConfirmarVisible.getText())) {
                    VendedorDto dto = new VendedorDto();
                    dto.setApellido(txtApellido.getText());
                    dto.setCedula(txtCedula.getText());
                    dto.setNombre(txtNombre.getText());
                    dto.setPassword(txtPass.getText());
                    dto.setUsuario(txtUsuario.getText());
                    dto.setDireccion(txtDireccion.getText());
                    dto.setIdVendedor(txtId.getText());
                    if (usuarioController.crearUsuario(dto)){
                        JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente!");
                        pasarAlDashBoard((VendedorDto) usuarioController.getUsuario(dto));
                    }else {
                        JOptionPane.showMessageDialog(null, "Error al crear usuario");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Tu contraseña no coincide");
                }
            }
        }else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los espacios correctamente");
        }
    }

    public void pasarAlDashBoard(VendedorDto dto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/vendedor-dashboard.fxml"));
        Scene scene = new Scene(loader.load(),1012,809);
        VendedorDashboardViewController controller = loader.getController();
        controller.inicializarDashboard(dto);
        Stage stage = new Stage();
        stage.setScene(scene);

        Stage stageCerrar = (Stage) btnCrearCuenta.getScene().getWindow();
        stageCerrar.close();

        stage.show();
    }

    @FXML
    void clickPassVisible(ActionEvent event) {
        if (txtPass.isVisible()) {
            txtPassVisible.setText(txtPass.getText());
            txtPass.setVisible(false);
            txtPassVisible.setVisible(true);
        }else {
            txtPass.setText(txtPassVisible.getText());
            txtPassVisible.setVisible(false);
            txtPass.setVisible(true);
        }
    }
    @FXML
    void clickConfirVisible(ActionEvent event) {
        if (txtConfirmarPass.isVisible()) {
            txtConfirmarVisible.setText(txtConfirmarPass.getText());
            txtConfirmarPass.setVisible(false);
            txtConfirmarVisible.setVisible(true);
        }else {
            txtConfirmarPass.setText(txtConfirmarVisible.getText());
            txtConfirmarVisible.setVisible(false);
            txtConfirmarPass.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioController = new UsuarioController();
    }

}
